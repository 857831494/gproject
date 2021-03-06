package com.gproject.gate.service.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RPCDTO.GameMessage;


@Service
public class CmdService {

	public final static String PACK_NAME="com.gproject.gate.service.cmd";
	
	@Autowired
	ApplicationContext applicationContext;
	
	static Logger logger=LoggerFactory.getLogger(CmdService.class);
	
	public void doReq(GameMessage netPack) {
		TcpCmdService tcpCmdManager=(TcpCmdService) 
				applicationContext.getBean(PACK_NAME+netPack.getCmdType().getNumber());
		if (tcpCmdManager==null) {
			logger.info("找不到命令==========");
			return;
		}
		tcpCmdManager.doCmdReq(netPack);
	}
}
