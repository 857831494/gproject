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

public interface CardTableDef {

	public interface CardType{
		int month=1;//根据物品定义id决定
	}
	
	public class CardTime{
		public long start;
		public long end;
	}
	
	public class CardModel{
		public int cardType;
		/**
		 * 作用为了处理，卡类物品，在有效区间，每天0点执行逻辑
		 */
		public List<CardTime> list=new ArrayList<CardTableDef.CardTime>();
		public long expireTime;
		
		public CardModel() {
			// TODO Auto-generated constructor stub
		}
		
		public CardModel(int cardType, long expireTime) {
			
			this.cardType = cardType;
			this.expireTime = expireTime;
		}
	}
	
	public class CardRet{
		public HashMap<Integer, CardModel> cardModels=new HashMap<>();
	}
	
	//物理表
	@Entity(name = "tb_card")
	public class CardPojo implements DBEvent{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;
		
		@Transient
		public CardRet cardRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.cardRet=JSONUtil.getObjectType(logicData, CardRet.class);
			if (cardRet==null) {
				cardRet=new CardRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (cardRet==null) {
				cardRet=new CardRet();
			}
			this.logicData=JSONUtil.toJsonString(cardRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}
	}
}
