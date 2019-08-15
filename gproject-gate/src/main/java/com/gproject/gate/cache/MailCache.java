package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.MailDAO;
import com.gproject.gate.pojo.MailTableDef.MailPojo;
import com.gproject.gate.pojo.MailTableDef.MailRet;

//数据DAO
@Repository
public class MailCache extends GPCache<MailPojo, MailRet> {

	@Autowired
	MailDAO playerRoleRepository;

	@Override
	public CrudRepository<MailPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public MailPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}
