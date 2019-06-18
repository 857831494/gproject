package com.gproject.common.staticdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gproject.common.IDef.IAPPInit;
import com.gproject.common.IDef.InitParame;

public interface StaticDataDef {

	public final static String TX_CDN = "tx";

	final static String RES_URL="resUrl";
	
	
	

	final static String INT_TYPE = "int";

	final static String STRING_TYPE = "string";

	final static String BOOL_TYPE = "bool";

	final static String DATE_TYPE = "date";

	

	public interface ConfigExcel {
		int getId() ;
	}

	public class SheetData {

		public String fileName;
		
		public List<Map> dataMap=new ArrayList<>();
	}
	
	
	public class SheetField {

		public String type;
		
		public String nameString;
	}

}
