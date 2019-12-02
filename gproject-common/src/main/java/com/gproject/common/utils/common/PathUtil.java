package com.gproject.common.utils.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtil {

	public static String getPath(Class pathClass) {
		String temp=pathClass.getResource("").getPath();
		String packName=pathClass.getPackage().getName();
		packName=packName.replace(".", "/");
		return temp.replace(packName, "");
	}
	
	static Logger logger=LoggerFactory.getLogger(PathUtil.class);
	
	public static void writeFile(File file, String content) {
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
}
