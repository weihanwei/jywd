package com.lenovocw.utils;

public enum IcExecutionTime {
	/** 在业务处理器处理请求之前被调用  **/
	preHandle,
	/** 在业务处理器处理请求执行完成后,生成视图之前执行的动作  **/
	postHandle,
	/**
	 * 在DispatcherServlet完全处理完请求后被调用  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */
	afterCompletion
}
