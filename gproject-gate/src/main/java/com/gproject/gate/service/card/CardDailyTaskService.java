package com.gproject.gate.service.card;

import java.util.Date;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.utils.date.DateUtils;
import com.gproject.gate.cache.CardCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.CardTableDef.CardModel;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.CardTableDef.CardRet;
import com.gproject.gate.service.card.CardTypeDef.CardDailyTask;


@Service
public class CardDailyTaskService implements PlayerEnterEvent{

	@Autowired
	CardCache cardCache;
	
	@Autowired
	ApplicationContext applicationContext;
	
	

	private void doCardTask(CardModel cardModel,int cardId,long playerId) {
		if (DateUtils.isSameDate(new Date(cardModel.lastExceTime))) {
			return;
		}
		
		int day=0;
		CardDailyTask cardDailyTask=(CardDailyTask) applicationContext.getBean(CardTypeDef.PACK_NAME+cardId);
		if (System.currentTimeMillis()>cardModel.expireTime) {
			//过期
			day=DateUtils.calc2DateTDOADays(new Date(cardModel.lastExceTime), new Date(cardModel.expireTime));
		}else {
			day=DateUtils.calc2DateTDOADays(new Date(cardModel.lastExceTime), new Date());
		}
		if (day==0) {
			return;
		}
		for (int i = 0; i < day; i++) {
			Date date=org.apache.commons.lang3.time.DateUtils.addDays(new Date(cardModel.lastExceTime), i+1);
			cardDailyTask.doLogic(playerId,DateUtils.getDate0AM(date).getTime());
		}
	}

	@Override
	public void doPlayerEnterEvent(PlayerEnterEventParame playerEnterEventParame) {
		// TODO Auto-generated method stub
		//PlayerEnterEventParame playerEnterEventParame=(PlayerEnterEventParame) object;
		CardPojo cardPojo=cardCache.getPojo(playerEnterEventParame.playerId);
		CardRet cardRet=cardPojo.getLogicObj();
		for (Entry<Integer, CardModel>	cardModel:cardRet.cardModels.entrySet()) {
			
		}
	}
}
