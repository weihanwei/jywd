package com.lenovocw.music.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.UserBuyPackageService;

/**
 * 
 * jywd
 * 
 * @author zhangzhigao
 * 
 *         copyright:Copyright@2013 代码工作室 2015-9-7
 */

@Transactional
@Service(value = "UserBuyPackageServiceImpl")
public class UserBuyPackageServiceImpl implements UserBuyPackageService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public int saveBuyPackageInfo(String packageId, String phone)
			throws Exception {
		String sql = "insert into USER_RECEIVE_FLOWPACKAGE(ID,USERPHONE,FLOWPACKAGEID,RECEIVETIME)"
				+ " values(SYS_URECEIVE_FLOWPACKAGE_SEQ.nextval,'"
				+ phone
				+ "'" + ",'" + packageId + "',sysdate)";

		return dao.execute(sql);
	}

	@Override
	public int countPackagesById(String packageId) {

		int result = 0;
		String sql = "select (a.pcount - (select count(1) from user_RECEIVE_flowpackage b where b.FLOWPACKAGEID = a.id)) packCount from  sys_flow_package a where a.id='"
				+ packageId + "'";
		// 查询剩余的流量包数量
		result = dao.queryForInteger(sql);
		return result;
	}

	@Override
	public boolean isUserBuyPackage(String phone) {

		boolean result = false;

		String sql = "select count(1) from user_RECEIVE_flowpackage a  where a.USERPHONE = '"
				+ phone + "'";
		// 查询用户购买的流量包数量
		if (dao.queryForInteger(sql) > 0) {
			result = true;
		}
		return result;
	}

}
