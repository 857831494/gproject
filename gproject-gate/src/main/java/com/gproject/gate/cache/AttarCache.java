package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.AttarDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.AttarTableDef.AttarRet;

//数据DAO
@Repository
public class AttarCache extends GPCache<AttarPojo, AttarRet> {

	@Autowired
	AttarDAO playerRoleRepository;

	@Override
	public CrudRepository<AttarPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public AttarPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}
