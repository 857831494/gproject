package com.gproject.gate.service.item;


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

	
	
	
	public class ConsumeOrder{
		/**
		 * 添加数字
		 */
		public int addVal;
		public int itemId;
		
		
	
		public ConSumeType Type;
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
