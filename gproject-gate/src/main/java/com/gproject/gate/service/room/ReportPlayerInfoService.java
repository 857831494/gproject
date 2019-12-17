package com.gproject.gate.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RoomDTO.GPlayerInfo;
import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.gate.service.playerstatus.PlayerStatusDef.StatusModel;
import com.gproject.gate.service.playerstatus.PlayerStatusService;

@Service
public class ReportPlayerInfoService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
	PlayerStatusService playerStatusService;
	
	public String create(long playerId) {
		return null;
	}
	
	public GPlayerInfo.Builder createBuilder(long playerId) {
		return null;
	}
	
	/**
	 * 存储玩家状态
	 * 
	 * @param playerId
	 * @param roomId
	 */
	public void savePlayerIdJoinStatus(long playerId, String roomId) {
		StatusModel statusModel=playerStatusService.getPlayerStatus(playerId);
		if (statusModel==null) {
			statusModel=new StatusModel(StatusCode.joinRoom,roomId);
		}
		statusModel.joinRoomTime=System.currentTimeMillis();
		playerStatusService.update(statusModel, playerId);
	}
}
