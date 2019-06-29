package com.gproject.gate.service.item.consumehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gproject.gate.cache.AttarCache;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
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
		AttarPojo attarPojo=attarCache.getData(consumeOrder.playerId);
		Long curVal=attarPojo.attarRet.getVal(consumeOrder.itemId);
		if (curVal==null) {
			return false;
		}
		if (consumeOrder.consumeVal>curVal) {
			return false;
		}
		curVal-=consumeOrder.consumeVal;
		attarPojo.attarRet.attarMap.put(consumeOrder.itemId, curVal);
		attarCache.update(attarPojo);
		return true;
	}

	
}
