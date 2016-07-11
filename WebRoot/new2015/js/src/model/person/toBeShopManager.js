/*我的金币*/
var AGENTNO="";
define(function(require) {

	jy.innertHead('我要做店长');
	
    jy.innertFood(4);
    
    
    //1.判断是否已登记
    $.ajax({
		
		url :sys.getBaseUrl()+"person/getShopManagerMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			if(data.ISSHOPMANAGER=='0'){
				
				$("#J_toBe,.popupbg").show();
				
			}else{
				
                $("#AGENTNO").text("代理编号:"+data.AGENTNO);
                AGENTNO=data.AGENTNO;
				$(".main").show();
				
				var obj=$(".searchNav").find(".on");
				
				flash(obj,4);
				
			}
			
		  }
	});

});


//获取随机密码
function getRandomPassword(obj) {

	 
   
     jyn = encrypt(jyn, key);
   

    $.ajax({
        url: sys.getBaseUrl() + "login/getRandomPassword.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {jyn: jyn, key: key},
        success: function (data) {

            if (data.status != '1') {
                $.alerts.alert("短息发送失败！", "提示");
            } else {
                time(obj);
            }

        }
    });

}

//开启代理
function handleShopManager(){
		
		 var jym2=$("#jym2").val();
		    
	    if(!$("#checked1").is(':checked')){
	    	
	    	$.alerts.alert("请输勾选我要做店长注册协议！", "提示");
	        return false;
	    	
	    }
		
		 msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		 
		    $.ajax({
		        url: sys.getBaseUrl() + "person/handleShopManager.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {jym2:jym2},
		        success: function (data) {

		            if (data.state == '1') {
		            	
						var obj=$(".searchNav").find("on");
						
						flash(obj,1);
		            	
		            	$("#J_toBe,.popupbg").hide();
		            	
					    $("#AGENTNO").text("代理编号:"+data.AGENTNO);
		 				
		 				$(".main").show();
		 				
		 				msgTips("办理状态","<br>办理成功！",function(){});
		            	
		            } else {
		            	msgTips("办理状态","<br>验证码错误！",function(){});
		            }

		        }
		    });
	
}

//办理页面
function toHandle(name){
	
	var AGENTNO=$("#AGENTNO").text();
	
	name=encodeURI(encodeURI(name));
	
	 AGENTNO=AGENTNO.substring(5,AGENTNO.length);
	
	sys.goUrl(sys.getBaseUrl() + "new2015/person/toHandle.jsp?name="+name+"&AGENTNO="+AGENTNO+"&v=1", 1);
	
}

//按手机号查询用户信息
function queryUser(){
	
	var phone=$("input[name='phone']").val();
	
	if(!isPhone(phone)){
		
		$.alerts.alert("请输入正确的手机号！", "提示");
		
		return false;
		
	}
	
	$("#J_toSearch,.popupbg").show();
	
	 var src=sys.getBaseUrl()+"new2015/images/person/loading_u8.gif";
	
	var toSearchMain="<p>手机号：<img src="+src+"></p>"+//
				     "<p>是否换卡：<img src="+src+"></p>"+//
				     "<p>是否4g用户：<img src="+src+"></p>"+//
				     "<p>推荐4g套餐：<img src="+src+"></p>";
	
	$(".toSearchMain").html(toSearchMain);
	
    $.ajax({
		
		url :sys.getBaseUrl()+"toBeShopManager/queryUser.do",
		type:"post",
		dataType:"json",
		data:{phone:phone},
		success :function(data){
			
			var is4GUer="";
			
			var tj4g="";
			
			if(data.tj4g.state=='0'){
				
				is4GUer="否";
				tj4g="您还不是4g用户";
				
			}else{
				
				is4GUer="是";
				tj4g=data.tj4g.msg;
				
			}
			
			var is4G="";
			
			if(data.is4G=='0'){
				
				is4G="否";
				
			}else{
				
				is4G="是";
				
			}
				
			var msg="<p>手机号："+data.phone+"</p>"+//
			        "<p>是否换卡："+is4G+"</p>"+//
			        "<p>是否4g用户："+is4GUer+"</p>"+//
			        "<p>推荐4g套餐："+tj4g+"</p>";
			
			$(".toSearchMain").html(msg);
			
		  }
	});

}

//关闭对话框
function submit(){
	
	$(".toBePopu,.popupbg").hide();
	
}

function toMySales(){
	
	sys.goUrl(sys.getBaseUrl() + "new2015/person/mySales.jsp", 1);
	
}

function xy(){
	
    sys.goUrl(sys.getBaseUrl() + "new2015/person/xieyiManager.jsp", 0);
	
}

function flash(obj,type){
	
	var $oldobj=$(".searchNav").find(".on");
	
	var $newobj=$(obj);
	
	$oldobj.removeClass("on");
	
	$newobj.addClass("on");
	
    $.ajax({
		
		url :sys.getBaseUrl()+"toBeShopManager/getType.do",
		type:"post",
		dataType:"json",
		data:{type:type},
		success :function(data){
			
			var head="<div class=\"toBeTid\">尊敬的用户，你可以选择以下"+data[0].PNAME+"套餐推荐办理：</div>"+//
                         "<ul class=\"toList\">";
			
			var main="";
			
			var foot="</ul>";
			
			 $.each(data,function(i,j){
				 
				 var src=sys.getBaseUrl()+j.ICON;
				 
				 main=main+"<li>"+//
				              "<div class=\"toListImg\">"+//
				              	 "<img src="+src+">" +//
				              "</div>"+//
				              "<div class=\"toListText\">"+//
				                  "<h4>"+j.NAME+"</h4>"+//
				                  "<p>"+j.MSG+"</p>"+//
				                  "</div>"+//
				                  "<a href=\"javascript:\" onclick=\"toHandle('"+j.NAME+"');\"  class=\"toListBtn\">办理</a>"+//
				           "</li>";
				 
			 });
			 
			 $(".toBeShop").html(head+main+foot);
			
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


function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}
function gotoManagerManual()
{
	 sys.goUrl(sys.getBaseUrl() + "new2015/person/managerManual.jsp", 0);
}



