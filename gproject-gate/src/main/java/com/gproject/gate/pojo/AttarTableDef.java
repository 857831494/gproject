package com.gproject.gate.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.AbstratorDBTable;
import com.gproject.common.utils.common.JSONUtil;

public interface AttarTableDef {

	public class AttarRet{
		public HashMap<Integer, Long> attarMap=new HashMap<>();
		
		public long getVal(int itemId) {
			if (!attarMap.containsKey(itemId)) {
				return 0;
			}
			return attarMap.get(itemId);
		}
	}
	
	//物理表
	@Entity(name = "tb_attar")
	public class AttarPojo extends AbstratorDBTable{
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
			playerId=(long) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return playerId;
		}
		
		
	}
}
