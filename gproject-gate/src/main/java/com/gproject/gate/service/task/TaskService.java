package com.gproject.gate.service.task;

import static org.hamcrest.CoreMatchers.both;
import static org.junit.Assert.fail;

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
import com.gproject.common.staticdata.excelmodel.TaskDef.TaskType;
import com.gproject.common.utils.date.DateUtils;
import com.gproject.gate.cache.TaskCache;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEvent;
import com.gproject.gate.event.player.PlayerEnterEventDef.PlayerEnterEventParame;
import com.gproject.gate.pojo.TaskTableDef.TaskModel;
import com.gproject.gate.pojo.TaskTableDef.TaskPojo;
import com.gproject.gate.pojo.TaskTableDef.TaskRet;
import com.gproject.gate.service.task.TaskDef.CreateTask;
import com.gproject.gate.service.task.TaskDef.TaskParame;

@Service
public class TaskService implements PlayerEnterEvent{

	
	Logger logger=LoggerFactory.getLogger(TaskService.class);
	
	@Autowired
	ApplicationContext applicationContext;
	
	
	@Autowired
	TaskCache taskCache;
	
	@Autowired
	LockService lockService;
	
	@Autowired
	ExcelService excelService;
	
	
	
	
	private List<HTask> geTasks(int taskType) {
		List<HTask> list=excelService.getAll(HTask.class);
		List<HTask> reTasks=new ArrayList<>();
		list.forEach(task->{
			if (task.TaskType==taskType) {
				reTasks.add(task);
			}
		});
		return list;
	}
	
	/**
	 * 发布一个任务
	 * @param playerId
	 * @param taskData
	 */
	public void pub_task(long playerId,Task_LogicData taskData) {
		List<TaskParame> taskParames=new ArrayList<>();
		applicationContext.getBeansOfType(CreateTask.class).values().forEach((createtask)->{
			taskParames.addAll(createtask.doLogic(taskData));
		});
		this.doTask(taskParames, playerId);
	}
	
	/**
	 * 在埋点处调用
	 * @param parame
	 */
	private void doTask(List<TaskParame> tasks,long playerId) {
		//推送列表，此类型任务刷新一下
		List<TaskModel> pushList=new ArrayList<>();
		synchronized (lockService.getLock(playerId)) {
			
			
			TaskPojo taskPojo=taskCache.getPojo(playerId);
			TaskRet taskRet=taskPojo.getLogicObj();
			
			tasks.forEach((task)->{
				this.finishTask(task);
				pushList.add(taskRet.taskMap.get(task.taskId));
			});
		}
		//推送，状态变化
	}
	
	/**
	 * 完成了一个任务
	 * @param parame
	 */
	private boolean finishTask(TaskParame parame) {
		TaskPojo taskPojo=taskCache.getPojo(parame.playerId);
		TaskRet taskRet=taskPojo.getLogicObj();
		TaskModel taskModel=taskRet.taskMap.get(parame.taskId);
		if (null!=taskModel) {
			if (taskModel.code==StatusCode.finish) {
				return false;
			}
		}
		HTask hTask=excelService.getById(HTask.class, parame.taskId);
		TaskModel newModel=new TaskModel(parame.taskId, StatusCode.proccess,0);
		TaskModel retModel=taskRet.taskMap.putIfAbsent(parame.taskId,
				newModel);
		if (retModel.num+parame.num>Integer.MAX_VALUE) {
			retModel.num=Integer.MAX_VALUE;
		}else {
			retModel.num+=parame.num;
		}
		if (retModel.num>=hTask.num) {
			retModel.code=StatusCode.finish;
		}
		taskCache.update(taskPojo);
		return false;
	}

	@Override
	public void doEvent(Object object) {
		// TODO Auto-generated method stub
		PlayerEnterEventParame playerEnterEventParame=(PlayerEnterEventParame) object;
		TaskPojo taskPojo=taskCache.getPojo(playerEnterEventParame.playerId);
		TaskRet taskRet=taskPojo.getLogicObj();
		List<HTask> all_taskList=excelService.getAll(HTask.class);
		boolean update=false;
		for (HTask task : all_taskList) {
			if (taskRet.taskMap.containsKey(task.TaskId)) {
				TaskModel taskModel=new TaskModel(task.TaskId, StatusCode.proccess, 0);
				taskRet.taskMap.put(task.TaskId, taskModel);
				update=true;
			}
		}
		
		if (!DateUtils.isSameDate(playerEnterEventParame.lastLoginTime)) {
			List<HTask> dailyList=this.geTasks(TaskType.Daily);
			for (HTask task : dailyList) {
				TaskModel taskModel=taskRet.taskMap.get(task.TaskId);
				taskModel.code=StatusCode.proccess;
				taskModel.num=0;
				update=true;
			}
		}
		taskCache.update(taskPojo);
	}

	
}
