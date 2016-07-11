package com.lenovocw.music.model;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *  金币兑换记录
 *</span><br /><br />
 * <span> Author  XW </span><br /> 
 * <span> Create Time 2015-11-6 下午3:57:08  </span><br /> 
 * <span> Version 1.0.0 </span> <br />
 * <span> JDK version used 7.0 </span><br /> 
 */

public class ChangeRecord {
	private String id;
	private String phone;
	private String time;
	private int gold;
	private String changeCount;
	private String changeContent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public String getChangeCount() {
		return changeCount;
	}
	public void setChangeCount(String changeCount) {
		this.changeCount = changeCount;
	}
	public String getChangeContent() {
		return changeContent;
	}
	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}
	
}
