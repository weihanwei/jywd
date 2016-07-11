define(function(require) {
	
	jy.innertFood(1);
	
	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBanner.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			var banner="";
			
			 $.each(data,function(i,j){

				 var src=sys.getBaseUrl()+j.ICON;
				 
				 banner=banner+"<li class=\"swiper-slide\" >"+//
                 "<img onclick=\"banner('"+j.ID+"','"+j.URL+"','"+j.TYPE+"','"+j.NAME+"');\" src="+src+">"+//
                     "</li>";
		             
			   });
			
			 $("#banner").html(banner);
			 //加载滑动广告
		    jy.initSlider();
		}
	
    });
    
	//app功能
    $.ajax({
		
		url :sys.getBaseUrl()+"index/appListMap.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
              var appListMap=data;
			   
			   var app="<div class=\"ads-row\">";
			   
			   $.each(appListMap,function(i,j){

				   var src=sys.getBaseUrl()+j.ICON;
				   
				   app=app+"<div class=\"ads-c-4\">"+//
                	          "<a class=\"icon-link text-overflow\" href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                                  "<span class=\"icon\">"+//
                                     "<img src="+src+">"+//
                                   "</span>"+//
                                   j.NAME+
			                   "</a>"+//
			                 "</div>";
		             
			   });
			   
			   app=app+ "</div>";
			   $("#app").html(app);
			   
			   
		  }
	
    });
    
    

    //点击首页手势隐藏
    $(".iHandleState").on("touchstart click", function(){
        $(this).hide();
    })
	
});

function goAPP(url,type,name){
	
	if(type=='1'){
		
		jy.G(sys.getBaseUrl()+url,1,name, '');
		
	}else{
		
		var uuid=jy.getUserUuid();
		
	    url=url+"&uuid="+uuid;
	    
		jy.G(url,1,name, '');
		
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

function messages(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/messages.jsp",1,'消息', '');
	
}

function Search(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/search.jsp",1,'搜索', '');
	
}