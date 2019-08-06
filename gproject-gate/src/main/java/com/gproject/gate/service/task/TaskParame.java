package com.gproject.gate.service.task;

/**
 * 完成活动，参数
 * @author YW1825
 *
 */
public class TaskParame {

	/**
	 * 任务类型
	 */
	public int formulaType;
	
	public long playerId;
	
	public long num;

	public int taskId;
	
	public TaskParame( long playerId, long num) {
		super();
		this.formulaType = FormulaType.sum;
		this.playerId = playerId;
		this.num = num;
	}
	
	
}
