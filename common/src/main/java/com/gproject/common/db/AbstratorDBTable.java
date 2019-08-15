package com.gproject.common.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.context.ApplicationContext;

import com.gproject.common.utils.date.DateUtils;

public abstract class AbstratorDBTable {

//	@Column(columnDefinition = "text")
//	String logicData;
//	

	

	public abstract void setLogicDataStr(String logicData) ;

	public abstract String getLogicDataStr();
	
	

	/**
	 * 临时对象
	 */
	@Transient
	public Object tempObj;

	/**
	 * 到时，写入数据库
	 */
	@Transient
	public long saveDBTime;
	
	/**
	 * 获取逻辑业务数据
	 * @param <E>
	 * @return
	 */
	public  <E> E getLogicObj() {
		return (E) tempObj;
	}
	
	public abstract void setID(Object ID);

	public abstract Object getID();
	
	
	
}
