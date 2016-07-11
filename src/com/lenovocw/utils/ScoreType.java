package com.lenovocw.utils;

public enum ScoreType {
	 /**
	  * 说明参数
	  */
     USER_OUT_SCORE("2000","送出积分"),
     USER_IN_SCORE("2001","被捐积分"),
     USER_INVITE("18","邀请好友"),
     USER_FIRST_LOGIN("20","首次登陆"),
     ORDER_FLOW_PACKAGE("22","订购流量包"),
	 USER_STORE_FLOW("41","下载游戏"),
	 USER_STORE_FLOW_MOVIE("42","影视"),
	 USER_STORE_FLOW_MUSIC("43","下载音乐包"),
     USER_JUAN_FLOW("45","捐赠记录"),
     USER_IN_FLOW("46","赚流量"),
     USER_SCORE_WALL("47","积分墙"),
     USER_OTHER("50","其他"),
     SCORE_CONVERT_FLOW("998","积分换流量"),
     USER_TASK_ACTIVITY("999","任务活动"),
     USER_SIGN("1000","每日签到"),
     SCORE_CONVERT_GIFT("1001","积分兑换礼品"),
     USER_SHARE("1002","会员分享","5"),
     USER_LOTTERY("1003","大转盘抽奖"),
     USER_DATI("1005","答题积分"),
     USER_DATICG("1006","闯关积分"),
     USER_BAIBAO("1004","摇奖积分");
	 
      
     private String taskId;
     private String desc;
     private String score;
       
     private ScoreType(String taskId,String desc){
    	 this.taskId=taskId;
    	 this.desc=desc;
     }
     
     private ScoreType(String taskId,String desc,String score){
    	 this.taskId=taskId;
    	 this.desc=desc;
    	 this.score = score;
     }
     
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}  
    
}
