package com.gproject.gate.service.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.config.AppinitHandler;
import com.gproject.common.db.LockService;
import com.gproject.common.dto.proto.ItemDTO.ClientItem;
import com.gproject.common.dto.proto.ItemDTO.S2CAddItem;
import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.PushService;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.common.utils.common.GErrorException;
import com.gproject.gate.cache.AttarCache;
import com.gproject.gate.cache.BagCache;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagModel;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemHandlerType;
import com.gproject.gate.service.item.model.AddItemOrder;
import com.gproject.gate.service.mail.MailService;

@Service
public class AddItemService {

	@Autowired
	MailService mailService;

	@Autowired
	ExcelService excelService;

	@Autowired
	PushService pushService;

	@Autowired
	AttarCache attarCache;

	@Autowired
	BagCache bagCache;

	@Autowired
	ApplicationContext applicationContext;
	
	Logger logger = LoggerFactory.getLogger(AddItemService.class);

	@Autowired
	LockService lockService;
	
	/**
	 * 添加物品，百分百成功 背包满了的东西，直接发到邮件
	 * 
	 * @param itemOrder
	 */
	public  void addItem(AddItemOrder itemOrder) {
		Integer lock=lockService.getLockBy(itemOrder.playerId);
		synchronized (lock) {
			// GlobalManager.IOC;//ioc 读取配置 实例化出，物品处理器
			HItemConfig hItemConfig = excelService.getById(HItemConfig.class, itemOrder.itemId);

			AddItemHandler itemHandler = (AddItemHandler)applicationContext
					.getBean(ItemDef.ADD_ITEM_HANDLER + hItemConfig.addHandlerType);
			itemHandler.add(itemOrder);
			// 检查是否加到数值
			List<AddItemOrder> list = new ArrayList<AddItemOrder>();
			list.add(itemOrder);
			synClient(list);
			// 没有全部添加成功，发到邮件
			mailService.itemMail(itemOrder);
		}
		this.doTip(itemOrder);
	}

	/**
	 * 在加之前，测试，是否可以加,邮件模块
	 * 
	 * @param itemOrder
	 * @return
	 */
	public void canAdd(AddItemOrder itemOrder) {
		AttarPojo attarPojo = attarCache.getData(itemOrder.playerId);
		BagPojo bagPojo = bagCache.getData(itemOrder.playerId);
		HItemConfig hItemConfig = excelService.getById(HItemConfig.class, itemOrder.itemId);
		long curVal = 0;
		int maxBag = 200;
		if (hItemConfig.addHandlerType == AddItemHandlerType.bag) {
			if (bagPojo.bagRet.bagMap.size() >= maxBag) {
				// 失败，达到最大数量
				throw new GErrorException(TipCode.Max_bag_num);
			}
			BagModel bagModel = bagPojo.bagRet.getBagModel(itemOrder.itemId);
			if (bagModel != null) {
				curVal = bagModel.num;
			}
			if (curVal + itemOrder.addVal > hItemConfig.getMaxNum()) {
				throw new GErrorException(TipCode.Max_Item_num);
			}
		} else {
			// 属性加不进去
			curVal = attarPojo.attarRet.getVal(itemOrder.itemId);
			if (curVal + itemOrder.addVal > hItemConfig.getMaxNum()) {
				throw new GErrorException(TipCode.Max_Item_num);
			}
		}

	}

	/**
	 * 在加之前，测试，是否可以加,邮件模块
	 * @param itemOrder
	 * @return
	 */
	public void canAdd(List<AddItemOrder> itemOrders) {
		//检查背包是否满了
		//需要占用格子
	    int bagNum=0;
		BagPojo bagPojo=bagCache.getData(itemOrders.get(0).playerId);
		for (AddItemOrder itemOrder : itemOrders) {
			HItemConfig hItemConfig=excelService.getById(AddItemOrder.class, itemOrder.itemId);
			if (hItemConfig.addHandlerType==AddItemHandlerType.bag) {
				bagNum++;
			}
			canAdd(itemOrder);
		}
		
		int maxNum=200;
		if (bagNum+bagPojo.bagRet.bagMap.size()>maxNum) {
			throw new GErrorException(TipCode.Max_bag_num);
		}
		
	}

	/**
	 * 处理提示
	 * 
	 * @param addItemOrder
	 */
	private void doTip(AddItemOrder addItemOrder) {
		HItemConfig hItemConfig = excelService.getById(HItemConfig.class, addItemOrder.itemId);
		if (hItemConfig.addHandlerType == AddItemHandlerType.bag) {
			// 处理背包提示
			// 背包满了 道具数量达到最大值
		} else {
			// 处理金币类型的提示
		}
	}

	/**
	 * 开始检查是否需要通知客户端，数值发生变化
	 * 
	 * @param addItemOrder
	 */
	private void synClient(List<AddItemOrder> itemOrders) {
		S2CAddItem.Builder dtoBuilder = S2CAddItem.newBuilder();
		for (AddItemOrder addItemOrder : itemOrders) {
			ClientItem.Builder baBuilder = ClientItem.newBuilder();
			baBuilder.setBagId(addItemOrder.bagModelId);
			baBuilder.setAddNum(addItemOrder.addVal);
			baBuilder.setItemId(addItemOrder.itemId);
			baBuilder.setCanShow(addItemOrder.canShow);
			baBuilder.setLastNum(addItemOrder.lastVal);
			baBuilder.setBagId(addItemOrder.bagModelId);
			dtoBuilder.addData(baBuilder);
		}
	}

	/**
	 * 百分百成功，邮件满了部分，已经发到邮件 多个物品，可能有3种提示 1. 背包满了 2. 指定物品加不到背包 3. 属性数值，添加不了， 4.====
	 * 2,3的综合体
	 * 
	 * @param itemOrder
	 */
	public synchronized void addItem(List<AddItemOrder> itemOrders) {
		for (AddItemOrder addItemOrder : itemOrders) {
			HItemConfig hItemConfig = excelService.getById(HItemConfig.class, addItemOrder.itemId);

			AddItemHandler itemHandler = (AddItemHandler) applicationContext
					.getBean(ItemDef.ADD_ITEM_HANDLER + hItemConfig.addHandlerType);
			itemHandler.add(addItemOrder);
		}
		// 开始遍历物品订单，看看，哪个没有添加成功
		mailService.itemsMail(itemOrders);
		// 发到邮件
		doItemsTip(itemOrders);
		this.synClient(itemOrders);
	}

	private void doItemsTip(List<AddItemOrder> itemOrders) {
		HashSet<Integer> addItemHandlerHashSet = new HashSet<>();
		for (AddItemOrder addItemOrder : itemOrders) {
			HItemConfig hItemConfig = excelService.getById(HItemConfig.class, addItemOrder.itemId);
			addItemHandlerHashSet.add(hItemConfig.addHandlerType);
		}
		// 只处理背包类型的提示，先不处理属性类型的，属性几乎不可能溢出
		if (addItemHandlerHashSet.contains(AddItemHandlerType.bag)) {

		}
	}
}
