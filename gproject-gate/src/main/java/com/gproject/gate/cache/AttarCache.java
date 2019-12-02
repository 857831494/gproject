package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.AttarRet;

//数据DAO
@Repository
public class AttarCache extends GPCache<AttarRet> {

	
	@Override
	@Cached
	public AttarRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (AttarRet) super.getPojo(id);
	}
}
