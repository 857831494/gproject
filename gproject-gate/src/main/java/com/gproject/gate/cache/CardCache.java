package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.CardDAO;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.CardTableDef.CardRet;

//数据DAO
@Repository
public class CardCache extends GPCache<CardPojo, CardRet> {

	@Autowired
	CardDAO playerRoleRepository;

	@Override
	public CrudRepository<CardPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Cached
	@Override
	public CardPojo  getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}
