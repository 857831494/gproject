package com.gproject.gate.pojo;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.DBEvent;
import com.gproject.common.utils.common.JSONUtil;

public class AttarTableDef {

	public class AttarRet{
		public HashMap<Integer, Long> attarMap=new HashMap<>();
	}
	
	//物理表
	@Entity(name = "tb_attar")
	public class AttarPojo implements DBEvent{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;
		
		@Transient
		public AttarRet attarRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.attarRet=JSONUtil.getObjectType(logicData, AttarRet.class);
			if (attarRet==null) {
				attarRet=new AttarRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (attarRet==null) {
				attarRet=new AttarRet();
			}
			this.logicData=JSONUtil.toJsonString(attarRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}
	}
}
