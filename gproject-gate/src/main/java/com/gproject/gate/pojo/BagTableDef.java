package com.gproject.gate.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


import com.gproject.common.db.AbstratorDBTable;
import com.gproject.common.utils.common.JSONUtil;

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
	
	public class BagRet{
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
	
	//物理表
	@Entity(name = "tb_bag")
	public class BagPojo extends AbstratorDBTable{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;

		@Override
		public void setLogicDataStr(String logicData) {
			// TODO Auto-generated method stub
			this.logicData=logicData;
		}

		@Override
		public String getLogicDataStr() {
			// TODO Auto-generated method stub
			return logicData;
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}

		@Override
		public Object getID() {
			// TODO Auto-generated method stub
			return playerId;
		}
		
		
	}
}
