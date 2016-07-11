package com.lenovocw.music.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.DiscountService;
import com.lenovocw.music.service.LogService;

/**
 * 
 * jywd
 * 
 * @author zhangzhigao
 * 
 *         copyright:Copyright@2013 代码工作室 2015-9-7
 */
@Transactional
@Service(value = "discountServiceImpl")
public class DiscountServiceImpl implements DiscountService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;

	@Override
	public List<Map<String, Object>> getDiscount() throws Exception {

		String sql = "select ID, TILTLE,ICON,TYPE,URL,to_char(ENDTIME,'yyyy-mm-dd hh24:mi:ss') as ENDTIME"
				+ //
				" from sys_discount_tc where ISSHOW=1 order by NO ASC";

		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public List<Map<String, Object>> getDiscountById(String id) {
		String sql = "select ID, TILTLE,ICON,TYPE,URL,to_char(ENDTIME,'yyyy-mm-dd hh24:mi:ss') as ENDTIME,HTMLCONTENT  from sys_discount_tc where ISSHOW=1";
		if((null !=id) &&(!"".equals(id)))
		{
			sql+=" and ID='"+id+"'";
		}
		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}

}
