package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.gproject.common.db.AbstractorLogicRet;
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
	
	public class CardRet extends AbstractorLogicRet{
		public HashMap<Integer, CardModel> cardModels=new HashMap<>();
	}
	
	
}
