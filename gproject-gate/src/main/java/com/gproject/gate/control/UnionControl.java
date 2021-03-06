package com.gproject.gate.control;

import org.springframework.web.bind.annotation.RestController;

import com.gproject.common.cmdHandler.CmdType;
import com.gproject.common.cmdHandler.TCPCommand;
import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;
import com.gproject.common.dto.proto.RPCDTO.GameMessage;


@RestController
public class UnionControl {

	/**
	 * 工会命令
	 * @param netPack
	 */
	@TCPCommand(cmdType = CmdType.union, cmdCode =CommandCode.C2SCreateRoleCode )
	public void applyUnion(GameMessage netPack) {
		
	}

	@TCPCommand(cmdType = CmdType.union, cmdCode =CommandCode.C2SCreateRoleCode )
	public void joinUnion() {
		
	}
	
	@TCPCommand(cmdType = CmdType.union, cmdCode =CommandCode.C2SCreateRoleCode )
	public void joinUnionCallBack() {
		
	}
}
