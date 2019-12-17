package com.gproject.gate.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.RoomOpCode;
import com.gproject.gate.service.room.RoomDef.RoomOps;

@Service(RoomDef.RoomOpsList+RoomOpCode.stop_VALUE)
public class CancelMatchService implements RoomOps{

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
	MatchService matchService;
	
	@Override
	public void doReq(C2SRoomOps ops) {
		// TODO Auto-generated method stub
		stringRedisTemplate.opsForList().leftPush(RoomDef.CancelOpsList, ops.getRoomId());
		matchService.doMatch(ops);
	}

}