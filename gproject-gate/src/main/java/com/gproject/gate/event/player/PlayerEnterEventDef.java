package com.gproject.gate.event.player;

import java.util.Date;

import com.gproject.gate.event.IEventDef.GEvent;

/**
 * 
 * 玩家进入游戏,玩家掉线，一样是跟一打开游戏一样的处理逻辑,
 * 进入网关就会执行，一天可能被执行多次,异步执行
 * @author YW1825
 *
 */
public interface PlayerEnterEventDef {

	public class PlayerEnterEventParame extends PlayerEventParame{

		public PlayerEnterEventParame(long playerId) {
			this.playerId=playerId;
			// TODO Auto-generated constructor stub
		}
		
		public Date lastLoginTime;
		
	}
	
	public interface PlayerEnterEvent extends GEvent{
		
	}
}
