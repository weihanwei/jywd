package com.lenovocw.music.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.WeiXinUserService;

@Transactional
@Service(value = "weiXinUserService")
public class WeiXinUserServiceImpl implements WeiXinUserService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public Map<String, Object> queryWeiXinUserInfo(String openId, String phone) {

		Map<String, Object> result;

		StringBuffer sqlBuff = new StringBuffer(
				"select a.id,a.phone,a.openid,a.nickname,a.headimgurl from wx_user_info a where 1=1 ");
		if ((null != openId) && (!"".equals(openId))) {
			sqlBuff.append(" and a.openid='" + openId + "'");
		}
		if ((null != phone) && (!"".equals(phone))) {
			sqlBuff.append(" and phone='" + phone + "'");
		}

		sqlBuff.append(" order by a.modifytime desc");
		List<Map<String, Object>> listMap = dao.queryForListMap(sqlBuff
				.toString());
		if (null != listMap && (listMap.size() > 0)) {
			result = listMap.get(0);
		} else {
			result = new HashMap<String, Object>();
		}

		return result;
	}

	@Override
	public void save(String openId, String phone, String nickname,
			String headimgurl) {

		String sql;
		// 先查询用户信息是否存在
		Map<String, Object> weixinInfo = this.queryWeiXinUserInfo(openId, null);

		// 用户信息存在，则执行修改操作
		if (weixinInfo.containsKey("id")) {
			sql = "update wx_user_info set openId='" + openId + "',phone='"
					+ phone + "',nickname='" + nickname + "',headimgurl='"
					+ headimgurl + "',modifytime=sysdate where id="
					+ weixinInfo.get("id");
		} else {
			// 用户信息不存在，执行插入操作
			sql = "insert into wx_user_info(id,phone,openid,nickname,headimgurl,createtime,modifytime) values(SYS_WX_USER_SEQ.nextval,'"
					+ phone
					+ "','"
					+ openId
					+ "','"
					+ nickname
					+ "','"
					+ headimgurl + "',sysdate,sysdate)";
		}

		dao.execute(sql);
	}

	@Override
	public int countWeiXinUser(String openId, String phone) {
		int result = 0;

		StringBuffer sqlBuff = new StringBuffer(
				"select count(*) from wx_user_info a where 1=1 ");
		if ((null != openId) && (!"".equals(openId))) {
			sqlBuff.append(" and a.openid='" + openId + "'");
		}
		if ((null != phone) && (!"".equals(phone))) {
			sqlBuff.append(" and phone='" + phone + "'");
		}

		sqlBuff.append(" order by a.modifytime desc");

		result = dao.queryForInteger(sqlBuff.toString());
		return result;
	}

	@Override
	public int countWeiXinUserExceptOpenId(String openId, String phone) {
		int result = 0;

		StringBuffer sqlBuff = new StringBuffer(
				"select count(*) from wx_user_info a where 1=1 ");
		if ((null != openId) && (!"".equals(openId))) {
			sqlBuff.append(" and a.openid != '" + openId + "'");
		}
		if ((null != phone) && (!"".equals(phone))) {
			sqlBuff.append(" and phone='" + phone + "'");
		}
		result = dao.queryForInteger(sqlBuff.toString());
		return result;
	}

}
