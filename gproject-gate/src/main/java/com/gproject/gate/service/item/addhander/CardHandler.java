package com.gproject.gate.service.item.addhander;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gproject.common.utils.date.DateUtils;
import com.gproject.gate.cache.CardCache;
import com.gproject.gate.pojo.CardTableDef.CardModel;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.CardTableDef.CardRet;
import com.gproject.gate.service.card.CardTypeDef;
import com.gproject.gate.service.card.CardTypeDef.CardDailyTask;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemHandlerType;
import com.gproject.gate.service.item.model.AddItemOrder;

@Component(ItemDef.ADD_ITEM_HANDLER+AddItemHandlerType.card)
public class CardHandler implements AddItemHandler{

	@Autowired
	CardCache cardDAO;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public void add(AddItemOrder addItemOrder) {
		// TODO Auto-generated method stub
		CardPojo cardPojo=cardDAO.getPojo(addItemOrder.playerId);
		CardRet cardRet=cardPojo.getLogicObj();
		CardModel cardModel=cardRet.cardModels.get(addItemOrder.itemId);
		addItemOrder.successVal=addItemOrder.addVal;
		addItemOrder.canShow=false;
		//月卡类型，一样走物品变更，只是不需要客户端看见卡
		if (cardModel==null) {
			cardModel=new CardModel(addItemOrder.itemId,
					System.currentTimeMillis()+addItemOrder.expireTime);
			//一定要将到期，下一个开始时间写上
		}else {
			if (cardModel.expireTime>System.currentTimeMillis()) {
				cardModel.expireTime+=addItemOrder.expireTime;
				
			}else {
				cardModel.expireTime=System.currentTimeMillis()+addItemOrder.expireTime;
			}
		}
		if (cardModel.lastExceTime==0) {
			CardDailyTask cardDailyTask=(CardDailyTask) applicationContext.
					getBean(CardTypeDef.PACK_NAME+addItemOrder.itemId);
			cardDailyTask.doLogic(addItemOrder.playerId, System.currentTimeMillis());
			cardModel.lastExceTime=System.currentTimeMillis();
		}
		cardRet.cardModels.put(addItemOrder.itemId, cardModel);
		addItemOrder.lastVal=(cardModel.expireTime-System.currentTimeMillis())/1000;
		cardDAO.update(cardPojo);
	}
}