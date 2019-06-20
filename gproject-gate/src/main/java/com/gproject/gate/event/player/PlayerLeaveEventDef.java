package com.gproject.gate.event.player;



/**
 * 玩家离开事件   分主动离开  掉线离开
 * @author YW1825
 *
 */
public interface PlayerLeaveEventDef {

	public class PlayerLeaveEventParame extends PlayerEventParame{
		
	}
	
	public interface PlayerLeaveEvent{
		public void doPlayerLeaveEvent(PlayerLeaveEventParame parame);
	}
}
