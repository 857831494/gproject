package com.gproject.center.control;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gproject.center.service.FileService;
import com.gproject.center.service.GMService;
import com.gproject.common.config.AuthHandler;
import com.gproject.common.dto.json.ServersResp;
import com.gproject.common.dto.json.FileNameDef.FileNameDto;
import com.gproject.common.dto.json.FileNameDef.JAVAFileDto;
import com.gproject.common.dto.json.GMLoginDTO.GMLoginReq;
import com.gproject.common.dto.json.GMLoginDTO.GMLoginResp;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.excelmodel.HServerConfig;

@RestController
public class GMControl {

	@Autowired
	GMService gmService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	ExcelService excelManager;
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"getServers")
	public ServersResp getServers() {
		ServersResp serversResp=new ServersResp();
		serversResp.Rows.addAll(excelManager.getAll(HServerConfig.class));
		return serversResp;
	}
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"getFiles")
	public FileNameDto getFiles() {
		return fileService.getFileLst();
	}
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"getJavaFile")
	public String getJavaFile(@RequestParam("fileName") String fileName) {
		return fileService.getJAVAFile(fileName);
	}
	
	Logger logger=LoggerFactory.getLogger(GMControl.class);
	
	@RequestMapping(path ="/getToken")
	public GMLoginResp getToken(@RequestBody GMLoginReq dto) {
		return gmService.getToken(dto);
	}
	
	@RequestMapping(path =AuthHandler.NEED_AUTH_PATH+"uploadExcel")
	public boolean uploadExcel(@RequestParam("fileName") String fileName,
			@RequestParam("myfile") MultipartFile  file) throws Exception {
		
		return fileService.saveFile(fileName, file);
	}
	
	/**
	 * 上传战斗文件
	 * @param dto
	 */
	@RequestMapping(path ="/uploadBattleFile")
	public void getToken(@RequestBody byte[] dto) {
		
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
