package com.lenovocw.utils;

/**
 * 请求响应数据
 * 
 * @author 肖新慧
 * @link http://www.lenovo-cw.com
 * 
 * @version $Revision: 1.00 $ $Date: 2010-08-30
 */

public class ResBean {
	
	private int status = 0; 
	private String data = "";	
	private int len = 0;
	
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public ResBean(){}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getData() {
		return (data == null ? "" : data);
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
