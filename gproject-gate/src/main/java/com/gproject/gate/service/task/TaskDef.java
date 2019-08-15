package com.gproject.gate.service.task;

import java.util.List;

/**
 * 完成活动，参数
 * @author YW1825
 *
 */
public interface TaskDef{
	
	public interface CreateTask{
		public List<TaskParame> doLogic(Task_LogicData data);
	}
	
	public class TaskParame {

		
		public long playerId;
		
		/**
		 * 累计数值,由任务模块自己生成
		 */
		public long num;

		public int taskId;
		
		public TaskParame( long playerId, long num) {
			this.playerId = playerId;
			this.num = num;
		}
		
		
	}
}

