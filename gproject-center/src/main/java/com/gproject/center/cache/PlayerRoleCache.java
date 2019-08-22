package com.gproject.center.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.center.dao.PlayerRoleDAO;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRoleRet;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole_Table;
import com.gproject.common.db.AbstratorDBTable;
import com.gproject.common.db.GPCache;
import com.gproject.common.utils.IDef.InitParame;

@Repository
public class PlayerRoleCache extends GPCache<PlayerRole_Table, PlayerRoleRet> {

	@Autowired
	PlayerRoleDAO playerRoleRepository;
	
	

	@Override
	public CrudRepository<PlayerRole_Table, String> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

	@Override
	@Cached
	public PlayerRole_Table getPojo(Object id) {
		// TODO Auto-generated method stub
		return super.getPojo(id);
	}
}