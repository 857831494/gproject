package com.gproject.gate.event.player;

import com.gproject.gate.event.IEventDef.GEvent;

/**
 * 
 * 玩家每周执行一次事件，可能会隔周执行
 * @author YW1825
 *
 */
public interface PlayerWeekEventDef {

	public class PlayerWeekEventParame extends PlayerEventParame{
		
	}
	
	public interface PlayerWeekEvent extends GEvent{
		
	}
}
