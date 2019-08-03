package com.gproject.gate.service.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.TipDTO.TipCode;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.common.utils.common.GErrorException;
import com.gproject.gate.event.player.PlayerItemEventDef.PlayerConsumeItemEvent;
import com.gproject.gate.event.player.PlayerItemEventDef.PlayerItemEventParame;
import com.gproject.gate.service.item.ItemDef.ConsumeItemHandler;
import com.gproject.gate.service.item.model.ConsumeOrder;

@Service
public class ConsumeItemService {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	ApplicationContext applicationContext;
	/**
	 * 消耗道具，或者金币，
	 * @param consumeOrder
	 */
	public void consumeAttar(ConsumeOrder consumeOrder) {
		//先找属性
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, consumeOrder.itemId);
		ConsumeItemHandler consumeItemHandler=(ConsumeItemHandler) applicationContext.getBean(ItemDef.
				CONSUME_ITEM_HANDLER+hItemConfig.consumeHandlerType);
		if (!consumeItemHandler.consume(consumeOrder)) {
			//数量不足
			throw new GErrorException(TipCode.Fail);
		}
		//物品事件变化通知
		Collection<PlayerConsumeItemEvent> lConsumeItemEvents=applicationContext.
				getBeansOfType(PlayerConsumeItemEvent.class).values();
		PlayerItemEventParame playerItemEventParame=new PlayerItemEventParame(consumeOrder.itemId,
				consumeOrder.consumeVal, consumeOrder.playerId);
		List<PlayerItemEventParame> listParames=new ArrayList<>();
		lConsumeItemEvents.forEach(event->{
			event.doEvent(listParames);
		});
	}
	
	/**
	 * 消耗道具，或者金币，
	 * @param consumeOrder
	 */
	public void consumeAttar(List<ConsumeOrder> consumeOrders) {
		
	}
}
