package com.gproject.common.net.netty;

public class NetPack {

	public int cmdType;
	
	public int cmdCode;

	public byte[] data;

	public long playerId;

	
	public long unionId;
	
	/**
	 * 房间id,专用
	 */
	public String roomId;
	
	/**
	 * 房间处理队列，定位
	 */
	public int room_random;
	
	public NetPack(int cmdCode, byte[] data) {
		super();
		this.cmdCode = cmdCode;
		this.data = data;
	}

	
	public NetPack() {
	}

}