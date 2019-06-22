package com.gproject.gate.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gproject.common.db.DBEvent;
import com.gproject.common.utils.common.JSONUtil;
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
	
	public class MailRet{
		public int curId;
		public List<MailModel> list=new ArrayList<>();
	}
	
	//物理表
	@Entity(name = "tb_mail")
	public class MailPojo implements DBEvent{
		@Id
		public long playerId;
		
		@Column(columnDefinition = "text")
		String logicData;
		
		@Transient
		public MailRet mailRet;

		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.mailRet=JSONUtil.getObjectType(logicData, MailRet.class);
			if (mailRet==null) {
				mailRet=new MailRet();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			if (mailRet==null) {
				mailRet=new MailRet();
			}
			this.logicData=JSONUtil.toJsonString(mailRet);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.playerId=(long) ID;
		}
	}
}
