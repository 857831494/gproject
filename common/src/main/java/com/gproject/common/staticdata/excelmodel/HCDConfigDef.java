package com.gproject.common.staticdata.excelmodel;

public interface HCDConfigDef {

	
	/**
	 * cd 类型
	 * @author YW1825
	 *
	 */
	public interface CDType{
		int Daily=1;
		int Week=2;
		int Month=3;
	}
	
	public interface CDID{
		
	}
	/**
	 * cd 次数配置
	 * @author YW1825
	 *
	 */
	public class HCDConfig{
		public int cdType;
		public int cdId;
		public long max_num;
		/**
		 * 抛错误码
		 */
		public boolean showError;
	}
}
