package com.gproject.gate.service.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.config.AppinitHandler;
import com.gproject.common.dto.proto.ItemDTO.ClientItem;
import com.gproject.common.dto.proto.ItemDTO.S2CAddItem;
import com.gproject.common.net.PushService;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemHandlerType;
import com.gproject.gate.service.mail.MailService;

@Service
public class AddItemService {

	@Autowired
	MailService mailService;
	
	@Autowired
	ExcelService excelService;
	
	@Autowired
	PushService pushService;
	
	Logger logger=LoggerFactory.getLogger(AddItemService.class);
	
	
	
	/**
	 * 添加物品，百分百成功
	 * 背包满了的东西，直接发到邮件
	 * @param itemOrder
	 */
	public synchronized void addItem(AddItemOrder itemOrder) {
		//GlobalManager.IOC;//ioc 读取配置 实例化出，物品处理器
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, itemOrder.itemId);
		
		AddItemHandler itemHandler=(AddItemHandler) 
				AppinitHandler.IOC.getBean(ItemDef.ITEM_HANDLER+hItemConfig.addHandlerType);
		itemHandler.add(itemOrder);
		//检查是否加到数值
		List<AddItemOrder> list=new ArrayList<AddItemOrder>();
		list.add(itemOrder);
		synClient(list);
		//没有全部添加成功，发到邮件
		mailService.itemMail(itemOrder);
		this.doTip(itemOrder);
	}
	
	/**
	 * 处理提示
	 * @param addItemOrder
	 */
	private void doTip(AddItemOrder addItemOrder) {
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
		if (hItemConfig.addHandlerType==AddItemHandlerType.bag) {
			//处理背包提示
			//背包满了  道具数量达到最大值
		}else {
			//处理金币类型的提示
		}
	}
	
	/**
	 * 开始检查是否需要通知客户端，数值发生变化
	 * @param addItemOrder
	 */
	private void synClient(List<AddItemOrder> itemOrders) {
		S2CAddItem.Builder dtoBuilder=S2CAddItem.newBuilder();
		for (AddItemOrder addItemOrder : itemOrders) {
			ClientItem.Builder baBuilder=ClientItem.newBuilder();
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
	 * 百分百成功，邮件满了部分，已经发到邮件
	 * 多个物品，可能有3种提示
	 *1. 背包满了
	 *2. 指定物品加不到背包
	 *3. 属性数值，添加不了，
	 *4.==== 2,3的综合体
	 * @param itemOrder
	 */
	public synchronized void addItem(List<AddItemOrder> itemOrders) {
		for (AddItemOrder addItemOrder : itemOrders) {
			HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
			
			AddItemHandler itemHandler=(AddItemHandler) 
					AppinitHandler.IOC.getBean(ItemDef.ITEM_HANDLER+hItemConfig.addHandlerType);
			itemHandler.add(addItemOrder);
		}
		//开始遍历物品订单，看看，哪个没有添加成功
		mailService.itemsMail(itemOrders);
		//发到邮件
		doItemsTip(itemOrders);
		this.synClient(itemOrders);
	}
	
	
	
	private void doItemsTip(List<AddItemOrder> itemOrders) {
		 HashSet<Integer> addItemHandlerHashSet=new HashSet<>();
		 for (AddItemOrder addItemOrder : itemOrders) {
			HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
			addItemHandlerHashSet.add(hItemConfig.addHandlerType);
		}
		//只处理背包类型的提示，先不处理属性类型的，属性几乎不可能溢出
		if (addItemHandlerHashSet.contains(AddItemHandlerType.bag)) {
			
		}
	}
}
