package com.gproject.common.db;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.persistence.Table;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.util.ClassUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.gproject.common.utils.IDef;
import com.gproject.common.utils.IDef.IAPPInit;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.common.utils.common.GClassUtil;

public abstract class GPCache<E,ID> implements  IAPPInit {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 最大缓存数量
	 */
	final int MAX_OBJECT_NUM = 5000;

	/**
	 * 最大缓存时间，分钟
	 */
	final int MAX_TIME = 10;

	final int INIT_NUM=100;
	
	public static boolean DEBUG = true;
	/**
	 * 入库队列
	 */
	private ConcurrentHashMap<ID, E> dataMap=new ConcurrentHashMap<>(INIT_NUM);
	
	public abstract CrudRepository<E,ID> getCrudRepository();
	
	@Autowired
	LockService lockService;

	Class<E> entityType;
	
	
	private void initEntityType() {
		ParameterizedType parameterizedType=(ParameterizedType)this.getClass().getGenericSuperclass();
		entityType=(Class<E>) parameterizedType.getActualTypeArguments()[0];
	}
	
	// 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
	Cache<ID, E> cache;
	
	public E getData(ID id) {
		E e=null;
		try {
			e=this.cache.getIfPresent(id);
			if (e!=null) {
				return e;
			}
			Integer lock=null;
			if (id instanceof String) {
				lock=lockService.getLockBy((String) id);
			}
			if (id instanceof Long) {
				lock=lockService.getLockBy((String) id);
			}
			
			
			synchronized (lock) {
				e=this.cache.getIfPresent(id);
				if (e==null) {
					//存储数据
					E oldE=dataMap.get(id);
					if (oldE!=null) {
						((DBEvent)oldE).beforeSaveDB();
						getCrudRepository().save(oldE);
						this.cache.put(id, oldE);
					}else {
						Optional<E> dbEntitys=getCrudRepository().findById(id);
						if (dbEntitys.isPresent()) {
							e=dbEntitys.get();
						}else {
							e=entityType.newInstance();
							((DBEvent)e).setID(id);
							((DBEvent)e).beforeSaveDB();
							getCrudRepository().save(e);
						}
						((DBEvent)e).initAfterQueryDB();
						this.cache.put(id, e);
					}
				}
			}
		} catch (Exception exception) {
			// TODO: handle exception
			logger.error("获取数据发生错误=================");
			logger.error(ExceptionUtils.getStackTrace(exception));
		}
		return e;
	}

	

	@Override
	public void init(InitParame initParame) {
		// TODO Auto-generated method stub
		initEntityType();
		// CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
		cache = CacheBuilder.newBuilder()
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
				.removalListener(new RemovalListener<ID, E>() {
					@Override
					public void onRemoval(RemovalNotification<ID, E> notification) {
						// TODO Auto-generated method stub

					}
				}).build();
	}

	/**
	 * @param e
	 */
	public void update(E e) {
		// 调试模式下，直接入库
		if (e==null) {
			return;
		}
		if (DEBUG) {
			((DBEvent)e).beforeSaveDB();
			getCrudRepository().save(e);
		}

	}
}
