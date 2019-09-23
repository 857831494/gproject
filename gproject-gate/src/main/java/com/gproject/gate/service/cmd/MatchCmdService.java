package com.gproject.gate.service.cmd;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.gproject.common.cmdHandler.CmdType;
import com.gproject.common.net.netty.NetPack;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.gate.service.match.MatchClient;

@Service(CmdService.PACK_NAME + CmdType.match)
public class MatchCmdService extends TcpCmdService implements Runnable {

	@Override
	public int getCmdType() {
		// TODO Auto-generated method stub
		return CmdType.match;
	}

	URI url;

	MatchClient matchClient;

	@Override
	public void init(InitParame initParame) {
		// TODO Auto-generated method stub
		super.init(initParame);
		try {
			url = new URI(initParame.applicationContext.getEnvironment().getProperty("matchServer"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchClient = new MatchClient(url);
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(this, 4, 1, TimeUnit.SECONDS);
	}

	@Override
	public void doCmdReq(NetPack netPack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (matchClient.isClosed()) {
			matchClient.connect();
		}
	}
}