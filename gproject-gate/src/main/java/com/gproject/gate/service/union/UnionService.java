package com.gproject.gate.service.union;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.gproject.common.dto.proto.RPCDTO.GameMessage;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;


@Service
public class UnionService implements IAPPInit{

	final static int MAX_THREAD_NUM = 50;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);

	int[] LockArray=new int[MAX_THREAD_NUM];
	
	@Override
	public void init(InitParame initParame) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < LockArray.length; i++) {
			LockArray[i]=i;
		}
	}

	private Integer getLock(long unionId) {
		int size=(int) (unionId%MAX_THREAD_NUM);
		return LockArray[size];
	}
	
	public void apply(long playerId,GameMessage message) {
		
		executorService.execute(()->{
			long unionId = 0;
			Integer lock=getLock(unionId);
			synchronized (lock) {
				//处理逻辑
			}
		});
	}
	
	
}
