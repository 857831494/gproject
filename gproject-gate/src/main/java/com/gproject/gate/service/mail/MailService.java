package com.gproject.gate.service.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gproject.gate.cache.MailCache;
import com.gproject.gate.pojo.MailTableDef.MailModel;
import com.gproject.gate.pojo.MailTableDef.MailPojo;
import com.gproject.gate.pojo.MailTableDef.MailRet;
import com.gproject.gate.service.item.AddItemService;
import com.gproject.gate.service.item.model.AddItemOrder;

@Service
public class MailService {

	@Autowired
	MailCache mailCache;

	@Autowired
	AddItemService addItemService;
	
	Logger logger=LoggerFactory.getLogger(MailService.class);
	/**
	 * 物品添加不了，发到邮件 判断 成功数量是否全部添加 全部添加表示成功 不需要发邮件
	 * 
	 * @param addItemOrder
	 */
	public void itemMail(AddItemOrder addItemOrder) {
		if (addItemOrder.successVal == addItemOrder.addVal) {
			return;
		}
		int maxDay=10;
		// 处理逻辑
		MailPojo mailPojo=mailCache.getPojo(addItemOrder.playerId);
		MailRet mailRet=mailPojo.getLogicObj();
		long createTime=System.currentTimeMillis();
		if (addItemOrder.mailTime>0) {
			createTime=addItemOrder.mailTime;
		}
		long inDate=createTime+maxDay*DateUtils.MILLIS_PER_DAY;
		if (System.currentTimeMillis()>inDate) {
			logger.info("邮件过期，无法加===物品邮件"+addItemOrder.toString());
			return;
		}
		MailModel mailModel=new MailModel(inDate, MailType.MAX_ITEM, createTime);
		mailRet.curId++;
		mailModel.mailId=mailRet.curId;
		//加itemid
		mailModel.addItemOrder=addItemOrder;
		mailRet.list.add(mailModel);
		mailCache.update(mailPojo);
	}

	/**
	 * 物品添加不了，发到邮件,邮件系统自己实现，压缩成一封邮件
	 * 
	 * @param addItemOrder
	 */
	public void itemsMail(List<AddItemOrder> addItemOrders) {
		List<AddItemOrder> list=new ArrayList<>();
		addItemOrders.forEach(addItemOrder->{
			if (addItemOrder.successVal!=addItemOrder.addVal) {
				list.add(addItemOrder);
			}
		});
		if (!list.isEmpty()) {
			return;
		}
		int maxDay=10;
		// 处理逻辑
		MailPojo mailPojo=mailCache.getPojo(list.get(0).playerId);
		MailRet mailRet=mailPojo.getLogicObj();
		long createTime=System.currentTimeMillis();
		if (list.get(0).mailTime>0) {
			createTime=list.get(0).mailTime;
		}
		long inDate=createTime+maxDay*DateUtils.MILLIS_PER_DAY;
		if (System.currentTimeMillis()>inDate) {
			logger.info("邮件过期，无法加===物品邮件"+list.get(0).playerId);
			return;
		}
		MailModel mailModel=new MailModel(inDate, MailType.MAX_ITEM, createTime);
		mailRet.curId++;
		mailModel.mailId=mailRet.curId;
		//加itemid
		mailModel.addItemOrderLst=list;
		mailRet.list.add(mailModel);
		mailCache.update(mailPojo);
	}
	
	
	/**
	 * 领取附件
	 * @param playerId
	 */
	public void receiveAttachment(long playerId,int mailId) {
		//小心抛出错误码
		MailPojo mailPojo=mailCache.getPojo(playerId);
		MailRet mailRet=mailPojo.getLogicObj();
		MailModel mailModel=mailRet.getMailModel(mailId);
		//检查邮件是否可以加到属性，或者背包
		if (mailModel.addItemOrder!=null) {
			addItemService.canAdd(mailModel.addItemOrder);
		}
		if (mailModel.addItemOrderLst!=null) {
			addItemService.canAdd(mailModel.addItemOrderLst);
		}
		
	}
}
