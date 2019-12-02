package com.gproject.common.db;


import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.anno.Cached;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.common.utils.common.JSONUtil;
import com.gproject.common.utils.common.PathUtil;

/**
 * @author YW1825
 *
 * @param <POJO> 数据库表
 * @param <LogicModel>  业务逻辑
 */
public abstract class GPCache<LogicModel> implements  IAPPInit {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	

	static final int HEART_TIME=1;
	
	static final int THREAD_NUM=6;
	static final int INIT_NUM=100;
	static final  long DELAY_TIME=3*60*1000l;
	public static boolean DEBUG = true;
	  // 创建线程池
    static ScheduledExecutorService THREAD_POOL = Executors.newScheduledThreadPool(THREAD_NUM);
	/**
	 * 入库队列
	 */
	private ConcurrentHashMap<Object, AbstractorLogicRet> db_Map=new ConcurrentHashMap<>(INIT_NUM);
	
	
	@Autowired
	LockService lockService;

	Class<LogicModel> logicModelClass;
	
	
	@Autowired
	ApplicationContext applicationContext;
	
	private void initEntityType(InitParame initParame) throws Exception {
		ParameterizedType parameterizedType=(ParameterizedType)this.getClass().getGenericSuperclass();
		logicModelClass=(Class<LogicModel>) parameterizedType.getActualTypeArguments()[0];
		if (!AbstractorLogicRet.class.isAssignableFrom(logicModelClass)) {
			throw new Exception(logicModelClass.getName()+"数据模型没有继承AbstractorLogicRet====");
		}
		String path=getFilePath();
		File aFile=new File(path+"/db");
		if (!aFile.exists()) {
			aFile.mkdir();
		}
		File pFile=new File(path+"/db/"+logicModelClass.getSimpleName());
		if (!pFile.exists()) {
			pFile.mkdir();
		}
	}
	
	private String getFilePath() {
		//优先检查
		String dir=System.getProperties().getProperty("user.dir");
		File file=new File(dir+"/logback.xml");
		if (file.exists()) {
			return dir;
		}
		return PathUtil.getPath(logicModelClass);
	}

	/**
	 * 根据id，从文件夹下加载数据
	 * @param id
	 * @return
	 */
	private AbstractorLogicRet getLogicRet(Object id){
		try {
			String path=getFilePath();
			File file=new File(path+"/db/"+logicModelClass.getSimpleName()+"/"+id);
			if (!file.exists()) {
				return null;
			}
			ObjectMapper objectMapper=new ObjectMapper();
			AbstractorLogicRet ret=(AbstractorLogicRet) 
					objectMapper.readValue(file, logicModelClass);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	private void saveData(AbstractorLogicRet ret,Object id) {
		String path=getFilePath();
		File file=new File(path+"/db/"+logicModelClass.getSimpleName()+"/"+id);
		String jString=JSONUtil.toJsonString(ret);
		PathUtil.writeFile(file, jString);
	}
	
	/**
	 * 需要注解阿里的缓存组件
	 * @param id
	 * @return
	 */
	public AbstractorLogicRet getPojo(Object id) {
		AbstractorLogicRet ret=db_Map.get(id);
		if (null!=ret) {
			return ret;
		}
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			Integer lock=lockService.getLock(id);
			synchronized (lock) {
				//先检查是否数据库存在
				ret=this.getLogicRet(id);
				if (ret==null) {
					ret=(AbstractorLogicRet) logicModelClass.newInstance();
				
					this.update(ret,id);
				}
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("存储数据报错===============id:"+id);
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public boolean needLoad() {
		return true;
	}

	@Override
	public void init(InitParame initParame) throws Exception {
		// TODO Auto-generated method stub
		initEntityType(initParame);
		this.THREAD_POOL.scheduleAtFixedRate(()->{
			saveDB();
		}, 0, HEART_TIME,TimeUnit.SECONDS);
	}

	private void saveDB() {
		if (db_Map.isEmpty()) {
			return;
		}
		List<Object> Ids=new ArrayList<Object>();
		for (Entry<Object, AbstractorLogicRet> entry : db_Map.entrySet()) {
			Long lastTime=lastSaveTime.get(entry.getKey());
			if (lastTime!=null) {
				if (System.currentTimeMillis()<lastTime) {
					continue;
				}
			}
			
			this.saveData(entry.getValue(),entry.getKey());
			Ids.add(entry.getKey());
		}
		
		for (Object object : Ids) {
			Ids.remove(object);
		}
	}
	
	private ConcurrentHashMap<Object, Long> lastSaveTime=new ConcurrentHashMap<Object, Long>();
	
	/**
	 * @param e
	 */
	public void update(AbstractorLogicRet abstratorDBTable,Object id) {
		// 调试模式下，直接入库
		if (abstratorDBTable==null) {
			return;
		}
		lastSaveTime.put(id, System.currentTimeMillis()+DELAY_TIME);
		if (DEBUG) {
			saveData(abstratorDBTable,id);
		}else {
			db_Map.put(id, abstratorDBTable);
		}

	}
}
