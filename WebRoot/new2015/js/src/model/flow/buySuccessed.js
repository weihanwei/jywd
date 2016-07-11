this.weChatShare;
this.weChatShare;
this.flowRedPacketId;
define([sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare",
        sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent"],function(require) {
	jy.innertFood(1);
	this.flowRedPacketId=decodeURI(decodeURI(sys.getHttpParam("orderId")));;
	this.weChatShare = require(sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare");
	this.share = require(sys.getBaseUrl() +"new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent");
   
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
 this.weChatShare.weChatShare.ready(wedChatShare,true);
});


function weChatCallBack(){
	msgTips("提示","点击页面右上角按钮，发送给好友",function(){
		   
	   });
}
function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
}

