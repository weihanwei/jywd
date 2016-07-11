package com.lenovocw.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lenovocw.music.dao.BeanJdbcDao;
import com.lenovocw.music.model.ActionTypeModel;

public class ActionTypeFactory {
	static Map<ActionType, ActionTypeModel> map=new HashMap<ActionType, ActionTypeModel>();
	
	BeanJdbcDao<ActionTypeModel> beanJdbcDao;
	
	public ActionTypeFactory() {
	
	}

	public BeanJdbcDao<ActionTypeModel> getBeanJdbcDao() {
		return beanJdbcDao;
	}

	public void setBeanJdbcDao(BeanJdbcDao<ActionTypeModel> beanJdbcDao) {
		this.beanJdbcDao = beanJdbcDao;
	}
	
	public void init(){
		List <ActionTypeModel> list=beanJdbcDao.queryForList("select * from tb_u_score_regular_bak_", ActionTypeModel.class);
		for(ActionTypeModel m:list){
			map.put(ActionType.valueOf(m.getACTION_TYPE()),m);
		}
	}
	
	public static ActionTypeModel getActionType(String key){
		return map.get(key);
	}
}
