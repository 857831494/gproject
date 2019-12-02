package com.gproject.gate.pojo;

import java.util.HashMap;
import java.util.HashSet;

import com.gproject.common.db.AbstractorLogicRet;

public interface UnionTableDef {

	public interface UnionRoleType{
		int master=1;
	}
	
	
	public class UnionModel{
		public long playerId;
		public int roleType;
	}
	
	public class UnionRet extends AbstractorLogicRet{
		public HashSet<Long> applySet=new HashSet<>();
		
		public HashMap<Long, UnionModel> teams=new HashMap<>();
	}
	
	
}
