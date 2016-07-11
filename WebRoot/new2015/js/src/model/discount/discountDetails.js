define(function(require) {
	
    jy.innertHead('专享特惠详情');
	
	jy.innertFood(3);
	
	 //加载滑动广告
    jy.initSlider();
		
	var tcid=sys.getHttpParam("id");
	
	//获取优惠
    $.ajax({
		
		url :sys.getBaseUrl()+"discount/getTcByid.do",
		type:"post",
		dataType:"json",
		data:{tcid:tcid},
		success :function(data){
			
			$("#NAME").html(data.NAME);
			
			$("#TYPE").html("业务类型："+data.TYPE);
			
			$("#STARTTIME").html("开始时间："+data.STARTTIME);
			
			$("#ENDTIME").html("结束时间："+data.ENDTIME);
			
			$("#INTRODUCE").html(data.INTRODUCE);
			
		}
	
    });
	
});

function toHandle(){
	
	var tcid=sys.getHttpParam("id");
	
	msgConfirm("提醒","<br><br><p align='center'>确认办理此特惠吗？</p>",function(){
		
		msgTips("办理状态","<br><span id='status'>办理套餐中</span> <br><br><div class='loading_bar'><span></span></div>",function(){
	
	    },false);
		
		 $.ajax({
				
				url :sys.getBaseUrl()+"discount/handle.do",
				type:"post",
				dataType:"json",
				data:{tcid:tcid},
				success :function(data){
					
					msgTips("办理状态","<br>"+data.msg,function(){});
					  
				}
		    });
	
	});
	
}

