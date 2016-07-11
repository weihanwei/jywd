package com.lenovocw.utils.log;

import java.util.ResourceBundle;

/**
 * 根据配置文件获取写入日志的配置参数
 * @author tanjin
 * @since 2012-04-10
 *
 */
public final class LogConfig {
	// 写入日志配置
	protected static final ResourceBundle bundle = ResourceBundle.getBundle("accesslog");

	/**
	 * 是否采取文件追加形式
	 * 
	 * @return
	 */
	public boolean isFileAppend() {
		return "true".equals(bundle.getString("save.file.isAppend"));
	}

	/**
	 * 获取保存文件夹路径
	 * 
	 * @return
	 */
	public String getFileName() {
		return bundle.getString("save.file");
	}
	
	/**
	 * 获取保存日志模板
	 * 
	 * @return
	 */
	public String getSaveTemple() {
		return bundle.getString("message.format");
	}
	/**
	 * 清除缓存的总数
	 * 
	 * @return
	 */
	public int getClearCount() {
		return Integer
				.parseInt(bundle.getString("clearcache.count"));
	}

	/**
	 * 清除缓存的间隔时间
	 * 
	 * @return
	 */
	public static int getClearTime() {
		return Integer.parseInt(bundle.getString("root.time"));
	}
}
