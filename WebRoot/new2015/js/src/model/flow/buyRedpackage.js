define(function(require) {
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
//    jy.innertHead('购买流量红包');
	addHerd('购买流量红包');
	jy.innertFood(1);
	
	changeNumber(1);
	
	access();
	
	ZHData.get("indexMSG",function(data){
			
		    var userMap=data;
		    phoneNumber=userMap.phone;
		    
	 });
});



function addHerd(name){
	var mainDiv=$("body > .main").first();
	
	var divObj="<div class='headBox'>"+//
                  "<div class='main'>"+//
                    "<div class='header'>"+//
                       "<span class='f-l' onclick='history.go(-2)'>返回</span>"+//
                       "<em>"+name+"</em>"+//
                    "</div>"+//
                  "</div>"+//
                "</div>";

    $("body").prepend(divObj);

	mainDiv.addClass("mainMarginTop");
}

function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
}
	
var number=1;//个数
	
var size=0;//大小

var priceNum=0;//价格

var phoneNumber;


function changeNumber(cnumber){
	
	number=cnumber;
	
	$.ajax({
        url: sys.getBaseUrl() + "flow/getSizesByNumber.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {number:number},
        success: function (data) {
           
        	var sizes="";
        	
        	var sizeno=2;
        	
        	for(var i =0;i<data.length;i++){
        		
        		if(i==0){
        			
        			size=data[i].FLOWSIZE;
        			
        			sizes=sizes+"<li class=\"f-l\">"+//
		                            "<input type=\"radio\" name=\"size\" id=\"size1\" onclick=\"changeSize("+data[i].FLOWSIZE+");\" checked/>"+//
		                            "<label for=\"size1\">"+data[i].FLOWSIZE+"M</label>"+//
		                        "</li>";
 
        			changePrice();
        			
        		}else{
        			
        			
        			
        			sizes=sizes+"<li class=\"f-l\">"+//
				                    "<input type=\"radio\" name=\"size\" onclick=\"changeSize("+data[i].FLOWSIZE+");\" id=\"size"+sizeno+"\"/>"+//
				                    "<label for=\"size"+sizeno+"\">"+data[i].FLOWSIZE+"M</label>"+//
				                "</li>";
        			
        			sizeno++;
        		}
        		
        	}
        	
        	
        	$("#sizes").html(sizes);
        }
    });
	
}

function changeSize(csize){
	
	size=csize;
	
	changePrice();
	
}


function changePrice(){
	
	 $.ajax({
	        url: sys.getBaseUrl() + "flow/changePrice.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {number:number,size:size},
	        success: function (data) {
              
	        	$("#price").html(data.PRICE+"元");
	        	priceNum=data.PRICE;
	        }
	    });
	
}

//add by hanwei 2015-10-23 检验用户是否勾选阅读按钮
var checkText = false ; 
function checkReadText(){
	var str = $('#buyRegText').is(':checked');
	if(str){
		checkText = true;
	}
}


function buy(){
//	if(!checkText){
//		alert("请先确认阅读阅读并理解此活动规则！");
//		return ;
//	}
	var str = $('#buyRegText').is(':checked');
	if(!str){
		msgTips("提示","请先阅读并理解此活动的规则！",function(){
			   
		   });
		return;
	}
	

	
	// var AGENTNO=sys.getHttpParam("AGENTNO");
	
	   //点击【确认购买流量红包】按钮时，弹出“现确认使用1358020***购买*个*M流量红包(费用合计*元)吗？”
		msgConfirm("提醒","<br><br><p align='center'>现确认使用"+phoneNumber+"购买"+number+"个"+size+"M流量红包(费用合计"+priceNum+"元)吗？</p>",function(){
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	   	
		var AGENTNO=sys.getHttpParam("AGENTNO");
		
		$.ajax({
	        url: sys.getBaseUrl() + "flow/buyRedEnvelope.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {number:number,size:size,AGENTNO:AGENTNO},
	        success: function (data) {
	        	   if(data.state=="1"){
	        		   msgTips("购买成功",data.successStr,function(){
	   	            	   jy.ReMoveUserMsg();
	        			   sys.goUrl(sys.getBaseUrl()+"new2015/flow/buySuccessed.jsp?orderId="+data.ID,1);
	        		   });
	        	   }else{
	        		   
	        		   msgTips("办理状态",data.msg,function(){});
	        		   
	        	   }
   
	        }
	    });
		
	});
	
}



