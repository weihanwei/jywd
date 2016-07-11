define(function(require) {

    jy.innertHead('金币明细');

    jy.innertFood(4);
    
    var name=decodeURI(decodeURI(sys.getHttpParam("name")));;
    
    var AGENTNO=sys.getHttpParam("AGENTNO");
    
    $.ajax({
		
		url :sys.getBaseUrl()+"toBeShopManager/mysalesDetail.do",
		type:"post",
		dataType:"json",
		data:{name:name,AGENTNO:AGENTNO},
		success :function(data){
            
			  var myBDeta="";
			  
			  if(data.length==0){
				  myBDeta="<div class=\"noMain\">亲~您暂无赠送金币记录哦！</div>";
			  }
			  
			
			   $.each(data,function(i,j){
				   
				   myBDeta=myBDeta+"<li>"+//
							            "<p><span>办理号码:</span>"+j.BEPHONE+"</p>"+//
							            "<p>"+j.NAME+"</p>"+//
							            "<p class=\"myBDetaTime\"><span>办理时间：</span>"+j.TIME+"</p>"+//
							            "<p class=\"myBMoney\"><span>金币:</span> +"+j.SENDGOLD+"</p>"+//
							        "</li>";
									   
			   });
			   
			   $(".myBDeta").html(myBDeta);
           
		}
    
    });
    
});