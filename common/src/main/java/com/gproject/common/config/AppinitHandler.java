package com.gproject.common.config;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;

public class AppinitHandler implements ApplicationListener<ApplicationReadyEvent> {
	
    Logger logger = LoggerFactory.getLogger(AppinitHandler.class);

   
    
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		
		InitParame initParame=new InitParame();
		
		ExcelService excelManager=event.getApplicationContext().getBean(ExcelService.class);
		excelManager.init(initParame);
		//加载所有启动类
		for(IAPPInit appInit:event.getApplicationContext().
				getBeansOfType(IAPPInit.class).values()) {
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
