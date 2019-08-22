package com.gproject.common.staticdata.writejson;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import com.gproject.common.staticdata.StaticDataDef;

/**
 * 获取Java 类名称
 * @author YW1825
 *
 */
@Component
public class ClassNameHandler {

	public final static String SERVER_NAME = "SERVER_NAME";
	public final static int MARK_SIZE = 1;
	public final static int SERVER_NAME_INDEX = 0;
	
	public String doLogic(Row row) {
		String fileName = null;
		int i = 0;
		Iterator<Cell> iterator = row.iterator();
		while (iterator.hasNext()) {
			Cell cell = (Cell) iterator.next();
			if (i > MARK_SIZE) {
				break;
			}
			fileName = cell.getStringCellValue();
			if (i == SERVER_NAME_INDEX) {
				if (!SERVER_NAME.equals(fileName)) {
					return null;
				}
			}
			if (i == SERVER_NAME_INDEX + 1) {
				if (StringUtils.isEmpty(fileName)) {
					return null;
				}
			}
			i++;
		}

		return fileName;
	}
}
