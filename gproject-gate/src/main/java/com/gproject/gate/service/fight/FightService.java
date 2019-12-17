package com.gproject.gate.service.fight;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.gate.service.fight.FightDef.FightParame;
import com.gproject.gate.service.fight.FightDef.GBattle;
import com.gproject.gate.service.room.CreateRoomService;
import com.gproject.gate.service.room.RoomDef.GRoomInfo;

@Service
public class FightService {

	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	CreateRoomService createRoomService;
	
	/**
	 * @param fightParame
	 */
	public void startFight(FightParame fightParame) {
		//下发，战斗开始
		GBattle battle=new GBattle(UUID.randomUUID().toString());
		for (String roomId : fightParame.roomIds) {
			GRoomInfo roomInfo=createRoomService.getRoomInfo(roomId);
			for (String string : roomInfo.playList) {
				battle.playerInfos.add(string);
			}
		}
	}
}
