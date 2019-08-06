package com.gproject.gate.service.task.formula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.TaskDef.HTask;
import com.gproject.gate.cache.TaskCache;
import com.gproject.gate.pojo.TaskTableDef.TaskModel;
import com.gproject.gate.pojo.TaskTableDef.TaskPojo;
import com.gproject.gate.service.task.FormulaType;
import com.gproject.gate.service.task.FormulaType.FormulaHandler;
import com.gproject.gate.service.task.TaskParame;
import com.gproject.gate.service.task.TaskService;

@Service(TaskService.PACK_NAME+FormulaType.sum)
public class SumFormula implements FormulaHandler{

	@Autowired
	TaskCache taskCache;
	
	@Autowired
	ExcelService excelService;
	
	@Override
	public boolean checkCondition(TaskParame taskParame) {
		// TODO Auto-generated method stub
		HTask hTask=excelService.getById(HTask.class, taskParame.taskId);
		TaskPojo taskPojo=taskCache.getData(taskParame.playerId);
		int target=hTask.parames[0];
		TaskModel taskModel=new TaskModel(taskParame.taskId, StatusCode.proccess,0);
		TaskModel retModel=taskPojo.taskRet.taskMap.putIfAbsent(taskParame.taskId,
				taskModel);
		if (retModel.num+taskParame.num>Integer.MAX_VALUE) {
			retModel.num=Integer.MAX_VALUE;
		}else {
			retModel.num+=taskParame.num;
		}
		return retModel.num>=target;
	}
	
}
