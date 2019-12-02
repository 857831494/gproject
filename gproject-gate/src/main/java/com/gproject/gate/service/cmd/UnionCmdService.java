package com.gproject.gate.service.cmd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.cmdHandler.CmdType;
import com.gproject.common.dto.proto.RPCDTO.CommandType;
import com.gproject.common.net.netty.NetPack;
import com.gproject.gate.cache.PlayerInfoCache;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfo;

@Service(CmdService.PACK_NAME+CommandType.Union_VALUE)
public class UnionCmdService extends AbstractorUnionCmdService{

	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CommandType.Union_VALUE;
	}
	
}
