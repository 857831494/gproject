package com.gproject.common.net.netty;

public class NetPack {

	public int cmdType;
	
	public int cmdCode;

	public byte[] data;

	public long playerId;

	
	public long unionId;
	
	public int serverId;
	
	public NetPack(int cmdCode, byte[] data) {
		super();
		this.cmdCode = cmdCode;
		this.data = data;
	}

	
	public NetPack() {
	}

}