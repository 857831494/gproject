package com.gproject.center.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.gproject.common.db.AbstractorLogicRet;
import com.gproject.common.db.AbstratorDBTable;

public interface PlayerRole_Table_DEF {

	public class PlayerRole {
		public long playerId;
		public String roleName;
		public int serverId;
	}

	public class PlayerRoleRet extends AbstractorLogicRet{
		public HashMap<Integer, PlayerRole> set = new HashMap<>();
	}

	// 数据库表,只有2个字段，可以理解是单纯的文本
	@Entity(name = "tb_PlayerRole")
	public class PlayerRole_Table extends AbstratorDBTable {

		@Id
		@Column(length=80)
		public String tID;
		
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
			this.tID=(String) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return tID;
		}

	}

}
