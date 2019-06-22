package com.gproject.gate.service.item.hander;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.gate.cache.AttarCache;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.AttarTableDef.AttarRet;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemHandlerType;
import com.gproject.gate.service.item.model.AddItemOrder;

@Component(ItemDef.ITEM_HANDLER+AddItemHandlerType.attar)
public class AttarAddHandler implements AddItemHandler{

	
	@Autowired
	AttarCache attarDAO;
	
	
	@Autowired
	ExcelService excelService;
	
	@Override
	public synchronized void add(AddItemOrder addItemOrder) {
		// TODO Auto-generated method stub
		AttarPojo attarPojo=attarDAO.getData(addItemOrder.playerId);
		AttarRet attarRet=attarPojo.attarRet;
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
		Long curVal=attarRet.attarMap.get(hItemConfig.itemId);
		if (curVal==null) {
			curVal=(long) 0;
		}
		long max=hItemConfig.getMaxNum();
		
		if (curVal>=max) {
			addItemOrder.successVal=0;
			addItemOrder.lastVal=max;
			return;
		}
		if (curVal+addItemOrder.addVal>max) {
			addItemOrder.successVal=max-curVal;
			addItemOrder.lastVal=max;
			curVal=max;
		}else {
			curVal+=addItemOrder.addVal;
			addItemOrder.successVal=addItemOrder.addVal;
			addItemOrder.lastVal=curVal;
		}
		attarRet.attarMap.put(addItemOrder.itemId, curVal);
		attarDAO.update(attarPojo);
	}

	

	

}
