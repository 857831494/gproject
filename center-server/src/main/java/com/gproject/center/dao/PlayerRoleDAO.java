package com.gproject.center.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole_Table;
import com.gproject.common.IDef.InitParame;
import com.gproject.db.GPDAO;

//数据DAO
@Repository
public class PlayerRoleDAO extends GPDAO<PlayerRole_Table, String> {

	//@Autowired
	PlayerRoleRepository playerRoleRepository;
	
	

	@Override
	public CrudRepository<PlayerRole_Table, String> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

}