define(function(require) {
	
    jy.innertHead('4G专区');
	
	jy.innertFood(1);
	
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
	
    ZHData.get("index4GMSG",function(data){
				
				var phone="<span class=\"icon-index-user bg-cover\"></span>"+data.phone+" , 欢迎您";
				var jbAndJb=data.REMAININGGOLD+"金币"; //<span class=\"red\">"+data.jibie+"</span>
				var simMSG="<span class=\"icon-4gcard bg-cover\"></span>";
				
				if(data.is4G=='1'){
					simMSG=simMSG+"您的号码是 4G Usim卡";
				}else{
					simMSG=simMSG+"您的号码非 4G Usim卡";
				}
				
				simMSG=simMSG+" <span class=\"yellow\">"+data.pName+"</span>";
				
				$("#phone").html(phone);
				$("#jbAndJb").html(jbAndJb);
				$("#simMSG").html(simMSG);
			
	    });
	
	
	$.ajax({
		
		url :sys.getBaseUrl()+"handle4G/recommend4G.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			var state=data.state;
			
			var msg=data.msg;
			
            var tjbt="";
            
            var tjct="";
            
            var istj="";
			
			if(state=='4'||state=='5'||state=='6'||state=='7'||state=='8'||state=='9'){//推荐
				
				tjbt="优惠推荐您升级为：";
				
				tjtc=msg;
				
				istj="推荐升级";
				
				$("#tjbt").html(tjbt);
				
				$("#tjct").html(tjct);
				
				$("#istj").html(istj);
				
				$("#tj").show();
				
			}
			
		}
	
    });
	

});

function tc4G(){
	sys.goUrl(sys.getBaseUrl()+"new2015/4g/package.jsp?data="+new Date(),1);
}

function hk(){
	
	var uuid=jy.getUserUuid();
	
	sys.goUrl("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/products/change4gCard/index.jsps&uuid"+uuid,1);
}

function hj(){
	
	var uuid=jy.getUserUuid();
	
	sys.goUrl("http://wap.gd.10086.cn/nwap/login/jieYangAuToLogin/autoLogin.jsps?backUrl=/nwap/shop/index.jsps?index=2&branch&uuid"+uuid,1);
}

function js(){
	sys.goUrl(sys.getBaseUrl()+"new2015/4g/introduce.jsp",1);
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