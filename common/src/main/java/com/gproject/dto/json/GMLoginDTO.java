package com.gproject.dto.json;

public interface GMLoginDTO{
	public class GMLoginReq {

		public String UserName;
		
		public String Pwd;
	}

	public class GMLoginResp {

		public String uid;
		
		public String time;
		
		public String sessionId;
	}
	
	
	
}


