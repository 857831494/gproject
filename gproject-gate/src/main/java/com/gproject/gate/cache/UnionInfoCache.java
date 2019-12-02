package com.gproject.gate.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.BagTableDef.BagRet;
import com.gproject.gate.pojo.UnionTableDef.UnionRet;

//数据DAO
@Repository
public class UnionInfoCache extends GPCache<UnionRet> {

	

	@Override
	@Cached
	public UnionRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (UnionRet) super.getPojo(id);
	}
	
	@Override
	public boolean needLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
