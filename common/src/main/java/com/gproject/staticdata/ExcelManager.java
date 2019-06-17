package com.gproject.staticdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.IDef;
import com.gproject.common.IDef.IAPPInit;
import com.gproject.common.IDef.InitParame;
import com.gproject.staticdata.StaticDataDef.ConfigExcel;
import com.gproject.staticdata.StaticDataDef.ICDN;
import com.gproject.utils.common.GClassUtil;

/**
 *   静态数据管理类
 * @author YW1825
 *
 */
@Component
public  class ExcelManager {

	
	
	static Logger logger=LoggerFactory.getLogger(ExcelManager.class);
	
	
	private ICDN icdn;
	
	private Map<Class,Map<Integer,Object>> dataMap=new HashMap<>();
	
	private Map<String, Class> classMap=new HashMap<>();
	
	public void loadData(String fileName) {
		try {
			Class typeName=classMap.get(fileName);
			if (typeName==null) {
				logger.error(fileName+"找不到这个配置文件");
				return;
			}
			String jsonString=icdn.getJson(fileName);
			ObjectMapper mapper = new ObjectMapper();
			JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, 
					typeName);
			List list=mapper.readValue(jsonString,jt);
			HashMap<Integer, Object> datas=new HashMap<>(list.size());
			for (Object object : list) {
				int id=((ConfigExcel)object).getId();
				if (id>0) {
					datas.put(id, object);
				}
			}
			this.dataMap.put(typeName, datas);
			logger.info(fileName+"数据加载成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载数据报错 :"+fileName);
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
	}
	
	/**
	 * 获取全部数据
	 * @param class1
	 * @return
	 */
	public  List getAll(Class class1) {
		return new ArrayList<>(dataMap.get(class1).values());
	}
	
	/**
	 * 根据主键获取数据
	 * @param <T>
	 * @param class1
	 * @param id
	 * @return
	 */
	public <T> T getById(Class class1,int id) {
		List list=getAll(class1);
		if (list==null) {
			return null;
		}
		return (T) dataMap.get(id);
	}
	
	private boolean isConfigExcel(Class class1) {
		for(Class tClass:class1.getInterfaces()) {
			if (tClass.getSimpleName().equals(ConfigExcel.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}
	
	public void loadAll() {
		try {
			List<Class<?>> list=GClassUtil.getClasssFromPackage(IDef.ROOT_PACKAGE);
			for (Class<?> class1 : list) {
				if (!isConfigExcel(class1)) {
					continue;
				}
				this.classMap.put(class1.getSimpleName(), class1);
				loadData(class1.getSimpleName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

	
	public void init(InitParame initParame) {
		// TODO Auto-generated method stub
		String cdnName=initParame.applicationContext.getEnvironment().
				getProperty(StaticDataDef.CDN_NAME);
		if (StringUtils.isEmpty(cdnName)) {
			icdn=(ICDN) initParame.applicationContext.
					getBean(StaticDataDef.TX_CDN);
		}else {
			icdn=(ICDN) initParame.applicationContext.
					getBean(cdnName);
		}
		icdn.init(initParame);
	}
}
