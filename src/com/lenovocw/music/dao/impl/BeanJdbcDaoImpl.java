package com.lenovocw.music.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.lenovocw.music.dao.BeanJdbcDao;

/**
 * 基于Spring配置数据源模式的数据库持久化事务管理接口实现类，查询结果根据特定的Bean进行处理和封装
 * 
 * @Author xiaoxh
 * @Version 1.0.0
 * @JDK version used 6.0
 */
@SuppressWarnings("unchecked")
public class BeanJdbcDaoImpl<T> extends JdbcDaoSupport implements BeanJdbcDao<T> {

	/** 无参构造方法 */
	public BeanJdbcDaoImpl() {
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

	/**
	 * 批量执行DML-SQL语句（Insert、Delete、Update）
	 * 
	 * @param String dmlSql[] Insert、Delete或Update语句数组集合
	 * 
	 * @return int[] int[i]值表示执行第i条SQL语句影响数据库记录条数
	 * 
	 * @author xiaoxh
	 */
	public int[] executeBatch(String dmlSql[]) {
		return this.getJdbcTemplate().batchUpdate(dmlSql);
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
	 * 执行DDL-SQL检索语句获取单条数据记录并封装成指定格式的JavaBean对象
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @param Class beanClass JavaBean.Class
	 * 
	 * @return Bean 封装成制定格式的JavaBean对象
	 * 
	 * @author xiaoxh
	 */
	public T queryForBean(String ddlSql, Class<T> beanClass) {
		try {
			return (T) this.getJdbcTemplate().queryForObject(ddlSql, new BeanPropertyRowMapper(beanClass));
		} catch (EmptyResultDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时未能获取到预期数据［Spring-jdbc-EmptyResultDataAccessException］----------");
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("----------根据查询语句获取数据时返回数据记录条数超过预期条数［Spring-jdbc-IncorrectResultSizeDataAccessException］----------");
		}
		return null;
	}

	/**
	 * 执行DDL-SQL检索语句获取数据记录集合并根据指定格式的JavaBean类型封装成List集合
	 * 
	 * @param String ddlSql SQL检索语句
	 * 
	 * @param Class beanClass JavaBean.Class
	 * 
	 * @return List<Bean> 指定JavaBean实例集合
	 * 
	 * @author xiaoxh
	 */
	public List<T> queryForList(String ddlSql, Class<T> beanClass) {
		return this.getJdbcTemplate().query(ddlSql, new BeanPropertyRowMapper(beanClass));
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
	public List<T> queryForListByPage(String ddlSql, int startIndex, int pagesize, Class<T> beanClass) {
		ddlSql = "select rst3.* from (select rst2.* from (select rst1.*,rownum as rankId from (" + ddlSql + ") rst1)rst2)rst3 where rst3.rankId>=" + (startIndex + 1) + " and rst3.rankId<=" + (startIndex + pagesize);
		return this.queryForList(ddlSql, beanClass);
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
		return null;
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
}
