define(function(require) {
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
	jy.innertHead('流量红包');
	jy.innertFood(1);
	getMyshareRedpackage();
});


//查看领取详情
function getMyshareRedpackage(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getMyshareRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$('#redp1 tbody').html("");
        	if(data.length > 0){
        		$.each(data, function(i, item){     
//            		var status = changeStatus(item.RECEIVESTATUS);
//            	   var str = '<tr><td width="30%"><div class="redList">'+item.FLOWSIZE+"M</div></td>"+'<td width="20%">'
//            	   					+status+"</td><td width='50%'>有效期:"+item.VALIDITYTIME+"</td></tr>";
//            	   $('#redp1 tbody').append(str);
            	   
            	   if(item.SHARESTATUS == 0 ){
            		   var FSTR ="";
            			    FSTR  = " onClick=\"toShareRedpackage('"+item.ID+"','"+item.COUNTSTATUS+"')\"";
    	       			var str = '<tr '+FSTR+'><td width="30%" ><div class="redList">'+item.FLOWSIZE+"M</div><p>未分享</p></td>"+
    	       			'<td width="26%">'
       					+item.CREATEDATE+"</td><td width='44%'><p>有效期至"+item.VALIDITYTIME+"</p></td></tr>";
    	       			$('#redp1 tbody').append(str);
    	       		}else{
    	       			var q = item.QUANTITY;
    	           		var r = item.REMAININGNUMBER;
    	           		var no = 0;
    	           			no = q-r;
    	           		var status = changeStatus(item.RECEIVESTATUS);
    	           		var FSTR ="";
    		      			    FSTR  = " onClick=\"toShareRedpackage('"+item.ID+"','"+item.COUNTSTATUS+"')\"";
    	         	   var str = '<tr '+FSTR+'><td width="30%"><div class="redList">'+item.FLOWSIZE+"M</div><p>已领取"+no+"/"+item.QUANTITY+"个</p></td>"+'<td width="26%">'
    	         	   					+item.CREATEDATE+"</td><td width='44%'><p>有效期至"+item.VALIDITYTIME+"</p>"
    	         	   					+"</td></tr>";
    	         	   $('#redp1 tbody').append(str);
    	       		}
            	});        
        	}else{
        		var str = ' <tr><td >亲，您还未发起过红包哦！</td></tr >' ;
        		 $('#redp1 tbody').append(str);
        	}
        }
    });
}
function toShareRedpackage(cnumber,countStarus){
	var countStatus = countStarus;
	flowRedPacketId = cnumber;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/shareRedpackage.jsp?orderId="+flowRedPacketId+"&countStatus="+countStatus,1);
}

//更改状态
function changeStatus(data){
	if(data == '0'){
		return "未领取";
	}else if(data == '1'){
		return "已领取";
	}
}

function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/buyRedpackage.jsp",1);
//	var localUrl = sys.getBaseUrl()+"flow/totWebchatBuyRedPK.do";
//	localUrl = encodeURIComponent(localUrl) ;
//	window.document.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri="+localUrl+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
}

function selectredpNav(data){
	if(data == 'a'){
		$('#redpNavLi2').removeClass('on');
		$('#showTable2').attr("class","redpShow hide");
		$('#redpNavLi3').removeClass('on');
		$('#showTable3').attr("class","redpShow hide");
		$('#redpNavLi1').attr("class","on");
		$('#showTable1').attr("class","redpShow");
	}else if(data == 'b'){
		getMyReceviceRedpackage();
		$('#redpNavLi1').removeClass('on');
		$('#showTable1').attr("class","redpShow hide");
		
		$('#redpNavLi3').removeClass('on');
		$('#showTable3').attr("class","redpShow hide");
		
		$('#redpNavLi2').attr("class","on");
		$('#showTable2').attr("class","redpShow");
		
	}else if(data == 'c'){
		$('#redpNavLi1').removeClass('on');
		$('#showTable1').attr("class","redpShow hide");
		$('#redpNavLi2').removeClass('on');
		$('#showTable2').attr("class","redpShow hide");
		$('#redpNavLi3').attr("class","on");
		$('#showTable3').attr("class","redpShow");
		
	}
	
}



//查询我抢到的红包

function getMyReceviceRedpackage(){
	$.ajax({
        url: sys.getBaseUrl() + "flow/getMyReceviceRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	$('#redp2 tbody').html("");
        	if(data.length > 0){
        		$.each(data, function(i, item){ 
            		var id = item.ID;
            		var status = changeReceiveStatus(item.EXCHANGESTATUS);
            		var funStr = "";
            		var clickStr = "";
            		if(item.EXCHANGESTATUS == 0 && item.COUNTSTATUS == 0){
            			clickStr = ' onClick="'+"clashRedpackage('"+id+"');"+'"';
            			funStr = '<a href="javascript:"'+ clickStr+">"+status+"</a>";
            		}else{
            			clickStr = ' onClick="'+"toRedpackageInfo('"+id+"')"+'"';
            			funStr = '<a href="javascript:"'+ clickStr+">"+status+"</a>";
            		}
            	    var str = '<tr>><td width="30%"><div class="redList">'+item.SENDWECHATNAME+"的红包</div></td>"+'<td width="20%">'
            	   					+item.FLOWSIZE+"M</td><td width='50%'><div >"+funStr+"</div></td></tr>";
            	    $('#redp2 tbody').append(str);
            	});    
        	}else{
        		var str = ' <tr><td >亲，您还未领到过红包哦！</td></tr >' ;
        		$('#redp2 tbody').append(str);
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
function changeReceiveStatus(data){
	if(data == '0'){
		return "未兑换";
	}else if(data == '1'){
		return "已兑换";
	}else if(data == '2'){
		return "已过期未兑换";
	}
}