package com.gproject.common.staticdata.excelmodel;

import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;

public interface CommonConfigDef{
	
	/**
	 * 游戏配置文件主键，按照自增方式，不要采用分段模式
	 * @author YW1825
	 *
	 */
	public interface HCommonConfigID{
		int client_version=1;
	}
	
	public class HCommonConfig implements ConfigExcel {

		public int id;
		
		public String val;
		
		public String remark;
		
		public int getInt() {
			return Integer.parseInt(this.val);
		}
		
		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		
	}
}


