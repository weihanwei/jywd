define(function(require) {
	
//	jy.innertFood(1);
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();	
	
});

//var flowRedPacketId="";//获取红包id
//var receiveWechatCode = "wwwww";//抢红包人微信号
//var receiveWechatName = "wei";//抢红包人微信昵称
//var exchangeMobile = "13724049459";//抢红包人微信绑定电话


function openRedpackage(data){
	flowRedPacketId = data;
	$.ajax({
        url: sys.getBaseUrl() + "flow/grabFlowRedenvelope.do",
        type: "post",
        asyne: false,
        dataType: "json",
//        data: {receiveWechatCode:receiveWechatCode,receiveWechatName:receiveWechatName,flowRedPacketId:flowRedPacketId,exchangeMobile:exchangeMobile},
        success: function (data) {
        	if(data.msgs == "success"){
        		 sys.goUrl(sys.getBaseUrl()+"new2015/flow/receiveRedpackage.jsp",0);
      	   }else{
      		   msgTips("提示",data.msgs,function(){
      			   if(data.status == "2"){
      				   sys.goUrl(sys.getBaseUrl()+"new2015/index/bindingPage.jsp",0);
      			   }
      		   });
      	   }   
        }
    });
	
}

function buyRedpackage(){
//	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
	alert("请先登录再进行红包购买！");
	sys.goUrl(sys.getBaseUrl()+"new2015/index/login.jsp",1);
	
}

function toShareRedpackage(cnumber){
	flowRedPacketId = cnumber;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageInfo.jsp?orderId="+flowRedPacketId,0);
}
