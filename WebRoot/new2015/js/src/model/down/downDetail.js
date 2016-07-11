define(function(require) {
	
    jy.innertHead('应用下载');
	
	jy.innertFood(4);
	
	$.ajax({
		
		url :sys.getBaseUrl()+"index/appDownloadMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			var downAppListMap=data;
			var downApp="";
			
			$.each(downAppListMap,function(i,j){

				var src=sys.getBaseUrl()+j.ICON;
		        
				downApp=downApp+"<li>"+//
                                  "<a href=\"#\">"+//
                                    "<div class=\"appImg\">" +//
                                       "<img src="+src+">" +//
                                    "</div>"+//
                                    "<span>和生活</span>"+//
						          "</a>"+//
						        "</li>";
				
			   });
			
			$(".appDown").html(downApp);
		  }
	});
	
});

function down(url,id,name){
	
     $.ajax({
		
		url :sys.getBaseUrl()+"Log/down.do",
		type:"post",
		dataType:"json",
		data:{id:id,name:name},
		success :function(data){ 
			   
		 }
	
    });
	
	window.location.href=(url);
	
}