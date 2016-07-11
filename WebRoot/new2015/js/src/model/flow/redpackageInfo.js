this.weChatShare;
this.weChatShare;
define([],function(require) {
    redPacketId=decodeURI(decodeURI(sys.getHttpParam("orderId")));
	 getRedpackage();
	 jy.innertHead('流量红包领取详情');
	 var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
		wx.hideOptionMenu();
});
var redPacketId;


function getRedpackage(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {redpackageId:redPacketId},
        success: function (data) {
        	$('#mainDiv').html("");
        		    var q = data.QUANTITY;
	           		var r = data.REMAININGNUMBER;
	           		var no = 0;
	           			no = q-r;
	           		var liStr = getRedpackageRecevice(data,no);
	           		
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
	        				item.UPDATEDATE+"<span class='f-r'>"+status+"</span></p></li>";
        	}); 
        	var str = '<div class="shareMain whiteBg"><p>'+
         	data2.WECHATNAME+"的流量红包&nbsp;&nbsp;"+data2.CREATEDATES +'&nbsp;&nbsp;有效期至'+data2.VALIDITYTIMES+"</p><p> "+data2.QUANTITY +"个"+data2.FLOWSIZE+"MB流量红包，已领取"+no+"个"+"</p></div>"+
				     	    '<div class="pl-1em pr-1em">'+
				     	    "<p class='myRecTitle'>详细领取记录</p>"+
				     	    "<ul class='mySendRedp noDetails is-load-occ'>"+
				     	   strList+
				          "</ul>"+
			          '</div>';
     	   $('#mainDiv').append(str);
        }
    });
}



function changeReceiveStatus(data){
	if(data == '0'){
		return "未兑换";
	}else if(data == '1'){
		return "已兑换";
	}else if(data == '2'){
		return "已过期";
	}
}
