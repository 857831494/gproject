package com.gproject.gate.service.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.config.AppinitHandler;
import com.gproject.common.dto.proto.ItemDTO.ClientItem;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemOrder;
import com.gproject.gate.service.mail.MailService;

@Service
public class AddItemService {

	@Autowired
	MailService mailService;
	
	@Autowired
	ExcelService excelService;
	
	Logger logger=LoggerFactory.getLogger(AddItemService.class);
	/**
	 * 添加物品，百分百成功
	 * 背包满了的东西，直接发到邮件
	 * @param itemOrder
	 */
	public void addItem(AddItemOrder itemOrder) {
		//GlobalManager.IOC;//ioc 读取配置 实例化出，物品处理器
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, itemOrder.itemId);
		
		AddItemHandler itemHandler=(AddItemHandler) 
				AppinitHandler.IOC.getBean(ItemDef.ITEM_HANDLER+hItemConfig.addHandlerType);
		itemHandler.add(itemOrder);
		//检查是否加到数值
		synClient(itemOrder);
		//没有全部添加成功，发到邮件
		if (itemOrder.successVal!=itemOrder.addVal) {
			mailService.itemMail(itemOrder);
			this.doTip(itemOrder);
		}
	}
	
	/**
	 * 处理提示
	 * @param addItemOrder
	 */
	private void doTip(AddItemOrder addItemOrder) {
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
		switch (hItemConfig.showWhere) {
		case ItemDef.ShowClientWhereType.bag:
		{
			//背包类型，提示，玩家背包满了，
		}
			break;

		case ItemDef.ShowClientWhereType.attar:
		{
			//数值类型，数值已经超出界限，不提示
		}
			break;
		}
	}
	
	/**
	 * 开始检查是否需要通知客户端，数值发生变化
	 * @param addItemOrder
	 */
	private void synClient(AddItemOrder addItemOrder) {
		if (0>=addItemOrder.successVal) {
			return;
		}
		ClientItem.Builder clBuilder=ClientItem.newBuilder();
		clBuilder.setItemId(addItemOrder.itemId);
		clBuilder.setAddNum(addItemOrder.successVal);
		clBuilder.setCanShow(addItemOrder.canShow);
		clBuilder.setLastNum(addItemOrder.lastVal);
	}
	
	
	/**
	 * 添加物品，如果物品是进入背包，需要抛出错误码---背包满了
	 * 调用方可以根据自己的业务处理,
	 * 背包满了的东西，直接发到邮件
	 * @param itemOrder
	 */
	public void addItem(List<AddItemOrder> itemOrders) {
		for (AddItemOrder addItemOrder : itemOrders) {
			HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
			
			AddItemHandler itemHandler=(AddItemHandler) 
					AppinitHandler.IOC.getBean(ItemDef.ITEM_HANDLER+hItemConfig.addHandlerType);
			itemHandler.add(addItemOrder);
		}
		//开始遍历物品订单，看看，哪个没有添加成功
		//发到邮件
	}
}
