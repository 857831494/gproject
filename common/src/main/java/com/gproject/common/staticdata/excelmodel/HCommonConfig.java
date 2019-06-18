package com.gproject.common.staticdata.excelmodel;

import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;

public class HCommonConfig implements ConfigExcel {

	public int id;
	
	public String val;
	
	public int getInt() {
		return Integer.parseInt(this.val);
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
