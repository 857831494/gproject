package com.gproject.center.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.center.cache.PlayerRoleCache;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole;
import com.gproject.center.pojo.PlayerRole_Table_DEF.PlayerRole_Table;



@Service
public class PlayerInitService {

	@Autowired
	PlayerRoleCache playerRoleDAO;
	
	
}
