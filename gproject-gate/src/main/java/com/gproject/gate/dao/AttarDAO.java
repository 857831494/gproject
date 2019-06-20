package com.gproject.gate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;

//数据DAO
@Repository
public class AttarDAO extends GPDAO<AttarPojo, Long> {

	// @Autowired
	AttarRepository playerRoleRepository;

	@Override
	public CrudRepository<AttarPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
