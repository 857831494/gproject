package com.gproject.common.net;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.MessageLite;
import com.gproject.common.cmdHandler.CMDDef;
import com.gproject.common.dto.proto.TipDTO;
import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;
import com.gproject.common.dto.proto.TipDTO.GameTip;
import com.gproject.common.dto.proto.TipDTO.TipCode;

@Component(PushService.WEBSCOKET)
public class WebSocketManager implements PushService{

	static Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

	@Override
	public void push(long playerId, CommandCode cmdCode, MessageLite dto) {
		// TODO Auto-generated method stub
		logger.info("发生============" + playerId);
	}

	@Override
	public void push(List<Long> playerId, CommandCode cmdCode, MessageLite dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pushOnline(CommandCode cmdCode, MessageLite dto)  {
		// TODO Auto-generated method stub
		for(Session session:IDMap.values()) {
			try {
				session.getAsyncRemote().sendText(""+System.currentTimeMillis());
				logger.info("发生============" );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static ConcurrentHashMap<Long, Session> IDMap=new ConcurrentHashMap<Long, Session>();

	public static ConcurrentHashMap<Session, Long> sessionMap=new ConcurrentHashMap();
	
	@Override
	public long getPlayerId(Object session) {
		// TODO Auto-generated method stub
		Long playerId=sessionMap.get(session);
		if (playerId==null) {
			return CMDDef.NO_ID;
		}
		return playerId;
	}

	@Override
	public void pushError(long playerId, TipCode tipCode) {
		// TODO Auto-generated method stub
		Session session=IDMap.get(playerId);
		GameTip.Builder dto=GameTip.newBuilder();
		dto.setCode(tipCode);
	}

	@Override
	public void pushError(Object player, TipCode tipCode) {
		// TODO Auto-generated method stub
		
	}
	
}
