package com.gproject.cmdHandler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gproject.dto.proto.CommandCodeDTO.CommandCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 协议号注解
 * 
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE, ElementType.METHOD})
public @interface TCPCommand {
	
	/**
	 * 是否需要校验密码
	 * @return
	 */
	public CommandCode cmdCode() ;//
	/**
	 * 是否需要校验密码
	 * @return
	 */
	public boolean needSec() default true;//
	/**
	 * 打印日志
	 * @return
	 */
	public boolean needPrintLog() default true;//
	/**
	 * cd控制
	 * @return
	 */
	public boolean needCD() default true;//
	
	/**
	 * 默认一秒
	 * @return
	 */
	public long CDTime() default 1000;
	
	
}
