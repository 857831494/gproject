package com.gproject.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.db.LockService;
import com.gproject.common.dto.proto.RedTipDTO.RedTipCode;
import com.gproject.common.net.PushService;
import com.gproject.gate.cache.RedTipCache;
import com.gproject.gate.pojo.RedTipTableDef.RedTipModel;
import com.gproject.gate.pojo.RedTipTableDef.RedTipPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipRet;

@Service
public class RedTipService {

	@Autowired
	PushService pushService;

	@Autowired
	RedTipCache redTipCache;

	@Autowired
	LockService lockService;

	/**
	 * 添加红点
	 * 
	 * @param playerId
	 * @param redTipCode
	 */
	public void addTips(long playerId, RedTipCode redTipCode) {
		RedTipPojo redTipPojo = redTipCache.getData(playerId);
		RedTipRet redTipRet = redTipPojo.redTipRet;
		Integer lock = lockService.getLockBy(playerId);
		synchronized (lock) {
			if (redTipRet.isExist(redTipCode)) {
				return;
			}
			RedTipModel redTipModel=new RedTipModel(redTipCode, 0);
			redTipRet.redTipModels.add(redTipModel);
			redTipCache.update(redTipPojo);
			//推送红点
		}
	}

}
