package com.gproject.gate.pojo;

import java.util.Date;
import java.util.HashMap;

import com.gproject.common.db.AbstractorLogicRet;

public interface CDTableDef {


	public class CDNumRet extends AbstractorLogicRet{
		public HashMap<Integer, Integer> map=new HashMap<>();
		public Date lastflushTime;
	}

	
}
