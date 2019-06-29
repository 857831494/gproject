package com.gproject.gate.service;

import java.util.Collection;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.net.PushService;
import com.gproject.common.utils.common.GErrorException;
import com.gproject.gate.event.IEventDef.GEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEventParame;

@Service
public class EventService {

	
	@Autowired
	ApplicationContext applicationContext;
	
	Logger logger=LoggerFactory.getLogger(EventService.class);
	
	@Autowired
	PushService pushService;
	
	/**
	 * 发布事件
	 * @param classType
	 * @param object
	 */
	public void publish(Class<? extends GEvent> classType,Object object) {
		Collection<GEvent> list=(Collection<GEvent>) 
				applicationContext.getBeansOfType(classType).values();
		for (GEvent gEvent : list) {
			try {
				gEvent.doEvent(object);
			} catch (Exception e) {
				// TODO: handle exception
				if (e instanceof GErrorException) {
					if (object instanceof PlayerEnterEvent) {
						PlayerEventParame playerEventParame=(PlayerEventParame) object;
						GErrorException errorException=(GErrorException) e;
						pushService.pushError(playerEventParame.playerId, errorException.tipCode);
					}
				}else {
					logger.error("发生未知错误============");
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}
		
	}
}
