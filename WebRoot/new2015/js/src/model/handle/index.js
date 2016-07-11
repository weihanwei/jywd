define(function(require) {
    
	jy.innertFood(2);
	
	var obj=$(".common-change-wrap").find(".active");
	
	change("0",obj);
	
	
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

function change(isBoutique,obj){
	
	var oldActive=$(".common-change-wrap").find(".active");
	
	oldActive.removeClass("active");
	
	var newActive=$(obj);
	
	newActive.addClass("active");
	
    $.ajax({
		
		url :sys.getBaseUrl()+"handle/getHandleApp.do",
		type:"post",
		dataType:"json",
		data:{isBoutique:isBoutique},
		success :function(data){
			
              var handleApp=data;
			   
			   var app="";
			   
			   $.each(handleApp,function(i,j){

				   var src=sys.getBaseUrl()+j.ICON;
				   
				   app=app+"<li class=\"icon-jt-right\">"+//
                	          "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                	          "<span class=\"icon\">"+//
                	            "<img src="+src+">" +//
                	          "</span>"+//
                              "<p class='fem-12'>"+j.NAME+"</p>"+//
                              "<p class=\"text-overflow fem-07\">"+j.DETAILS+"</p>"+//
                              "</a>"+//
                            "</li>";
				   
				  
			   });
			   
			   $("#handleApp").html(app);
			   
		  }
    });
    
  //客户消息
    $.ajax({
		url :sys.getBaseUrl()+"notice/readMessagesMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			if(data.total.unread_count>0){
				$("#hi").html(data.total.unread_count);
				
			}
			   
		  }
	
    });
	
}


function messages(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/messages.jsp",1,'消息', '');
	
}

function Search(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/search.jsp",1,'搜索', '');
	
}