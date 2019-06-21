package com.gproject.center.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.center.dao.PlayerRoleDAO;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole_Table;
import com.gproject.common.IDef.InitParame;
import com.gproject.common.db.GPCache;

//数据DAO
@Repository
public class PlayerRoleCache extends GPCache<PlayerRole_Table, String> {

	//@Autowired
	PlayerRoleDAO playerRoleRepository;
	
	

	@Override
	public CrudRepository<PlayerRole_Table, String> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

}