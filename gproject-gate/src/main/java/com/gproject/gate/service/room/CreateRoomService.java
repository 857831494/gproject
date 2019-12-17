package com.gproject.gate.service.room;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.googlecode.protobuf.format.JsonFormat;
import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.utils.common.JSONUtil;
import com.gproject.gate.service.room.RoomDef.GRoomInfo;

@Service
public class CreateRoomService {


	@Autowired
	ReportPlayerInfoService reportPlayerService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * 创建队伍
	 * 
	 * @param playerId
	 */
	public void create(long playerId) {
		String roomId = UUID.randomUUID().toString();
		// 创建房间信息
		GRoomInfo matchRoomInfo=new GRoomInfo(roomId);
		stringRedisTemplate.opsForValue().set(RoomDef.matchRoomInfo+roomId, 
				JSON.toJSONString(matchRoomInfo));
		matchRoomInfo.playList.add(reportPlayerService.create(playerId));
		reportPlayerService.savePlayerIdJoinStatus(playerId, roomId);
	}
	
	public GRoomInfo getRoomInfo(String roomId) {
		String json=stringRedisTemplate.opsForValue().get(RoomDef.matchRoomInfo+roomId);
		if (StringUtils.isBlank(json)) {
			return null;
		}
		return JSON.parseObject(json, GRoomInfo.class);
	}
	
	
}
