package com.gproject.gate.service.item.hander;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.gate.cache.BagCache;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.BagTableDef.BagRet;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemOrder;
import com.gproject.gate.service.item.ItemDef.ItemHandlerType;


@Component(ItemDef.ITEM_HANDLER+ItemHandlerType.bag)
public class BagHandler implements AddItemHandler{

	
	@Autowired
	BagCache bagDAO;
	
	final static int MAX_NUM=99999999; 
	
	@Autowired
	ExcelService excelService;
	
	int max_bag=200;
	
	
	/**
	 * 处理无属性 背包物品
	 * @param addItemOrder
	 */
	private void doUnHasAttar(AddItemOrder addItemOrder) {
		BagPojo bagPojo=bagDAO.getData(addItemOrder.playerId);
		BagRet bagRet=bagPojo.bagRet;
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
		Long curVal=bagRet.unAttarMap.get(hItemConfig.itemId);
		if (curVal==null) {
			curVal=(long) 0;
		}
		long max=hItemConfig.maxNum;
		if (0>=max) {
			max=MAX_NUM;
		}
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
		bagRet.unAttarMap.put(hItemConfig.itemId, curVal);
		bagDAO.update(bagPojo);
	}
	
	@Override
	public synchronized void add(AddItemOrder addItemOrder) {
		// TODO Auto-generated method stub
		BagRet bagRet=bagDAO.getData(addItemOrder.playerId).bagRet;
		//检查背包是否满了
		if (bagRet.attarModels.size()+bagRet.unAttarMap.size()>max_bag) {
			return;
		}
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, addItemOrder.itemId);
		if (hItemConfig.hasAttar) {
			//处理有属性，需要根据实际游戏处理
		}else {
			this.doUnHasAttar(addItemOrder);
		}
	}

	

	

}