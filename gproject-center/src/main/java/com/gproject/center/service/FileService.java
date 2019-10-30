package com.gproject.center.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.config.AuthHandler;
import com.gproject.common.dto.json.FileNameDef.FileNameDto;
import com.gproject.common.dto.json.FileNameDef.FileNameModel;
import com.gproject.common.dto.json.FileNameDef.JAVAFileDto;
import com.gproject.common.staticdata.ExcelService;
import com.gproject.common.staticdata.StaticDataDef.SheetData;
import com.gproject.common.staticdata.writejson.UploadExcelService;
import com.gproject.common.utils.common.PathUtil;

@Component
public class FileService {

	
	
	static String EXCEL_PATH="/static/excel/";
	
	@Autowired
	UploadExcelService uploadExcelManager;

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
	
	
	@Autowired
	ExcelService excelService;
	
	public String getJAVAFile(String fileName) {
		JAVAFileDto fileDto=new JAVAFileDto();
		List list=excelService.getJson(fileName);
		HashMap<String, Object> map=(HashMap<String, Object>) list.get(0);
		StringBuilder stringBuilder=new StringBuilder();
		for (Entry<String, Object> objEntry : map.entrySet()) {
			stringBuilder.append("<br/>");
			logger.info(objEntry.getValue().getClass().getName());
			if (objEntry.getValue() instanceof Integer) {
				stringBuilder.append("public int ");
				stringBuilder.append(objEntry.getKey());
				stringBuilder.append(";");
			}
			if (objEntry.getValue() instanceof String) {
				stringBuilder.append("public String ");
				stringBuilder.append(objEntry.getKey());
				stringBuilder.append(";");
			}
			
			if (objEntry.getValue() instanceof Boolean) {
				stringBuilder.append("public boolean ");
				stringBuilder.append(objEntry.getKey());
				stringBuilder.append(";");
			}
			if (objEntry.getValue() instanceof List) {
				stringBuilder.append("public List ");
				stringBuilder.append(objEntry.getKey());
				stringBuilder.append(";");
			}
			if (objEntry.getValue() instanceof Double) {
				stringBuilder.append("public int ");
				stringBuilder.append(objEntry.getKey());
				stringBuilder.append(";");
			}
		}
		fileDto.info=stringBuilder.toString();
		return stringBuilder.toString();
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
			fileNameModel.detailLink="<a href=\"../../../excel/"+file.getName()+"\">数据详情</a>";
			fileNameModel.javaFile="<a href=\"../../.."
			+AuthHandler.NEED_AUTH_PATH+"getJavaFile?fileName="+file.getName()+"\">JAVA代码</a>";
			dto.Rows.add(fileNameModel);
		}
		return dto;
	}
	
	public boolean saveFile(String fileName, MultipartFile file) throws Exception {
		SheetData sheetData=uploadExcelManager.convertSheetData(file.getInputStream(),fileName);
		if (sheetData==null) {
			return false;
		}
		ObjectMapper mapper=new ObjectMapper();
		String basePath=getFilePath();
		HashSet<String> hashSet=new HashSet<>();
		logger.info("文件写入目录============"+basePath);
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
		return true;
	}

}
