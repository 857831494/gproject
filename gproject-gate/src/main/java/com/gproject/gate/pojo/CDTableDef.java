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

public interface CDTableDef {


	public class CDNumRet {
		public HashMap<Integer, Integer> map=new HashMap<>();
	}

	// 物理表
	@Entity(name = "tb_cd_num")
	public class CDNumPojo implements DBEvent {
		@Id
		public long playerId;

		@Column(columnDefinition = "text")
		String logicData;

		@Transient
		public CDNumRet cdNumRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.cdNumRet = JSONUtil.getObjectType(logicData, CDNumRet.class);
			if (cdNumRet == null) {
				cdNumRet = new CDNumRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (cdNumRet == null) {
				cdNumRet = new CDNumRet();
			}
			this.logicData = JSONUtil.toJsonString(cdNumRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId = (long) ID;
		}
	}
}
