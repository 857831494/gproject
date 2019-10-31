package com.gproject.common.staticdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.net.http.HttpUtil;
import com.gproject.common.staticdata.StaticDataDef.ConfigExcel;
import com.gproject.common.utils.IDef;
import com.gproject.common.utils.IDef.InitParame;
import com.gproject.common.utils.common.GClassUtil;

/**
 *   静态数据管理类
 * @author YW1825
 *
 */
@Component
public  class ExcelService {

	
	
	Logger logger=LoggerFactory.getLogger(ExcelService.class);
	
	
	private Map<Class,Map<Integer,Object>> dataMap=new HashMap<>();
	
	public Map<String, Class> classMap=new HashMap<>();
	
	@Value("${resUrl}")
	String res_url;
	
	public List getJson(String fileName) {
		try {
			String res_url_path=res_url+"/"+fileName;
			logger.info(res_url_path+"==========开始加载数据======");
			String jsonString=HttpUtil.doGet(res_url_path);
			logger.info(jsonString);
			ObjectMapper mapper = new ObjectMapper();
			List list=mapper.readValue(jsonString,ArrayList.class);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public void loadData(String fileName) {
		try {
			Class typeName=classMap.get(fileName);
			if (typeName==null) {
				logger.error(fileName+"找不到这个配置文件");
				return;////cc
			}
			String res_url_path=res_url+"/"+fileName+".json";
			logger.info(res_url_path+"==========开始加载数据======");
			String jsonString=HttpUtil.doGet(res_url_path);
			logger.info(jsonString);
			if (StringUtils.isBlank(jsonString)) {
				logger.info(fileName+"获取不到数据=====");
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, 
					typeName);
			List list=mapper.readValue(jsonString,jt);
			HashMap<Integer, Object> datas=new HashMap<>(list.size());
			for (Object object : list) {
				ConfigExcel configExcel=(ConfigExcel) object;
				int id=configExcel.getId();
				configExcel.afterLoad();
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
		if (class1.equals(ConfigExcel.class)) {
			return false;
		}
		return ConfigExcel.class.isAssignableFrom(class1);
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
		this.loadAll();
	}
}
