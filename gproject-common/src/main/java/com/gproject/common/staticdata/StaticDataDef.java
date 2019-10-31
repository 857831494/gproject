package com.gproject.common.staticdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;

public interface StaticDataDef {

	public final static String TX_CDN = "tx";

	final static String RES_URL="resUrl";
	
	
	public final static String JSON_PATH = "json";

	public final static String FILE_PATH = "file";

	final static int SHEET_LEN=2;
	
	final static String INT_TYPE = "int";

	final static String STRING_TYPE = "string";

	final static String BOOL_TYPE = "bool";

	final static String DATE_TYPE = "date";

	

	public abstract class ConfigExcel {
		public abstract int getId() ;
		public void afterLoad() {
			
		}
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
