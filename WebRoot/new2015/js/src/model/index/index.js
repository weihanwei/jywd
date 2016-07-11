define(function(require) {

	//新手引导
    $.ajax({
		
		url :sys.getBaseUrl()+"index/isFirstLogin.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			if(data.ISGUIDE=='0'){
				
				var baseSrc=sys.getBaseUrl();
				
				var gg="<div class=\"banner guideBox\">"+//
			              "<div class=\"main\">"+//
					        "<div class=\"guide-container\" id=\"slider1\">"+//
					            "<ul class=\"swiper-wrapper\" style=\"height:auto\">"+//
					                "<li class=\"swiper-slide\">"+//
					                    "<img src="+baseSrc+"/new2015/images/index/banner01.jpg>"+//
					                "</li>"+//
					                "<li class=\"swiper-slide\">"+//
					                    "<img src="+baseSrc+"/new2015/images/index/banner02.jpg>"+//
					                "</li>"+//
					                "<li class=\"swiper-slide\">"+//
					                    "<img src="+baseSrc+"/new2015/images/index/banner03.jpg>"+//
					                "</li>"+//
					                "<li class=\"swiper-slide\">"+//
					                   "<img src="+baseSrc+"/new2015/images/index/banner04.jpg>"+//
					                "</li>"+//
					                "<li class=\"swiper-slide\">"+//
					                   "<img src="+baseSrc+"/new2015/images/index/banner05.jpg>"+//
					                "</li>"+//
					            "</ul>"+//
					            "<div class=\"guide-pagination\"></div>"+//
					        "</div>"+//
					    "</div>"+//
			           "</div>";

				var mainDiv=$("body").find(".main").eq(0);
				
				mainDiv.before(gg);
				
				//新手引导
			    var mySwiper  = new Swiper('.guide-container', {
			        pagination: '.guide-pagination',
			        paginationClickable: true,
			        loop: true,
			        updateOnImagesReady: true,
			        onTouchMove: function(){
			            if(mySwiper.activeIndex == 5){
			                $(".guideBox").remove();
			            }
			        }
			    });
			    
			    
			    isGuide();
				
			}		
			   
		  }
	
    });
	
	
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
    
   
		
    ZHData.get("indexMSG",function(data){
			
		    var userMap=data;
			
			var user="";

			user="<div class=\"index-login index-login-in\" id=\"userMap\">"+//
                    "<div class=\"index-user-info\">"+//
	                    "<div class=\"ads-row\">"+//
		                    "<div class=\"ads-c-8 fem-10\">"+//
		                       "<span class=\"icon-index-user bg-cover\"></span>"+userMap.phone+" , 欢迎您 "+//
		                    "</div>"+//
			                 "<div class=\"ads-c-4 fem-10 pr\">"+//
			                    userMap.REMAININGGOLD+"金币"+//<span class=\"red pa indText\">"+userMap.jibie+"</span>
			                 "</div>"+//
	                    "</div>"+//
                   "</div>"+//
                   
			       "<div class=\"ads-row\">"+//
			           "<div class=\"ads-c-4\">"+//
			               "<div class=\"menuwrap\">"+//
			                   "<span class=\"bg-cover index-menu-icon menu-icon1\"></span>"+//
			                   "<span class=\"yellow\">话费余额</span><br>"+((userMap.hf)/100).toFixed(2)+"元"+//
			            "</div>"+//
			       "</div>"+//
	           
		           "<div class=\"ads-c-4\">"+//
		               "<div class=\"menuwrap\">"+//
		                   "<span class=\"bg-cover index-menu-icon menu-icon2\"></span>"+//
		                   "<span class=\"yellow\">流量余量</span><br>"+((userMap.ll)/1024).toFixed(2)+"M"+//
		               "</div>"+//
		            "</div>"+//
		            
		           "<div class=\"ads-c-4\">"+//
		                "<div class=\"menuwrap\">"+//
		                   "<span class=\"bg-cover index-menu-icon menu-icon3\"></span>"+//
		                   "<span class=\"yellow\">剩余语音</span><br>"+userMap.yy+"分钟"+//
		               "</div>"+//
		           "</div>"+//
		           
			      "</div>"+//
			   "</div>";
			   
			   $("#user").html(user);
			   
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
    
    
  //客户消息
    
    //赵凤玲修改获取用户未读取的重要消息
   $.ajax({
		url :sys.getBaseUrl()+"notice/noReadMessagesMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			if(data.noticeCount>0){
				var moticeCount=data.noticeCount;
				
				$("#zs").html(moticeCount+"条");
				
				$("#hi").html(data.total.unread_count);
				
				if($("#isLoginPageTo").length>0)
				{
					$("#hiddenMsgId").val(data.noticeInfo.ID);
					if('1'==$('#isLoginPageTo').val())
					{
						
						$("#msg").html("<i class=\"inTopIcon bg-cover\"></i>"+data.noticeInfo.TITLE+"。");
						$(".inewsShow").show();
						$("#isLoginPageTo").remove();
					}
				}
				
			}

            //点击关闭首页消息弹出框
            //var hei = $(".inewsShow").height();
            $(".inewsClose").on("touchstart click", function(){
                $(".inewsShow").animate({bottom: "-12em"},1000);
            })


        }
	
    });


    //点击首页手势隐藏
    $(".iHandleState").on("touchstart click", function(){
        $(this).hide();
    })
	
});

//已指引
function isGuide(){
	
	//app功能
    $.ajax({
		
		url :sys.getBaseUrl()+"index/isGuide.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			   
		  }
	
    });
	
}

function goAPP(url,type,name){
	
	if(type=='1'){
		
		jy.G(sys.getBaseUrl()+url,1,name, '');
		
	}else{
		
		var uuid=jy.getUserUuid();
		
	    url=url+"&uuid="+uuid;
	    
		jy.G(url,1,name, '');
		
	}
	
}

function messages(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/messages.jsp",1,'消息', '');
	//sys.goUrl(sys.getBaseUrl()+"bunding/toBundingUserPhone.do", 1);
	
}

function Search(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/search.jsp",1,'搜索', '');
	
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
function goToMessageDetail()
{
	sys.goUrl(sys.getBaseUrl()+"notice/messagesDetail.do?id="+$("#hiddenMsgId").val(),1);

}