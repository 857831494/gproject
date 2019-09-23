package com.gproject.common.dto.json;

import java.util.ArrayList;
import java.util.List;

import com.gproject.common.net.http.APIParameDef.NotNeedConvert;
import com.gproject.common.staticdata.excelmodel.HServerConfig;

public class ServersResp extends NotNeedConvert{

	public List<HServerConfig> Rows=new ArrayList<HServerConfig>();
}
