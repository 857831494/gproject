package com.gproject.common;

import org.springframework.context.ApplicationContext;

public interface IDef {

	/**
	 * 包根目录
	 */
	public final static String ROOT_PACKAGE="com.gproject";
	
	/**
	 * 系统根目录
	 */
	public final static String SYS_BASE_PATH=System.getProperty("user.dir");
	
	public class InitParame{
		public ApplicationContext applicationContext;
	}
	/**
	 * app启动接口
	 * @author YW1825
	 *
	 */
	public interface IAPPInit{
		public void	init(InitParame initParame);
	}
	
	
	
	
	
}
