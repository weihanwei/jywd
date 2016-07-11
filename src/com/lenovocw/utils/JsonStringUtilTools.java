package com.lenovocw.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * JSON工具类
 * 
 * @Copyright lenovo
 * 
 * @Project egame-core-api
 * 
 * @Author Mac
 * 
 * @timer 2011-04-20
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 * @Modified by none
 */
@SuppressWarnings("unchecked")
public class JsonStringUtilTools {
	private static Gson gson = new Gson();
	public JsonStringUtilTools() {
		super();
	}

	/**
	 * JavaBean转化为Json字符串
	 * 
	 * @param Object object 标准JavaBean基础信息实例
	 * 
	 * @return String json JavaBean-json格式字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-04-20
	 */
	public static String javaBeanToJsonString(Object object) {
		return gson.toJson(object);
	}

	/**
	 * 将标准JavaBean集合转化为Json字符串
	 * 
	 * @param Object obj JavaBean实例对象集合（List<T>,Vector<T>,T[]）
	 * 
	 * @return String json JavaBean实例对象集合的Json格式字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String collectionToJsonString(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * Json格式JavaBean字符串还原为JavaBean实例对象
	 * 
	 * @param String beanString Json格式JavaBean字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Object obj JavaBean实例对象
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> T jsonStringToJavaBean(String beanString, Class<T> beanClass) {
		beanString = beanString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return gson.fromJson(beanString, beanClass);
	}

	/**
	 * 将Json格式的JavaBean数据集合字符串转化为标准JavaBean实例对象集合
	 * 
	 * @param String jsonString Json格式的JavaBean数据集合字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Collection<T> collection JavaBean对象实例集合
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> List<T> jsonStringToCollection(String jsonString, Class<T> beanClass) {
		jsonString = jsonString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return (ArrayList<T>) gson.fromJson(jsonString, beanClass);
	}

	/**
	 * 将Json格式的JavaBean数据集合字符串转化为标准JavaBean实例对象数组
	 * 
	 * @param String jsonString Json格式的JavaBean数据集合字符串
	 * 
	 * @param Class <T> beanClass=JavaBean类类型
	 * 
	 * @return Object bean[] JavaBean对象实例数组
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static <T> T[] jsonStringToArray(String jsonString, Class<T> beanClass) {
		jsonString = jsonString.replaceAll("'null'", "''").replaceAll("\"null\"", "\"\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
		return (T[]) gson.fromJson(jsonString, beanClass);
	}
}