package com.gproject.gate.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


import com.gproject.common.db.DBEvent;
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
			super();
			this.itemId = itemId;
			this.bagId = bagId;
		}
		
		
	}
	
	public class BagRet{
		public int curId;
		public HashMap<Integer, BagModel> bagMap=new HashMap<>();
	}
	
	//物理表
	@Entity(name = "tb_bag")
	public class BagPojo implements DBEvent{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;
		
		@Transient
		public BagRet bagRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.bagRet=JSONUtil.getObjectType(logicData, BagRet.class);
			if (bagRet==null) {
				bagRet=new BagRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (bagRet==null) {
				bagRet=new BagRet();
			}
			this.logicData=JSONUtil.toJsonString(bagRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}
	}
}
