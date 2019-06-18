package com.gproject.center.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.gproject.common.staticdata.excelmodel.HServerConfig;

@Component
public class ZoneListService {

	
	static Logger logger=LoggerFactory.getLogger(FileService.class);
	
	
	
	/**
	 * 获取区服列表，要加上，区服状态
	 */
	public List<HServerConfig> getZoneList() {
		return null;
	}
}
