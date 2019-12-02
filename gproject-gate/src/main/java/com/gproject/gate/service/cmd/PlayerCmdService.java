package com.gproject.gate.service.cmd;

import org.springframework.stereotype.Service;

import com.gproject.common.cmdHandler.CmdType;
import com.gproject.common.dto.proto.RPCDTO.CommandType;


@Service(CmdService.PACK_NAME+CommandType.Common_VALUE)
public class PlayerCmdService extends TcpCmdService {

	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CommandType.Common_VALUE;
	}
}
