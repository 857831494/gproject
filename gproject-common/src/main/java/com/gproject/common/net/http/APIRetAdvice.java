package com.gproject.common.net.http;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.http.APIParameDef.APIRet;
import com.gproject.common.net.http.APIParameDef.NotNeedConvert;
import com.gproject.common.utils.common.GErrorException;

@RestControllerAdvice
public class APIRetAdvice implements ResponseBodyAdvice<Object>{

	static Logger logger=LoggerFactory.getLogger(APIRetAdvice.class);
	
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// TODO Auto-generated method stub
		if (body instanceof APIRet) {
			return body;
		}
		if (body instanceof NotNeedConvert) {
			return body;
		}
		if (body instanceof String) {
			return body;
		}
		APIRet apiRet=new APIRet();
		if (body==null) {
			return apiRet;
		}
		if (body instanceof byte[]) {
			byte[] msg=(byte[]) body;
			int[] temp=new int[msg.length];
			for (int i = 0; i < temp.length; i++) {
				temp[i]=msg[i];
			}
			apiRet.data=temp;
		}
		
		apiRet.data=body;
		return apiRet;
	}

	@ExceptionHandler(Exception.class)
    public Object handleException(Exception e,HttpServletRequest req){
		APIRet apiRet=new APIRet();
		if (e instanceof GErrorException) {
			apiRet.code=((GErrorException)e).tipCode.getNumber();
		}else {
			apiRet.code=TipCode.UnKown_VALUE;
		}
		logger.error("uid:"+req.getParameter(APIParameDef.UID)+"发生错误========"+apiRet.code);
		logger.error(ExceptionUtils.getStackTrace(e));
		return apiRet;
    }

}
