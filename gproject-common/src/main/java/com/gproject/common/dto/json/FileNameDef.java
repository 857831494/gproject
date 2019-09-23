package com.gproject.common.dto.json;

import java.util.HashSet;
import java.util.List;

import com.gproject.common.net.http.APIParameDef.NotNeedConvert;

public interface FileNameDef{
	
	public class FileNameModel{
		public String name;
	}
	
	public class FileNameDto extends NotNeedConvert{

		public HashSet<FileNameModel> Rows=new HashSet<>();
	}
}


