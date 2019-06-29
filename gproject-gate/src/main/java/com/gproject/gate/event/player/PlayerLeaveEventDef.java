package com.gproject.gate.event.player;

import com.gproject.gate.event.IEventDef.GEvent;

/**
 * 玩家离开事件   分主动离开  掉线离开
 * @author YW1825
 *
 */
public interface PlayerLeaveEventDef {

	public class PlayerLeaveEventParame extends PlayerEventParame{
		
	}
	
	public interface PlayerLeaveEvent extends GEvent{
		
	}
}
