package com.gproject.gate.service.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
	 * 背包id
	 */
	public int bagModelId;
	/**
	 * 是否在客户端看的见
	 */
	public boolean canShow=true;

	public long playerId;
	/**
	 * 玩家最后数值
	 * 卡类型 是玩家还剩余多少秒
	 */
	public long lastVal;
	
	/**
	 * 有效时间===类型  30 天的毫秒数  使用  DateUtil  
	 */
	public long expireTime;
	
	public AddItemOrder(int itemId, int addVal, AddType addType) {
		this.itemId = itemId;
		this.addVal = addVal;
		this.addType = addType;
	}

	
	public HashMap<Integer, Integer> attarMap=new HashMap<>();
	/**
	 * 创建 多个物品 订单  战斗结算 奖励
	 * excel 格式 [ [1,2],[3,3]]
	 * @param items
	 * @param addType
	 * @return
	 */
	public static List<AddItemOrder> getOrders(int[][] items,AddType addType) {
		List<AddItemOrder> list=new ArrayList<AddItemOrder>();
		for (int[] temp : items) {
			AddItemOrder order=new AddItemOrder(temp[0], temp[1], addType);
			list.add(order);
		}
		return list;
	}

	public AddType addType;
}