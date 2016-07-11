package com.lenovocw.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.bss.BssInterface;

public class BachHelp {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BachHelp.class);
	//批量插入的size 大小
	private int bachSize=500;
	//批量插入的参数
	@SuppressWarnings("rawtypes")
	private List bachArgs=new ArrayList();
	//批量参数设置接口
	private BssInterface bif;
	//插入计数器
	private int count=0;
	
	/**带参数构造
	 * @param bif 批量插入语句设置接口
	 */
	public BachHelp(BssInterface bif){
		this.bif=bif;
		this.bif.setBachArgs(bachArgs);
	}
	
	/**带参数构造
	 * @param bif 批量插入语句设置接口
	 * @param bachSize 批量插入缓冲大小
	 */
	public BachHelp(BssInterface bif,int bachSize){
		this.bif=bif;
		this.bif.setBachArgs(bachArgs);
		this.bachSize=bachSize;
	}

	/** 无参构造 **/
	public BachHelp() {
		
	}

	/**执行批量语句
	 * @return
	 */
	private int [] batchUpdate(){
		return batchUpdate(bif.getBachSql(), bif);
	}

	/**具体执行批量插入(启动事务)
	 * @param batchsql	批量插入语句(预编译)
	 * @param bss		批量插入语句(预编译)参数设置接口
	 * @return
	 */
	@Transactional
	private int [] batchUpdate(String batchsql, BatchPreparedStatementSetter bss){
		logger.debug("开始执行批量插入   大小"+bachArgs.size());
		try {
			int [] _count=SpringGetBeanUtil.getDao().batchUpdate(batchsql,bss);
			count+=_count.length;
			logger.debug("成功插入 "+_count.length+" 条数据");
			return _count;
		} catch (Exception e) {
			logger.debug("插入数据失败");
			e.printStackTrace();
			return null;
		}
	}


	
	/**把未保存数据库的数据保存入库	并返回一共保存多少条数据
	 * @return 一共批量插入成功多少条数据
	 */
	public int flush(){
		batchUpdate();
		logger.debug("此次一共插入 "+count+" 条数据");
		return count;
	}
	
	/**truncate table
	 * @param name 表名
	 * @return
	 */
	@Transactional
	public int truncateTable(String name){
		int count=0;
		try {
			logger.debug("truncate table "+name+" 开始");
			count=SpringGetBeanUtil.getDao().execute("truncate table "+name);
			logger.debug("truncate table "+name+" 成功");
			return count;
		} catch (Exception e) {
			logger.debug("truncate table "+name+" 失败");
			return count;
		}
	}
	
	/**获取批量插入大小
	 * @return
	 */
	public int getBachSize() {
		return bachSize;
	}

	public void setBachSize(int bachSize) {
		this.bachSize = bachSize;
	}

	public BssInterface getBif() {
		return bif;
	}

	public void setBif(BssInterface bif) {
		this.bif = bif;
		this.bif.setBachArgs(bachArgs);
	}

	
	/**添加一个jsonObject 对象(通过预编译参数设置接口解析为 预编译的参数)
	 * @param o	jsonObject对象
	 */
	@SuppressWarnings("unchecked")
	private void addJSONObject(JSONObject o){
		if(o!=null)
			bachArgs.add(o);
		if(bachArgs.size()>=bachSize){
			batchUpdate();
			bachArgs.clear();
		}
		
	}
	/**添加一个jsonObject 对象(通过预编译参数设置接口解析为 预编译的参数)
	 * @param jo	JSONObject对象
	 * @param key   给JSONObject  加上一个参数(key)
	 * @param value 给JSONObject  加上一个参数(value)
	 */
	private void addJSONObject(JSONObject jo, String key, Object value) {
		try {
			jo.put(key, value);
		} catch (Exception e) {}
		add(jo);
		
	}
	/**添加一个jsonArray 对象(通过预编译参数设置接口解析为 预编译的参数)
	 * @param ja	jsonArray对象
	 */
	private void addJSONArray(JSONArray ja) {
		if(ja!=null&&ja.length()>0){
			for(int i=0;i<ja.length();i++){
				try {
					add(ja.getJSONObject(i));
				} catch (Exception e) { }
			}
		}
	}
	
	/**添加一个jsonArray 对象(通过预编译参数设置接口解析为 预编译的参数)
	 * @param ja	jsonArray对象
	 * @param key   给jsonArray 中的 每个jsonObject 加上一个参数(key)
	 * @param value 给jsonArray 中的 每个jsonObject 加上一个参数(value)
	 */
	private void addJSONArray(JSONArray ja,String key,Object value) {
		if(ja!=null&&ja.length()>0){
			for(int i=0;i<ja.length();i++){
				try {
					JSONObject jo=ja.getJSONObject(i);
					add(jo, key, value);
				} catch (Exception e) { }
			}
		}
		
	}
	
	public void add(Object jsonObjectOrJsonArray, String key, Object value) {
		if(jsonObjectOrJsonArray instanceof JSONObject)
			addJSONObject((JSONObject)jsonObjectOrJsonArray, key, value);
		else if(jsonObjectOrJsonArray instanceof JSONArray)
			addJSONArray((JSONArray)jsonObjectOrJsonArray, key, value);
	}
	
	public void add(Object jsonObjectOrJsonArray) {
		if(jsonObjectOrJsonArray instanceof JSONObject)
			addJSONObject((JSONObject)jsonObjectOrJsonArray);
		else if(jsonObjectOrJsonArray instanceof JSONArray)
			addJSONArray((JSONArray)jsonObjectOrJsonArray);
	}
}
