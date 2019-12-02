package com.gproject.gate.cache;

import org.springframework.stereotype.Repository;

import com.alicp.jetcache.anno.Cached;
import com.gproject.common.db.GPCache;
import com.gproject.gate.pojo.CardTableDef.CardRet;

//数据DAO
@Repository
public class CardCache extends GPCache<CardRet> {



	@Cached
	@Override
	public CardRet  getPojo(Object id) {
		// TODO Auto-generated method stub
		return (CardRet) super.getPojo(id);
	}
}
