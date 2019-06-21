package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.DBEvent;
import com.gproject.common.utils.common.JSONUtil;

public interface PlayerInfoTableDef {

	public class PlayerInfo{
		public String nickName;
		
		public Date lastLoginTime;
	}
	
	//物理表
	@Entity(name = "tb_player_info")
	public class PlayerInfoPojo implements DBEvent{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;
		
		@Transient
		public PlayerInfo playerInfo;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.playerInfo=JSONUtil.getObjectType(logicData, PlayerInfo.class);
			if (playerInfo==null) {
				playerInfo=new PlayerInfo();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (playerInfo==null) {
				playerInfo=new PlayerInfo();
			}
			this.logicData=JSONUtil.toJsonString(playerInfo);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}
	}
}
