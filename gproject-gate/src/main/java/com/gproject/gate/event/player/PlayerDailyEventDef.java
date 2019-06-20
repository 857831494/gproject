package com.gproject.gate.event.player;



/**
 * 
 * 玩家每天执行一次事件，可能会隔天执行
 * @author YW1825
 *
 */
public interface PlayerDailyEventDef {

	public class PlayerDailyEventParame extends PlayerEventParame{
		
	}
	
	public interface PlayerEnterEvent{
		public void doPlayerDailyEvent(PlayerDailyEventParame parame);
	}
}
