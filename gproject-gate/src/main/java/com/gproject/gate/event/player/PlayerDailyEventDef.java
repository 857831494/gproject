package com.gproject.gate.event.player;

import java.util.Date;

import com.gproject.gate.event.IEventDef.GEvent;

/**
 * 
 * 玩家每天执行一次事件，可能会隔天执行
 * @author YW1825
 *
 */
public interface PlayerDailyEventDef {

	public class PlayerDailyEventParame extends PlayerEventParame{
		public Date lastLoginTime;
		public PlayerDailyEventParame(long playerId,Date lastLoginTime){
			this.lastLoginTime=lastLoginTime;
			this.playerId=playerId;
		}
	}
	
	public interface PlayerDailyEvent extends GEvent{
		
	}
}
