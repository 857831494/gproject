package com.gproject.gate.cache;

import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.CDTableDef.CDNumRet;

//数据DAO
@Repository
public class CDNumCache extends GPCache<CDNumRet> {

	
	@Override
	@Cached
	public CDNumRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (CDNumRet) super.getPojo(id);
	}
	
	@Override
	public boolean needLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
