package com.gproject.gate.service.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.protobuf.ByteString;
import com.gproject.common.dto.proto.CommandCodeDTO.CommandCode;
import com.gproject.common.dto.proto.RPCDTO.SendGateMessage;
import com.gproject.common.utils.IDef;

@Service
public class RedisService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	public void receiveMessage(byte[] data) throws Exception {
		SendGateMessage dto=SendGateMessage.parseFrom(data);
	}
	
	Logger logger=LoggerFactory.getLogger(RedisService.class);
	
	public void brocatstGate(long playerId,CommandCode commandCode,byte[] data) {
		List<Long> pList=new ArrayList<Long>();
		pList.add(playerId);
		this.brocatstGate(pList, commandCode, data);
	}
	/**
	 * 广播网关
	 * @param playerIds
	 * @param commandCode
	 * @param data
	 */
	public void brocatstGate(List<Long> playerIds,CommandCode commandCode,byte[] data) {
		if (CollectionUtils.isEmpty(playerIds)) {
			logger.error("广播的时候，发生playerIds空=======");
			return;
		}
		if (commandCode==null) {
			return;
		}
		SendGateMessage.Builder dto=SendGateMessage.newBuilder();
		dto.setCmdCode(commandCode);
		if (data!=null) {
			dto.setReqData(ByteString.copyFrom(data));
		}
		dto.addAllPlayerIds(playerIds);
		stringRedisTemplate.execute(connection -> {
			connection.publish(IDef.GATE_BROCAST_CHANNEL.getBytes(), dto.build().toByteArray());
			return null;
		}, true);
	}
}
