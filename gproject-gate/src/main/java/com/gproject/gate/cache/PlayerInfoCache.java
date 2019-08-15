package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.PlayerInfoDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfo;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfoPojo;

//数据DAO
@Repository
public class PlayerInfoCache extends GPCache<PlayerInfoPojo, PlayerInfo> {

	@Autowired
	PlayerInfoDAO playerRoleRepository;

	@Override
	public CrudRepository<PlayerInfoPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public PlayerInfoPojo getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
	
	@Override
	public boolean needLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
