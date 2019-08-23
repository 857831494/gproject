package com.gproject.gate.service.match;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.ApplicationContext;

import com.gproject.common.net.ByteUtil;
import com.gproject.gate.service.socket.PushService;

public class MatchClient extends WebSocketClient{

	PushService pushService;
	
	ApplicationContext applicationContext;
	
	public MatchClient(URI url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public void init(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;
		pushService=applicationContext.getBean(PushService.class);
	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(ByteBuffer bytes) {
		// TODO Auto-generated method stub
		//广播客户端
		int INT_LEN=4;
		byte[] playerIdsLen=new byte[INT_LEN];
		bytes.get(playerIdsLen);
		int len=ByteUtil.byteArrayToInt(playerIdsLen);
		byte[] playerIds=new byte[len];
		bytes.get(playerIds);
		byte[] cmdBytes=new byte[INT_LEN];
		bytes.get(cmdBytes);
		int cmdCode=ByteUtil.byteArrayToInt(cmdBytes);
		if (bytes.hasRemaining()) {
			byte[] data=new byte[bytes.remaining()];
			bytes.get(data);
		}
	}
	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
