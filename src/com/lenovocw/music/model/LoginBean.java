package com.lenovocw.music.model;

/**
 * 用户登录bean
 * @author Administrator
 *
 */
public class LoginBean {
	private String phone;
	private String loginTime;
	private String loginIp ;
	private String qdcode;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	} 
	public String getQdcode() {
		return qdcode;
	}
	public void setQdcode(String qdcode) {
		this.qdcode = qdcode;
	} 
	
	

}
