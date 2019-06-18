package com.gproject.common.db;

public interface DBEvent {

	/**
	 * 在查询数据库之后
	 */
	void initAfterQueryDB();
	
	/**
	 * 在数据保存之前
	 */
	void beforeSaveDB();
	
	void setID(Object ID);
}
