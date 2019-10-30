package com.gproject.common.staticdata.writejson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

import ch.qos.logback.core.joran.conditional.IfAction;

@Component
public class UploadExcelService {

	static Logger logger = LoggerFactory.getLogger(UploadExcelService.class);
	
	

	

	
	private Map<String, Object> convertRow(Row row,Row nameRow) {
		HashMap<String, Object> map=new HashMap<>();
		Iterator<Cell> iterator=row.iterator();
		int cell_index=0;
		while (iterator.hasNext()) {
			Cell cell=iterator.next();
			try {
				//先尝试数字
				double val=cell.getNumericCellValue();
				map.put(nameRow.getCell(cell_index).getStringCellValue(), val);
			} catch (Exception e) {
				// TODO: handle exception
				//尝试布尔值
				try {
					boolean val=cell.getBooleanCellValue();
					map.put(nameRow.getCell(cell_index).getStringCellValue(), val);
				} catch (Exception e2) {
					// TODO: handle exception
					// TODO: handle exception
					//字符串
					String val=cell.getStringCellValue();
					//尝试，是否可以变成数值
					ObjectMapper objectMapper=new ObjectMapper();
					try {
						List list=objectMapper.readValue(val, List.class);
						map.put(nameRow.getCell(cell_index).getStringCellValue(), list);
					} catch (Exception e4) {
						// TODO: handle exception
						map.put(nameRow.getCell(cell_index).getStringCellValue(), val);
					}
				}
				
			}
			cell_index++;
		}
		return map;
	}
	

	public  SheetData convertSheetData(InputStream inputStream,String fileName) {
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			SheetData sheetData=null;
			XSSFSheet xssfSheet=workbook.getSheetAt(0);
			workbook.close();
			if (xssfSheet==null) {
				return null;
			}
			if (null==xssfSheet.getRow(StaticDataDef.SHEET_LEN)) {
				return null;
			}
			Iterator<Row> rowIterator = xssfSheet.rowIterator();
			sheetData=new SheetData();
			sheetData.fileName=fileName;
			int index=0;
			Row name_row=xssfSheet.getRow(StaticDataDef.SHEET_LEN-1);
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				if (index!=0) {
					Map<String, Object> retHashMap=convertRow(row, name_row);
					if (!retHashMap.isEmpty()) {
						sheetData.dataMap.add(retHashMap);
					}
				}
				index++;
			}
			return sheetData;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
