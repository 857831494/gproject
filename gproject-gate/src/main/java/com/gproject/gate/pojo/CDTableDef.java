package com.gproject.gate.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.gproject.common.db.AbstratorDBTable;

public interface CDTableDef {


	public class CDNumRet {
		public HashMap<Integer, Integer> map=new HashMap<>();
	}

	// 物理表
	@Entity(name = "tb_cd_num")
	public class CDNumPojo extends AbstratorDBTable {
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
