package com.gproject.gate.service.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gproject.common.db.LockService;
import com.gproject.common.dto.proto.StatusDTO.StatusCode;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.TaskDef.HTask;
import com.gproject.gate.cache.TaskCache;
import com.gproject.gate.pojo.TaskTableDef.TaskModel;
import com.gproject.gate.pojo.TaskTableDef.TaskPojo;
import com.gproject.gate.pojo.TaskTableDef.TaskRet;
import com.gproject.gate.service.ThreadPoolService;
import com.gproject.gate.service.task.FormulaType.FormulaHandler;

@Service
public class TaskService {

	public final static String PACK_NAME="com.gproject.gate.service.activities.formula";
	
	Logger logger=LoggerFactory.getLogger(TaskService.class);
	
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	ThreadPoolService threadPoolService;
	
	@Autowired
	TaskCache taskCache;
	
	@Autowired
	LockService lockService;
	
	@Autowired
	ExcelService excelService;
	
	/**
	 * 在埋点处调用
	 * @param parame
	 */
	public void doTask(TaskParame parame) {
		synchronized (lockService.getLockBy(parame.playerId)) {
			List<HTask> list=excelService.getAll(HTask.class);
			//推送列表，此类型任务刷新一下
			List<TaskModel> pushList=new ArrayList<>();
			TaskPojo taskPojo=taskCache.getData(parame.playerId);
			TaskRet taskRet=taskPojo.taskRet;
			
			list.forEach((task)->{
				if (parame.formulaType==task.formulaType) {
					parame.taskId=task.TaskId;
					this.finishTask(parame);
					pushList.add(taskRet.taskMap.get(task.TaskId));
				}
			});
		}
	}
	
	/**
	 * 完成了一个任务
	 * @param parame
	 */
	private boolean finishTask(TaskParame parame) {
		TaskPojo taskPojo=taskCache.getData(parame.playerId);
		TaskRet taskRet=taskPojo.taskRet;
		TaskModel taskModel=taskRet.taskMap.get(parame.taskId);
		if (null!=taskModel) {
			if (taskModel.code==StatusCode.finish) {
				return false;
			}
		}
		HTask hTask=excelService.getById(HTask.class, parame.taskId);
		FormulaHandler handler=(FormulaHandler) applicationContext.getBean(
				PACK_NAME+hTask.formulaType);
		boolean ret=handler.checkCondition(parame);
		if (ret) {
			taskModel.code=StatusCode.finish;
			return true;
		}
		taskCache.update(taskPojo);
		return false;
	}
}
