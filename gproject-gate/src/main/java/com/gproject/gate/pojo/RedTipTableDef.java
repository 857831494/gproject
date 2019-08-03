package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.DBEvent;
import com.gproject.common.dto.proto.RedTipDTO.RedTipCode;
import com.gproject.common.utils.common.JSONUtil;
import com.gproject.gate.service.item.model.AddItemOrder;

public interface RedTipTableDef {

	public class RedTipModel {
		public RedTipCode code;
		public int itemId;
		public RedTipModel(RedTipCode code, int itemId) {
			super();
			this.code = code;
			this.itemId = itemId;
		}
	}

	public class RedTipRet {
		public List<RedTipModel> redTipModels = new ArrayList<>();

		public boolean isExist(RedTipCode code) {
			for (RedTipModel redTipModel : redTipModels) {
				if (redTipModel.code == code) {
					return true;
				}
			}
			return false;

		}

		public boolean isExist(RedTipCode code, int itemId) {
			for (RedTipModel redTipModel : redTipModels) {
				if (redTipModel.code == code && redTipModel.itemId == itemId) {
					return true;
				}
			}
			return false;
		}

	}

	// 物理表
	@Entity(name = "tb_redhot")
	public class RedTipPojo implements DBEvent {
		@Id
		public long playerId;

		@Column(columnDefinition = "text")
		String logicData;

		@Transient
		public RedTipRet redTipRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.redTipRet = JSONUtil.getObjectType(logicData, RedTipRet.class);
			if (redTipRet == null) {
				redTipRet = new RedTipRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (redTipRet == null) {
				redTipRet = new RedTipRet();
			}
			this.logicData = JSONUtil.toJsonString(redTipRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId = (long) ID;
		}
	}
}
