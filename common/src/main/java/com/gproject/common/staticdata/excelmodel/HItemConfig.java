package com.gproject.common.staticdata.excelmodel;

import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;

public class HItemConfig implements ConfigExcel{

	public int itemId;
	
	public int maxNum;
	
	/**
	 * 是否进入背包,抛错误码专用字段
	 */
	public int showWhere;
	
	/**
	 *加 处理器类型  
	 */
	public int addHandlerType;
	
	/**
	 * 是否有属性
	 */
	public boolean hasAttar=false;
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return itemId;
	}

}