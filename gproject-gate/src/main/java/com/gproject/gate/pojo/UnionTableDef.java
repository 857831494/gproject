package com.gproject.gate.pojo;

import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.gproject.common.db.AbstratorDBTable;

public interface UnionTableDef {

	public interface UnionRoleType{
		int master=1;
	}
	
	
	public class UnionModel{
		public long playerId;
		public int roleType;
	}
	
	public class UnionRet{
		public HashSet<Long> applySet=new HashSet<>();
		
		public HashMap<Long, UnionModel> teams=new HashMap<>();
	}
	
	//物理表
	@Entity(name = "tb_union")
	public class UnionPojo extends AbstratorDBTable{
		@Id
		public long unionId;
		
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
			unionId=(long) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return unionId;
		}
		
		
	}
}
