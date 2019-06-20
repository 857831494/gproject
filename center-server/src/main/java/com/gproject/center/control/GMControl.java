package com.gproject.center.control;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gproject.center.service.FileService;
import com.gproject.center.service.GMService;
import com.gproject.common.config.AuthHandler;
import com.gproject.common.dto.json.GMLoginDTO.GMLoginReq;
import com.gproject.common.dto.json.GMLoginDTO.GMLoginResp;
import com.gproject.common.staticdata.ExcelService;

@RestController
public class GMControl {

	@Autowired
	GMService gmService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	ExcelService excelManager;
	
	Logger Logger=LoggerFactory.getLogger(GMControl.class);
	
	@RequestMapping(path ="/getToken")
	public GMLoginResp getToken(@RequestBody GMLoginReq dto) {
		return gmService.getToken(dto);
	}
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"uploadExcel")
	public HashSet<String> uploadExcel(@RequestParam("path") String path,
			@RequestParam("myfile") MultipartFile  file) throws Exception {
		return fileService.saveFile(path, file);
	}
	
	
	/**
	 * 重新加载Excel
	 * @param fileName
	 * @throws Exception
	 */
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"reload")
	public void reload(String fileName) throws Exception {
		excelManager.loadData(fileName);
	}
	
}
