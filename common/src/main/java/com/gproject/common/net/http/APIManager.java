package com.gproject.common.net.http;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.json.HServerConfig;
import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.http.APIParameDef.APIRet;
import com.gproject.common.staticdata.ExcelManager;


@Component
public class APIManager {

	@Autowired
	ExcelManager excelManager;
	
	Logger logger=LoggerFactory.getLogger(APIManager.class);
	
	private HServerConfig getType(int type) {
		List<HServerConfig> serverConfigs=excelManager.getAll(HServerConfig.class);
		for (HServerConfig hServerConfig : serverConfigs) {
			if (hServerConfig.type==type) {
				return hServerConfig;
			}
		}
		return null;
	}
	
	public APIRet centerApi(String path,Object req){
		HServerConfig centerConfig=getType(HServerConfig.CENTER_SERVER);
		String url="http://"+centerConfig.host+":"+centerConfig.httpPort+path;
		ObjectMapper objectMapper=new ObjectMapper();
		APIRet apiRet=new APIRet();
		try {
			String retString=HttpUtil.doPost(url, objectMapper.writeValueAsString(req));
			apiRet=objectMapper.readValue(retString, APIRet.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			apiRet.code=TipCode.Fail_VALUE;
		}
		return apiRet;
	}
}
