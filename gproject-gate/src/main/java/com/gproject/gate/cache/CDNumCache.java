package com.gproject.gate.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.CDType;
import com.gproject.common.staticdata.excelmodel.HCDConfigDef.HCDConfig;
import com.gproject.common.utils.date.DateUtils;
import com.gproject.gate.pojo.CDTableDef.CDNumRet;

//数据DAO
@Repository
public class CDNumCache extends GPCache<CDNumRet> {

	
	@Override
	@Cached
	public CDNumRet getPojo(Object id) {
		// TODO Auto-generated method stub
		CDNumRet cmNumRet=(CDNumRet) super.getPojo(id);
		this.doDataReSet(cmNumRet, (long) id);
		return cmNumRet;
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
	
	
	@Autowired
	ExcelService excelService;
	
	
	private void resetCDNum(long playerid,int type,CDNumRet cdNumRet) {
		List<HCDConfig> list=getList(CDType.Daily);
		for (HCDConfig hcdConfig : list) {
			cdNumRet.map.put(hcdConfig.cdId, 0);
		}
		this.update(cdNumRet,playerid);
	}
	
	/**
	 * 玩家进来的时候，就调用
	 * @param playerEnterEventParame
	 */
	private void doDataReSet(CDNumRet cdNumRet,long playerId) {
		if (cdNumRet.lastflushTime!=null) {
			if (!DateUtils.isSameDate(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerId, CDType.Daily,cdNumRet);
			}
			if (!DateUtils.isSameWeek(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerId, CDType.Week,cdNumRet);
			}
			if (!DateUtils.isSameMonth(cdNumRet.lastflushTime)) {
				this.resetCDNum(playerId, CDType.Month,cdNumRet);
			}
		}
		cdNumRet.lastflushTime=new Date();
	}
}
