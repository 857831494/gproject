package com.gproject.gate.control;

import org.springframework.web.bind.annotation.RestController;

import com.gproject.common.cmdHandler.CMDDef.TcpParame;
import com.gproject.common.cmdHandler.CmdType;
import com.gproject.common.cmdHandler.TCPCommand;
import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;

@RestController
public class BagControl {

	@TCPCommand(cmdType = CmdType.player, cmdCode =CommandCode.C2SCreateRoleCode )
	public void getBag(TcpParame netPack) {
		
	}
}
