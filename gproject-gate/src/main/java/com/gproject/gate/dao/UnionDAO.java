package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;

import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.UnionTableDef.UnionPojo;

public interface UnionDAO extends CrudRepository<UnionPojo,Long>{

}
