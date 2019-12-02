package com.gproject.gate.pojo;

import java.util.HashMap;


import com.gproject.common.db.AbstractorLogicRet;

public interface BagTableDef {

	public class BagModel{
		public int itemId;
		/**
		 * 唯一标识
		 */
		public int bagId;
		
		public long num;
		
		public HashMap<Integer, Integer> attarMap=new HashMap<>();

		public BagModel(int itemId, int bagId) {
			this.itemId = itemId;
			this.bagId = bagId;
		}
		
		
	}
	
	public class BagRet extends AbstractorLogicRet{
		public int curId;
		public HashMap<Integer, BagModel> bagMap=new HashMap<>();
		
		public BagModel getBagModel(int itemId) {
			for (BagModel bagModel : bagMap.values()) {
				if (bagModel.attarMap.isEmpty()&&itemId==bagModel.itemId) {
					return bagModel;
				}
			}
			return null;
		}
	}
	
	
}
