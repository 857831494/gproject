package com.gproject.gate.event.player;



/**
 * 
 * 玩家只要在有效时间之内，都会执行，会在0点执行
 * 一般玩家要购买月卡之类的，才会进入执行
 * @author YW1825
 *
 */
public interface PlayerCardDailyEventDef {

	public class PlayerCardDailyEventParame extends PlayerEventParame{
		/**
		 * 执行时间
		 */
		public long execTime;
	}
	
	public interface PlayerCardDailyEvent{
		public void doPlayerCardDailyEvent(PlayerCardDailyEventParame parame);
	}
}
