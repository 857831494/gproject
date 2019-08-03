package com.gproject.gate.service;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.gate.cache.PlayerInfoCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;
import com.gproject.gate.service.event.PlayerDailyEventService;


@Service
public class EnterServie {

	@Autowired
	PlayerInfoCache playerInfoDAO;
	
	@Autowired
	PlayerDailyEventService playerDailyEventService;
	
	
	@Autowired
	EventService eventService;
	
	Logger logger=LoggerFactory.getLogger(EnterServie.class);
	
	public void doEnterEvent(long playerId) {
		PlayerInfoPojo playerInfoPojo=playerInfoDAO.getData(playerId);
		PlayerEnterEventParame playerEnterEventParame=new PlayerEnterEventParame(playerId);
		playerEnterEventParame.lastLoginTime=playerInfoPojo.playerInfo.lastLoginTime;
		
		//发布玩家进入事件
		eventService.publish(PlayerEnterEvent.class, playerEnterEventParame);
		
		//发布，玩家每日执行事件
		playerDailyEventService.doLogic(playerEnterEventParame);
		playerInfoPojo.playerInfo.lastLoginTime=new Date();
		playerInfoDAO.update(playerInfoPojo);
		logger.info(playerId+"===玩家进来==============");
	}
	
	
}
