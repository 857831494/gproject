package com.gproject.gate.pojo;


import java.util.Date;

import com.gproject.common.db.AbstractorLogicRet;

public interface PlayerInfoTableDef {

	public class PlayerInfo extends AbstractorLogicRet{
		public String nickName;
		public long unionId;
		public Date lastLoginTime=new Date();
	}
	
	
}
