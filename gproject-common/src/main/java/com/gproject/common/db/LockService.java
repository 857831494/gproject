package com.gproject.common.db;

import org.apache.commons.lang3.StringUtils;

import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;


public abstract class LockService implements IAPPInit{

	/**
	 * 最大锁数量
	 */
	final int MAX_LOCK_NUM = 1000;
	
	Integer[] lockArray=new Integer[MAX_LOCK_NUM];
	
	@Override
	public void init(InitParame initParame) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < lockArray.length; i++) {
			lockArray[i]=i+1;
		}
	}

	public Integer getLock(Object id) {
		if (id instanceof Long) {
			return this.getLongLockBy( (long) id);
		}
		if (id instanceof String) {
			return this.getStrLockBy((String) id);
		}
		return null;
	}
	
	/**
	 * 获取锁
	 * @param key
	 * @return
	 */
	public Integer getStrLockBy(String key) {
		int size=0;
		if (!StringUtils.isBlank(key)) {
			size=key.length();
		}
		int index=size/MAX_LOCK_NUM;
		return lockArray[index];
	}
	
	/**
	 * 获取锁
	 * @param key
	 * @return
	 */
	public Integer getLongLockBy(long key) {
		
		int index=(int) (key/MAX_LOCK_NUM);
		return lockArray[index];
	}
}
