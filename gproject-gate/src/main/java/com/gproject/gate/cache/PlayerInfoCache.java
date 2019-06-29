package com.gproject.gate.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;

//数据DAO
@Repository
public class PlayerInfoCache extends GPCache<PlayerInfoPojo, Long> {

	// @Autowired
	PlayerInfoCache playerRoleRepository;

	@Override
	public CrudRepository<PlayerInfoPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}