package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.gproject.common.db.AbstractorLogicRet;
import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.common.utils.common.JSONUtil;

public interface TaskTableDef {

	public class TaskModel {
		public int taskId;
		public StatusCode code;
		public long num;
		public TaskModel(int taskId, StatusCode code, long num) {
			super();
			this.taskId = taskId;
			this.code = code;
			this.num = num;
		}
		public TaskModel() {
			super();
		}
		
	}

	public class TaskRet extends AbstractorLogicRet{
		public Map<Integer, TaskModel> taskMap=new HashMap<>();
		
		
	}

	
}
