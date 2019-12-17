package com.gproject.gate.service.playerstatus;

import com.gproject.common.dto.proto.StatusDTO.StatusCode;

public interface PlayerStatusDef {

	
	
	public final static String status="status";
	
	public class StatusModel {

		public StatusCode statusCode;
		public String roomId;
		/**
		 * 开房间态，有用
		 */
		public long joinRoomTime;
		
		public long matchingTime;
		
		public StatusModel(StatusCode statusCode, String roomId) {
			super();
			this.statusCode = statusCode;
			this.roomId = roomId;
		}
		/**
		 * 战斗开始时间，战斗状态下，有用
		 */
		public long fightTime;
	}
	
}
