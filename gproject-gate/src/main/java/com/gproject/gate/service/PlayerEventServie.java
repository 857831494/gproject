package com.gproject.gate.service;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.config.AppinitHandler;
import com.gproject.gate.cache.PlayerInfoCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;


@Service
public class PlayerEventServie {

	@Autowired
	PlayerInfoCache playerInfoDAO;
	
	Logger logger=LoggerFactory.getLogger(PlayerEventServie.class);
	
	public void doEnterEvent(long playerId) {
		Collection<PlayerEnterEvent> list=AppinitHandler.IOC.getBeansOfType(
				PlayerEnterEvent.class).values();
		PlayerInfoPojo playerInfoPojo=playerInfoDAO.getData(playerId);
		PlayerEnterEventParame playerEnterEventParame=new PlayerEnterEventParame(playerId);
		playerEnterEventParame.lastLoginTime=playerInfoPojo.playerInfo.lastLoginTime;
		list.forEach((PlayerEnterEvent)->{
			PlayerEnterEvent.doPlayerEnterEvent(playerEnterEventParame);
		});
		playerInfoPojo.playerInfo.lastLoginTime=new Date();
		playerInfoDAO.update(playerInfoPojo);
		logger.info(playerId+"===玩家进来==============");
	}
	
	
}
