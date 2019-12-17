package com.gproject.gate.service.fight;

import java.util.ArrayList;
import java.util.List;

import com.gproject.common.dto.proto.RoomDTO.GPlayerInfo;

public interface FightDef{
	
	
	
	public class FightParame {

		public List<String> roomIds;
		
		
		
		
	}
	
	
	public class GBattle{
		public String battleId;
		
		public List<String> playerInfos=new ArrayList<>(8);
		
		public long createTime=System.currentTimeMillis();
		
		public List<Long> turnTime=new ArrayList<Long>();

		public GBattle(String battleId) {
			
			this.battleId = battleId;
		}
		
		
	}
}


