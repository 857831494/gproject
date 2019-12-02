package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.List;


import com.gproject.common.db.AbstractorLogicRet;
import com.gproject.gate.service.item.model.AddItemOrder;

public interface MailTableDef {

	public class MailModel{
		/**
		 * 到期时间
		 */
		public long inDate;
		
		public int mailType;
		
		public long createTime;
		
		public int  mailId;
		
		/**
		 * 当个物品
		 */
		public AddItemOrder addItemOrder;

		/**
		 * 多个物品
		 */
		public List<AddItemOrder> addItemOrderLst;
		
		public MailModel(long inDate, int mailType, long createTime) {
			this.inDate = inDate;
			this.mailType = mailType;
			this.createTime = createTime;
		}

		public MailModel() {
			
		}
		
		
	}
	
	public class MailRet extends AbstractorLogicRet{
		public int curId;
		public List<MailModel> list=new ArrayList<>();
		
		public MailModel getMailModel(int mailId) {
			for (MailModel mailModel : list) {
				if (mailModel.mailId==mailId) {
					return mailModel;
				}
			}
			return null;
		}
	}
	
	
}
