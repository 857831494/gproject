package com.gproject.center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class PlayerInitService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	public void getGateToken() {
		//
		String openId;
		//验证通过后，直接在redis里面存储数据
		//可以返回，各个区服 的角色信息
		//是否，存在组队，战斗等
	}
	
	
}
