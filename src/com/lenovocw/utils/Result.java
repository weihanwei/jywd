package com.lenovocw.utils;

public class Result {

	/** 执行结果状态 **/ 
	private boolean status=false;
	/** 描述  **/ 
	private String desc="";
	/** 状态码  **/ 
	private String code="";
	/** 提示信息  **/
	private String msg="";
	/** 封装数据  **/
	private Object data=null;
	
	public Result() {

	}
	
	
	
	public Result(boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public Result(boolean status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Result(boolean status, String msg, String code) {
		super();
		this.status = status;
		this.msg = msg;
		this.code = code;
	}

	public Result(boolean status, String desc, String code, Object data) {
		super();
		this.status = status;
		this.desc = desc;
		this.code = code;
		this.data = data;
	}



	public Result(boolean status, String desc, String code, String msg) {
		super();
		this.status = status;
		this.desc = desc;
		this.code = code;
		this.msg = msg;
	}



	public Result(boolean status, String desc, String code, String msg,
			Object data) {
		super();
		this.status = status;
		this.desc = desc;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}



	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "status:["+this.status+"],desc:["+this.desc+"],code:["+this.code+"],data:["+data.toString()+"]";
	}
	
}