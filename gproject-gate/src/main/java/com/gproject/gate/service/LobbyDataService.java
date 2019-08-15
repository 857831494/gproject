package com.gproject.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.db.GPCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;

/**
 * 游戏大厅数据，异步加载，二级页面数据，
 * 
 * @author YW1825
 *
 */
@Service
public class LobbyDataService implements PlayerEnterEvent{

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	ThreadPoolService threadPoolService;
	
	@Override
	public void doEvent(Object object) {
		// TODO Auto-generated method stub
		PlayerEnterEventParame playerEnterEventParame=(PlayerEnterEventParame) object;
		applicationContext.getBeansOfType(GPCache.class).values().forEach(cache->{
			threadPoolService.getExecutorService().execute(()->{
				if (cache.needLoad()) {
					cache.getPojo(playerEnterEventParame.playerId);
				}
			});
		});
	}

}
