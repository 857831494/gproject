package com.gproject.gate.service.card;

public interface CardTypeDef {

	String PACK_NAME="com.gproject.gate.service.card";
	
	public interface CardDailyTask{
		/**
		 * @param playerId
		 * @param execTime 执行逻辑时间，不一定是当前时间
		 */
		public void doLogic(long playerId,long execTime);
	}
	
	int MONTH_CARD_ID=1;//一定跟物品表id相同
}
