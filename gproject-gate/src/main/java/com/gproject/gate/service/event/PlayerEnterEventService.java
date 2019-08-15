package com.gproject.gate.service.event;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.gate.cache.PlayerInfoCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfo;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;
import com.gproject.gate.service.LobbyDataService;


@Service
public class PlayerEnterEventService {

	@Autowired
	PlayerInfoCache playerInfoDAO;
	
	
	
	@Autowired
	PlayerEventService eventService;
	
	Logger logger=LoggerFactory.getLogger(PlayerEnterEventService.class);
	
	
	
	public void doEnterEvent(long playerId) {
		PlayerInfoPojo playerInfoPojo=playerInfoDAO.getPojo(playerId);
		PlayerEnterEventParame playerEnterEventParame=new PlayerEnterEventParame(playerId);
		PlayerInfo playerInfo=playerInfoPojo.getLogicObj();
		playerEnterEventParame.lastLoginTime=playerInfo.lastLoginTime;
		
		//发布玩家进入事件
		eventService.asyn_publish(PlayerEnterEvent.class, playerEnterEventParame);
		
		playerInfo.lastLoginTime=new Date();
		playerInfoDAO.update(playerInfoPojo);
		logger.info(playerId+"===玩家进来==============");
	}
	
	
}
