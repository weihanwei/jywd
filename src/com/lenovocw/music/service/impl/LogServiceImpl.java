package com.lenovocw.music.service.impl;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;

/**
 * 
 * jywd
 * @author zhangzhigao
 * 
 * copyright:Copyright@2013 代码工作室
 * 2015-9-7
 */

@Transactional
@Service(value = "LogServiceImpl")
public class LogServiceImpl implements LogService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public int saveOperation(String phone, String urlName, String url) throws Exception {
		
		String sql="insert into SYS_OPERATION_LOG(ID,PHONE,URL,NAME,TIME)" +//
				" values(SYS_OPERATION_LOG_sequence.nextval,'"+phone+"'" +
						",'"+url+"','"+urlName+"',sysdate)";

		return dao.execute(sql);
	}

	@Override
	public int down(String phone, String downAppName, String downAppId)throws Exception {
		
		String sql="insert into SYS_DOWNAPP_LOG(ID,DOWNAPPNAME,DOWNAPPID,PHONE,TIME,ISDEL)" +//
				" values(SYS_DOWNAPP_LOG_sequence.nextval,'"+downAppName+"'" +
						",'"+downAppId+"','"+phone+"',sysdate,'0')";

			return dao.execute(sql);
        
	}

	@Override
	public int interfacelog(String eid, String sid, String phone, String state,String msg) throws Exception {
		
		
		String sql="insert into SYS_INTERFACE_LOG(ID,EID,SID,PHONE,STATE,MSG,TIME)"+//
		           " values(SYS_INTERFACE_LOG_sequence.nextval,'"+eid+"','"+sid+"'" +//
		           ",'"+phone+"','"+state+"','"+msg+"',sysdate)";

		if(phone.equals("")){
			
			return 0;
			
		}else{
			
			return dao.execute(sql);
		}
			
		
	}

	@Override
	public int saveHandleLog(String NAME, String phone,String type, String state, String msg) {
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSS");
		Date date=new Date();
		String ORDERNO=format.format(date)+phone;
		
		String sql="insert into SYS_HANDLE(ID,NAME,PHONE,ORDERNO,TYPE,TIME,STATE,MSG)"+//
		           " values( SYS_HANDLE_sequence.nextval,'"+NAME+"','"+phone+"'" +//
		           ",'"+ORDERNO+"','"+type+"',sysdate,'"+state+"','"+msg+"')";
		
		return dao.execute(sql);
	}
}
