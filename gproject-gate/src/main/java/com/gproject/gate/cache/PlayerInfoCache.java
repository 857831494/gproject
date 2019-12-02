package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.PlayerInfoTableDef.PlayerInfo;

//数据DAO
@Repository
public class PlayerInfoCache extends GPCache<PlayerInfo> {

	
	@Override
	@Cached
	public PlayerInfo getPojo(Object id) {
		// TODO Auto-generated method stub
		return (PlayerInfo) super.getPojo(id);
	}
	
	@Override
	public boolean needLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
