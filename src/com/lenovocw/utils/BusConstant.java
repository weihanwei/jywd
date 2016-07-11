package com.lenovocw.utils;

/**
 * 业务相关的常量
 * @author ZengZS
 *
 */
public class BusConstant {

	/*********************************参数*********************************************/
	/** 字符串类的参数，为null时，不允许为null 给的默认参数值 , 以判断参数错**/
	public static final String PARAM_STR_ISNULL_ERR = "-1";
	
	/** 字符串类的参数，为null时，允许为null,给默认值为"" **/
	public static final String PARAM_STR_ISNULL_EMPTY = "";
	
	/** 字符串类的参数，为null时，允许为null,给默认值为"" **/
	public static final String PARAM_STR_ISNULL_NULL = null;
	
	/*********************************参数*********************************************/
	
	/*********************************返回值*********************************************/
	/**
	 * 成功
	 */
	public static final String RESULT_SUCESS = "1";
	
	/**
	 * 失败
	 */
	public static final String RESULT_ERROR = "-1";
	
	/**
	 * 参数错误失败
	 */
	public static final String RESULT_PARAMS_ERROR = "-2";
	
	/**
	 * 成功，已存在
	 */
	public static final String RESULT_VALUE_EXISTS = "2";
	
	
	/*********************************返回值*********************************************/
	
	/*********************************幸运19*********************************************/
	/** 某期号开奖结束  **/
	public  final static String BET_VERSION_OVER = "1";
	/** 某期号开奖未结束  **/
	public  final static String BET_VERSION_NOOVER = "0";
	/*********************************幸运19*********************************************/
	
	/**********************************订购业务*******************************************/
	/** 流量包业务 **/
	public final static String ORDER_FLOW_BUS_TYPE = "1";
	
	/** 增值业务订购 **/
	public final static String ORDER_INCREMENT_BUS_TYPE = "0";
	
	/**********************************订购业务*******************************************/
	
	
	/**********************************缓存相关*******************************************/
	/** 查看业务，增加积分的Key**/
	public final static String CACHE_PRE_ADD_SCORE = "AD_SCORE";
	
	/**兑奖积分的Key**/
	public final static String CACHE_PRIZE_SCORE = "PZ_SCORE";
	/**缓存30分钟内有效**/
	public final static int CACHE_PRIZE_AUTH_CODE_VOLIDTIME = 60;
	/** 每日可选取任务数**/
	public final static int EVERY_CHOOSE_TASK_NUM = 5;
	
	
	/**********************************缓存相关*******************************************/
	
	
	/**********************************动态相关*******************************************/
	/**增加评论**/
	public final static int DYNAMIC_TYPE_ADDMUCICCOMMENT = 1;
	/**听歌**/
	public final static int DYNAMIC_TYPE_LISTENMUSIC = 2;
	/**下载振铃、全曲**/
	public final static int DYNAMIC_TYPE_DOWNLOADMUSIC = 3;   
	/** 订购（设置）彩铃**/
	public final static int DYNAMIC_TYPE_SETMUSIC = 4;  
	/** 点歌  ---  现在换成分享   **/
	public final static int DYNAMIC_TYPE_THEMUSIC = 7;  
	/** 收藏歌单 **/
	public final static int DYNAMIC_TYPE_STOREMUSIC = 9;  
	/**分享歌曲 **/
	public final static int DYNAMIC_TYPE_SHAREMUSIC = 10;  
	/**开通彩铃**/
	public final static int DYNAMIC_TYPE_OPENMUSIC = 12;  
	/**赠送彩铃**/
	public final static int DYNAMIC_TYPE_PRESENTMUSIC = 14;  
	/**19手工投注**/
	public final static int DYNAMIC_TYPE_BET19 = 19;  
	/** 发贴子  **/
	public final static int DYNAMIC_TYPE_CREATEBBS = 21;
	/** 加入	圈子**/ 
	public final static int DYNAMIC_TYPE_JOINCYCLE = 5;
	/** 发表话题**/
	public final static int DYNAMIC_TYPE_TOPIC = 22;
	/** 发表评论**/
	public final static int DYNAMIC_TYPE_COMMENT = 23;
	/** 开通增值业务**/
	public final static int DYNAMIC_TYPE_INCREMENT = 24;
	/** 下载应用 **/
	public final static int DYNAMIC_TYPE_DOWNLOADAPPS = 25;
	/** 下载音乐包 **/
	public final static int DYNAMIC_TYPE_DOWNLOADRES = 26;
	/**********************************动态相关*******************************************/
	
	/**********************************任务相关*******************************************/
	/**任务对象ID**/
	public static final String TASK_OBJECTID = "TASK_OBJECTID";
	
	/**完成任务对象ID之间的分隔**/
	public static final String TASK_OBJECTID_SPLIT = ",";
	
	
	/**********************************任务相关*******************************************/
}
