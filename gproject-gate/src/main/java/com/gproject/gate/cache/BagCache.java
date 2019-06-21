package com.gproject.gate.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.BagDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;

//数据DAO
@Repository
public class BagCache extends GPCache<BagPojo, Long> {

	// @Autowired
	BagDAO playerRoleRepository;

	@Override
	public CrudRepository<BagPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
