package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;

import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.CardTableDef.CardPojo;

public interface CardDAO extends CrudRepository<CardPojo,Long>{

}
