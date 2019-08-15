package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.BagDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.BagTableDef.BagRet;

//数据DAO
@Repository
public class BagCache extends GPCache<BagPojo, BagRet> {

	@Autowired
	BagDAO playerRoleRepository;

	@Override
	public CrudRepository<BagPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public BagPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}
