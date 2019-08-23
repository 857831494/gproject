package com.gproject.common.staticdata.excelmodel;

import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;

public interface RoomDef {

	public interface RoomType{
		
	}
	
	public class HRoom implements ConfigExcel{

		public int Id;
		
		public int isAlone;
		
		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
