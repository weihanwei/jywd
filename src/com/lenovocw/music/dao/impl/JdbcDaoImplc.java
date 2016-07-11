package com.lenovocw.music.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.init.InitSystemData;
import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.model.Dynamic;
import com.lenovocw.music.model.DynamicRule;
import com.lenovocw.music.sql.DynamicSqlSet;
import com.lenovocw.utils.CacheKeyUtil;
import com.lenovocw.utils.DataBaseSqlTools;
import com.lenovocw.utils.JedisPoolUtil;
import com.lenovocw.utils.Pager;

/**
 * 基于Spring配置数据源模式的数据库持久化事务管理接口实现类
 * 
 * @Author xiaoxh
 * @Version 1.0.0
 * @JDK version used 6.0
 */
public class JdbcDaoImplc extends JdbcDaoSupport implements JdbcDao {

	private static final Logger logger = LoggerFactory.getLogger(JdbcDaoImplc.class);
	private static final Logger scoreLogger = LoggerFactory.getLogger("score"); 
	
	/** 无参构造方法 */
	public JdbcDaoImplc() {
		super();
	}

	/**
	 * 执行单条DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql Insert、Delete或Update语句
	 * 
	 * @return int 执行SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	public int execute(String dmlSql) {
		return this.getJdbcTemplate().update(dmlSql);
	}
	
	public int execute(String dmlSql,Object ...args ) {
		return this.getJdbcTemplate().update(dmlSql, args);
	}

	/**
	 * 批量执行DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql[] Insert、Delete或Update语句数组集合
	 * 
	 * @return int[] int[i]值表示执行第i条SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	@Transactional
	public int[] executeBatch(String dmlSql[]) {
		return this.getJdbcTemplate().batchUpdate(dmlSql);
	}
	
	public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss){
		try {
		    getJdbcTemplate().getDataSource();
			return  getJdbcTemplate().batchUpdate(sql, pss);
			
		} catch (Exception e) {
			System.out.println(pss);
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
       public DataSource getSource(){
	   return getJdbcTemplate().getDataSource();
       }

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
	public int[] preparedStatement(String dmlSql, final Object paramList[][][]) {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement statement, int i) throws SQLException {
				// for (Object parameter[][] : paramList) {
				Object[][] parameter = paramList[i];
				int index = 1;
				for (Object param[] : parameter) {
					if (param[0] != null && param[1] != null) {
						if (param[1].toString().equals(Integer.class.getName())) {
							statement.setInt(index, Integer.parseInt(param[0].toString()));
							index = index + 1;
						} else if (param[1].toString().equals(Long.class.getName())) {
							statement.setLong(index, Long.parseLong(param[0].toString()));
							index = index + 1;
						} else if (param[1].toString().equals(Float.class.getName())) {
							statement.setFloat(index, Float.parseFloat(param[0].toString()));
							index = index + 1;
						} else if (param[1].toString().equals(String.class.getName())) {
							statement.setString(index, param[0].toString());
							index = index + 1;
						} else {
							throw new SQLException("PreparedStatement parameter format is error !");
						}
					}
				}
				// }
			}

			public int getBatchSize() {
				return paramList.length;
			}
		};
		return this.getJdbcTemplate().batchUpdate(dmlSql, setter);
	}

	/**
	 * 执行DDL-SQL检索语句获取数据记录集合并根据指定格式的JavaBean类型封装成List集合
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return List<Bean> 指定JavaBean实例集合
	 * 
	 * @author xiaoxh
	 */
	public List<Map<String, Object>> queryForListMap(String ddlSql) {
//		return this.getJdbcTemplate().query(ddlSql, new BeanPropertyRowMapper(beanClass));
		List<Map<String, Object>> list =this.getJdbcTemplate().queryForList(ddlSql);
		List<Map<String, Object>>  newList = new ArrayList<Map<String,Object>>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String,Object> newMap = new HashMap<String,Object>();
				Map<String,Object> map = list.get(i);
				Iterator it = map.keySet().iterator();
				while(it.hasNext()){
					String key = (String) it.next();
					Object value= map.get(key);
					if(value==null){
						newMap.put(key, "");
					}else{
						newMap.put(key,value);
					}
				}
				newList.add(newMap);
			}
		}
		//return this.getJdbcTemplate().queryForList(ddlSql);
		return newList;
	}

	/**
	 * 执行DDL-SQL检索语句通过数据库分页模式获取数据记录集合并根据指定格式的JavaBean类型封装成List集合
	 * 
	 * 其中拼接SQL的地方[rownum as rank] 不能修改,修改后会对用户积分排行造成影响
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @param int startIndex 起始行号（包含）［起始索引从零开始计算］
	 * 
	 * @param int pagesize 数据库页面容量
	 * 
	 * @param Class beanClass JavaBean.Class
	 * 
	 * @return List<Bean> 指定JavaBean实例集合
	 * 
	 * @author xiaoxh
	 */
	public List<Map<String, Object>> queryForListMapByPage(String ddlSql, int startIndex, int pageSize) {
//		Oracle版本
		ddlSql = "select rst3.* from (select rst2.* from (select rst1.*,rownum as rankId from (" + ddlSql + ") rst1)rst2)rst3 where rst3.rankId>=" + (startIndex + 1) + " and rst3.rankId<=" + (startIndex + pageSize);
//		MySQL版本
		//ddlSql = "select rst3.* from (select rst2.* from (select rst1.* from (" + ddlSql + ") rst1)rst2)rst3 limit " + (startIndex) + "," + (pageSize);
	//	ddlSql+=" limit " + (startIndex) + "," + (pageSize);
		return this.queryForListMap(ddlSql);
	}

	/**
	 * 执行DDL-SQL检索语句获取按照键值对形式存储的单条数据记录
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Map<K,V> 键值对数据记录集合[K columnName,V columnValue]
	 * 
	 * @author xiaoxh
	 */
	public Map<String, Object> queryForMap(String ddlSql) {
		try {
			return this.getJdbcTemplate().queryForMap(ddlSql);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时未能获取到预期数据［Spring-jdbc-EmptyResultDataAccessException］----------");
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时返回数据记录条数超过预期条数［Spring-jdbc-IncorrectResultSizeDataAccessException］----------");
		}
		return new HashMap<String, Object>();
	}

	/**
	 * 执行DDL-SQL检索语句获取一个短整
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Integer 短整数值
	 * 
	 * @author xiaoxh
	 */
	public Integer queryForInteger(String ddlSql) {
		try {
			return getJdbcTemplate().queryForInt(ddlSql);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时未能获取到预期数据［Spring-jdbc-EmptyResultDataAccessException］----------");
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时返回数据记录条数超过预期条数［Spring-jdbc-IncorrectResultSizeDataAccessException］----------");
		}
		return 0;
	}

	/**
	 * 执行SQL检索语句获取一个长整
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Long 长整数值
	 * 
	 * @author xiaoxh
	 */
	public Long queryForLong(String ddlSql) {
		try {
			return this.getJdbcTemplate().queryForLong(ddlSql);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时未能获取到预期数据［Spring-jdbc-EmptyResultDataAccessException］----------");
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时返回数据记录条数超过预期条数［Spring-jdbc-IncorrectResultSizeDataAccessException］----------");
		}
		return 0L;
	}

	/**
	 * 执行SQL检索语句获取一个字符串
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return String 字符串
	 * 
	 * @author xiaoxh
	 */
	public String queryForString(String ddlSql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	/**
	 * 执行SQL检索语句获取字符串列表
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return List<String> 字符串集合
	 * 
	 * @author xiaoxh
	 */
	public List<String> queryForListString(String ddlSql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

	/**
	 * 执行SQL检索语句获取N字符串
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Set<String> 字符串集合
	 * 
	 * @author xiaoxh
	 */
	public Set<String> queryForSetString(String ddlSql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		Set<String> set = new HashSet<String>();
		while (rs.next()) {
			set.add(rs.getString(1));
		}
		return set;
	}

	
	public Set<Integer> queryForSetInteger(String ddlSql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		Set<Integer> set = new HashSet<Integer>();
		while (rs.next()) {
			set.add(rs.getInt(1));
		}
		return set;
	}
	/**
	 * 执行SQL检索语句获取数据并将数据以键值对方式存储［第一列默认唯一标识］
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @return Map<String, String> 键值对数据集合
	 * 
	 * @author xiaoxh
	 */
	public Map<String, String> queryForMapString(String ddlSql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		Map<String, String> map = new LinkedHashMap<String, String>();
		while (rs.next()) {
			map.put(rs.getString(1), rs.getString(2));
		}
		return map;
	}

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
	public Map<String, String[]> queryForMapString(String ddlSql, int column) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(ddlSql);
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		String value[] = null;
		while (rs.next()) {
			value = new String[column];
			for (int i = 0; i < column; i++) {
				value[i] = rs.getString(i + 1);
			}
			map.put(rs.getString(1), value);
			value = null;
		}
		return map;
	}

	/**
	 * 获取序列的下一个值
	 * 
	 * @param String seqName 序列名称
	 * 
	 * @return String 序列值
	 */
	public String querySequenceNextVal(String seqName) {
		String sql = "select " + seqName + ".nextval from dual";
		return queryForString(sql);
	}

	/**
	 * 根据动态规则表，实现动态信息插入。
	 * 
	 * @param d
	 * @return
	 *
	 * @author ZengZS
	 *
	 * @time 2012-4-19
	 *
	 */
	public boolean addDynamicInfo(Dynamic d) {
		int dynamicCode = d.getDYNAMICINFOTYPE();
		String  dynamicCodeStr = String.valueOf(dynamicCode);
		if(!InitSystemData.dynamicRuleMap.containsKey(dynamicCodeStr)){
			logger.debug("---------------------not dynamic info . return operate success.-------------------");
			return Boolean.TRUE;
		}
		String dynamicKey = null;
		DynamicRule dynamicRule = InitSystemData.dynamicRuleMap.get(dynamicCodeStr);
		// 根据类型设置Key
		if(dynamicRule.getType() == -1){   // 处理特殊信息的Key
			dynamicKey = CacheKeyUtil.getUserDynamicKey(dynamicCode, d.getUSER_ID(), d.getOBJECTID());			
		}else {
			dynamicKey = CacheKeyUtil.getUserDynamicKey(dynamicCode, d.getUSER_ID());
		}

		if(dynamicRule.getType() == 2){ // 按次数判断
			String rsTimes = JedisPoolUtil.getResource().get(dynamicKey);
			int timesInt = 0; 
			if(StringUtils.isNotBlank(rsTimes)){
				timesInt = Integer.valueOf(rsTimes);
			}
			if( timesInt >= dynamicRule.getTimes()){
				logger.info("---------------- dynamic info times over . return operate success. -----------");
				return Boolean.TRUE;
			}
			boolean rsInsertData = inertDynamicData(d);
			if(rsInsertData){
				JedisPoolUtil.set(dynamicKey, String.valueOf(++timesInt));
				logger.debug("---------------- dynamic info times add one. retrurn operate success. -------");
				return Boolean.TRUE;
			}else {
				logger.info("---------------- dynamic info times over . return operate fail. -----------");
				return Boolean.FALSE;
			}
		}else if(dynamicRule.getType() == 3){// 按周期判断
			if(JedisPoolUtil.getResource().exists(CacheKeyUtil.getUserDynamicKey(dynamicCode, d.getUSER_ID()))){
				logger.info("---------------- dynamic info times over . return operate success. -----------");
				return Boolean.TRUE;
			}
			boolean rsInsertData = inertDynamicData(d);
			if(rsInsertData){
				JedisPoolUtil.set(dynamicKey, "1", dynamicRule.getSecondTime());
				logger.debug("----------------dynamic info second time . return operate success. ----------");
				return Boolean.TRUE;
			}else {
				logger.info("---------------- dynamic info times over . return operate fail. -----------");
				return Boolean.FALSE;
			}
		}else {
			boolean rsInsertData = inertDynamicData(d);
			if(rsInsertData){
				return Boolean.TRUE;
			}else {
				logger.info("---------------- dynamic info times over . return operate fail. -----------");
				return Boolean.FALSE;
			}
		}
		
	}
	
	private boolean inertDynamicData(Dynamic d){
		String sql=DataBaseSqlTools.constractSql(DynamicSqlSet.I_DYNAMIC_BET,d);
		return getJdbcTemplate().update(sql)>0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Pager queryForPage(String sql, String order, Pager page) {
		String queryCount="SELECT COUNT(*) from ("+sql+") as _t";
		if(page==null)
			page=new Pager();
		page.setTotalRows(this.queryForInteger(queryCount));
		if(order!=null&&!order.trim().equals(""))
			sql+=" "+order;
		List data= this.queryForListMapByPage(sql, page.getFirstRow(), page.getPageRows());
		page.setData(data);
		return page;
	}

	/**
	 * 增加扣除用户的银币
	 * @param userId
	 * @param score
	 * @param reason
	 * @return
	 *
	 * @author xiaoxh
	 *
	 * @time 2012-12-14
	 *
	 */

	@Override
	public int addScore(String userId, int score, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET SCORE = SCORE + (#score#),EXPERIENCE=EXPERIENCE+(#score#) WHERE USERID = #userId# "
				.replaceAll("#score#", score + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		//增加经验
		String jsql = "UPDATE TB_SNS_USERGRADE t SET t.GRADEVALUE=(SELECT EXP FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.GRADEID = (SELECT GRADEID FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.GRADENAME = (SELECT GRADENAME FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.NEXTGRADEEXPERIENCE = (SELECT EXP-t.EXPERIENCE FROM tb_sns_usergradedefine WHERE EXP>t.EXPERIENCE ORDER BY EXP LIMIT 0,1)";
		jsql += " WHERE t.USERID = #userId#";
		jsql = jsql.replaceFirst("#userId#", userId);
		int ss = this.execute(jsql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}
	
	@Override
	public int addScore(String userId, long score, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET SCORE = SCORE + (#score#),EXPERIENCE=EXPERIENCE+(#score#) WHERE USERID = #userId# "
				.replaceAll("#score#", score + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		//增加经验
		String jsql = "UPDATE TB_SNS_USERGRADE t SET t.GRADEVALUE=(SELECT EXP FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.GRADEID = (SELECT GRADEID FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.GRADENAME = (SELECT GRADENAME FROM tb_sns_usergradedefine WHERE EXP<=t.EXPERIENCE ORDER BY EXP DESC LIMIT 0,1),";
		jsql += " t.NEXTGRADEEXPERIENCE = (SELECT EXP-t.EXPERIENCE FROM tb_sns_usergradedefine WHERE EXP>t.EXPERIENCE ORDER BY EXP LIMIT 0,1)";
		jsql += " WHERE t.USERID = #userId#";
		jsql = jsql.replaceFirst("#userId#", userId);
		int ss = this.execute(jsql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}

	/**
	 * 增加扣除用户的银币
	 * @param userId
	 * @param score
	 * @param reason
	 * @return
	 *
	 * @author xiaoxh
	 *
	 * @time 2012-12-14
	 *
	 */
	@Override
	public int subScore(String userId, long score, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET SCORE = SCORE - (#score#) WHERE USERID = #userId# "
				.replaceFirst("#score#", score + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}
	
	/**
	 * 设置用户的银币
	 * @param userId
	 * @param score
	 * @param reason
	 * @return
	 *
	 * @author xiaoxh
	 *
	 * @time 2012-12-14
	 *
	 */
	@Override
	public int setScore(String userId, int score, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET SCORE = (#score#) WHERE USERID = #userId# "
				.replaceFirst("#score#", score + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}
	
	/**
	 * 设置现有金币
	 * @param userId
	 * @param score
	 * @param reason
	 * @return
	 *
	 * @author X230I
	 *
	 * @time 2013-8-22
	 *
	 */
	public int setGoldCoinScore(String userId, int score, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET GOLD_COIN = (#score#) WHERE USERID = #userId# "
				.replaceFirst("#score#", score + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}
	
	/**
	 * 增加用户金币
	 * @param userId
	 * @param gold_coin
	 * @param reason
	 * @return
	 *
	 * @author ibm
	 *
	 * @time 2013-8-20
	 *
	 */
	@Override
	public int addGoldCoin(String userId, int gold_coin, String reason) {
		String sql = "UPDATE TB_SNS_USERGRADE SET gold_coin =gold_coin+ (#gold_coin#) WHERE USERID = #userId# "
				.replaceFirst("#gold_coin#", gold_coin + "").replaceFirst("#userId#", userId);
		int suc = this.execute(sql);
		if(suc > 0)
			scoreLogger.warn(reason+ sql);
		return suc;
	}

	/**
	 * @param insertSql
	 * @return
	 *
	 * @author creali
	 *
	 * @time 2014-1-8
	 *
	 */
	@Override
	public int insertRetKey(String insertSql) {
		final String sql=insertSql;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				return ps;
			}
			
		},keyHolder);
		return keyHolder.getKey().intValue();
	}
	
}
