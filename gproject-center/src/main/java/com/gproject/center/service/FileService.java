package com.gproject.center.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.json.FileNameDef.FileNameDto;
import com.gproject.common.dto.json.FileNameDef.FileNameModel;
import com.gproject.common.staticdata.StaticDataDef.SheetData;
import com.gproject.common.staticdata.writejson.UploadExcelManager;
import com.gproject.common.utils.common.PathUtil;

@Component
public class FileService {

	
	
	static String EXCEL_PATH="/static/excel/";
	
	@Autowired
	UploadExcelManager uploadExcelManager;

	static Logger logger = LoggerFactory.getLogger(FileService.class);

	private void writeFile(File file, String content) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("写入json报错========");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	private String getFilePath() {
		//优先检查
		String dir=System.getProperties().getProperty("user.dir");
		File file=new File(dir+"/static");
		if (file.exists()&&file.isDirectory()) {
			return dir;
		}
		return PathUtil.getPath(FileService.class);
	}
	
	
	public FileNameDto	getFileLst() {
		FileNameDto dto=new FileNameDto();
		File json=new File(getFilePath()+EXCEL_PATH);
		for (File file : json.listFiles()) {
			if (!file.isFile()) {
				continue;
			}
			FileNameModel fileNameModel=new FileNameModel();
			fileNameModel.name=file.getName();
			dto.Rows.add(fileNameModel);
		}
		return dto;
	}
	
	public HashSet<String> saveFile(String path, MultipartFile file) throws Exception {
		List<SheetData> list=uploadExcelManager.getJson(file.getInputStream());
		ObjectMapper mapper=new ObjectMapper();
		String basePath=getFilePath();
		HashSet<String> hashSet=new HashSet<>();
		logger.info("文件写入目录============"+basePath);
		for (SheetData sheetData : list) {
			File json=new File(basePath+EXCEL_PATH+sheetData.fileName+".json");
			String content=mapper.writeValueAsString(sheetData.dataMap);
			if (!json.exists()) {
				writeFile(json, content);
			}else {
				json.delete();
				json.createNewFile();
				writeFile(json, content);
			}
			hashSet.add(sheetData.fileName);
			logger.info("json文件写入成功=========="+sheetData.fileName);
		}
		return hashSet;
	}

}
