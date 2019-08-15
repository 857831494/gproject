package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.CardDAO;
import com.gproject.gate.dao.RedTipDAO;
import com.gproject.gate.dao.TaskDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipPojo;
import com.gproject.gate.pojo.TaskTableDef.TaskPojo;
import com.gproject.gate.pojo.TaskTableDef.TaskRet;

//数据DAO
@Repository
public class TaskCache extends GPCache<TaskPojo, TaskRet> {

	@Autowired
	TaskDAO playerRoleRepository;

	@Override
	public CrudRepository<TaskPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public TaskPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}
