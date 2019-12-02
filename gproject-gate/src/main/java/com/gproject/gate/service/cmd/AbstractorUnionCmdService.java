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

public abstract class AbstractorUnionCmdService extends TcpCmdService{

	static int MAX=10;
	private UnionTask[] taskDeques=new UnionTask[MAX];
	
	ExecutorService pool = Executors.newFixedThreadPool(MAX);
	
	
	
	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CommandType.Union_VALUE;
	}

	public void doUnionReq(NetPack netPack) {
		int index=(int) (netPack.unionId%MAX);
		UnionTask unionTask=taskDeques[index];
		unionTask.taskDeque.add(netPack);
		if (unionTask.runFlag.compareAndSet(false, true)) {
			while (true) {
				NetPack taPack=unionTask.taskDeque.poll();
				if (taPack==null) {
					break;
				}
				super.doCmdReq(taPack);
			}
			unionTask.runFlag.compareAndSet(true, false);
		}
		super.doCmdReq(netPack);
	}
	
	@Override
	public void doCmdReq(NetPack netPack) {
		// TODO Auto-generated method stub
		this.doUnionReq(netPack);
	}
}
