package com.lenovocw.utils;

public class TaskHelp {
	/**对那类任务进行操作**/
	private Integer type;
	/**操作数值**/
	private int value=1;
	/**是否重写执行结果 (true 重写   false 追加)**/
	private boolean rewrite=Boolean.FALSE;
	/**requset 作用域中的key**/
	public static String key="TASKHELP";
	/**user requset作用域中的key**/
	public static String userKey="userKey";
	
	/**目标对象**/
	private String objectId;
	
	public TaskHelp(Integer type,Integer value,Boolean rewrite) {
		this.type=type;
		this.value=value;
		this.rewrite=rewrite;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean getRewrite() {
		return rewrite;
	}
	public void setRewrite(Boolean rewrite) {
		this.rewrite = rewrite;
	}

	/**
	 * @return 返回 objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param 对objectId进行赋值
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	
}
