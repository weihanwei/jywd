/*我的金币*/
define(function(require,wx) {
//	getMyReceviceRedpackage();
	jy.innertHead('我领到的红包');
	jy.innertFood(4);
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");
	if(wx){
		wx.hideOptionMenu();
	}
	getMyReceviceCallRedpackage();
	getMyReceviceRedpackage_new();
    //绑定面板间的切换事件
    jy.navShow(".myPriNav ul li", ".myShow");
    //绑定tab切换事件
 //   jy.switchTabList(".myRecRedp");
//	jy.switchTabList("#test1");
    //加载状态占位
    jy.loadOccInit();
    //详细领取记录
//    jy["pageAjax"]["receiveList1"] = function(){
//        var tempJSON = [{name:"Free Bird", flow:"10M", time:"2012-11-11 11:13:23", handle:"已兑换"},{name:"Free Bird", flow:"100M", time:"2012-11-11 11:13:23", handle:"已兑换"}];
//
//        setTimeout(function(){
//            $("#mySendRedp1").html("");
//            $("#tempreceiveList1").tmpl(tempJSON).appendTo('#mySendRedp1');
//        },2000);
 //   };

    //通话详细领取记录
    jy["pageAjax"]["receiveList2"] = function(){
        var tempJSON = [{name:"Free Bird", flow:"10M", time:"2012-11-11 11:13:23", handle:"已兑换"},{name:"Free Bird", flow:"100M", time:"2012-11-11 11:13:23", handle:"已兑换"}];

        setTimeout(function(){
            $("#mySendRedp2").html("");
            $("#tempreceiveList2").tmpl(tempJSON).appendTo('#mySendRedp2');
        },2000);
    };
});


//function buyRedpackage(){
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri=http%3a%2f%2fjyyd.umeol.com%3a38020%2fjywd%2fflow%2ftotWebchatBuyRedPK.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
//}
function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
//	var localUrl = sys.getBaseUrl()+"flow/totWebchatBuyRedPK.do";
//	localUrl = encodeURIComponent(localUrl) ;
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri="+localUrl+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
}

function sendCallRed(){
	
	jy.G("http://221.179.101.131/jsp/activity/huawu/index.jsp",0,'通话红包', '');
	
}
//新
//查询我领取到的红包
function getMyReceviceRedpackage_new(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/MyReceviceRedpackageStatus.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	if(data.length > 0){
        		$.each(data, function(i, item){  
            		var id = item.RID;
            		var status = changeReceiveStatus(item.EXCHANGESTATUS);
            		var clickStr = "";
            		if(item.EXCHANGESTATUS == 0){
            			clickStr = ' onClick="'+"clashRedpackage('"+id+"')"+'"';
            		}else{
            			clickStr = ' onClick="'+"toRedpackageInfo('"+id+"')"+'"';
            		}
            	    var str = "<div class='myRecRedp'"+clickStr+">"+
    					            "<h4>"+item.WECHATNAME+"的流量红包</h4>"+
    					            "<p>"+item.FLOWSIZE+"M   "+status+" 有效期至"+item.VDATE+
    					        "</p></div>";
            	    
            	   	$('#flowDiv').append(str).before($('#button'));
            	});    
        	}else{
        		var str = ' <div class="noMain">亲~您暂时还没有领到的红包哦！</div>' ;
        		$('#flowDiv').append(str).before($('#button'));
        	}
        }
    });
}


function toRedpackageInfo(data){
	var id = data;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/cashSuccessed_Q.jsp?receiveId="+id,1);
}
//
function clashRedpackage(data){
	var id = data;
	$.ajax({
        url: sys.getBaseUrl() + "flow/toClashRedpackageJSP.do?receiveId="+id,
        type: "post",
        asyne: false,
        dataType: "text",
        success: function (data) {
        	if(data == 'success'){
        		sys.goUrl(sys.getBaseUrl()+"new2015/flow/cashRedpackage_B.jsp",1);
        	}else{
        		alert("系统异常！");
        	}
        	
        	
        	
        }
    });
	
}


//查询我领取到的红包
function getMyReceviceRedpackage(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/MyReceviceRedpackageStatus.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	if(data.length > 0){
        		$.each(data, function(i, item){  
            		var id = item.ID;
            	    var str = "<div class='myRecRedp'"+' onClick="'+"showTable('"+id+"')"+'"'+">"+
    					            "<h4>"+item.WECHATNAME+"的流量红包<span class='f-r'>"+item.CREATEDATE+"</span></h4>"+
    					            "<p>"+item.QUANTITY+"个"+item.FLOWSIZE+"MB流量红包，已领取"+item.REMAININGNUMBER+"个</p>"+
    					        "</div>"+
            	    			"<div class='myRecDetails' style='display:none' id='"+id+"'>"+
    				                "<p class='myRecTitle'>详细领取记录</p>"+
    				                "<ul class='mySendRedp is-load-occ noAfter' id='"+id+"ul'>"+
    				                "</ul>"+
    				            "</div>";
            	    
            	   	$('#flowDiv').append(str).before($('#button'));
            	});    
        	}else{
        		var str = ' <div class="noMain">亲~您暂时还没有领到的红包哦！</div>' ;
        		$('#flowDiv').append(str).before($('#button'));
        	}
        	
        	
        	
        }
    });
}


function getMyReceviceCallRedpackage(){
	
	var param={};
	param.type=1;
	$.ajax({
        url: sys.getBaseUrl() + "call/getMyshareCallRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data:param,
        success: function (data) {
		
    	var state =data.state;
    	var callRedPackages = data.resultPackageList;
    	if(('0'==state) && callRedPackages.length>0)
    	{

        	$.each(callRedPackages, function(i, item){   
        		
        			var str ='<div class="myRecRedp" data-ajaxfnc="receiveList2" ><h4>'+item.mobileA+'的'+item.name+'红包<span class="f-r">'+item.time1+'</span></h4></div>';
        			
        			 $('#callDiv').append(str).before($('#button'));
        	}); 
    	}else
    	{
    		         $('#callDiv').append('<div class="noMain">亲~您暂时还没有领到的红包哦！</div>').before($('#button'));
        }
	}
    });
}

function getRedpackageRecevice(data){
	var redpackageId = data;
	$.ajax({
        url: sys.getBaseUrl() + "flow/getRedpackageRecevice.do?redpackageId="+redpackageId,
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$('#'+redpackageId+"ul").html("");
        	$.each(data, function(i, item){
	        	var status = changeReceiveStatus(item.EXCHANGESTATUS);
	        	var str = "<li><h3>"+item.RECEIVEWECHATNAME+"<span class='f-r'>"+item.FLOWSIZE+"M</span></h3><p>"+
	        				item.CREATEDATE+"<span class='f-r'>"+status+"</span></p></li>";
	        	$('#'+redpackageId+"ul").append(str);
        	});  
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

function showTable(id){
	getRedpackageRecevice(id);
	
	if($("#"+id).css('display') == "none"){
		$("#"+id).css('display','block'); 
	}else{
		$("#"+id).css('display','none'); 
	}
	
	
	
	
	
}


