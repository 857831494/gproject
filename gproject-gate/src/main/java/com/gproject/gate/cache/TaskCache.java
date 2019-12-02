package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.TaskTableDef.TaskRet;

//数据DAO
@Repository
public class TaskCache extends GPCache<TaskRet> {


	@Override
	@Cached
	public TaskRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (TaskRet) super.getPojo(id);
	}
}
