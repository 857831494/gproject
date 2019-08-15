package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.AbstratorDBTable;
import com.gproject.common.utils.common.JSONUtil;

public interface PlayerInfoTableDef {

	public class PlayerInfo{
		public String nickName;
		
		public Date lastLoginTime=new Date();
	}
	
	//物理表
	@Entity(name = "tb_player_info")
	public class PlayerInfoPojo extends AbstratorDBTable{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;

		@Override
		public void setLogicDataStr(String logicData) {
			// TODO Auto-generated method stub
			this.logicData=logicData;
		}

		@Override
		public String getLogicDataStr() {
			// TODO Auto-generated method stub
			return logicData;
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return playerId;
		}
		
		
	}
}
