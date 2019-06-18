package com.gproject.common.dto.json;

import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.common.staticdata.StaticDataDef;
import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;


public class HServerConfig {

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

}
