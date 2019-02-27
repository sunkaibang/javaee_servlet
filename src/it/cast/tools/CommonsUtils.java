package it.cast.tools;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

public class CommonsUtils {
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static <T> T toBean(Map map,Class<T> clazz){
		try {
			T bean =clazz.newInstance();
			BeanUtils.populate(bean, map);
			return bean;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
