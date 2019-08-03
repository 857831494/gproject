package com.gproject.gate.event.player;

import java.util.Date;

import com.gproject.gate.event.IEventDef.GEvent;

/**
 * 
 * 
 * @author YW1825
 *
 */
public interface PlayerItemEventDef {

	public class PlayerItemEventParame extends PlayerEventParame {
		public int itemId;
		public int val;
		public PlayerItemEventParame(int itemId, int val,long playerId) {
			super();
			this.itemId = itemId;
			this.val = val;
			this.playerId=playerId;
		}
		
	}

	public interface PlayerAddItemEvent extends GEvent {

	}

	public interface PlayerConsumeItemEvent extends GEvent {

	}
}
