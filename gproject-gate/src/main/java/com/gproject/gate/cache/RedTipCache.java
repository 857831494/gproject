package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.CardDAO;
import com.gproject.gate.dao.RedTipDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.CardTableDef.CardPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipPojo;
import com.gproject.gate.pojo.RedTipTableDef.RedTipRet;

//数据DAO
@Repository
public class RedTipCache extends GPCache<RedTipPojo, RedTipRet> {

	@Autowired
	RedTipDAO playerRoleRepository;

	@Override
	public CrudRepository<RedTipPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public  RedTipPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
	
	@Override
	public boolean needLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
