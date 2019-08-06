package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;

import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.MailTableDef.MailPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipPojo;
import com.gproject.gate.pojo.TaskTableDef.TaskPojo;

public interface TaskDAO extends CrudRepository<TaskPojo,Long>{

}
