package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;

import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;

public interface PlayerInfoDAO extends CrudRepository<PlayerInfoPojo,Long>{

}
