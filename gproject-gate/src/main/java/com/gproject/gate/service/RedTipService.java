package com.gproject.gate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.db.LockService;
import com.gproject.common.dto.proto.RedTipDTO.C2SDelRedTip;
import com.gproject.common.dto.proto.RedTipDTO.RedTipCode;
import com.gproject.common.net.PushService;
import com.gproject.gate.cache.RedTipCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.pojo.RedTipTableDef.RedTipModel;
import com.gproject.gate.pojo.RedTipTableDef.RedTipPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipRet;

@Service
public class RedTipService implements PlayerEnterEvent{

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
		RedTipPojo redTipPojo = redTipCache.getPojo(playerId);
		RedTipRet redTipRet = redTipPojo.getLogicObj();
		Integer lock = lockService.getLock(playerId);
		synchronized (lock) {
			if (redTipRet.isExist(redTipCode)) {
				return;
			}
			RedTipModel redTipModel=new RedTipModel(redTipCode, 0);
			redTipRet.redTipModels.add(redTipModel);
			redTipCache.update(redTipPojo);
			//推送单个红点
		}
	}
	
	/**
	 * 背包红点专用 
	 * @param playerId
	 * @param itemId
	 */
	public void addBagTips(long playerId,int itemId) {
		RedTipPojo redTipPojo = redTipCache.getPojo(playerId);
		RedTipRet redTipRet = redTipPojo.getLogicObj();
		Integer lock = lockService.getLock(playerId);
		RedTipCode redTipCode=RedTipCode.Bag;
		synchronized (lock) {
			if (redTipRet.isExist(redTipCode,itemId)) {
				return;
			}
			RedTipModel redTipModel=new RedTipModel(redTipCode, itemId);
			redTipRet.redTipModels.add(redTipModel);
			redTipCache.update(redTipPojo);
			//推送单个红点
		}
	}

	public void addBagTips(long playerId,List<Integer> itemIds) {
		RedTipPojo redTipPojo = redTipCache.getPojo(playerId);
		RedTipRet redTipRet = redTipPojo.getLogicObj();
		Integer lock = lockService.getLock(playerId);
		RedTipCode redTipCode=RedTipCode.Bag;
		synchronized (lock) {
			for (Integer itemId : itemIds) {
				if (redTipRet.isExist(redTipCode)) {
					continue;
				}
				RedTipModel redTipModel=new RedTipModel(redTipCode, itemId);
				redTipRet.redTipModels.add(redTipModel);
				redTipCache.update(redTipPojo);
			}
			
			//推送单个红点
		}
	}
	
	/**
	 * 删除红点
	 * @param playerId
	 * @param req
	 */
	public void delRedTip(long playerId,C2SDelRedTip req) {
		
	}
	
	/**
	 * 初始化的时候。获取所有红点
	 * @param playerId
	 */
	public void getAllRedTip(long playerId) {
		
	}

	@Override
	public void doEvent(Object object) {
		// TODO Auto-generated method stub
		//推送玩家大厅数据
	}

	
}
