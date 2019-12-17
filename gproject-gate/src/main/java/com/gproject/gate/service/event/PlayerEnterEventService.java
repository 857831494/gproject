package com.gproject.gate.service.event;


import java.util.Collection;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RPCDTO.GameMessage;
import com.gproject.common.utils.common.GErrorException;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.service.socket.PushService;




@Service
public class PlayerEnterEventService {

	
	
	@Autowired
	ApplicationContext applicationContext;
	
	Logger logger=LoggerFactory.getLogger(PlayerEventService.class);
	
	@Autowired
	PushService pushService;
	
	@Autowired
	ThreadPoolService threadPoolService;
	
	public boolean checkSecurity(GameMessage gameMessage) {
		return true;
	}
	/**
	 * 玩家进来
	 * @param playerId
	 */
	public void doEnterEvent(long playerId) {
		Collection<PlayerEnterEvent> list=(Collection<PlayerEnterEvent>) 
				applicationContext.getBeansOfType(PlayerEnterEvent.class).values();
		for (PlayerEnterEvent gEvent : list) {
			
			threadPoolService.getExecutorService().execute(()->{
				try {
					long now=System.currentTimeMillis();
					PlayerEnterEventParame playerEventParame=new PlayerEnterEventParame(playerId);
					gEvent.doPlayerEnterEvent(playerEventParame);
					if (gEvent instanceof PlayerEnterEvent) {
						logger.info(gEvent+"事件执行=============耗时ms:"+(System.currentTimeMillis()-now));
					}
				} catch (Exception e) {
					// TODO: handle exception
					if (e instanceof GErrorException) {
						GErrorException errorException=(GErrorException) e;
						pushService.pushError(playerId, errorException.tipCode);
					}else {
						logger.error("发布事件===发生未知错误============");
						logger.error(ExceptionUtils.getStackTrace(e));
					}
				}
			});
		}
	}
	
	
}
