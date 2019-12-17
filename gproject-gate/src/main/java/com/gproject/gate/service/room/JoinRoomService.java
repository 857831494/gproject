package com.gproject.gate.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.RoomOpCode;
import com.gproject.gate.service.room.RoomDef.GRoomInfo;
import com.gproject.gate.service.room.RoomDef.RoomOps;

@Service(RoomDef.RoomOpsList+RoomOpCode.join_VALUE)
public class JoinRoomService implements RoomOps{

	@Autowired
	ReportPlayerInfoService reportPlayerService;
	
	
	@Autowired
	CreateRoomService createRoomService;
	
	@Override
	public void doReq(C2SRoomOps ops) {
		//可以加上一些前置条件判断
		reportPlayerService.savePlayerIdJoinStatus(ops.getPlayerId(), ops.getRoomId());
		GRoomInfo matchRoomInfo=createRoomService.getRoomInfo(ops.getRoomId());
		
	}
	
	
}
