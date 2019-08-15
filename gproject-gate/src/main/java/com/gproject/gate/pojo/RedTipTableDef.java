package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.AbstratorDBTable;
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
	public class RedTipPojo extends AbstratorDBTable {
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
