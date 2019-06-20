package com.gproject.center.control;


import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole;
import com.gproject.center.service.PlayerInitService;
import com.gproject.common.config.AuthHandler;
import com.gproject.common.dto.proto.TipDTO.GameTip;
import com.gproject.common.net.PushService;

@RestController
public class PlayerInitControl {

	@Resource(name=PushService.WEBSCOKET)
	PushService pushService;
	
	Logger logger=LoggerFactory.getLogger(PlayerInitControl.class);
	
	@Autowired
	PlayerInitService playerInitService;
	
	@RequestMapping("/cai")
	public String[] doTest(@RequestBody byte[] msg) throws Exception {
		logger.info("ccccc-------------");
		GameTip gameTip=GameTip.parseFrom(msg);
		logger.info(gameTip.getCode()+"c0-----------------");
		int[] ret=new int[msg.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i]=msg[i];
		}
		HashMap<String, String> map=new HashMap<>();
		map.put("ccvv", "是没什么事");
		PlayerRole playerRole=new PlayerRole();
		playerRole.playerId=33333;
		return new String[]{"sksks大大方方"};
	}
	
	
	
	@RequestMapping(AuthHandler.NEED_AUTH_PATH+"getRoles")
	public byte[] getRoles(@RequestBody byte[] msg) {
		return null;
	}
	
	
	
}
