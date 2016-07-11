define(function(require) {
	
//	jy.innertFood(1);
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
});


function buyRedpackage(){
//	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
	alert("请先登录再进行红包购买！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
	
}


function toMyshareRedpackage(){
	alert("请先登录再进行更多查询！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageList.jsp",1);
	
}
function toShareRedpackage(cnumber){
	flowRedPacketId = cnumber;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageInfo.jsp?orderId="+flowRedPacketId,0);
}
//查看领取详情
function getReceiveRedpackage(data){
	$.ajax({
        url: sys.getBaseUrl() + "flow/.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {number:number,size:size},
        success: function (data) {
          
        	
        }
    });
}