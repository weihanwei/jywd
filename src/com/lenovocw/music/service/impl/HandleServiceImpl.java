package com.lenovocw.music.service.impl;





import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.HandleService;
import com.lenovocw.music.service.PersonService;

/**
 * 
 * jywd
 * @author zhangzhigao
 * 
 * copyright:Copyright@2013 代码工作室
 * 2015-9-7
 */
@Transactional
@Service(value = "handleService")
public class HandleServiceImpl implements HandleService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public List<Map<String, Object>> getHandleApp(String isBoutique) throws Exception {
		
		String sql="";
		
		if(isBoutique.equals("0")){
			
			sql="select * from SYS_APP_FUNCTION where ISSHOW=1 and TYPE in('2','3') order by NO ASC";
			
		}else{
			
			sql="select * from SYS_APP_FUNCTION where ISSHOW=1 and TYPE in('2','3') and ISBOUTIQUE='1' order by NO ASC";
			
		}
		
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	
}
