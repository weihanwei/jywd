package com.lenovocw.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统常量
 * 
 * @author 肖新慧
 * @link http://www.lenovo-cw.com
 * 
 * @version $Revision: 1.00 $ $Date: 2010-08-30
 */

public class Const {
	
	//Socket服务器信息
//	public static String HOST = "10.0.0.20";
//	public static int PORT = 2000;
	
	public static final int LISPORT = 20000; //本机Socket服务器监听端口
	
	public static final String STR_NUM_ZERO = "0";
	public static final String STR_NUM_ONE = "1";
	public static final String STR_NUM_TWO = "2";
	
	public static final String TYPE_SOCKET_XML = "0";
	public static final String TYPE_HTTP = "1";
	public static final String TYPE_SOCKET_MSG = "2";
	
	public static final int TRY_NUM = 4;
	public static final int INT_NUM_ZERO = 0;
	public static final int INT_NUM_TWO = 2;
	public static final int INT_NUM_FIVE = 5;
	
//	public static final String LOCAL_MUSIC_PRE = "http://113.105.167.152/jyclient/";
	
	public static String ROOTPATH = ""; //服务器部署路径
	public static String CLIENTVERSION = ""; //客户端版本
	public static String CONTEXTPATH = ""; //客户端版本
	
	//安全套接字连接超时时间
	public static final int CONNECT_TIMEOUT = 20*1000; // 20 seconds
	
	//缓冲大小
	public static final int BUFFER_SIZE = 1024 * 64;
	public static final int ARRAY_SIZE = 1024 * 8;
	
	//响应代码
	public static final int CODE_OK = 200;  //成功
	public static final int CODE_UNAUTHORIZED = 401;  //Unauthorized  客户试图未经授权访问
	public static final int CODE_SERVERERROR = 500;  //服务器端错误
	public static final int CODE_NETERROR = 404;  //网络异常
	public static final int CODE_REDIRECT = 301;  //跳转
	public static final int CODE_REDIRECT2 = 302;  //跳转
	public static final int CODE_TIMEOUT = 300;  //SESSION超时
	public static final ExecutorService THREADPOOL = Executors.newFixedThreadPool(500);
	
	public static final int RETRY_NUM = 3; //Socket发送失败，重试次数
	
	// redis 服务器HOST
//	public static  String CACHE_HOST = "";  // 正式库
//	public static final String CACHE_HOST = "113.105.167.160";  // 测试库
	// redis 服务器PORT
//	public static final int CACHE_PORT = 8888;
//	public static  int CACHE_PORT = 0;
	
	// 缓存成功
	public static final int CACHE_SUCCESS = 1;
	
	// 缓存失败
	public static final int CACHE_FAIL = -1;
	//分页数据在 request中的 key
	public static final String PAGER_KEY = "pager";
	
//	static{
//		try {
//			HOST = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			HOST = "127.0.0.1";
//		}
//	}
	
	public static final String AUTH_SIGN_CODE = "gzylxxkjyxgs";
	
	/**
	 * 月账单
	 */
	public static final String MONTH_BILL = "monthBill";
	/**
	 * 捐赠记录
	 */
	public static final String DONA = "dona";
	/**
	 * 兑换记录
	 */
	public static final String CONVERT = "convert";
	/**
	 * 存储记录
	 */
	public static final String MEMORY = "memory";
	/**
	 * 其他
	 */
	public static final String OTHER = "other";

	/**
	 * 闯关游戏
	 */
	public static final String CGYX = "cgyx";
	
	/**
	 * 旧的会员权益类型
	 */
	public static final String[] OldMember = {"会员权益","热门推荐","餐饮美食","时尚购物","休闲娱乐","生活服务","旅游酒店","超市便利店","文化教育","美容美发","数码家电"};
	/**
	 * 新的会员权益类型
	 */
	public static final String[] NewMember = {"热门","美食","购物","娱乐","生活","旅游","超市","文化","美容","数码"};

	/**
	 * 默认头像图片地址
	 */
	public static final String imager = "/picture/info-head.png";
	
	public static final String GUEST = "guest";
	
	public static final String GUEST_PASSWORD = "123456";
	
	public static final String DIANSHANG = "DIANSHANG";
	
}
