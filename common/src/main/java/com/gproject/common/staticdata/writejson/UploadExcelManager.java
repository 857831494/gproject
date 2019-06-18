package com.gproject.common.staticdata.writejson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.staticdata.StaticDataDef;
import com.gproject.common.staticdata.StaticDataDef.SheetData;
import com.gproject.common.staticdata.StaticDataDef.SheetField;

@Component
public class UploadExcelManager {

	static Logger logger = LoggerFactory.getLogger(UploadExcelManager.class);
	
	@Autowired
	ClassNameHandler classNameHandler;
	
	@Autowired
	FieldInfoHandler fieldInfoHandler;
	
	@Autowired
	LogicDataHandler logicDataHandler;
	
	private  SheetData doSheet(XSSFSheet xssfSheet) {
		try {
			Iterator<Row> rowIterator = xssfSheet.rowIterator();
			int index = 0;
			SheetData sheetData = new SheetData();
			Map<Integer, SheetField> fieldMap = null;
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				if (index ==ClassNameHandler. SERVER_NAME_INDEX) {
					sheetData.fileName = classNameHandler.doLogic(row);
					if (StringUtils.isEmpty(sheetData.fileName)) {
						return null;
					}
				}
				if (FieldInfoHandler.FILE_NAME_INDEX == index) {
					// 读取字段名称
					fieldMap =fieldInfoHandler.doFieldName(row);
					if (fieldMap.isEmpty()) {
						return null;
					}
				}
				if (FieldInfoHandler.FILE_NAME_INDEX + 1 == index) {
					// 读取字段名称
					fieldInfoHandler.doFieldType(row, fieldMap);
				}
				if (index > (FieldInfoHandler.FILE_NAME_INDEX + 1)) {
					// 读取静态数据
					Map<String, Object> logicMap =logicDataHandler.doLogicData(row, fieldMap);
					if (logicMap != null && !logicMap.isEmpty()) {
						sheetData.dataMap.add(logicMap);
					}
				}
				index++;
			}
			return sheetData;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Excel生成错误==========");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	

	
	
	

	public  List<SheetData> getJson(InputStream inputStream) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			Iterator<XSSFSheet> iterator = workbook.xssfSheetIterator();
			List<SheetData> retList = new ArrayList<>();
			while (iterator.hasNext()) {
				XSSFSheet xssfSheet = (XSSFSheet) iterator.next();
				SheetData sheetData = doSheet(xssfSheet);
				if (sheetData != null) {
					retList.add(sheetData);
				}
			}
			
			workbook.close();
			return retList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
