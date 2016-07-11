define(function(require) {
	
	
    jy.innertHead('通话专区');
	
	jy.innertFood(2);
	
	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBannerByArea.do",
		type:"post",
		dataType:"json",
		data:{area:'2'},
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
	
    ZHData.get("indexCallMSG",function(data){
			
			var msg=data;
			
			var phone="<i class=\"bg-cover\"></i>"+msg.phone+" ， 欢迎您 。"
			
			var msg4g="<i class=\"bg-cover\"></i>";
			
			if(msg.is4G=='1'){
				msg4g=msg4g+"您的号码是 4G Usim卡";
			}else{
				msg4g=msg4g+"您的号码非 4G Usim卡";
			}
			
			   msg4g=msg4g+"<span>"+msg.pName+"</span>";
              
			   var hf="<i class=\"bg-cover\"></i>话费余额："+((msg.hf)/100).toFixed(2)+"元";
			   
			  $("#phone").html(phone);
			  
			  $("#msg4g").html(msg4g);
			  
			  $("#hf").html(hf);
         
	});
	
});

function cornetNet(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/call/cornetNet.jsp",1);
	
}

function relativesNet(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/call/relativesNet.jsp",1);
	
}

function topUp(){
	
	var uuid=jy.getUserUuid();
	
	jy.G("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/rech/index.jsps&uuid="+uuid,1,"话费充值", '');
	
}

function callRedpackets(){
	jy.G("http://221.179.101.131/jsp/activity/huawu/index.jsp",1,"通话红包", '');
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