package com.gproject.gate.service.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class ThreadPoolService {

	final static int MAX_THREAD_NUM = 4;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);

	public ExecutorService getExecutorService() {
		return executorService;
	}

	
	
	
}
