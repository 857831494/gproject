package com.gproject.common.staticdata.excelmodel;

import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;


public class HServerConfig implements ConfigExcel{

	public final static int CENTER_SERVER=2;
	
	public final static int BATTLE_SERVER=3;
	
	
	/**
	 * 逻辑id
	 */
	public int logicId;
	
	/**
	 * 物理id
	 */
	public int realId;
	
	public int tcpPort;
	
	public int httpPort;
	
	public String host;
	public String name;
	
	public int type;
	
	public StatusCode statusCode;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return logicId;
	}

}
