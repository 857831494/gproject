package com.gproject.gate.service.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEvent;
import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEventParame;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.service.EventService;

@Service
public class PlayerDailyEventService  {

	
	@Autowired
	EventService eventService;
	
	public void doLogic(PlayerEnterEventParame parame) {
		// TODO Auto-generated method stub
		//处理玩家每天登陆逻辑
		PlayerDailyEventParame playerDailyEventParame=new PlayerDailyEventParame(parame.playerId, 
				parame.lastLoginTime);
		eventService.asyn_publish(PlayerDailyEvent.class, playerDailyEventParame);
	}

}
