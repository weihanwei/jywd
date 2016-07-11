package com.lenovocw.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * 拼装SQL
 * 
 * @Copyright lenovo-cw
 * 
 * @Project AppMusicServer
 * 
 * @Author ZengZs
 * 
 * @timer 2012-02-16
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 * @Modified by none
 */
public class DataBaseSqlTools {

	public DataBaseSqlTools() {
		super();
	}

	/**
	 * 组装SQL
	 * 
	 * @param source  SQL
	 * 
	 * @param criteriaMap  参数 
	 *  
	 * @author ZengZs
	 * 
	 * @time 2012-02-16
	 * @return
	 */
	public static String constractSql(String source,Map<String, Object> criteriaMap){
		if (source == null) {
			return source;
		}
		Iterator<Map.Entry<String,Object>> iter = criteriaMap.entrySet().iterator(); 
		while (iter.hasNext()) { 
			Map.Entry<String,Object> entry =  iter.next(); 
			String key =(String)entry.getKey(); 
			
			
			Object obj = entry.getValue(); 
			if(obj instanceof String){
				String value=entry.getValue().toString().replaceAll("\\$", "\\\\\\$");
				if(source.indexOf("'#" + key + "#'")!=-1)
					source = source.replaceAll("#" + key + "#", value);
				else
					source = source.replaceAll("#" + key + "#", "'"+value+"'");
			}else{
				if("".equals(obj)||obj==null)
					source = source.replaceAll("#" + key + "#","NULL");
				else
					source = source.replaceAll("#" + key + "#", obj.toString());
			}
		
		} 
		return source;
//		return  source.replaceAll("'null'", "''").replaceAll(",,", ",null,").replaceAll("=,", "=null,");
	}
	

	/**
	 * 构建数据库持久化SQL语句
	 * 
	 * @param String source 源SQL语句
	 * 
	 * @param String paramName 参数名称
	 * 
	 * @param String paramValue 参数值
	 * 
	 * @return String target 目标SQL语句
	 * 
	 * @author ZengZs
	 * 
	 * @time 2012-02-16
	 */
	public static String constractSql(String source, String paramName, String paramValue) {
		if (source == null || paramName == null || paramValue == null) {
			return null;
		}
		paramName = "#" + paramName + "#";
		// 检测是否存在参数
		if (source.indexOf(paramName) == -1) {
			return null;
		}
		if (paramValue != null) {
			// 特殊符号“$”和单引号
			paramValue = paramValue.replaceAll("\\$", "\\\\\\$");
		}
		return source.replaceAll(paramName, paramValue).replaceAll("'null'", "''").replaceAll(",,", ",null,").replaceAll("=,", "=null,");
	}

	/**
	 * 构建数据库持久化SQL语句
	 * 
	 * @param String source 源SQL语句
	 * 
	 * @param String params[][] 其中params[i][0]是参数名称；paramspi][1]是参数值
	 * 
	 * @return String target 目标SQL语句
	 * 
	 * @author ZengZs
	 * 
	 * @time 2012-02-16
	 */
	public static String constractSql(String source, String params[][]) {
		if (source == null || params == null)
			return null;
		int length = params.length;
		for (int i = 0; i < length; i++) {
			params[i][0] = "#" + params[i][0] + "#";
			// 检测是否存在参数
			if (source.indexOf(params[i][0]) == -1) {
				return null;
			}
			params[i][1] = params[i][1].replaceAll("\\$", "\\\\\\$");
			source = source.replaceAll(params[i][0], params[i][1]);
		}
		return source.replaceAll("'null'", "''").replaceAll(",,", ",null,").replaceAll("=,", "=null,");
	}

	/**
	 * 构建数据库持久化SQL语句
	 * 
	 * @param String source 源SQL语句
	 * 
	 * @param Object obj JavaBean数据存储对象
	 * 
	 * @return String target 目标SQL语句
	 * 
	 * @author ZengZs
	 * 
	 * @time 2012-02-16
	 */
	public static String constractSql(String source, Object javaBean) {
		if (source == null || javaBean == null)
			return null;
		try {
			// 获取所有变量信息
			Field[] field = javaBean.getClass().getDeclaredFields();
			// 变量名称
			String name = null;
			String value = null;
			// 构建ToString方法返回字符串值
			for (int i = 0; i < field.length; i++) {
				// 单一安全性检查
				field[i].setAccessible(true);
				if (field[i].getName().equals("serialVersionUID")) {
					continue;
				} else {
					name = field[i].getName();
					if (source.indexOf("#" + name + "#") != -1) {
						if (field[i].get(javaBean) == null) {
							value = "";
						} else {
							value = field[i].get(javaBean).toString();
							value = value.replaceAll("\\$", "\\\\\\$");
						}
						if(field[i].getType()==java.lang.String.class){
							if(source.indexOf("'#" + name + "#'")!=-1)
								source = source.replaceAll("#" + name + "#", value);
							else
								source = source.replaceAll("#" + name + "#", "'"+value+"'");
						}else{
							if("".equals(value)||value==null)
								source = source.replaceAll("#" + name + "#","NULL");
							else
								source = source.replaceAll("#" + name + "#", value);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return source.replaceAll("'null'", "''").replaceAll(",,", ",null,").replaceAll("=,", "=null,");
	}

	/**
	 * 数据库统计记录数的工具函数
	 * 
	 * @param sql 基础数据库查询语句
	 * 
	 * @return String 组装统计数后的SQL
	 * 
	 * @author ZengZS
	 * 
	 * @time 2012-02-16
	 */
	public static String constractQueryCount(String sql) {
		return "SELECT COUNT(*) FROM ( " + sql + " ) ";
	}
	
	
	
	/**
	 * Oracle 分页sql语句生成方法
	 * 
	 * @param sql sql语句
	 * 
	 * @param pageIndex 页下标
	 * 
	 * @param pageSize 页面大小
	 * 
	 * @return
	 * 
	 * @author ZengZs 
	 * 
	 * @time 2012-02-16
	 */
   public  static String createOraclePagingSql(String sql, int pageIndex, int pageSize){
	   int m = (pageIndex-1) * pageSize; 
       int n = m + pageSize; 
	   StringBuffer pagingSelect = new StringBuffer(100); 
	   pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ")
	   .append(sql) 
	   .append(" ) row_ where rownum <=")
	   .append(n)
	   .append(") where rownum_ >")
	   .append(m); 	   
	   return pagingSelect.toString(); 	
   }


}
