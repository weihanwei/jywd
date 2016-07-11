package com.lenovocw.music.sql;

public class DynamicSqlSet {
	/**插入动态信息**/
	public static String I_DYNAMIC_BET ="INSERT INTO `tb_sns_userdynamicinfo` " +
			"( `USER_ID`, `DYNAMICINFOTYPE`, `INFORMATION`, `PREFIX`, `INSERTTIME`, `TOUSERID`, `TONICKNAME`, `OBJECTID`, `NICKNAME`, `OBJECTNAME`, `FROMER` ) " +
			"VALUES" +
			"( #USER_ID#, #DYNAMICINFOTYPE#, #INFORMATION#, #PREFIX#, NOW(), #TOUSERID#, #TONICKNAME#, #OBJECTID#, #NICKNAME#, #OBJECTNAME#, #FROMER# );"; 
	
	/** 判断当天是否已经加入过该圈子 */
	public static String I_DYNAMIC_ADDTODAY = "SELECT COUNT(*) FROM tb_sns_userdynamicinfo d " +
			"WHERE USER_ID = '#USER_ID#' AND OBJECTID = '#OBJECTID#' AND date_format(INSERTTIME, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d') AND STATUS = 1";
	
	/** 设置当天已经加入过该圈子 */
	public static String I_DYNAMIC_AREADYADDTODAY = "UPDATE tb_sns_userdynamicinfo SET STATUS = #STATUS# " +
			"WHERE USER_ID = '#USER_ID#' AND OBJECTID = '#OBJECTID#' AND date_format(INSERTTIME, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')";
} 