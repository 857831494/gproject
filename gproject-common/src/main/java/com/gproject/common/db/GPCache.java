package com.gproject.common.db;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.common.utils.date.DateUtils;

/**
 * @author YW1825
 *
 * @param <POJO> 数据库表
 * @param <LogicModel>  业务逻辑
 */
public abstract class GPCache<POJO,LogicModel> implements  IAPPInit {

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
	private ConcurrentHashMap<Object, AbstratorDBTable> db_Map=new ConcurrentHashMap<>(INIT_NUM);
	
	public abstract CrudRepository getCrudRepository();
	
	@Autowired
	LockService lockService;

	Class<LogicModel> logicModelClass;
	
	Class<POJO> pojoClass;
	
	@Autowired
	ApplicationContext applicationContext;
	
	private void initEntityType(InitParame initParame) throws Exception {
		ParameterizedType parameterizedType=(ParameterizedType)this.getClass().getGenericSuperclass();
		logicModelClass=(Class<LogicModel>) parameterizedType.getActualTypeArguments()[1];
		if (!AbstractorLogicRet.class.isAssignableFrom(logicModelClass)) {
			throw new Exception(logicModelClass.getName()+"数据模型没有继承AbstractorLogicRet====");
		}
		pojoClass=(Class<POJO>) parameterizedType.getActualTypeArguments()[0];
	}
	
	

	/**
	 * 需要注解阿里的缓存组件
	 * @param id
	 * @return
	 */
	public <E> E getPojo(Object id) {
		AbstratorDBTable ret=db_Map.get(id);
		if (null!=ret) {
			return (E) ret;
		}
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			Integer lock=lockService.getLock(id);
			synchronized (lock) {
				//先检查是否数据库存在
				Optional list=this.getCrudRepository().findById(id);
				if (list.isPresent()) {
					AbstratorDBTable pojoObj=(AbstratorDBTable) list.get();
					if (StringUtils.isBlank(pojoObj.getLogicDataStr())) {
						pojoObj.tempObj=logicModelClass.newInstance();
					}else {
						pojoObj.tempObj=objectMapper.readValue(pojoObj.getLogicDataStr(), logicModelClass);
					}
					return (E) pojoObj;
				}else {
					AbstratorDBTable pojoObj=(AbstratorDBTable) this.pojoClass.newInstance();
					Object logicObj=this.logicModelClass.newInstance();
					pojoObj.setID(id);
					pojoObj.setLogicDataStr(objectMapper.writeValueAsString(logicObj));
					pojoObj.tempObj=logicObj;
					this.getCrudRepository().save(pojoObj);
					return  (E) pojoObj;
				}
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
		for (AbstratorDBTable table : db_Map.values()) {
			if (System.currentTimeMillis()<table.saveDBTime) {
				continue;
			}
			this.getCrudRepository().save(table);
		}
	}
	
	/**
	 * @param e
	 */
	public void update(AbstratorDBTable abstratorDBTable) {
		// 调试模式下，直接入库
		if (abstratorDBTable==null) {
			return;
		}
		if (DEBUG) {
			getCrudRepository().save(abstratorDBTable);
		}else {
			abstratorDBTable.saveDBTime=System.currentTimeMillis()+DELAY_TIME;
			db_Map.put(abstratorDBTable.getID(), abstratorDBTable);
		}

	}
}
