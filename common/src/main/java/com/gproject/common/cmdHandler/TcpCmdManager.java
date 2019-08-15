package com.gproject.common.cmdHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.google.protobuf.MessageLite;
import com.gproject.common.cmdHandler.CMDDef.CDModel;
import com.gproject.common.cmdHandler.CMDDef.TCPCommandInfo;
import com.gproject.common.cmdHandler.CMDDef.TcpParame;
import com.gproject.common.net.PushService;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.common.utils.common.GErrorException;

@Component
public class TcpCmdManager implements IAPPInit {

	private static final long MAX_TIME = 15;

	private static final int INIT_NUM = 1000;

	private static final long MAX_OBJECT_NUM = 5000;

	
	private HashMap<Integer,TCPCommandInfo> handlerMap=new HashMap<>();
	
	static Logger logger=LoggerFactory.getLogger(TcpCmdManager.class);
	
	private PushService pushService;
	
	
	@Cached(cacheType = CacheType.LOCAL,localLimit = 10000,expire =300)
	private CDModel getCDModel(Object session,int cmdCode) {
		return new CDModel();
	}
	
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
			
			long cd=now-getCDModel(session, cmdCode).lastCDTime;
			if (tCommandInfo.tcpCommand.CDTime()>cd) {
				logger.info("cd中============");
				return;
			}
		}
		now=System.currentTimeMillis();
		TcpParame tcpParame=new TcpParame();
		tcpParame.netPack=netPack;
		tcpParame.playerId=pushService.getPlayerId(session);
		try {
			Object ret=tCommandInfo.method.invoke(tCommandInfo.handler, tcpParame);
			if (tCommandInfo.tcpCommand.needPrintLog()) {
				logger.info("执行协议="+cmdCode+"====="+"耗时 ms=="+(System.currentTimeMillis()-now));
			}
			if (ret!=null&&(ret instanceof MessageLite)) {
				pushService.pushOnline(tCommandInfo.tcpCommand.cmdCode(), (MessageLite) ret);
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
