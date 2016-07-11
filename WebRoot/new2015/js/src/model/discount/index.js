define(function(require) {
	
	jy.innertFood(3);
		
	//获取优惠
    $.ajax({
		
		url :sys.getBaseUrl()+"discount/getDiscount.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			var discountList="";
			
			 $.each(data,function(i,j){

				 var src=sys.getBaseUrl()+j.ICON;
				 
				 discountList=discountList+"<li>"+//
							                    "<a href=\"javascript:\" onclick=\"discountDetails('"+j.ID+"','"+j.URL+"','"+j.TYPE+"');\">"+//
							                        "<div class=\"myPriMainText pr\">"+//
							                            "<p>"+j.TILTLE+"</p>"+//
							                            "<span>至"+j.ENDTIME+"</span>"+//
							                            "<i class=\"myPriNext\"></i>"+//
							                        "</div>"+//
							                        "<div class=\"myPriMainImg\">"+//
							                            "<img src="+src+" />"+//
							                        "</div>"+//
							                    "</a>"+//
							                "</li>";
				 
			 });
			 
			 $("#discountTC").html(discountList);
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
	
});

function discountDetails(id,url,type){
	
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
