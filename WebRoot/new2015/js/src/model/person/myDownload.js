/*我的金币*/
define(function(require) {
	

	jy.innertHead('我的下载');
	
	jy.innertFood(4);
	
	
    $.ajax({
		
		url :sys.getBaseUrl()+"person/myDownloadMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
			var myDown="";
			
			if(data.length>0)
			{
				$.each(data,function(i,j){
					
					var src=sys.getBaseUrl()+j.ICON;
					
					myDown=myDown+"<li class=\"pr\">"+//
	                                  "<div class=\"myDownImg\">"+//
					                     "<img src="+src+">"+//
					                  "</div>"+//
	                                  "<div class=\"myDownText\">"+//
	                                     "<h3>"+j.NAME+"</h3>"+//
	                                     "<p>"+j.TIME+"/"+j.APPSIZE+"</p>"+//
	                                  "</div>"+//
	                               "</li>";
					
				   });
				
				$(".myDownMain").html(myDown);
			}else
			{
				$(".myDownMain").remove();
				$(".myDownHeader").after('<div class="noMain">亲~您还没有下载记录</div>');
			}
			


		  }
	});
	
});


function cleanDowm(){
	
	msgConfirm("提醒提示","<br><br><p align='center'>确定删除下载记录吗？</p>",function(){
		
	      $.ajax({
				
				url :sys.getBaseUrl()+"person/cleanDowm.do",
				type:"post",
				dataType:"json",
				data:'',
				success :function(data){
					
	               $.ajax({
						
						url :sys.getBaseUrl()+"person/myDownloadMSG.do",
						type:"post",
						dataType:"json",
						data:'',
						success :function(data){
							
							var myDown="";
							
							$.each(data,function(i,j){
								
								var src=sys.getBaseUrl()+j.ICON;
								
								myDown=myDown+"<li class=\"pr\">"+//
					                            "<div class=\"myDownImg\">"+//
								                     "<img src="+src+">"+//
								                  "</div>"+//
					                            "<div class=\"myDownText\">"+//
					                               "<h3>"+j.NAME+"</h3>"+//
					                               "<p>"+j.TIME+"/"+j.APPSIZE+"</p>"+//
					                            "</div>"+//
					                         "</li>";
								
							   });
							
							$(".myDownMain").html(myDown);

						  }
					});
					
					$.alerts.alert("清除成功！","提示");
					
				  }
			});
			
		
		
	});
	
	
}