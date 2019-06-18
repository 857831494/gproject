package com.gproject.common.net.netty;

public class NetPack {

	public int cmdCode;

	public byte[] data;

	

	
	
	
	
	public NetPack(int cmdCode, byte[] data) {
		super();
		this.cmdCode = cmdCode;
		this.data = data;
	}

	
	public NetPack() {
	}

}