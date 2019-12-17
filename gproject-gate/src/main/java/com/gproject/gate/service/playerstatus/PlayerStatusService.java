package com.gproject.gate.service.playerstatus;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gproject.gate.service.playerstatus.PlayerStatusDef.StatusModel;

@Service
public class PlayerStatusService{
	
	@Autowired
	StringRedisTemplate redisTemplate;
	
	public StatusModel	getPlayerStatus(long playerId) {
		String ret=redisTemplate.opsForValue().get(
				PlayerStatusDef.status+playerId);
		if (StringUtils.isBlank(ret)) {
			return null;
		}
		return JSON.parseObject(ret, StatusModel.class);
	}
	
	/**
	 * 更新玩家状态
	 * @param statusModel
	 * @param playerId
	 */
	public void update(StatusModel statusModel,long playerId) {
		redisTemplate.opsForValue().set(PlayerStatusDef.status+playerId, 
				JSON.toJSONString(statusModel));
	}
}