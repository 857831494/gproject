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
public class CDService {

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
	
	

	
	
	
	
}
