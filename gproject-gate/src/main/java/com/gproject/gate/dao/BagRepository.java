package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;

import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;

public interface BagRepository extends CrudRepository<BagPojo,Long>{

}
