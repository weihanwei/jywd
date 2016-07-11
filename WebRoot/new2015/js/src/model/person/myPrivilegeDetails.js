define(function(require) {

    jy.innertHead('我的优惠');
    
    jy.innertFood(4);  
   
    var activityName=decodeURI(decodeURI(sys.getHttpParam("activityName")));
    var beginTime=decodeURI(decodeURI(sys.getHttpParam("beginTime")));
    var endTime=decodeURI(decodeURI(sys.getHttpParam("endTime")));
    var activityContent=decodeURI(decodeURI(sys.getHttpParam("activityContent")));   
  			
	var htmlvar = "<div class='main'>"
					+"<div class='myPriDetTitle'>"
				        +"<h4>"+activityName+"</h4>"
				        +"<p>开始时间："+beginTime+"</p>"
				        +"<p>结束时间："+endTime+"</p>"
				    +"</div>"
				    +"<div class='myPriDetMain'>"
				        +"<p>"+activityContent+"</p>"
				    +"</div>"
				 +"</div>";
	
	$("#divdata").html(htmlvar);
	
});
