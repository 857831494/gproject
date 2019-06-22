package com.gproject.gate.service.item;

import com.gproject.gate.service.item.model.AddItemOrder;

public interface ItemDef {

	public final static String ITEM_HANDLER="com.gproject.gate.service.item"; 
	
	
	/**
	 *  加  物品处理器
	 * @author YW1825
	 *
	 */
	public interface AddItemHandlerType{
		int attar=1;//属性类型，金币，钻石，
		int bag=2;//道具，需要判断背包是否满了
		int card=3;//月卡类型
	}

	
	
	/**
	 * 消耗物品
	 * @author YW1825
	 *
	 */
	public interface ConsumeHandlerType{
		int attar=1;//金币类型
		int bag_common=2;//背包不带属性的道具
		int bag_hasAttar=3;//带属性===需要扩展   背包
	}
	
	
	/**
	 *    物品 处理器 背包满了不需要抛出错误码  满了开始走邮件
	 * @author YW1825
	 *
	 */
	public interface AddItemHandler{
		public void add(AddItemOrder addItemOrder) ;
	}
	
}
