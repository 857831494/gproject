package com.gproject.staticdata.writejson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.staticdata.StaticDataDef.SheetField;

/**
 * 
 * 获取静态表逻辑数据
 * @author YW1825
 *
 */
@Component
public class LogicDataHandler {

	static Logger logger = LoggerFactory.getLogger(LogicDataHandler.class);
	

	final static String LEFT_BRACKET = "[";
	
	public  Map<String, Object> doLogicData(Row row, Map<Integer, SheetField> fieldMap) {
		Iterator<Cell> ceIterator = row.cellIterator();
		int i = 1;
		HashMap<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			while (ceIterator.hasNext()) {
				Cell cell = (Cell) ceIterator.next();
				SheetField sheetField = fieldMap.get(i);
				int fileType=cell.getCellType();
				// 检查是否数组
				int num = 0;
				if (Cell.CELL_TYPE_STRING==fileType) {
					if (StringUtils.isEmpty(cell.getStringCellValue())) {
						continue;
					}
					num = StringUtils.countMatches(cell.getStringCellValue(),LEFT_BRACKET);
				}
				switch (num) {
				case 0: {
					if (fileType==Cell.CELL_TYPE_STRING) {
						map.put(sheetField.nameString, cell.getStringCellValue());
					}
					if (fileType==Cell.CELL_TYPE_NUMERIC) {
						map.put(sheetField.nameString, cell.getNumericCellValue());
					}
					if (fileType==Cell.CELL_TYPE_BOOLEAN) {
						map.put(sheetField.nameString, cell.getBooleanCellValue());
					}
				}
					break;
				case 1: {
					try {
						int[] list = mapper.readValue(cell.getStringCellValue(),
								new TypeReference<int[]>() {
						});
						map.put(sheetField.nameString, list);
					} catch (Exception e) {
						// TODO: handle exception
						map.put(sheetField.nameString, cell.getStringCellValue());
					}
					
				}
					break;
				case 2: {
					try {
						List<int[]> list = mapper.readValue(cell.getStringCellValue(),
								new TypeReference<List<int[]>>() {
						});
						map.put(sheetField.nameString, list);
					} catch (Exception e) {
						// TODO: handle exception
						map.put(sheetField.nameString, cell.getStringCellValue());
					}
					
				}
					break;

				}

				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("转换json出错=========");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return map;
	}
}
