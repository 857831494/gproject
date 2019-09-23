package com.gproject.common.net.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.proto.TipDTO.TipCode;

public interface APIParameDef {

	
	public class NotNeedConvert{
		
	}
	
	public class APIRet {

		public int code;
		
		public String remark;
		
		public Object data;
		
		public <T> T getObj(Class classType) {
			try {
				if (code>=TipCode.UnKown_VALUE) {
					return null;
				}
				if (data==null){
					return null;
				}
				ObjectMapper objectMapper=new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				String json=objectMapper.writeValueAsString(data);
				return (T) objectMapper.readValue(json, classType);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
			return null;
		}
	}
	
	public final static String UID="UID";
	
	public final static String TOKEN="token";
	
	
}
