package com.gproject.common.utils;

import org.springframework.context.ApplicationContext;

public interface IDef {

	/**
	 * 包根目录
	 */
	public final static String ROOT_PACKAGE="com.gproject";
	
	/**
	 * 包根目录
	 */
	public final static String RPC_URL="/RPC/WebSocket";
	
	
	
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
		public void	init(InitParame initParame) throws Exception;
	}
	
	public final String GATE_BROCAST_CHANNEL="GATE_BROCAST_CHANNEL";
	
	public final String FIGHT_BROCAST_CHANNEL="FIGHT_BROCAST_CHANNEL";
	
	
	
}
