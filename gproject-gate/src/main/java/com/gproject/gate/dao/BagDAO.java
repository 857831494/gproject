package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;

//数据DAO
@Repository
public class BagDAO extends GPDAO<BagPojo, Long> {

	// @Autowired
	BagRepository playerRoleRepository;

	@Override
	public CrudRepository<BagPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
