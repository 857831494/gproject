package com.gproject.common.cmdHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.gproject.common.GErrorException;
import com.gproject.common.IDef.IAPPInit;
import com.gproject.common.IDef.InitParame;
import com.gproject.common.cmdHandler.CMDDef.TCPCommandInfo;
import com.gproject.common.cmdHandler.CMDDef.TcpParame;
import com.gproject.common.net.PushService;

@Component
public class TcpCmdManager implements IAPPInit {

	private static final long MAX_TIME = 15;

	private static final int INIT_NUM = 1000;

	private static final long MAX_OBJECT_NUM = 5000;

	
	private HashMap<Integer,TCPCommandInfo> handlerMap=new HashMap<>();
	
	static Logger logger=LoggerFactory.getLogger(TcpCmdManager.class);
	
	private PushService pushService;
	
	Cache<Object, ConcurrentHashMap<Integer, Long>> cdMap;
	
	@Override
	public void init(InitParame initParame) {
		// TODO Auto-generated method stub
		Map<String, Object> map=initParame.applicationContext.
				getBeansWithAnnotation(RestController.class);
		for(Object object:map.values()) {
			addMethodMap(object);
		}
		//默认采用websocket
		Environment environment=initParame.applicationContext.getEnvironment();
		String pushString=environment.getProperty(PushService.PUSH_TYPE);
		if (StringUtils.isEmpty(pushString)) {
			pushString=PushService.WEBSCOKET;
		}
		this.pushService=(PushService) initParame.applicationContext.getBean(pushString);
		cdMap = CacheBuilder.newBuilder()
				// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
				.concurrencyLevel(Runtime.getRuntime().availableProcessors()*2)
				// 设置写缓存后8秒钟过期
				.expireAfterWrite(MAX_TIME, TimeUnit.MINUTES)
				// 设置缓存容器的初始容量为10
				.initialCapacity(INIT_NUM)
				// 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
				.maximumSize(MAX_OBJECT_NUM)
				// 设置要统计缓存的命中率
				.recordStats()
				// 设置缓存的移除通知
				.removalListener(new RemovalListener<Object, ConcurrentHashMap<Integer, Long>>() {
					@Override
					public void onRemoval(RemovalNotification<Object, ConcurrentHashMap<Integer, Long>> notification) {
						// TODO Auto-generated method stub

					}
				}).build();
	}
	
	private void addMethodMap(Object object) {
		Method[] methods=object.getClass().getDeclaredMethods();
		for (Method method : methods) {
			TCPCommand tcpCommand=method.getAnnotation(TCPCommand.class);
			if (tcpCommand==null) {
				continue;
			}
			TCPCommandInfo commandInfo=new TCPCommandInfo();
			commandInfo.handler=object;
			commandInfo.tcpCommand=tcpCommand;
			commandInfo.method=method;
			handlerMap.put(tcpCommand.cmdCode().getNumber(), commandInfo);
		}
	}
	
	public void doCmdReq(byte[] netPack,int cmdCode,Object session) {
		TCPCommandInfo tCommandInfo=handlerMap.get(cmdCode);
		if (tCommandInfo==null) {
			logger.error("找不到协议处理==========="+cmdCode);
			return;
		}
		long playerId=pushService.getPlayerId(session);
		if (tCommandInfo.tcpCommand.needSec()) {
			if (playerId==CMDDef.NO_ID) {
				logger.error("玩家没有登录=========");
				return;
			}
		}
		long now=System.currentTimeMillis();
		if (tCommandInfo.tcpCommand.needCD()) {
			ConcurrentHashMap<Integer, Long> map=cdMap.getIfPresent(cmdCode);
			if (map==null) {
				map=new ConcurrentHashMap<Integer, Long>();
			}
			Long lashTime=map.get(cmdCode);
			if (lashTime!=null) {
				
				long cd=now-lashTime;
				
				if (tCommandInfo.tcpCommand.CDTime()>cd) {
					logger.info("cd中============");
					return;
				}
				map.put(cmdCode, now);
				cdMap.put(session,map);
			}
		}
		now=System.currentTimeMillis();
		TcpParame tcpParame=new TcpParame();
		tcpParame.netPack=netPack;
		tcpParame.playerId=pushService.getPlayerId(session);
		try {
			tCommandInfo.method.invoke(tCommandInfo.handler, tcpParame);
			if (tCommandInfo.tcpCommand.needPrintLog()) {
				logger.info("执行协议="+cmdCode+"====="+"耗时 ms=="+(System.currentTimeMillis()-now));
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof GErrorException) {
				GErrorException errorException=(GErrorException) e;
				if (tcpParame.playerId==CMDDef.NO_ID) {
					pushService.pushError(session, errorException.tipCode);
				}else {
					pushService.pushError(tcpParame.playerId, errorException.tipCode);
				}
				
			}else {
				logger.error("playerId:"+tcpParame.playerId+"执行协议"+cmdCode+"出错====");
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		
		
	}

}
