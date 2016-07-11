package com.lenovocw.music.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.NoticeService;

/**
 * 
 * jywd
 * 
 * @author zhangzhigao
 * 
 *         copyright:Copyright@2013 代码工作室 2015-9-7
 */

@Transactional
@Service(value = "NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public List<Map<String, Object>> getMessages(String phone) throws Exception {
		String sql = "SELECT sys_notice.id as nid,sys_notice_msg.id as mid,sys_notice.title,sys_notice.content"
				+ //
				" FROM sys_notice,sys_notice_msg WHERE sys_notice_msg.phone = '"
				+ phone
				+ "' and sys_notice_msg.ISREAD = '0'"
				+ //
				" and sys_notice_msg.nid = sys_notice.id and sys_notice.ISIMPORTANCE=1 order by sys_notice.STARTTIME desc";
		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public Map<String, Object> noReadMessagesMSG(String phone) {
		Map<String, Object> result = new HashMap<String, Object>();

		String sql = "select a.id,a.title,a.content from sys_notice a where a.isshow = 1 and a.isimportance = 1 and (a.istoall = 1 or (a.istoall = 0 and exists (select d.* from sys_notice_group        d, sys_noticegrouprelation e,  sys_notice_phone        f where d.id = e.groupid and e.noticeid = a.id  and d.type = 'XXZX' and d.id = f.groupid and f.phone = '"
				+ phone
				+ "'))) and not exists (select * from USER_READ_NOTICE b where b.noticeid = a.id and b.phone = '"
				+ phone
				+ "') and not exists (select * from USER_DELETE_NOTICE c where c.noticeid = a.id and c.phone = '"
				+ phone + "') order by a.createtime desc";

		List<Map<String, Object>> listMap = dao.queryForListMap(sql);

		if (null != listMap && listMap.size() > 0) {
			result.put("noticeCount", listMap.size());
			result.put("noticeInfo", listMap.get(0));
		} else {
			result.put("noticeCount", 0);
		}

		return result;
	}

	@Override
	public Map<String, Object> getMessagesById(String id){
		String sql = "select a.id,"
				+ " a.title,a.content, a.type,a.isimportance,to_char(a.createtime,'yyyy-mm-dd hh24:mi:ss')as CREATETIME, to_char(a.starttime,'yyyy-mm-dd hh24:mi:ss')as starttime ,to_char(a.endtime,'yyyy-mm-dd hh24:mi:ss')as endtime " +
						" from  SYS_NOTICE a where a.id='"+id+"' and a.isshow=1";
		Map<String, Object> map = dao.queryForMap(sql);
		return map;
	}

	@Override
	public List<Map<String, Object>> getMessagesByType(String phone, String type)
			throws Exception {
		
		
		String sql = "select a.id,"
				+ " a.title,a.content, a.type,a.isimportance,to_char(a.createtime,'yyyy-mm-dd hh24:mi')as CREATETIME, a.starttime,a.endtime,(select count(1)"
				+ "  from USER_COLLECTION_NOTICE b  where b.noticeid = a.id and b.phone = '"
				+ phone
				+ "') as collCount "
				+ ",(select count(1) from USER_READ_NOTICE g where g.noticeid= a.id and g.phone = '"
				+ phone + "') as readCount "
				+ " from sys_notice a  where a.isshow = 1 ";
		if ((null != type) && (!"".equals(type))) {
			sql += " and  a.type='" + type + "'";
		}

		sql += " and (a.istoall = 1 or (a.istoall = 0 and exists (select d.*  from sys_notice_group        d,  sys_noticegrouprelation e,    sys_notice_phone        f"
				+ " where d.id = e.groupid  and e.noticeid = a.id  and d.type = 'XXZX' and d.id = f.groupid and f.phone = '"
				+ phone
				+ "')))"
				+ " and not exists (select *  from USER_DELETE_NOTICE c  where c.noticeid = a.id and c.phone = '"
				+ phone + "') order by a.createtime desc ";

		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}
	
	
	@Override
	public List<Map<String, Object>> getMyCollMessages(String phone) {
		String sql = "select a.id,"
			+ " a.title,a.content, a.type,a.isimportance,to_char(a.createtime,'yyyy-mm-dd hh24:mi')as CREATETIME, a.starttime,a.endtime,1 as collCount "
			+ ",(select count(1) from USER_READ_NOTICE g where g.noticeid= a.id and g.phone = '"
			+ phone + "') as readCount "
			+ " from sys_notice a , USER_COLLECTION_NOTICE b where a.isshow = 1 and b.phone='"+phone+"' and b.noticeid=a.id";
		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}

	/** 获取一共有多少消息，未读 */
	public Map<String, Object> getMessagesCount(String phone) {
		Map<String, Object> result = new HashMap<String, Object>();
		String total_count_sql = "select count(1) from sys_notice a where a.isshow = 1  and (a.istoall = 1 or (a.istoall = 0 and exists      (select d.*"
				+ "   from sys_notice_group  d,   sys_noticegrouprelation e,   sys_notice_phone  f where d.id = e.groupid  and e.noticeid = a.id   and d.type = 'XXZX'  and d.id = f.groupid  and f.phone = '"
				+ phone
				+ "')))"
				+ " and not exists (select *    from USER_DELETE_NOTICE c where c.noticeid = a.id   and c.phone = '"
				+ phone + "') ";
		int total_count = dao.queryForInteger(total_count_sql);

		String unread_count_sql = "select count(1) from sys_notice a where a.isshow = 1  and (a.istoall = 1 or (a.istoall = 0 and exists      (select d.*"
				+ "   from sys_notice_group  d,   sys_noticegrouprelation e,   sys_notice_phone  f where d.id = e.groupid  and e.noticeid = a.id   and d.type = 'XXZX'  and d.id = f.groupid  and f.phone = '"
				+ phone
				+ "')))"
				+ " and not exists (select *   from USER_READ_NOTICE b where b.noticeid = a.id   and b.phone = '"
				+ phone
				+ "') and not exists (select *    from USER_DELETE_NOTICE c where c.noticeid = a.id   and c.phone = '"
				+ phone + "') ";
		int unread_count = dao.queryForInteger(unread_count_sql);

		result.put("total_count", total_count);
		result.put("unread_count", unread_count);

		return result;
	}

	@Override
	public void read(String id,String phone) throws Exception {

		String querySql="select * from USER_READ_NOTICE a where a.NOTICEID='"+id+"' and a.phone='"+phone+"'";
		
		List<Map<String, Object>> listMap = dao.queryForListMap(querySql);
		if((null ==listMap) || listMap.size()==0)
		{
			//
			String insertSql = "insert into USER_READ_NOTICE(id,noticeid,phone,READDATE) values (SYS_READ_NOTICE_SEQ.nextval,'"+id+"','"+phone+"',"+"sysdate)";
			dao.execute(insertSql);
		}
			
	  

	}

	/** 收藏消息 */
	public int collectNotices(String id,String phone) {
		
		//删除掉已有的收藏记录
		
		String deleteSql ="delete from USER_COLLECTION_NOTICE a where a.NOTICEID='"+id+"' and a.phone='"+phone+"'";
		
		dao.execute(deleteSql);
		
		
		//重新生成收藏记录
		String insertSql = "insert into USER_COLLECTION_NOTICE(id,noticeid,phone,coldate) values (SYS_COLLECTION_NOTICE_SEQ.nextval,'"+id+"','"+phone+"',"+"sysdate)";
		return dao.execute(insertSql);
	}

	/** 删除消息 */
	public int delById(String id, String phone) {
		

	    int result ;
 
		String deleteNoticeSql ="delete from USER_COLLECTION_NOTICE a where a.NOTICEID='"+id+"' and a.phone='"+phone+"'";
		dao.execute(deleteNoticeSql);
		String insertSql = "insert into USER_DELETE_NOTICE(id,noticeid,phone,DELETEDATE) values (SYS_DELETE_NOTICE_SEQ.nextval,'"+id+"','"+phone+"',"+"sysdate)";
		result =dao.execute(insertSql);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getMessagesType(String phone) {

		String sql = "select t.value, t.label,  (select count(1) from sys_notice a where a.isshow = 1 "
				+ " and a.type = t.value  and (a.istoall = 1 or  (a.istoall = 0 and exists (select d.*    from sys_notice_group        d,       sys_noticegrouprelation e,"
				+ "   sys_notice_phone   f  where d.id = e.groupid   and e.noticeid = a.id   and d.type = 'XXZX'  and d.id = f.groupid     and f.phone = '"
				+ phone
				+ "')))"
				+ " and not exists (select *  from USER_DELETE_NOTICE c where c.noticeid = a.id and c.phone = '"
				+ phone
				+ "')) noticeCount from sys_dict t where t.type = 'noticetype'  and t.del_flag = 0 order by t.sort asc";

		List<Map<String, Object>> listMap = dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public int countCollMessage(String phone) {
		int result=0;
		String sql="select count(a.id)from USER_COLLECTION_NOTICE a, SYS_NOTICE b where a.noticeid = b.id and b.isshow = 1 and a.phone = '"+phone+"'";
		result =dao.queryForInteger(sql);
		return result;
	}

	@Override
	public void cancelCollectNotices(String id, String phone) {
			String deleteNoticeSql ="delete from USER_COLLECTION_NOTICE a where a.NOTICEID='"+id+"' and a.phone='"+phone+"'";
			dao.execute(deleteNoticeSql);
	}



}
