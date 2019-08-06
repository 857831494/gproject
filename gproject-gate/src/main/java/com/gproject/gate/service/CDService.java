package com.gproject.gate.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.db.LockService;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.CDType;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.HCDConfig;
import com.gproject.gate.cache.CDNumCache;
import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEvent;
import com.gproject.gate.event.player.PlayerDailyEventDef.PlayerDailyEventParame;
import com.gproject.gate.event.player.PlayerWeekEventDef.PlayerWeekEvent;
import com.gproject.gate.event.player.PlayerWeekEventDef.PlayerWeekEventParame;
import com.gproject.gate.pojo.CDTableDef.CDNumPojo;
import com.gproject.gate.pojo.CDTableDef.CDNumRet;

@Service
public class CDService implements PlayerDailyEvent,PlayerWeekEvent{

	Logger logger=LoggerFactory.getLogger(CDService.class);
	

	@Autowired
	ExcelService excelService;
	
	@Autowired
	LockService lockService;
	
	@Autowired
	CDNumCache cdNumCache;
	
	
	/**
	 * 直接，减掉次数，如果发现超过，直接抛错误码
	 * @param cdId
	 */
	public void doCDNum(int cdId,long playerId) {
		
	}

	/**
	 * 检查cd次数，不够，抛错误码
	 * @param playerId
	 * @param cdId
	 */
	public void checkCDNum(long playerId,int cdId) {
		
	}
	
	/**
	 * 减少cd 数量
	 * @param playerId
	 * @param cdId
	 */
	public void HincrbyCDNum(long playerId,int cdId) {
		
	}
	
	public List<HCDConfig> getList(int type) {
		ArrayList<HCDConfig> list=new ArrayList<>();
		List<HCDConfig> dataConfigs=excelService.getAll(HCDConfig.class);
		for (HCDConfig hcdConfig :dataConfigs ) {
			if (hcdConfig.cdType==type) {
				list.add(hcdConfig);
			}
		}
		return list;
	}
	
	private void resetCDNum(long playerid,int type) {
		Integer lockInteger=lockService.getLockBy(playerid);
		List<HCDConfig> list=getList(CDType.Daily);
		synchronized (lockInteger) {
			CDNumPojo cdNumPojo=cdNumCache.getData(playerid);
			CDNumRet cdNumRet=cdNumPojo.cdNumRet;
			for (HCDConfig hcdConfig : list) {
				
				cdNumRet.map.put(hcdConfig.cdId, 0);
			}
			cdNumCache.update(cdNumPojo);
		}
	}
	
	
	
	@Override
	public void doEvent(Object object) {
		// TODO Auto-generated method stub
		//事件都是并发，注意加锁
		if (object instanceof PlayerDailyEventParame) {
			PlayerDailyEventParame dailyEventParame=(PlayerDailyEventParame) object;
			this.resetCDNum(dailyEventParame.playerId, CDType.Daily);
		}
		
		if (object instanceof PlayerWeekEventParame) {
			PlayerWeekEventParame weekEventParame=(PlayerWeekEventParame) object;
			this.resetCDNum(weekEventParame.playerId, CDType.Week);
		}
		
		
	}
}
