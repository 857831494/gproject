package com.gproject.center.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRoleRet;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole_Table;

public interface PlayerRoleDAO extends CrudRepository<PlayerRole_Table,String>{

}
