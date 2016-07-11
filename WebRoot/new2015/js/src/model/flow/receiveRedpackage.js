define(function(require) {
	
//	jy.innertFood(1);
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
	getRedenvelopeInfo();
});

function cashRedpackage(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/cashRedpackage.jsp",0);

}

function getRedenvelopeInfo(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedenvelopeInfo.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$('#flowSize').html(data.FLOWSIZE);
        	$('#sendWechatName').html("（"+data.WECHATNAME+"）");
        }
    });

}
//function buyRedpackage(){
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri=http%3a%2f%2fjyyd.umeol.com%3a38020%2fjywd%2fflow%2ftotWebchatBuyRedPK.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
//}
function buyRedpackage(){
//	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
	alert("请先登录再进行红包购买！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
	
}
function toShareRedpackage(cnumber){
	flowRedPacketId = cnumber;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageInfo.jsp?orderId="+flowRedPacketId,0);
}

//查看领取详情
function getReceiveRedpackage(){
	alert("请先登录再进行更多查询！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/person/myReceiveRedpackets.jsp",0);
}