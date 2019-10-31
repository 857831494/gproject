package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.AbstratorDBTable;
import com.gproject.common.utils.common.JSONUtil;

public interface CardTableDef {

	public interface CardType{
		int month=1;//根据物品定义id决定
	}
	
	
	
	public class CardModel{
		public int cardType;
		
		public long expireTime;
		
		/**
		 * 上次执行时间
		 */
		public long lastExceTime;
		
		public CardModel() {
			// TODO Auto-generated constructor stub
		}
		
		public CardModel(int cardType, long expireTime) {
			
			this.cardType = cardType;
			this.expireTime = expireTime;
		}
	}
	
	public class CardRet {
		public HashMap<Integer, CardModel> cardModels=new HashMap<>();
	}
	
	//物理表
	@Entity(name = "tb_card")
	public class CardPojo extends AbstratorDBTable{
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
