define(function(require) {
	
    jy.innertHead('4G办理');
	
	jy.innertFood(1);
	
});

function toHandle(id,name){
	
    var AGENTNO=sys.getHttpParam("AGENTNO");
	
	msgConfirm("提醒","<br><br><p align='center'>你确认为"+$("input[name='hiddensPhone']").val()+"办理"+name+"吗？</p>",function(){
	
		msgTips("办理状态","<br><span id='status'>办理套餐中</span> <br><br><div class='loading_bar'><span></span></div>",function(){
	
	    },false);
		
		 $.ajax({
				
				url :sys.getBaseUrl()+"handle4G/handle.do",
				type:"post",
				dataType:"json",
				data:{id:id,AGENTNO:AGENTNO},
				success :function(data){
	            	jy.ReMoveUserMsg();
					msgTips("办理状态","<br>"+data.msg,function(){});
					  
				}
		    });
	
	});
}

