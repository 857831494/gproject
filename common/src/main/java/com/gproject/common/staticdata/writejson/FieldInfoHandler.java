package com.gproject.common.staticdata.writejson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import com.gproject.common.staticdata.StaticDataDef.SheetField;

@Component
public class FieldInfoHandler {

	/**
	 * 字段名称
	 */
  public	final static int FILE_NAME_INDEX = 6;
	
	/**
	 * 生成字段名称
	 * @param row
	 * @return
	 */
	public  Map<Integer, SheetField> doFieldName(Row row) {
		Iterator<Cell> ceIterator = row.cellIterator();
		int i = 1;
		HashMap<Integer, SheetField> map = new HashMap<>();
		while (ceIterator.hasNext()) {
			Cell cell = (Cell) ceIterator.next();
			SheetField sheetField = new SheetField();
			sheetField.nameString = cell.getStringCellValue();
			map.put(i, sheetField);
			i++;
		}
		return map;
	}
	
	
	/**
	 * 生成字段类型
	 * @param row
	 * @param map
	 */
	public  void doFieldType(Row row, Map<Integer, SheetField> map) {
		Iterator<Cell> ceIterator = row.cellIterator();
		int i = 1;
		while (ceIterator.hasNext()) {
			Cell cell = (Cell) ceIterator.next();
			SheetField sheetField = map.get(i);
			sheetField.type = cell.getStringCellValue();
			i++;
		}
	}
}
