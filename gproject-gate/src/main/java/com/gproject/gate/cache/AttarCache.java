package com.gproject.gate.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.AttarDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;

//数据DAO
@Repository
public class AttarCache extends GPCache<AttarPojo, Long> {

	// @Autowired
	AttarDAO playerRoleRepository;

	@Override
	public CrudRepository<AttarPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
