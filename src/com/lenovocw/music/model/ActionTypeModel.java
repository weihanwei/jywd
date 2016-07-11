package com.lenovocw.music.model;


public class ActionTypeModel {
	
	private String ACTION_TYPE;
	private Integer ZHOUQI;
	private Integer SCORE;
	private Integer JINBI;
	private Integer MAXSCORE;
	private String INFO;
	private String PREFIX;
	//'1 动态,\r\n2 给操作用户发消息 \r\n3 给toSUer发消息  \r\n4 user toUser 都发送消息\r\n12 动态 和  user\r\n13 动态 和  touser\r\n14 动态 和  user双方'
	private Integer TYPE;
	public String getACTION_TYPE() {
		return ACTION_TYPE;
	}
	public void setACTION_TYPE(String aCTION_TYPE) {
		ACTION_TYPE = aCTION_TYPE;
	}
	public Integer getZHOUQI() {
		return ZHOUQI;
	}
	public void setZHOUQI(Integer zHOUQI) {
		ZHOUQI = zHOUQI;
	}
	public Integer getSCORE() {
		return SCORE;
	}
	public void setSCORE(Integer sCORE) {
		SCORE = sCORE;
	}
	public Integer getJINBI() {
		return JINBI;
	}
	public void setJINBI(Integer jINBI) {
		JINBI = jINBI;
	}
	public Integer getMAXSCORE() {
		return MAXSCORE;
	}
	public void setMAXSCORE(Integer mAXSCORE) {
		MAXSCORE = mAXSCORE;
	}
	public String getINFO() {
		return INFO;
	}
	public void setINFO(String iNFO) {
		INFO = iNFO;
	}
	public Integer getTYPE() {
		return TYPE;
	}
	public void setTYPE(Integer tYPE) {
		TYPE = tYPE;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String pREFIX) {
		PREFIX = pREFIX;
	}
	
}
