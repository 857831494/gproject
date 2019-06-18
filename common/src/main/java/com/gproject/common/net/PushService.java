package com.gproject.common.net;

import java.util.List;

import com.google.protobuf.MessageLite;
import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;
import com.gproject.common.dto.proto.TipDTO.TipCode;

public interface PushService {

	public final static String WEBSCOKET="PushService_WEBSCOKET";
	
	public final static String PUSH_TYPE="pushType";
	
	void push(long playerId,CommandCode cmdCode,MessageLite dto);
	
	void push(List<Long> playerId,CommandCode cmdCode,MessageLite dto);
	
	void pushOnline(CommandCode cmdCode,MessageLite dto);
	
	public long getPlayerId(Object session);
	
	public void pushError(long playerId,TipCode tipCode);
	
	public void pushError(Object player,TipCode tipCode);
}
