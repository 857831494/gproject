package com.gproject.center.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gproject.center.config.AuthHandler;
import com.gproject.center.service.FileService;
import com.gproject.center.service.GMService;
import com.gproject.dto.json.GMLoginDTO.GMLoginReq;
import com.gproject.dto.json.GMLoginDTO.GMLoginResp;

@RestController
public class GMControl {

	@Autowired
	GMService gmService;
	
	@Autowired
	FileService fileService;
	
	Logger Logger=LoggerFactory.getLogger(GMControl.class);
	
	@RequestMapping(path ="/getToken")
	public GMLoginResp getToken(@RequestBody GMLoginReq dto) {
		return gmService.getToken(dto);
	}
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"uploadExcel")
	public void uploadExcel(@RequestParam("path") String path,
			@RequestParam("myfile") MultipartFile  file) throws Exception {
		fileService.saveFile(path, file);
	}
	
}
