package com.gproject.common.staticdata.excelmodel;

public interface TaskDef {

	public interface TaskType{
		int Daily=1;//日常活动，每天重置
		int Achievement=2;
	}
	
	
	
	public class HTask{
		public int TaskId;//活动id
		public int TaskType;//活动类型
		public int[] parames;//参数
		public int formulaType;//公式类型id
	}
}
