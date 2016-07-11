var AGENTNO="";
define(function(require) {
	jy.innertFood(2);
	//推荐代理号
	 AGENTNO= sys.getHttpParam("AGENTNO");
	
	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBannerByArea.do",
		type:"post",
		dataType:"json",
		data:{area:'4'},
		success :function(data){
			
			var banner="";
			
			 $.each(data,function(i,j){

				 var src=sys.getBaseUrl()+j.ICON;
				 
				 banner=banner+"<li class=\"swiper-slide\">"+//
                 "<img onclick=\"banner('"+j.ID+"','"+j.URL+"','"+j.TYPE+"','"+j.NAME+"');\" src="+src+">"+//
              "</li>";
		             
			   });
			
			 $("#banner").html(banner);
			 
			//加载滑动广告
		    jy.initSlider();
		}
	
    });
	 
});


function sumbitDD(){
	var type=$(".broadNav ul .on").attr("tvalue");
	if(type=="kd"){
		var kdzh=$("input[name='kdzh']").val();
		var sfz=$("input[name='sfz']").val();
		if(kdzh!=""&&sfz!=""){
			 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			   if(reg.test(sfz) === false)  
			   {  			      
			       $.alerts.alert("身份证输入不合法","提示");
			      
			   }else if(reg.test(sfz) === true){
				   sys.showLoading();
				   $.ajax({//判断用户是否登录成功
			    		url:sys.getBaseUrl()+"/onlinepay/userDL.do",
			    		type:"post",
			    		dataType:"json",
			    		data:{type:type,khzh:kdzh,sfz:sfz,AGENTNO:sys.getHttpParam("AGENTNO")},
			    		success:function(data){
			    			 sys.hideLoading();
			    			var status=data.status;
			    			if(status=="1"){
			    				window.location.href="paymentChoose.jsp";
			    			}else{
			    				$.alerts.alert(data.msg,"提示");
			    				//window.location.href="onlinePayment.jsp";
			    			}
			    			}
			    		});
			     
			   }  
		}else if(kdzh==""){
			$.alerts.alert("请输入宽带账号","提示");
		}else if(sfz==""){
			$.alerts.alert("请输入身份证号码","提示");
		}	
	}else if(type=="sj"){
		var sjhm=$("input[name='sjhm']").val();
		var yzm=$("input[name='yzm']").val();
		if(sjhm!=""&&yzm!=""){
			var telReg = !!sjhm.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
			if(telReg==false){
				$.alerts.alert("输入的手机号码有误","提示");
			}else if(telReg==true){//根据手机号码查询
				  sys.showLoading();
				  yzm = encrypt(yzm, key);
				 $.ajax({//判断用户是否登录成功
			    		url:sys.getBaseUrl()+"/onlinepay/userDL.do",
			    		type:"post",
			    		dataType:"json",
			    		data:{type:type,sjhm:sjhm,yzm:yzm,key: key},
			    		success:function(data){
			    			 sys.hideLoading();
			    			var status=data.status;
			    			if(status=="1"){
			    				window.location.href="paymentChoose.jsp";
			    			}else{
			    				
			    				 $.alerts.alert(data.msg,"提示");
			    			}
			    			
			    			}
			    		});
				
			}
		}else if(sjhm==""){
			$.alerts.alert("请输入手机号码","提示");
		}else if(yzm==""){
			$.alerts.alert("验证码不能为空","提示");
		}
		
	}
	
}


function queryDD(){
	
	var type=$(".broadNav ul .on").attr("tvalue");
	if(type=="kd"){
		var kdzh=$("input[name='kdzh']").val();
		var sfz=$("input[name='sfz']").val();
		if(kdzh!=""&&sfz!=""){
			 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			   if(reg.test(sfz) === false)  
			   {  			      
			       $.alerts.alert("身份证输入不合法","提示");
			      
			   }else if(reg.test(sfz) === true){
				   sys.showLoading();
				   $.ajax({//判断用户是否登录成功
			    		url:sys.getBaseUrl()+"/onlinepay/userbyorder.do",
			    		type:"post",
			    		dataType:"json",
			    		data:{type:type,broadbandAccount:kdzh},
			    		success:function(data){
			    			sys.hideLoading();
			    			var status=data.status;
			    			if(status=="1"){
			    				window.location.href="queryOrPay.jsp";
			    			}else{
			    				//window.location.href="onlinePayment.jsp";
			    				 $.alerts.alert(data.msg,"提示");
			    			}
			    			}
			    		});
			     
			   }  
		}else if(kdzh==""){
			$.alerts.alert("请输入宽带账号","提示");
		}else if(sfz==""){
			$.alerts.alert("请输入身份证号码","提示");
		}
	}else if(type=="sj"){

		var sjhm=$("input[name='sjhm']").val();
		var yzm=$("input[name='yzm']").val();
		if(sjhm!=""&&yzm!=""){
			
			var telReg = !!sjhm.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
			if(telReg==false){
				$.alerts.alert("输入的手机号码有误","提示");
			}else if(telReg==true){//根据手机号码查询
				 sys.showLoading();
				   $.ajax({//判断用户是否登录成功
			    		url:sys.getBaseUrl()+"/onlinepay/userbyorder.do",
			    		type:"post",
			    		dataType:"json",
			    		data:{type:type,broadbandAccount:sjhm,yzm:yzm},
			    		success:function(data){
			    			sys.hideLoading();
			    			var status=data.status;
			    			if(status=="1"){
			    				window.location.href="queryOrPay.jsp";
			    			}else{
			    				//window.location.href="onlinePayment.jsp";
			    				 $.alerts.alert(data.msg,"提示");
			    			}
			    			}
			    		});
			     
			   }  
		}else if(sjhm==""){
			$.alerts.alert("请输入手机号码","提示");
		}else if(yzm==""){
			$.alerts.alert("请输入短信验证码","提示");
		}
	
	}
}

function banner(id,url,type,name){
	
	var tourl="";
	
	if(type=="0"){
		tourl=sys.getBaseUrl()+url;
	}else{
		tourl=url;
	}
	sys.goUrl(sys.getBaseUrl()+"/new2015/index/messagesIframe.jsp?id="+id,0);
	
}

function getRandomyzm(obj){
	   
    var sjhm=$("#sjhm").val();
    var telReg = !!sjhm.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	if(telReg==false){
		$.alerts.alert("输入的手机号码有误","提示");
	}else if(telReg==true){//根据手机号码查询
		sjhm=encrypt(sjhm,key);
	}
	 
  	$.ajax({
		url :sys.getBaseUrl()+"broadband/getRandomyzm.do",
		type:"post",
		asyne :false,
		dataType:"json",
		data:{jyn:sjhm,key:key},
		success :function(data){
			
			if(data.status!='1'){
				$.alerts.alert("短息发送失败！","提示");
			}else{
				time(obj);
			}
			
		  }
   });
   
}

var wait = 60;
function time(obj) {

    if (wait == 0) {
        obj.setAttribute("onclick", "getRandomPassword(this);"); 
        obj.innerHTML = "<span class=\"fem-12\">点击获取</span>";
        wait = 60;
    } else {
    	obj.removeAttribute("onclick");
        obj.innerHTML = "重新发送(" + wait + ")";
        wait--;
        setTimeout(function () {
                time(obj);
            },
            1000);
    }
}

function  isNum(str){
  var re = /^[0-9]*$/;
  var flag=re.test(str);
  return flag;
}

function isPhone(str){
  var re = /^(1[0-9])\d{9}$/;
  var flag=re.test(str);
  return flag;
}
