package com.gproject.gate.service.cmd;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import com.gproject.common.net.netty.NetPack;

public class UnionTask {

	public ConcurrentLinkedDeque<NetPack> taskDeque=new ConcurrentLinkedDeque<NetPack>();
	public AtomicBoolean runFlag=new AtomicBoolean(false);
}
