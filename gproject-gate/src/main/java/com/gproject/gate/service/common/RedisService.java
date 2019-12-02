package com.gproject.gate.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;
import com.gproject.common.dto.proto.RPCDTO.GateSendMessage;
import com.gproject.common.dto.proto.RPCDTO.SendGateMessage;
import com.gproject.common.net.netty.NetPack;
import com.gproject.common.utils.IDef;

@Service
public class RedisService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	public void receiveMessage(byte[] data) throws Exception {
		SendGateMessage dto=SendGateMessage.parseFrom(data);
	}
	
	/**
	 * 广播消息到战斗服,采用redis分发，是为了，后期，水平扩展战斗服
	 * @param netPack
	 */
	public void brocatstFight(NetPack netPack) {
		GateSendMessage.Builder dto=GateSendMessage.newBuilder();
		stringRedisTemplate.execute(connection -> {
			connection.publish(IDef.FIGHT_BROCAST_CHANNEL.getBytes(), dto.build().toByteArray());
			return null;
		}, true);
	}
	
	/**
	 * 广播网关
	 * @param playerIds
	 * @param commandCode
	 * @param data
	 */
	public void brocatstGate(List<Long> playerIds,CommandCode commandCode,byte[] data) {
		GateSendMessage.Builder dto=GateSendMessage.newBuilder();
		stringRedisTemplate.execute(connection -> {
			connection.publish(IDef.GATE_BROCAST_CHANNEL.getBytes(), dto.build().toByteArray());
			return null;
		}, true);
	}
}
