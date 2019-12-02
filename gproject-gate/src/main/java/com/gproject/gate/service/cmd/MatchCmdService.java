package com.gproject.gate.service.cmd;

import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RPCDTO.CommandType;

@Service(CmdService.PACK_NAME+CommandType.Match_VALUE)
public class MatchCmdService extends AbstractorUnionCmdService{

	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CommandType.Match_VALUE;
	}
}