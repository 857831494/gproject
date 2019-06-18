package com.gproject.center.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.http.APIParameDef.APIRet;


@Component
public class AuthHandler extends org.springframework.web.servlet.handler.HandlerInterceptorAdapter {

	public final static String NEED_AUTH_PATH="/auth/";
	
	public final static String UID="uid"; 
	
	public final static String TOKEN="token"; 
	
	public final static String RANDOM="random"; 
	
	private boolean checkAuth(HttpServletRequest request, HttpServletResponse response) {
		String uid=request.getParameter(UID);
		if (StringUtils.isBlank(uid)) {
			APIRet apiRet=new APIRet();
			apiRet.code=TipCode.Uid_NULL_VALUE;
		}
		String token=request.getParameter(TOKEN);
		String random=request.getParameter(RANDOM);
		
		return true;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (request.getServletPath().startsWith(NEED_AUTH_PATH)) {
			return this.checkAuth(request, response);
		}
		return true;
	}
}
