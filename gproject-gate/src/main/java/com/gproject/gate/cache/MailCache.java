package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.MailTableDef.MailRet;

//数据DAO
@Repository
public class MailCache extends GPCache<MailRet> {

	
	@Override
	@Cached
	public MailRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (MailRet) super.getPojo(id);
	}
}
