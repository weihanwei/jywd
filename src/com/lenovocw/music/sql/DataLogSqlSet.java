package com.lenovocw.music.sql;

/**
 * 基础日志保存sql
 * @author tanjin
 * @since 2012-04-10
 *
 */
public class DataLogSqlSet {
	public final static String INSERT_DATA_SQL = "INSERT INTO `TB_D_DATA_LOG`( USER_NUMBER, USER_ID ,USER_IP, OPERATION_DATE, BUSINESS_NAME,BUSINESS_ID,APPLICATION_SOURCE,RESULT,RESULT_DESC) " +
								"VALUES( #USER_NUMBER#,#USER_ID#, #USER_IP#, #OPERATION_DATE#, #BUSINESS_NAME#, #BUSINESS_ID#,#APPLICATION_SOURCE#,#RESULT#,#DESC#)";
}
