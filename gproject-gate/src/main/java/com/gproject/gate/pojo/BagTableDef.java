package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


import com.gproject.common.db.DBEvent;
import com.gproject.common.utils.common.JSONUtil;

public class BagTableDef {

	public class HasAttarModel{
		public int itemId;
		/**
		 * 唯一标识
		 */
		public int dbItemId;
		
		public HashMap<Integer, Integer> attarMap=new HashMap<>();
	}
	
	public class BagRet{
		/**
		 * 无属性物品
		 */
		public HashMap<Integer, Long> unAttarMap=new HashMap<>();
		
		/**
		 * 有属性物品
		 */
		public List<HasAttarModel> attarModels=new ArrayList<BagTableDef.HasAttarModel>();
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
