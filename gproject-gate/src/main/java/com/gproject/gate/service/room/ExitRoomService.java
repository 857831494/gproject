package com.gproject.gate.service.room;

import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.RoomOpCode;
import com.gproject.gate.service.room.RoomDef.RoomOps;

@Service(RoomDef.RoomOpsList+RoomOpCode.exit_VALUE)
public class ExitRoomService implements RoomOps{

	@Override
	public void doReq(C2SRoomOps ops) {
		// TODO Auto-generated method stub
		
	}

	
}
