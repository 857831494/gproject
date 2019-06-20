package com.gproject.gate.service.item;

import java.util.ArrayList;
import java.util.List;

public interface ItemDef {

	public final static String ITEM_HANDLER="com.gproject.gate.service.item"; 
	/**
	 * 
	 * 添加物品---订单
	 * @author YW1825
	 *
	 */
	public class AddItemOrder{
		
		public int itemId;
		/**
		 * 添加数字
		 */
		public long addVal;
		
		
		/**
		 * 添加成功的数量，超出数量，发到邮件
		 */
		public long successVal;
		
	
		/**
		 * 是否在客户端看的见
		 */
		public boolean canShow=true;

		public long playerId;
		/**
		 * 玩家最后数值
		 */
		public long lastVal;
		
		
		
		public AddItemOrder(int itemId, int addVal, AddType addType) {
			super();
			this.itemId = itemId;
			this.addVal = addVal;
			this.addType = addType;
		}

		/**
		 * 创建 多个物品 订单  战斗结算 奖励
		 * excel 格式 [ [1,2],[3,3]]
		 * @param items
		 * @param addType
		 * @return
		 */
		public static List<AddItemOrder> getOrders(int[][] items,AddType addType) {
			List<AddItemOrder> list=new ArrayList<ItemDef.AddItemOrder>();
			for (int[] temp : items) {
				AddItemOrder order=new AddItemOrder(temp[0], temp[1], addType);
				list.add(order);
			}
			return list;
		}

		public AddType addType;
	}
	
	/**
	 *    物品处理器
	 * @author YW1825
	 *
	 */
	public interface ItemHandlerType{
		int attar=1;//属性类型，金币，钻石，
		int bag=2;//道具，需要判断背包是否满了
		int card=3;//月卡类型
	}

	
	/**
	 * 客户端在哪里显示该数值
	 * @author YW1825
	 *
	 */
	public interface ShowClientWhereType{
		int bag=1;
		int attar=2;
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
