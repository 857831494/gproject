package com.gproject.gate.service.item.consumehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gproject.gate.cache.AttarCache;
import com.gproject.gate.cache.BagCache;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagModel;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.BagTableDef.BagRet;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.ConsumeItemHandler;
import com.gproject.gate.service.item.model.ConsumeOrder;

/**
 * 消耗背包无属性道具
 * @author YW1825
 *
 */
@Component(ItemDef.CONSUME_ITEM_HANDLER+ItemDef.ConsumeHandlerType.bag_common)
public class ConsumeNotHasAttarBagHandler implements ConsumeItemHandler{

	@Autowired
	BagCache bagCache;
	
	
	@Override
	public boolean consume(ConsumeOrder consumeOrder) {
		BagPojo attarPojo=bagCache.getPojo(consumeOrder.playerId);
		BagRet bagRet=attarPojo.getLogicObj();
		BagModel bagModel=bagRet.bagMap.get(consumeOrder.bagModelId);
		if (bagModel==null) {
			return false;
		}
		if (consumeOrder.consumeVal>bagModel.num) {
			return false;
		}
		bagModel.num-=consumeOrder.consumeVal;
		if (0>=bagModel.num) {
			bagRet.bagMap.remove(consumeOrder.bagModelId);
		}
		bagCache.update(attarPojo);
		return true;
	}

	
}
