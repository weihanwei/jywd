define(function(require) {

    jy.innertHead('我要做店长');

    jy.innertFood(4);
    
    var typeName=decodeURI(decodeURI(sys.getHttpParam("name")));
    
    var AGENTNO=sys.getHttpParam("AGENTNO");
    
    $.ajax({
		
		url :sys.getBaseUrl()+"toBeShopManager/getTc.do",
		type:"post",
		dataType:"json",
		data:{name:typeName},
		success :function(data){
		
			var tcdiv="";
			
			$.each(data,function(i,j){
				
				var src=sys.getBaseUrl()+j.ICON;
				var url=sys.getBaseUrl() +"new2015/person/toScaned.jsp?tourl="+j.TOURL+"&id="+j.ID+"&AGENTNO="+AGENTNO;
				if(j.TYPE=="登录推荐"){
					url=sys.getBaseUrl() +"new2015/person/toLogin.jsp?id="+AGENTNO+"&dhhm="+AGENTNO+"&tcid="+j.ID;
				}
				
				
				var tj="";
				
				if(j.ISMSMRECOMMEND=='0'){
					
					tj="<li>"+//
					   "<p>1、点击下面链接/ <em class=\"J_toErweima\">二维码：</em></p>"+//
					   "<div class=\"toBeMainBox\">"+//
					   "<a href=\"javascript:\" onclick=\"getQrCode('"+url+"');\">"+url+"</a>"+//
					   "</div>"+//
					"</li>";
					
				}else{
					
					tj="<li>"+//
			                "<p>1、短信推荐：</p>"+//
			                "<div class=\"toBeText\">"+//
			                    "<input type=\"text\" name=\"phone\" placeholder=\"请输入推荐的号码\" class='J_footHide' />"+//
			                    "<a href=\"javascript:\" onclick=\"MsmRecommend('"+j.ID+"','"+j.NAME+"',this);\">推荐</a>"+//
			                "</div>"+//
			            "</li>"+//
			            "<li>"+//
			               "<p>2、点击下面链接/ <em class=\"J_toErweima\">二维码：</em></p>"+//
			               "<div class=\"toBeMainBox\">"+//
			                   "<a href=\"javascript:\" onclick=\"getQrCode('"+url+"');\">"+url+"</a>"+//
			               "</div>"+//
			            "</li>";
					
				}
				
				
				tcdiv=tcdiv+"<div class=\"toHandle\">"+//
						        "<div class=\"toHandleTop\">"+//
						           "<div class=\"toHanTopImg\">"+//
						           		"<img src="+src+">"+//
						           "</div>"+//
						           "<div class=\"toHanTopText\">"+//
						              "<h4>"+j.NAME+"</h4>"+//
						           "</div>"+//
						        "</div>"+//
						        "<div class=\"toBeTid\">尊敬的用户，你可以选择以下方式进行办理：</div>"+//
						        "<ul class=\"toBeMain\">"+tj+"</ul>"+//
						    "</div>";
				
			});
			
			$("body > .main").first().html(tcdiv);
			
          }
    });

});

function toScaned(){
	
	sys.goUrl(sys.getBaseUrl() + "new2015/person/toScaned.jsp", 1);
	
}

function getQrCode(url){
	
	var qrcodediv="<div class=\"toPopuHanImg\" id=\"qrcode\">"+//
                  "</div>";
	
	$(".toPopuHan").html(qrcodediv);
	
	var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 96,//设置宽高
        height : 96
    });
	
    qrcode.makeCode(url);

	$(".toBePopu").show();
	
}

function MsmRecommend(tcid,name,obj){
	
	var AGENTNO=sys.getHttpParam("AGENTNO");
	
	var phone=$(obj).prev().val();
	
	if(phone==''){

		$.alerts.alert("手机号不能为空！", "提示");
		return false;
			
	}else{
		
        if (isPhone(phone) == false) {
            $.alerts.alert("手机号不正确！", "提示");
            return false;
        }
		
	}

	msgConfirm("提醒","<br><br><p align='center'>确认推荐"+phone+"办理"+name+"吗？</p>",function(){
		
		
		 msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		 
		    $.ajax({
		        url: sys.getBaseUrl() + "toBeShopManager/MsmRecommend.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {AGENTNO:AGENTNO,phone:phone,tcid:tcid},
		        success: function (data) {

		        	msgTips("办理状态","<br>"+data.msg,function(){});
		        	
		        }
		    });
		    
	});
		
}

function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}


function submit(){
	
	$(".toBePopu").hide();
	
}