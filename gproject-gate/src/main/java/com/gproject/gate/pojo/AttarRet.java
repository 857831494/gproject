package com.gproject.gate.pojo;

import java.util.HashMap;


import com.gproject.common.db.AbstractorLogicRet;

public class AttarRet extends AbstractorLogicRet{
	public HashMap<Integer, Long> attarMap=new HashMap<>();
	
	public long getVal(int itemId) {
		if (!attarMap.containsKey(itemId)) {
			return 0;
		}
		return attarMap.get(itemId);
	}
}