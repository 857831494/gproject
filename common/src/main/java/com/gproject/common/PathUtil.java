package com.gproject.common;

public class PathUtil {

	public static String getPath(Class pathClass) {
		String temp=pathClass.getResource("").getPath();
		String packName=pathClass.getPackage().getName();
		packName=packName.replace(".", "/");
		return temp.replace(packName, "");
	}
}
