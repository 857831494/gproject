package com.gproject.gate.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.RoomOpCode;
import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.gate.service.room.RoomDef.GRoomInfo;
import com.gproject.gate.service.room.RoomDef.RoomOps;


@Service(RoomDef.RoomOpsList+RoomOpCode.start_VALUE)
public class MatchService implements RoomOps{

	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	CreateRoomService createRoomService;
	
	@Override
	public void doReq(C2SRoomOps ops) {
		// TODO Auto-generated method stub
		GRoomInfo gRoomInfo=createRoomService.getRoomInfo(ops.getRoomId());
		long playerId=ops.getPlayerId();
		//检查是否房主
		if (gRoomInfo.statusCode==StatusCode.matching) {
			return;
		}
		gRoomInfo.statusCode=StatusCode.matching;
		String key=RoomDef.MatchOpsList+gRoomInfo.areaId;
		stringRedisTemplate.opsForList().leftPush(key, gRoomInfo.teamIds);
		this.doMatch(ops);
	}
	
	
	/**
	 * 处理每个区间匹配
	 * @param i
	 */
	public void doArea(int i) {
		int areaId = 0;
		int teamSize=2;//队伍人数对战数量
		long lstSize=stringRedisTemplate.opsForList().
				size(RoomDef.RoomOpsList+areaId);
		
	}
	
	/**
	 * 取消匹配
	 */
	private void cancelMatch() {
		
	}
	
	public void doMatch(C2SRoomOps ops) {
		int areaNum=0;//匹配区间数量
		boolean flag=stringRedisTemplate.opsForValue().
				setIfAbsent(RoomDef.MatchOpsLock, 
				""+System.currentTimeMillis());
		try {
			cancelMatch();
			for (int i = 0; i < areaNum; i++) {
				this.doArea(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		stringRedisTemplate.delete(RoomDef.MatchOpsLock);
	}

	
}