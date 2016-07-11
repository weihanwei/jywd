define(function(require) {
	
    jy.innertHead('4G套餐');
    
	jy.innertFood(1);
	

		//$('.package-type').children().eq(2).remove();
	
	
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
	
	 var obj=$(".package-type").find('.hover').children();
	   
	changeType(obj,'飞享套餐');
	
    access();
    
});

function changeType(inputobj,type){
	
$.ajax({
		
		url :sys.getBaseUrl()+"handle4G/getTcByType.do",
		type:"post",
		dataType:"json",
		data:{type:type},
		success :function(data){
			
			   var inttc="";
			  
			   $.each(data,function(i,j){
					
				   inttc=inttc+"<li>"+
                    	         "<div class=\"ads-row\">"+//
                        	       "<div class=\"ads-c-2\">"+//
                                    "<span class=\"icon\">"+//
                                     "<img src=\"/jywd/new2015/images/4g/package_img.png\">" +
                                    "</span>"+//
                                   "</div>"+//
		                           "<div class=\"ads-c-7\">"+//
		                            "<p class=\"package-name\">"+j.NAME+"</p>"+//
		                            "</div>"+//
		                            "<div class=\"ads-c-3\">"+//
		                             "<a href=\"javascript:\" onclick=\"toHandleDetails('"+j.ID+"');\" class=\"handel_btn radius-05\">办理</a>"+//
		                           "</div>"+//
		                         "</div>"+//
                               "</li>";
                            
				  });
			   
			   $(".package-list").html(inttc);
			   
			   $(".package-type").find('.hover').removeClass("hover");
			   
			   var $jqueryinputobj=$(inputobj);
			   var $parentli=$jqueryinputobj.parent();
			   $parentli.addClass("hover");
			   
		}
		
    });
	
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

function toHandleDetails(id){
	
    var AGENTNO=sys.getHttpParam("AGENTNO");
	sys.goUrl(sys.getBaseUrl()+"handle4G/detail.do?id="+id+"&AGENTNO="+AGENTNO+"&data="+new Date(),1);
	
}

