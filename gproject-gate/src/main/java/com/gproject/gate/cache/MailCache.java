package com.gproject.gate.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.MailDAO;
import com.gproject.gate.pojo.MailTableDef.MailPojo;

//数据DAO
@Repository
public class MailCache extends GPCache<MailPojo, Long> {

	// @Autowired
	MailDAO playerRoleRepository;

	@Override
	public CrudRepository<MailPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
