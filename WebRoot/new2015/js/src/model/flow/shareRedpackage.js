this.weChatShare;
this.weChatShare;
var redPacketId;
var countStatus;
define([sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare",
        sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent"],function(require) {
    jy.innertHead('流量红包');
    redPacketId=decodeURI(decodeURI(sys.getHttpParam("orderId")));
    countStatus=decodeURI(decodeURI(sys.getHttpParam("countStatus")));
    jy.innertFood(1);
	this.flowRedPacketId=decodeURI(decodeURI(sys.getHttpParam("orderId")));;
	this.weChatShare = require(sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare");
	this.share = require(sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent");
   
	
	 getRedpackage();
	 function wedChatShare(){
			var image = sys.getBaseUrl()+"new2015/images/flow/opentopImg.png";;
			var shareObject = {};
			shareObject.title="流量红包分享";
			shareObject.link=createShareUrl();
		    shareObject.imgUrl=image;
		    shareObject.desc="欢迎进入红包疯狂抢";
		    this.share.shareFun.shareLineTime(shareObject);
		}
		function createShareUrl(){
			var localUrl = sys.getBaseUrl()+"flow/toOpenRedpackage.do?redpackageId="+flowRedPacketId;
			localUrl = encodeURIComponent(localUrl) ;
			var webchatUrl1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri=";
			var webchatUrl2 = "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
			var retUrl = webchatUrl1+localUrl+webchatUrl2;
			return retUrl;
		}
		
	if(countStatus == '0'){
		this.weChatShare.weChatShare.ready(wedChatShare,true);
	}else{
		this.weChatShare.weChatShare.ready(null,true);
	}
});
function weChatCallBack(){
    msgTips("提示","点击页面右上角按钮，发送给好友",function(){
		   
	   });
}
function getRedpackage(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {redpackageId:redPacketId},
        success: function (data) {
        	$('#mainDiv').html("");
        	   if(data.SHARESTATUS == "0"){
        		   var shareStr = "";
        		   if(countStatus == 0){
        			   shareStr =  '<div class="pd1em">'+
				        	        '<a href="javascript:;" onClick="weChatCallBack()" class="aBtn aBtn-red">点击页面右上角按钮，继续转发红包</a>'+
					        	    '</div>';
        			}
        		   var str = '<div class="sharTip"><p>'+
		     	   	data.WECHATNAME+"的流量红包&nbsp;&nbsp;"+ data.CREATEDATES + '&nbsp;&nbsp;有效期至'+ data.VALIDITYTIMES + "</p><p>"+data.QUANTITY + "个" +data.FLOWSIZE+"MB流量红包，未分享</p></div>"+shareStr;
	        	   
        		   $('#mainDiv').append(str);
        		   
        	   }else{
        		    var q = data.QUANTITY;
	           		var r = data.REMAININGNUMBER;
	           		var no = 0;
	           			no = q-r;
	           		var liStr = getRedpackageRecevice(data,no);
	           		
        	   }
        }
    });
}
function getRedpackageRecevice(data2,no){
	var redpackageId = data2.ID;
	var strList = "";
	
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedpackageRecevice.do?redpackageId="+redpackageId,
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i, item){
	        	var status = changeReceiveStatus(item.EXCHANGESTATUS);
	        	strList += "<li><h3>"+item.RECEIVEWECHATNAME+"<span class='f-r'>"+item.FLOWSIZE+"M</span></h3><p>"+
	        				item.UPDATEDATE+"<span class='f-r yellow'>"+status+"</span></p></li>";
        	}); 
        	var str = '<div class="sharTip"><p>'+
				     	   	data2.WECHATNAME+"的流量红包&nbsp;&nbsp;"+data2.CREATEDATES +'&nbsp;&nbsp;有效期至'+data2.VALIDITYTIMES+"</p><p>"+data2.QUANTITY +"个"+data2.FLOWSIZE+"MB流量红包，已领取"+no+"个"+"</p></div>"+
				     	    '<div class="shareMain">'+
				     	    "<p>详细领取记录</p>"+
				     	    "<ul class='shareMList'>"+
				     	   strList+
				          "</ul>"+
			          '</div>';
     	   $('#mainDiv').append(str);
     	   if(data2.RECEIVESTATUS == "0" && countStatus == 0){
     		    var btnStr = '<div class="pd1em">'+
					  	        '<a href="javascript:;" onClick="weChatCallBack()" class="aBtn aBtn-red">点击页面右上角按钮，继续转发红包。</a>'+
					  	    '</div>';
				   $('#mainDiv').append(btnStr);
     	   }
        }
    });
}



function changeReceiveStatus(data){
	if(data == '0'){
		return "未兑换";
	}else if(data == '1'){
		return "已兑换";
	}else if(data == '2'){
		return "已过期未兑换";
	}
}
function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
//	var localUrl = sys.getBaseUrl()+"flow/totWebchatBuyRedPK.do";
//	localUrl = encodeURIComponent(localUrl) ;
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri="+localUrl+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
}