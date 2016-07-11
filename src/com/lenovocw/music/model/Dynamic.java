package com.lenovocw.music.model;

import java.util.Date;

public class Dynamic {
	private Integer ID;
	private Integer USER_ID;
	private Integer DYNAMICINFOTYPE;
	private String  INFORMATION;
	private String  PREFIX;
	private Date  INSERTTIME;
	private Integer TOUSERID;
	private String OBJECTID;
	private String NICKNAME;
	private String OBJECTNAME;
	private String FROMER;
	private String TONICKNAME;
	private String ACTION_TYPE;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(Integer uSER_ID) {
		USER_ID = uSER_ID;
	}
	public Integer getDYNAMICINFOTYPE() {
		return DYNAMICINFOTYPE;
	}
	public void setDYNAMICINFOTYPE(Integer dYNAMICINFOTYPE) {
		DYNAMICINFOTYPE = dYNAMICINFOTYPE;
	}
	public String getINFORMATION() {
		return INFORMATION;
	}
	public void setINFORMATION(String iNFORMATION) {
		INFORMATION = iNFORMATION;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String pREFIX) {
		PREFIX = pREFIX;
	}
	public Date getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(Date iNSERTTIME) {
		INSERTTIME = iNSERTTIME;
	}
	public Integer getTOUSERID() {
		return TOUSERID;
	}
	public void setTOUSERID(Integer tOUSERID) {
		TOUSERID = tOUSERID;
	}
	public String getOBJECTID() {
		return OBJECTID;
	}
	public void setOBJECTID(String oBJECTID) {
		OBJECTID = oBJECTID;
	}
	public String getNICKNAME() {
		return NICKNAME;
	}
	public void setNICKNAME(String nICKNAME) {
		NICKNAME = nICKNAME;
	}
	public String getOBJECTNAME() {
		return OBJECTNAME;
	}
	public void setOBJECTNAME(String oBJECTNAME) {
		OBJECTNAME = oBJECTNAME;
	}
	public String getFROMER() {
		return FROMER;
	}
	public void setFROMER(String fROMER) {
		FROMER = fROMER;
	}
	public String getTONICKNAME() {
		return TONICKNAME;
	}
	public void setTONICKNAME(String tONICKNAME) {
		TONICKNAME = tONICKNAME;
	}
	public String getACTION_TYPE() {
		return ACTION_TYPE;
	}
	public void setACTION_TYPE(String aCTION_TYPE) {
		ACTION_TYPE = aCTION_TYPE;
	}
	
}
