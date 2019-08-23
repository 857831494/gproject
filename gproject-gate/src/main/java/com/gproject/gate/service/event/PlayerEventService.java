package com.gproject.gate.service.event;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.utils.common.GErrorException;
import com.gproject.gate.event.IEventDef.GEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.service.ThreadPoolService;
import com.gproject.gate.service.socket.PushService;
import com.gproject.gate.event.player.PlayerEventParame;

@Service
public class PlayerEventService {

	
	@Autowired
	ApplicationContext applicationContext;
	
	Logger logger=LoggerFactory.getLogger(PlayerEventService.class);
	
	@Autowired
	PushService pushService;
	
	@Autowired
	ThreadPoolService threadPoolService;
	
//	/**
//	 * 同步，发布事件
//	 * @param classType
//	 * @param object
//	 */
//	public void publish(Class<? extends GEvent> classType,Object object) {
//		Collection<GEvent> list=(Collection<GEvent>) 
//				applicationContext.getBeansOfType(classType).values();
//		for (GEvent gEvent : list) {
//			try {
//				gEvent.doEvent(object);
//			} catch (Exception e) {
//				// TODO: handle exception
//				if (e instanceof GErrorException) {
//					if (object instanceof PlayerEnterEvent) {
//						PlayerEventParame playerEventParame=(PlayerEventParame) object;
//						GErrorException errorException=(GErrorException) e;
//						pushService.pushError(playerEventParame.playerId, errorException.tipCode);
//					}
//				}else {
//					logger.error("发布事件===发生未知错误============");
//					logger.error(ExceptionUtils.getStackTrace(e));
//				}
//			}
//		}
//	}
	
	/**
	 * 同步，发布事件
	 * @param classType
	 * @param object
	 */
	public void asyn_publish(Class<? extends GEvent> classType,Object object) {
		Collection<GEvent> list=(Collection<GEvent>) 
				applicationContext.getBeansOfType(classType).values();
		for (GEvent gEvent : list) {
			
			threadPoolService.getExecutorService().execute(()->{
				try {
					long now=System.currentTimeMillis();
					gEvent.doEvent(object);
					if (gEvent instanceof PlayerEnterEvent) {
						logger.info(gEvent+"事件执行=============耗时ms:"+(System.currentTimeMillis()-now));
					}
				} catch (Exception e) {
					// TODO: handle exception
					if (e instanceof GErrorException) {
						if (object instanceof PlayerEnterEvent) {
							PlayerEventParame playerEventParame=(PlayerEventParame) object;
							GErrorException errorException=(GErrorException) e;
							pushService.pushError(playerEventParame.playerId, errorException.tipCode);
						}
					}else {
						logger.error("发布事件===发生未知错误============");
						logger.error(ExceptionUtils.getStackTrace(e));
					}
				}
			});
		}
	}
}
