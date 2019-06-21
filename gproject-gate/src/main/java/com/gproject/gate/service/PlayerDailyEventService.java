package com.gproject.gate.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.config.AppinitHandler;
import com.gproject.common.config.AuthHandler;
import com.gproject.gate.cache.PlayerInfoCache;
import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEvent;
import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEventParame;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;

@Service
public class PlayerDailyEventService implements PlayerEnterEvent {

	@Autowired
	PlayerInfoCache playerInfoDAO;
	
	@Override
	public void doPlayerEnterEvent(PlayerEnterEventParame parame) {
		// TODO Auto-generated method stub
		//处理玩家每天登陆逻辑
		PlayerDailyEventParame playerDailyEventParame=new PlayerDailyEventParame(parame.playerId, 
				parame.lastLoginTime);
		Collection<PlayerDailyEvent> playerDailyEvents=
				AppinitHandler.IOC.getBeansOfType(PlayerDailyEvent.class).values();
		playerDailyEvents.forEach((PlayerDailyEvent)->{
			PlayerDailyEvent.doPlayerDailyEvent(playerDailyEventParame);
		});
	}

}
