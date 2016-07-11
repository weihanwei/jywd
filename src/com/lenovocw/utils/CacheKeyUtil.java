package com.lenovocw.utils;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-29  下午02:16:17 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class CacheKeyUtil{

	/** 缓存KEY分隔符**/
	private static final String SPLIT = "_";
	private static final String AUTH_CODE = "FSDX_AUTH";
	/** 用户动态KEY前缀**/
	private static final String USER_DYNAMIC = "fsdx_dyna";
	/** 首次任务KEY前缀**/
	private static final String FIRST_ACTION_TASK = "fsdx_firstaction" ;
	/** 每日任务KEY前缀 **/
	private static final String EVERY_DAY_TASK = "fsdx_everyday" ;
	/**  当日选取的任务KEY前缀**/
	private static final String FINISH_RECEIVE_TASK = "fsdx_finishreceive";
	
	private static final String HIT_EGGS = "fsdx_HitEggs";
	
	/**开通增值业务前缀**/
	private static final String INCREMENT_PREFIX = "fsdx_increment";
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  取得用户验证码的KEY
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-29  下午02:18:19 </span><br /> 
	 *
	 */
	public static String getUserAuthCode(String phone){
		return AUTH_CODE + phone;
	}
	
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  取用户动态信息Key
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *   type  动态类型<br />
	 *   userid 用户ID<br />
	 *   targetId 目标对象ID<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *  key
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-18  下午03:23:36 </span><br /> 
	 *
	 */
	public static String getUserDynamicKey( final int type, final int userid, final String targetId){
		return USER_DYNAMIC   + type + SPLIT + userid + SPLIT + targetId;
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  取用户动态信息Key
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *   type  动态类型<br />
	 *   userid 用户ID<br /> 
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *  key
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-18  下午03:23:36 </span><br /> 
	 *
	 */
	public static String getUserDynamicKey( final int type, final int userid){
		return USER_DYNAMIC  + SPLIT  + type + SPLIT + userid ;
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 * 第一次完成任务
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-27  下午10:34:13 </span><br /> 
	 *
	 */
	public static String getFirstActionTaskKey(final String userId, int taskId){
		return FIRST_ACTION_TASK  + SPLIT + userId + SPLIT + taskId;
	}
	
	/**
	 * 返回用户每日任务的Key
	 * ----------------
	 * userId 用户ID,
	 * taskId 任务ID
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-28  下午06:14:02 </span><br /> 
	 *
	 */
	public static String getEveryDayTaskKey(final String userId, int taskId){
		return EVERY_DAY_TASK + SPLIT + userId + SPLIT + taskId;
	}
	
	/**
	 * 每日选择选择任务的KEY
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-29  下午01:38:26 </span><br /> 
	 *
	 */
	public static String getFinishReceiveTask(final String userId){
		return FINISH_RECEIVE_TASK + SPLIT + userId;
	}
	
	/**
	 * 取得砸蛋时的Key
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-6-9  下午03:03:54 </span><br /> 
	 *
	 */
	public static String getHitEggsKeys(final String userId){
		return HIT_EGGS + SPLIT + userId;
	}
	
	public static String getIncrementKeys(final String imsidn,final String product_id){
		return INCREMENT_PREFIX + SPLIT + imsidn + SPLIT + product_id;
	}
	
	public static void main(String[] args) {
		System.out.println(getUserDynamicKey(1,12));
	}
	
	
}
