package com.gproject.gate.service.socket;


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

import com.gproject.common.dto.proto.RPCDTO.GameMessage;
import com.gproject.gate.service.event.PlayerEnterEventService;


@Component
@ServerEndpoint(value = "/" + PushService.WEBSCOKET)
public class GWebSocket  {

	static Logger logger = LoggerFactory.getLogger(GWebSocket.class);

	@Autowired
	WebSocketService webSocketService;
	
	final static int MAX_SESSION_NUM=5000;
	
	final static int IDLE_TIME=30*1000;
	
	/**
	 * 连接建立成功调用的方法
	 * @throws Exception 
	 */
	@OnOpen
	public void onOpen(Session session) throws Exception {
//		if (webSocketManager.sessionMap.size()>MAX_SESSION_NUM) {
//			session.close();
//			logger.info("当前服务器已经超出最大数量=================");
//			return;
//		}
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

	@Autowired
	PlayerEnterEventService playerEnterEventService;
	
	@OnMessage
	public void onMessage(byte[] message,Session session) {
		try {
			GameMessage gameMessage=GameMessage.parseFrom(message);
			long playerId=webSocketService.getPlayerId(session);
			if (0==playerId) {
				//必须进行安全监测
				
			}else {
				//可以处理
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
