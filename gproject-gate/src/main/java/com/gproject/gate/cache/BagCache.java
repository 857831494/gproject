package com.gproject.gate.cache;

import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.BagTableDef.BagRet;

//数据DAO
@Repository
public class BagCache extends GPCache< BagRet> {


	@Override
	@Cached
	public BagRet getPojo(Object id) {
		// TODO Auto-generated method stub
		return (BagRet) super.getPojo(id);
	}
}
