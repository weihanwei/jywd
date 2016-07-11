package com.lenovocw.music.model;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-4-18  下午03:38:27 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class DynamicRule{

	private String id;  
	private Integer code;   // 规则代码
	private String dynamicname;  // 规则名称 
	private Integer type;   // 类型  ：  1 无限制，2 次数限制  3 周期限制
	private Integer times;  // 次数  ： type = 2时有效
	private Integer secondTime;  // 周期时间  ： type = 3时有效
	private String descInfo;  // 描述

	/**
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 返回 code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param 对code进行赋值
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return 返回 dynamicname
	 */
	public String getDynamicname() {
		return dynamicname;
	}

	/**
	 * @param 对dynamicname进行赋值
	 */
	public void setDynamicname(String dynamicname) {
		this.dynamicname = dynamicname;
	}

	/**
	 * @return 返回 type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param 对type进行赋值
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return 返回 times
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * @param 对times进行赋值
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * @return 返回 secondTime
	 */
	public Integer getSecondTime() {
		return secondTime;
	}

	/**
	 * @param 对secondTime进行赋值
	 */
	public void setSecondTime(Integer secondTime) {
		this.secondTime = secondTime;
	}

	/**
	 * @return 返回 descInfo
	 */
	public String getDescInfo() {
		return descInfo;
	}

	/**
	 * @param 对descInfo进行赋值
	 */
	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

}
