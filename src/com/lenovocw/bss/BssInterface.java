package com.lenovocw.bss;

import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**预编译接口
 * @author linxiaohui
 *
 */
public interface BssInterface extends  BatchPreparedStatementSetter{
	public String getBachSql();

	@SuppressWarnings("rawtypes")
	public  void setBachArgs(List bachArgs);
}
