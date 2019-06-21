package com.gproject.gate.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gproject.common.db.GPCache;
import com.gproject.gate.dao.CardDAO;
import com.gproject.gate.pojo.AttarTableDef.AttarPojo;
import com.gproject.gate.pojo.BagTableDef.BagPojo;
import com.gproject.gate.pojo.CardTableDef.CardPojo;

//数据DAO
@Repository
public class CardCache extends GPCache<CardPojo, Long> {

	// @Autowired
	CardDAO playerRoleRepository;

	@Override
	public CrudRepository<CardPojo, Long> getCrudRepository() {
		// TODO Auto-generated method stub
		return playerRoleRepository;
	}

}
