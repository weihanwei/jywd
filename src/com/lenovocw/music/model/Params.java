package com.lenovocw.music.model;

/**
 * @author lin
 * 接口的参数
 * 
 */
public class Params {
	
	/** 默认值  	**/
	private String def;
	/** 默认类型  	**/
	private String def_type;
	/** 参数名	**/
	private String name;
	/** 参数描述	**/
	private String info;
	/** 参数id	**/
	private Integer id;
	/**	接口id	**/
	private Integer interface_id;
	
	private String  interface_url;
	
	public Params() {
	}
	
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDef_type() {
		return def_type;
	}

	public void setDef_type(String def_type) {
		this.def_type = def_type;
	}

	public Integer getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(Integer interface_id) {
		this.interface_id = interface_id;
	}

	public String getInterface_url() {
		return interface_url;
	}

	public void setInterface_url(String interface_url) {
		this.interface_url = interface_url;
	}
	
	
}
