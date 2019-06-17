package com.gproject.center.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
import com.gproject.staticdata.StaticDataDef.SheetData;
import com.gproject.staticdata.writejson.UploadExcelManager;

@Component
public class FileService {

	@Value("${App.Path}")
	String root_path;
	
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

	public void saveFile(String path, MultipartFile file) throws Exception {
		List<SheetData> list=uploadExcelManager.getJson(file.getInputStream());
		ObjectMapper mapper=new ObjectMapper();
		for (SheetData sheetData : list) {
			File json=new File(root_path+EXCEL_PATH+path+"/"+sheetData.fileName+".json");
			String content=mapper.writeValueAsString(sheetData.dataMap);
			if (!json.exists()) {
				writeFile(json, content);
			}else {
				json.delete();
				json.createNewFile();
				writeFile(json, content);
			}
			logger.info("json文件写入成功=========="+sheetData.fileName);
		}
		
	}

}
