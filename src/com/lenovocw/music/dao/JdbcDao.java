package com.lenovocw.music.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;



import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;



import com.lenovocw.music.model.Dynamic;
import com.lenovocw.utils.Pager;

/**
 * 数据库持久化事务管理接口，不封装成特定的Bean，对象封装成map，List<Bean>封装成List<Map>
 * 
 * @Author xiaoxh
 * @Version 1.0.0
 * @JDK version used 6.0
 */
public interface JdbcDao {
	 int execute(String dmlSql,Object ...args ) ;
	/**
	 * 执行单条DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql Insert、Delete或Update语句
	 * 
	 * @return int 执行SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	int execute(String dmlSql);
	  public DataSource getSource();
	/**
	 * 执行insertSql,并且返回自增的主键
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author creali </span><br /> 
	 * <span> Create Time  2014-1-8  下午4:49:23 </span><br /> 
	 *
	 */
	int insertRetKey(String insertSql);

	/**
	 * 批量执行DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql[] Insert、Delete或Update语句数组集合
	 * 
	 * @return int[] int[i]值表示执行第i条SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	int[] executeBatch(String dmlSql[]);

	/**
	 * 批量执行DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql Insert、Delete或Update语句
	 * 
	 * @param Object paramList[][] PreparedStatement对应的基础数据［paramList[n][m][0]=value,paramList[n][m][1]=classType（java.lang.Integer,java.lang.Long,java.lang.Float,java.lang.String）］
	 * 
	 * @return int 执行SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	int[] preparedStatement(String dmlSql, final Object paramList[][][]);

	/**
	 * 执行DDL-SQL检索语句获取数据记录集合并根据指定格式的JavaBean类型封装成List集合
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return List<Bean> 指定JavaBean实例集合
	 * 
	 * @author xiaoxh
	 */
	List<Map<String, Object>> queryForListMap(String ddlSql);

	/**
	 * 执行DDL-SQL检索语句通过数据库分页模式获取数据记录集合并根据指定格式的JavaBean类型封装成List集合
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @param int startIndex 起始行号（包含）［起始索引从零开始计算］
	 * 
	 * @param int pagesize 数据库页面容量
	 * 
	 * @return List<Bean> 指定JavaBean实例集合
	 * 
	 * @author xiaoxh
	 */
	List<Map<String, Object>> queryForListMapByPage(String ddlSql, int startIndex, int pageSize);

	/**
	 * 执行DDL-SQL检索语句获取按照键值对形式存储的单条数据记录
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Map<K,V> 键值对数据记录集合[K columnName,V columnValue]
	 * 
	 * @author xiaoxh
	 */
	Map<String, Object> queryForMap(String ddlSql);

	/**
	 * 执行DDL-SQL检索语句获取一个短整
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Integer 短整数值
	 * 
	 * @author xiaoxh
	 */
	Integer queryForInteger(String ddlSql);

	/**
	 * 执行SQL检索语句获取一个长整
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Long 长整数值
	 * 
	 * @author xiaoxh
	 */
	Long queryForLong(String ddlSql);

	/**
	 * 执行SQL检索语句获取一个字符串
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return String 字符串
	 * 
	 * @author xiaoxh
	 */
	String queryForString(String ddlSql);

	/**
	 * 执行SQL检索语句获取N字符串
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return List<String> 字符串集合
	 * 
	 * @author xiaoxh
	 */
	List<String> queryForListString(String ddlSql);

	/**
	 * 执行SQL检索语句获取N字符串
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Set<String> 字符串集合
	 * 
	 * @author xiaoxh
	 */
	Set<String> queryForSetString(String ddlSql);

	/**
	 * 执行SQL检索语句获取数据并将数据以键值对方式存储［第一列默认唯一标识］
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Map<String, String> 键值对数据集合
	 * 
	 * @author xiaoxh
	 */
	Map<String, String> queryForMapString(String ddlSql);

	/**
	 * 执行SQL检索语句获取数据并将数据以键值对方式存储［第一列默认唯一标识］
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @param int column 总列数
	 * 
	 * @return Map<String, String[]> 键值对数据集合
	 * 
	 * @author xiaoxh
	 */
	Map<String, String[]> queryForMapString(String ddlSql, int column);

	/**
	 * 获取序列的下一个值
	 * 
	 * @param String seqName 序列名称
	 * 
	 * @return String 序列值
	 * 
	 * @author xiaoxh
	 */
	String querySequenceNextVal(String seqName);
	
	Set<Integer> queryForSetInteger(String sql);
	
	int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss);
	
	boolean addDynamicInfo(Dynamic d);
	
	@SuppressWarnings("rawtypes")
	Pager queryForPage(String sql,String order,Pager page);
	
	/**  
	 * 增加扣除用户的银币
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author xiaoxh </span><br /> 
	 * <span> Create Time  2012-12-14  下午3:22:19 </span><br /> 
	 *
	 */
	int addScore(String userId, int score, String reason);
	int addScore(String userId, long score, String reason);
	int subScore(String userId, long score, String reason);
	int setScore(String userId, int score, String reason);
	int setGoldCoinScore(String userId, int score, String reason);
	int addGoldCoin(String userId, int score, String reason);
}
