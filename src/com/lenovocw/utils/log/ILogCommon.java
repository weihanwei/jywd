package com.lenovocw.utils.log;

/**
 * 日志保存接口类
 * @author tanjin
 * @since 2012-04-10
 *
 */
public interface ILogCommon {
	public void save(TDataLogDTO dto);
	public void file(TDataLogDTO dto);
}
