package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.AbstratorDBTable;
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

	public class TaskRet {
		public Map<Integer, TaskModel> taskMap=new HashMap<>();
		
		
	}

	// 物理表
	@Entity(name = "tb_task")
	public class TaskPojo extends AbstratorDBTable {
		@Id
		public long playerId;

		@Column(columnDefinition = "text")
		String logicData;

		@Override
		public void setLogicDataStr(String logicData) {
			// TODO Auto-generated method stub
			this.logicData=logicData;
		}

		@Override
		public String getLogicDataStr() {
			// TODO Auto-generated method stub
			return logicData;
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return playerId;
		}

		
	}
}
