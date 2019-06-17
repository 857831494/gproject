package com.gproject.utils.date;

import java.util.Date;

/**
 * 常量定义
 * @author dyhool
 *
 */
public interface Constants {
	
	/**
	 * 每自然天(每天0点)
	 */
	String EVERY_DAY = "0 0 0 * * *";
	
	/**
	 * 每自然月(每月1号0点)
	 */
	String EVERY_MONTH = "0 0 0 1 * *";
	
	/**
	 * 每周星期一0点
	 */
	String EVERY_MONDAY = "0 0 0 * * 1";
	
	/**
	 * 一天的毫秒数
	 */
	long DAY_MILISECEND = 24 * 60 * 60 * 1000;
	
	/**
	 * 一小时的毫秒数
	 */
	long HOUR_MILISECEND = 60 * 60 * 1000;
	
	/**
	 * 一分钟的毫秒数
	 */
	long MINUTE_MILISECEND = 60 * 1000;
	
	/**
	 * 一周的天数
	 */
	long WEEK_DAY = 7;
	
	/***
	 * 一个很大的天数
	 */
	Date MAX_DATE = new Date(2145888000000L);
}
