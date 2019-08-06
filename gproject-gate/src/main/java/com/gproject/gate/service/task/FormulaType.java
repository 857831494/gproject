package com.gproject.gate.service.task;

/**
 * 公式类型
 * @author YW1825
 *
 */
public interface FormulaType{
	
	public interface FormulaHandler{
		public boolean checkCondition(TaskParame taskParame) ;
	}
	
	int sum=1;//累计类型
}