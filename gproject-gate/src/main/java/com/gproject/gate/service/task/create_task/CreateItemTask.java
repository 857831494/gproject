package com.gproject.gate.service.task.create_task;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gproject.gate.service.task.TaskDef.CreateTask;
import com.gproject.gate.service.task.TaskDef.TaskParame;
import com.gproject.gate.service.task.Task_LogicData;

@Component
public class CreateItemTask implements CreateTask{

	@Override
	public List<TaskParame> doLogic(Task_LogicData data) {
		// TODO Auto-generated method stub
		//创建，物品类任务,可以生成金币消费，获得，等等，开发人员，需要根据自己的要求开发
		return null;
	}

}
