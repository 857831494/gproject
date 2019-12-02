package com.gproject.common.net.http;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.http.APIParameDef.APIRet;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HServerConfig;


@Component
public class APIService {

	@Autowired
	ExcelService excelManager;
	
	Logger logger=LoggerFactory.getLogger(APIService.class);
	
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
	
	/**
	 * 从网关获取数据
	 * @param serverId
	 * @param path
	 * @param req 必须是试题对象，不可以是 byte[]
	 * @return
	 */
	public APIRet getGateRet(int serverId,String path,Object req) {
		HServerConfig centerConfig=excelManager.getById(HServerConfig.class, serverId);
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
	
	/**
	 * 直接广播数据到指定网关，广播专用
	 * @param serverId
	 * @param path
	 * @param req
	 */
	public void postBytes(int serverId,String path,byte[] req) {
		try {
			HServerConfig centerConfig=excelManager.getById(HServerConfig.class, serverId);
			String ADD_URL="http://"+centerConfig.host+":"+centerConfig.httpPort+path;
		    //创建连接
		    URL url = new URL(ADD_URL);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setDoOutput(true);
		    connection.setDoInput(true);
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    //application/x-javascript 
		    //text/xml->xml数据 
		    //application/x-javascript->json对象 
		    //application/x-www-form-urlencoded->表单数据 
		    //application/json;charset=utf-8 -> json数据
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    connection.setRequestProperty("accept", "*/*");
		    connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		    connection.connect();

		    //POST请求
		    DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
		    out.write(req);
		    out.flush();
		    out.close();

		    // 断开连接
		    connection.disconnect();
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	}
}
