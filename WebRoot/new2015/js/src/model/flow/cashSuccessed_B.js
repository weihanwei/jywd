define(function(require) {
	jy.innertHead('流量红包兑换');
	jy.innertFood(1);
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
});


function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	var localUrl = sys.getBaseUrl()+"flow/totWebchatBuyRedPK.do";
//	localUrl = encodeURIComponent(localUrl) ;
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri="+localUrl+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
}


function toMyshareRedpackage(){
	alert("请先登录再进行更多查询！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageList.jsp",1);
	
}
function toShareRedpackage(cnumber){
	flowRedPacketId = cnumber;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageInfo_B.jsp?orderId="+flowRedPacketId,0);
}
