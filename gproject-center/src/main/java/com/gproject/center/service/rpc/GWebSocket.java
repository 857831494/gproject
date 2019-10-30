package com.gproject.center.service.rpc;


import java.util.concurrent.ConcurrentHashMap;

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

import com.gproject.common.utils.IDef;


@Component
@ServerEndpoint(value = IDef.RPC_URL)
public class GWebSocket  {

	static Logger logger = LoggerFactory.getLogger(GWebSocket.class);

	public ConcurrentHashMap<Integer,Session> id_zoneMap=new ConcurrentHashMap<>();
	
	public ConcurrentHashMap<Session,Integer> zone_idMap=new ConcurrentHashMap<>();
	 
	
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
		logger.info("服务器下线============" + getServerId(session));
		this.zoneLeave(session);
	}

	public int getServerId(Session session) {
		Integer serverId=zone_idMap.get(session);
		if (serverId==null) {
			return 0;
		}
		return serverId;
	}
	
	public void zoneLeave(Session session) {
		Integer serverId=zone_idMap.get(session);
		if (serverId==null) {
			return;
		}
		zone_idMap.remove(session);
		id_zoneMap.remove(serverId);
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		logger.info("服务器发生错误============" + getServerId(session));
		
	}

	@OnMessage
	public void onMessage(String message,Session session) {
		logger.info("区服开始连接========"+message);
	}
	
	@OnMessage
	public void onMessage(byte[] message,Session session) {
		logger.info("消息上线============" + message);
	}
}
