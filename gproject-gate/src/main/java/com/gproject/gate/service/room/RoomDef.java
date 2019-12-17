package com.gproject.gate.service.room;

import java.util.ArrayList;
import java.util.List;

import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.GPlayerInfo;
import com.gproject.common.dto.proto.StatusDTO.StatusCode;

public interface RoomDef{
	
    /**
     * 存储房间信息
     */
    public  String matchRoomInfo="MatchRoomInfo-";
	
	public class GRoomInfo{
		public String teamIds;
		public String maxSize;
		public long createTime=System.currentTimeMillis();
		public GRoomInfo(String teamIds) {
			super();
			this.teamIds = teamIds;
			
		}
		
		public StatusCode statusCode;
		
		/**
		 * 匹配区间
		 */
		public int areaId;
		
		public List<String> playList=new ArrayList<String>();
	}
	
	
	
	
	
	
	public String RoomOpsList="RoomOpsList";
	public String RoomOpsLock="RoomOpsLock";
	
	public String MatchOpsList="MatchOpsList";
	public String CancelOpsList="CancelOpsList";
	public String MatchOpsLock="MatchOpsLock";
	
	public interface RoomOps{
		public void doReq(C2SRoomOps ops);
	}
	
	
}


