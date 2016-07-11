define(function(require) {
	
//	jy.innertFood(1);
	getRedpackageUseTime();	
	getRedenvelopeInfo();
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
});


function cashRedpackage(){
	
	var mobileNo = $('#mobileNo').val();
	if(mobileNo == ""){
		alert("请输入正确的电话号码");
		return;
	}
	msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	//ajax请求
	$.ajax({
        url: sys.getBaseUrl() + "flow/cashRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {mobileNo:mobileNo},
        error: function (data) {
        	msgTips("错误信息","兑换失败",function(){});
        },
        success: function (data) {
        	if(data.status == "1"){
        		sys.goUrl(sys.getBaseUrl()+"new2015/flow/cashSuccessed.jsp",0);
        	}else if(data.status == "0"){
        		msgTips("提示信息",data.msgs,function(){});
        	}else if(data.status == "error"){
        		msgTips("错误信息","您的号码兑换失败，请查看兑换规则!",function(){});
        	}
        }
    });

}
//查询红包有效期
function getRedpackageUseTime(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedpackageUseTime.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	var text = "于"+data.time+"前兑换有效，兑换后流量立即生效，有效期至下一月结日。";
        	$('#TimeTEXT').html(text);
        }
    });

}


//查询红包详情
function getRedenvelopeInfo(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedenvelopeInfo.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$('#flowSize').html(data.FLOWSIZE);
        	$('#sendName').html("（来自好友"+data.WECHATNAME+"）");
        }
    });

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