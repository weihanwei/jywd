define(function(require) {
    jy.innertHead('4G介绍');
    jy.innertFood(2);

	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBannerByArea.do",
		type:"post",
		dataType:"json",
		data:{area:'1'},
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
    
    //绑定tab切换事件
    jy.switchTabList(".intro > dt");
    jy.switchTabList(".intrQMain > dt");
});

function hk(){
	
	var uuid=jy.getUserUuid();
	
	sys.goUrl("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/products/change4gCard/index.jsps&uuid"+uuid,1);
}


function tc4G(){
	sys.goUrl(sys.getBaseUrl()+"new2015/4g/package.jsp?date="+new Date(),1);
}


function hj(){
	
	var uuid=jy.getUserUuid();
	
	sys.goUrl("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/shop/index.jsps?index=2&branch&uuid"+uuid,1);
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