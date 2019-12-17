package com.gproject.gate.service.room;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.googlecode.protobuf.format.JsonFormat;
import com.gproject.common.dto.proto.RoomDTO.C2SRoomOps;
import com.gproject.common.dto.proto.RoomDTO.GPlayerInfo;
import com.gproject.common.dto.proto.RoomDTO.RoomOpCode;
import com.gproject.gate.service.room.RoomDef.RoomOps;

@Service
public class RoomOpsService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	ReportPlayerInfoService reportPlayerInfoService;
	
	final static int MAX_THREAD_NUM = 4;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);

	
	
	
	public void reqRoomOps(C2SRoomOps matchOps,long playerId) {
		C2SRoomOps.Builder builder=matchOps.toBuilder();
		if (matchOps.getOpCode()==RoomOpCode.join) {
			matchOps.toBuilder().setInfo(reportPlayerInfoService.createBuilder(playerId));
		}
		ListOperations<String,String> listOperations = stringRedisTemplate.opsForList();

		String key = RoomDef.RoomOpsList + matchOps.getRoomId();
		String lockString = RoomDef.RoomOpsLock+ matchOps.getRoomId();
		
		builder.setPlayerId(playerId);
		listOperations.leftPush(key, JsonFormat.printToString(builder.build()));
		
		executorService.execute(()->{
			// 获取锁，防止，并发
			ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
			boolean flag = operations.setIfAbsent(lockString, System.currentTimeMillis() + "");

			if (!flag) {
				return;
			}
			try {
				long time = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
				stringRedisTemplate.expireAt(lockString, new Date(time));
				while (true) {
					String matchOpString =  listOperations.rightPop(key);
					if (matchOpString == null) {
						break;
					}
					C2SRoomOps.Builder cOps =C2SRoomOps.newBuilder();
					JsonFormat.merge(matchOpString, cOps);
					RoomOps roomops=(RoomOps) applicationContext.
							getBean(RoomDef.RoomOpsList
							+cOps.getOpCode().getNumber());
					roomops.doReq(cOps.build());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			stringRedisTemplate.delete(key);
		});
		
		
	}

	@Autowired
	ApplicationContext applicationContext;
	
	

}
