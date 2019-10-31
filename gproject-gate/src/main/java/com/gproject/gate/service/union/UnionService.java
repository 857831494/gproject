package com.gproject.gate.service.union;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.net.netty.NetPack;
import com.gproject.gate.service.cmd.UnionCmdService;

@Service
public class UnionService {

	@Autowired
	UnionCmdService unionCmdService;
	
	/**
	 * 申请加入公会，不知道，公会id，先解析出公会id
	 * @param netPack
	 */
	public void apply(NetPack netPack) {
		long unionId=0;
		netPack.unionId=unionId;
		unionCmdService.doUnionReq(netPack);
	}
}
