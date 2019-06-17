package com.gproject.cmdHandler;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public interface CMDDef {

	public long NO_ID = 0;

	
	public class TCPCommandInfo {
		public Object handler;
		public TCPCommand tcpCommand;
		public Method method;
	}

	public class TcpParame{
		public long playerId;
		public byte[] netPack;
	}
}
