package com.gproject.common.utils.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	public static String toJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T getObjectType(String string, Class type) {
		try {
			if (StringUtils.isEmpty(string)) {
				return (T) type.newInstance();
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return (T) objectMapper.readValue(string, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> getListType(String string, Class type) {
		if (StringUtils.isEmpty(string)) {
			return new ArrayList<>();
		}
		ObjectMapper mapper = new ObjectMapper();
		JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, type);
		try {
			List list = mapper.readValue(string, jt);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
