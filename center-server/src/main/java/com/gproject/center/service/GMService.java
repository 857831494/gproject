package com.gproject.center.service;

import org.springframework.stereotype.Component;

import com.gproject.dto.json.GMLoginDTO;
import com.gproject.dto.json.GMLoginDTO.GMLoginReq;
import com.gproject.dto.json.GMLoginDTO.GMLoginResp;

@Component
public class GMService {

	public GMLoginResp getToken(GMLoginReq dto) {
		GMLoginResp gmLoginResp=new GMLoginResp();
		gmLoginResp.time=System.currentTimeMillis()+"";
		gmLoginResp.sessionId=System.currentTimeMillis()+"";
		
		return gmLoginResp;
	}
}
