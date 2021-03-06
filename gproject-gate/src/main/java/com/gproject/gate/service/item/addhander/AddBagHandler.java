package com.gproject.gate.service.item.addhander;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HItemConfig;
import com.gproject.gate.cache.BagCache;
import com.gproject.gate.pojo.BagTableDef.BagModel;
import com.gproject.gate.pojo.BagTableDef.BagRet;
import com.gproject.gate.service.item.ItemDef;
import com.gproject.gate.service.item.ItemDef.AddItemHandler;
import com.gproject.gate.service.item.ItemDef.AddItemHandlerType;
import com.gproject.gate.service.item.model.AddItemOrder;


@Component(ItemDef.ADD_ITEM_HANDLER+AddItemHandlerType.bag)
public class AddBagHandler implements AddItemHandler{

	
	@Autowired
	BagCache bagCache;
	
	final static int MAX_NUM=99999999; 
	
	@Autowired
	ExcelService excelService;
	
	int max_bag=200;
	
	
	private BagModel getBagModel(AddItemOrder addItemOrder) {
		BagRet bagRet=bagCache.getPojo(addItemOrder.playerId);
		
		for (BagModel bagModel : bagRet.bagMap.values()) {
			if (bagModel.itemId==addItemOrder.itemId) {
				return bagModel;
			}
		}
		return null;
	}
	
	/**
	 * 处理无属性 背包物品
	 * @param addItemOrder
	 */
	private void doUnHasAttar(AddItemOrder addItemOrder) {
		BagRet bagRet=bagCache.getPojo(addItemOrder.playerId);
		
		HItemConfig hItemConfig=excelService.getById(HItemConfig.class, 
				addItemOrder.itemId);
		BagModel bagModel=getBagModel(addItemOrder);
		if (bagModel==null) {
			bagRet.curId++;
			bagModel=new BagModel(addItemOrder.itemId, bagRet.curId);
			bagRet.bagMap.put(bagModel.bagId, bagModel);
		}
		long curVal=bagModel.num;
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
		bagModel.attarMap=addItemOrder.attarMap;
		addItemOrder.bagModelId=bagModel.bagId;
		bagCache.update(bagRet,addItemOrder.playerId);
	}
	
	@Override
	public synchronized void add(AddItemOrder addItemOrder) {
		BagRet bagRet=bagCache.getPojo(addItemOrder.playerId);
		// TODO Auto-generated method stub
		
		//检查背包是否满了
		if (bagRet.bagMap.size()>max_bag) {
			return;
		}
		//检查是否有属性
		//可以检查订单，也可以通过配置表，根据实际项目
		doUnHasAttar(addItemOrder);
	}

	

	

	

}