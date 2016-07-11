define(function(require) {
	
	 jy.innertFood(4);
	
     ZHData.get("indexMSG",function(data){
			
		    var userMap=data;
			
			var user="";

			var issm="";
			
			if(data.issm='1'){
				
				issm="(<em class=\"yellow\">已实名</em>)";
				
			}else{
				
				issm="(<em class=\"yellow\">未实名</em>)";
				
			}
			
			user="<div class=\"index-login index-login-in\" id=\"userMap\">"+//
                    "<div class=\"index-user-info\">"+//
	                    "<div class=\"ads-row\">"+//
		                    "<div class=\"ads-c-70 fem-10\">"+//
		                       "<span class=\"icon-index-user bg-cover\"></span>"+userMap.phone+issm+", 欢迎您 "+//
		                    "</div>"+//
			                 "<div class=\"ads-c-30 fem-10 pr\">"+//
			                    userMap.REMAININGGOLD+"金币  <span class=\"red pa indText\"></span>"+//
			                 "</div>"+//
	                    "</div>"+//
                   "</div>"+//
                   
			       "<div class=\"ads-row\">"+//
			           "<div class=\"ads-c-4\">"+//
			               "<div class=\"menuwrap\">"+//
			                   "<span class=\"bg-cover index-menu-icon menu-icon1\"></span>"+//
			                   "<span class=\"yellow\">话费余额</span><br>"+((userMap.hf)/100).toFixed(2)+"元"+//
			            "</div>"+//
			       "</div>"+//
	           
		           "<div class=\"ads-c-4\">"+//
		               "<div class=\"menuwrap\">"+//
		                   "<span class=\"bg-cover index-menu-icon menu-icon2\"></span>"+//
		                   "<span class=\"yellow\">流量余量</span><br>"+((userMap.ll)/1024).toFixed(2)+"M"+//
		               "</div>"+//
		            "</div>"+//
		            
		           "<div class=\"ads-c-4\">"+//
		                "<div class=\"menuwrap\">"+//
		                   "<span class=\"bg-cover index-menu-icon menu-icon3\"></span>"+//
		                   "<span class=\"yellow\">剩余语音</span><br>"+userMap.yy+"分钟"+//
		               "</div>"+//
		           "</div>"+//
		           
			      "</div>"+//
			   "</div>";
			   
			   $("#user").html(user);
			   
	});

	 //客户消息
    $.ajax({
		url :sys.getBaseUrl()+"notice/readMessagesMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			if(data.total.unread_count>0){
				$("#hi").html(data.total.unread_count);
				
			}
			   
		  }
	
    });
	
});

/**
 * 套餐使用情况
 */
function comboDetails(){
	
	var uuid=jy.getUserUuid();

	jy.G("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/personal/service/detail.jsps?servCode=PACKAGEMANAGEMENT&uuid="+uuid,0,'我的套餐', '');
	
}

/**
 * 已办理
 */
function myHandle(){
	
	var uuid=jy.getUserUuid();
	
	jy.G("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/personal/queryServerFunc/index.jsps?uuid="+uuid,1,'已办理', '');
}

/**
 * 我的金币
 */
function myGold(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/myGold.jsp",1,'我的金币', '');
}

/**
 * 我的账单
 */
function myBill(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/myBill.jsp",1,'我的账单', '');
}

/**
 * 我要做店长
 */
function toBeShopManager(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/toBeShopManager.jsp?data="+new Date(),1,'我要做店长', '');
}

/**
 * 我的电子优惠卷
 */
function myCoupon(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/myCoupon.jsp",1,'我的电子优惠卷', '');
}

/**
 * 我发出的红包
 */
function myReceiveRedpackets(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/myReceiveRedpackets.jsp",1,'我发出的红包', '');
	
}

/**
 * 我收到的红包
 */
function mySendRedpackets(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/mySendRedpackets.jsp",1,'我收到的红包', '');
	
}

/**
 * 我的下载
 */
function myDownload(){

	jy.G(sys.getBaseUrl()+"new2015/person/myDownload.jsp",1,'我的下载', '');
}

/**
 * 我的优惠
 */
function myPrivilege(){
	
	jy.G(sys.getBaseUrl()+"new2015/person/myPrivilege.jsp",1,'我的优惠', '');
	
}

/**
 * 我的服务密码
 */
function servicePassword(){
	
	var uuid=jy.getUserUuid();
	
	jy.G("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/personal/password/pwdDetail.jsps?servCode=UPDATE_PASSWORD&_v=20150915175519&uuid="+uuid,1,'我的服务密码', '');

}

/**
 * 退出
 */
function loginOut(){
	
	msgConfirm("提醒","<br><br><p align='center'>确定要退出登录吗？</p>",function(){
		
	    $.ajax({
	        url: sys.getBaseUrl() + "person/loginOut.do",
	        type: "post",
	 	    cache : false, 
		    async : false, 
	        dataType: "json",
	        data: '',
	        success: function (data) {
	        	jy.ReMoveUserMsg();
	        	
	        	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",0);

	        }
	    });
		
	});
		
	
}

function messages(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/messages.jsp",1,'消息', '');
	
}

function Search(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/search.jsp",1,'搜索', '');
	
}
