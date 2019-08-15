package com.gproject.gate.service.card;

import org.springframework.stereotype.Component;

import com.gproject.gate.service.card.CardTypeDef.CardDailyTask;

@Component(CardTypeDef.PACK_NAME+CardTypeDef.MONTH_CARD_ID)
public class MonthCard_DailyTask implements CardDailyTask{

	@Override
	public void doLogic(long playerId, long execTime) {
		// TODO Auto-generated method stub
		
	}

}
