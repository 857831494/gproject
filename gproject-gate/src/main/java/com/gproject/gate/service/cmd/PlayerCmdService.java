package com.gproject.gate.service.cmd;

import org.springframework.stereotype.Service;

import com.gproject.common.cmdHandler.CmdType;


@Service(CmdService.PACK_NAME+CmdType.player)
public class PlayerCmdService extends TcpCmdService {

	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CmdType.player;
	}
}
