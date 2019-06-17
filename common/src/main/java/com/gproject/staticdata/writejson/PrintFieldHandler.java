package com.gproject.staticdata.writejson;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gproject.staticdata.StaticDataDef.SheetData;
import com.gproject.staticdata.StaticDataDef.SheetField;

public class PrintFieldHandler {

	
	
//	public static void main(String[] arg) throws Exception {
//		
//		File file = new File("c:kk/D_道具和资源表.xlsx");
//
//		FileInputStream fileInputStream = new FileInputStream(file);
//
//		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//
//		Iterator<XSSFSheet> iterator = workbook.xssfSheetIterator();
//
//		while (iterator.hasNext()) {
//			XSSFSheet xssfSheet = iterator.next();
//			printFileName(xssfSheet);
//		}
//	}

	/**
	 * 打印实体类字段
	 * 
	 * @param xssfSheet
	 */
	private static void printFileName(XSSFSheet xssfSheet) {
		Iterator<Row> rowIterator = xssfSheet.rowIterator();
		int index = 0;
		SheetData sheetData = new SheetData();
		Map<Integer, SheetField> fieldMap = null;
		while (rowIterator.hasNext()) {
			Row row = (Row) rowIterator.next();
			if (index ==ClassNameHandler.SERVER_NAME_INDEX) {
				sheetData.fileName = new ClassNameHandler().doLogic(row);
				if (StringUtils.isEmpty(sheetData.fileName)) {
					break;
				}
			}
			if (FieldInfoHandler.FILE_NAME_INDEX == index) {
				// 读取字段名称
				fieldMap =new FieldInfoHandler().doFieldName(row);
				if (fieldMap.isEmpty()) {
					break;
				}
			}
			if (FieldInfoHandler.FILE_NAME_INDEX + 1 == index) {
				// 读取字段名称
				new FieldInfoHandler().doFieldType(row, fieldMap);
			}

			index++;
		}
		if (fieldMap == null) {
			return;
		}
		for (SheetField sheetField : fieldMap.values()) {
			if (StringUtils.isBlank(sheetField.nameString)) {
				continue;
			}
			System.out.println("public " + sheetField.type + "  " + sheetField.nameString + ";");
		}
	}

}
