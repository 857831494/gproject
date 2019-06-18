package com.gproject.center.config;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.util.ClassUtils;

import com.gproject.common.IDef.IAPPInit;
import com.gproject.common.IDef.InitParame;
import com.gproject.common.staticdata.ExcelManager;
import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;


public class GlobalManager implements ApplicationListener<ApplicationReadyEvent> {
	
    private Logger logger = LoggerFactory.getLogger(GlobalManager.class);

    public static ConfigurableApplicationContext IOC;
    
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		IOC=event.getApplicationContext();
		InitParame initParame=new InitParame();
		initParame.applicationContext=IOC;
		//ExcelManager excelManager=IOC.getBean(ExcelManager.class);
		//excelManager.init(initParame);
		//加载所有启动类
		
		for(IAPPInit appInit:IOC.getBeansOfType(IAPPInit.class).values()) {
			try {
				appInit.init(initParame);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("启动类报错======");
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
		}
		logger.info("执行完毕启动类==================");
	}
    
   
}
