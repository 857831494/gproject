package com.gproject.gate.service.match;

public interface MatchOpsDef{
	
	public interface MatchOpsCode{
		public int join=1;
		public int leave=2;
	}
	
	public class MatchOps {

		public long playerId;
		
		public String roomId;
		
		public int code;
	}
}


