package com.gproject.gate.service.mail;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gproject.gate.service.item.ItemDef.AddItemOrder;


@Service
public class MailService {

	/**
	 * 物品添加不了，发到邮件  判断 成功数量是否全部添加 全部添加表示成功 不需要发邮件
	 * @param addItemOrder
	 */
	public void itemMail(AddItemOrder addItemOrder) {
		if (addItemOrder.successVal==addItemOrder.addVal) {
			return;
		}
		//处理逻辑
	}
	
	/**
	 * 物品添加不了，发到邮件,邮件系统自己实现，压缩成一封邮件
	 * @param addItemOrder
	 */
	public void itemsMail(List<AddItemOrder> addItemOrders) {
		
	}
}
