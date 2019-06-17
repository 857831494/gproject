package com.gproject.net;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value = "/" + PushService.WEBSCOKET)
public class GWebSocket  {

	static Logger logger = LoggerFactory.getLogger(GWebSocket.class);

	
	
	final static int MAX_SESSION_NUM=5000;
	
	final static int IDLE_TIME=30*1000;
	@Autowired
	WebSocketManager webSocketManager;
	
	/**
	 * 连接建立成功调用的方法
	 * @throws Exception 
	 */
	@OnOpen
	public void onOpen(Session session) throws Exception {
		if (webSocketManager.sessionMap.size()>MAX_SESSION_NUM) {
			session.close();
			return;
		}
		session.setMaxIdleTimeout(IDLE_TIME);
	}

	@OnClose
	public void onClose(Session session) {
		logger.info("onClose上线============" + session);
	}

	@OnError
	public void onError(Session session,Throwable error) {
		logger.info("onError上线============" + session);
	}

	@OnMessage
	public void onMessage(byte[] message,Session session) {
		logger.info("消息上线============" + message);
	}
}
