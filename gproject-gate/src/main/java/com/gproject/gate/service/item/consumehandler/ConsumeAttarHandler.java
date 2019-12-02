package com.gproject.gate.service.item.consumehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gproject.gate.cache.AttarCache;
import com.gproject.gate.pojo.AttarRet;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.ConsumeItemHandler;
import com.gproject.gate.service.item.model.ConsumeOrder;

/**
 * 消耗属性，类型，金币
 * @author YW1825
 *
 */
@Component(ItemDef.CONSUME_ITEM_HANDLER+ItemDef.ConsumeHandlerType.attar)
public class ConsumeAttarHandler implements ConsumeItemHandler{

	@Autowired
	AttarCache attarCache;
	
	
	@Override
	public boolean consume(ConsumeOrder consumeOrder) {
		AttarRet attarRet=attarCache.getPojo(consumeOrder.playerId);
		
		Long curVal=attarRet.getVal(consumeOrder.itemId);
		if (curVal==null) {
			return false;
		}
		if (consumeOrder.consumeVal>curVal) {
			return false;
		}
		curVal-=consumeOrder.consumeVal;
		attarRet.attarMap.put(consumeOrder.itemId, curVal);
		attarCache.update(attarRet,consumeOrder.playerId);
		return true;
	}

	
}
