package com.gproject.gate.service.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.db.LockService;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.CDType;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.HCDConfig;
import com.gproject.common.utils.date.DateUtils;
import com.gproject.gate.cache.CDNumCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.CDTableDef.CDNumRet;

/**
 * 周数据重置，月数据重置,日数据重置
 * @author YW1825
 *
 */
@Service
public class CDService implements PlayerEnterEvent{

	Logger logger=LoggerFactory.getLogger(CDService.class);
	

	@Autowired
	ExcelService excelService;
	
	@Autowired
	LockService lockService;
	
	@Autowired
	CDNumCache cdNumCache;
	
	
	/**
	 * 增加数量，根据配置表，决定是否抛错误码,
	 * @param cdId
	 */
	public void addCDNum(int cdId,long playerId) {
		
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
	public void consumeCDNum(long playerId,int cdId) {
		
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
	
	
	/**
	 * 玩家进来的时候，就调用
	 * @param playerEnterEventParame
	 */
	public void doDataReSet(PlayerEnterEventParame playerEnterEventParame) {
		CDNumRet cdNumRet=cdNumCache.getPojo(playerEnterEventParame.playerId);
		
		if (cdNumRet.lastflushTime!=null) {
			if (!DateUtils.isSameDate(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerEnterEventParame.playerId, CDType.Daily);
			}
			if (!DateUtils.isSameWeek(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerEnterEventParame.playerId, CDType.Week);
			}
			if (!DateUtils.isSameMonth(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerEnterEventParame.playerId, CDType.Month);
			}
		}
		cdNumRet.lastflushTime=new Date();
		cdNumCache.update(cdNumRet,playerEnterEventParame.playerId);
	}
	
	private void resetCDNum(long playerid,int type) {
		List<HCDConfig> list=getList(CDType.Daily);
		CDNumRet cdNumRet=cdNumCache.getPojo(playerid);
	
		for (HCDConfig hcdConfig : list) {
			cdNumRet.map.put(hcdConfig.cdId, 0);
		}
		cdNumCache.update(cdNumRet,playerid);
	}

	

	@Override
	public void doPlayerEnterEvent(PlayerEnterEventParame playerEnterEventParame) {
		// TODO Auto-generated method stub
		this.doDataReSet(playerEnterEventParame);
	}
	
	
	
	
}
