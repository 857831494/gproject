package com.gproject.gate.service.match;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;

@Service
public class MatchService implements IAPPInit{

	public final static String matchRoom="matchRoom-";
	
	public final static String match_PlayerId_RoomId="match_PlayerId_RoomId";
	
	/**
	 * 是否主服，
	 */
	@Value("${master}")
	boolean master;//
	
	
	public void create(long playerId) {
		String roomId=UUID.randomUUID().toString();
		ListOperations lst=stringRedisTemplate.opsForList();
		lst.leftPush(matchRoom+roomId,playerId);
	}
	
	private void setIdToRoom(long playerId,String roomId) {
		
	}
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	public void join() {
		//redis当锁
		
	}
	
	
	public void startMatch() {
		
	}
	
	
	public void stopMatch() {
		
	}

	@Override
	public void init(InitParame initParame) throws Exception {
		// TODO Auto-generated method stub
		
		if (master) {
			//启动定时器线程池，处理，匹配
		}
	}
}
