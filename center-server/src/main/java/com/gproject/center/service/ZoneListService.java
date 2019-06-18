package com.gproject.center.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.IDef.IAPPInit;
import com.gproject.common.IDef.InitParame;
import com.gproject.common.dto.json.HServerConfig;

@Component
public class ZoneListService implements IAPPInit{

	static String SERVER_LIST_NAME="Servers.json";
	
	static Logger logger=LoggerFactory.getLogger(FileService.class);
	
	static List<HServerConfig> ZONE_LIST=null;
	
	private String readToString(String fileName) {  
        String encoding = "UTF-8";  
        try {  
            InputStream  in =new ClassPathResource(fileName).getInputStream();
            int filelength = in.available();
            byte[] filecontent = new byte[filelength];
            in.read(filecontent);  
            in.close();  
            return new String(filecontent, encoding);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
	
	@Override
	public void init(InitParame initParame)  {
		// TODO Auto-generated method stub
		String env=initParame.applicationContext.getEnvironment()
				.getProperty("ZoneList.FileName");
		if (!StringUtils.isBlank(env)) {
			SERVER_LIST_NAME=env;
		}
		logger.info("获取到区服列表名称"+SERVER_LIST_NAME);
		reloadZone();
	}
	
	public void reloadZone() {
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			String txt=readToString(SERVER_LIST_NAME);
			ZONE_LIST= objectMapper.readValue(txt,
					new TypeReference<List<HServerConfig>>() {
			});
			logger.info(objectMapper.writeValueAsString(ZONE_LIST));
			logger.info("加载区服列表成功==============");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
	}
	
	/**
	 * 获取区服列表，要加上，区服状态
	 */
	public List<HServerConfig> getZoneList() {
		return null;
	}
}
