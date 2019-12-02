package com.gproject.center.control;


import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gproject.center.service.PlayerInitService;
import com.gproject.common.config.AuthHandler;
import com.gproject.common.dto.proto.TipDTO.GameTip;

@RestController
public class PlayerInitControl {

	
	Logger logger=LoggerFactory.getLogger(PlayerInitControl.class);
	
	@Autowired
	PlayerInitService playerInitService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	
	
	@RequestMapping("/cai")
	public void doTest() throws Exception {
	
	}
	
	
	
	@RequestMapping(AuthHandler.NEED_AUTH_PATH+"getRoles")
	public byte[] getRoles(@RequestBody byte[] msg) {
		return null;
	}
	
	
	
}
