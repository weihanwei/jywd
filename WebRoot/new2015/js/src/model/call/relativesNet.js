define(function(require) {
	
	jy.innertHead('亲友网');
	
	jy.innertFood(2);
	
	flash();

    //面板间切换
    jy.navShow(".relaNoNav li",".relaCorBox .relaCor");
    
    access();

});

function flash(){
	
	 jy.loadingShow();
	
    ZHData.get("relativesNetMSG",function(data){
			
			var state=data.state;
			
			var msg=data.msgs;
			
			
			
			if(state.state=='0'){//已办理
				
				var groupphone="";//我的短号
				
				var phones="<tr>"+//
	                           "<th width=\"35%\">短号</th>"+//
	                           "<th width=\"65%\">长号</th>"+//
	                       "</tr>";
				
				 for(var i =0;i<msg.length;i++){
					 
                     if(i==0){
						 
						 groupphone=groupphone+"<div class=\"cornetNo\">我的短号: "+msg[i].cornet+"</div>";
						 
					 }else{
						
						 phones=phones+"<tr>"+////成员
					                     "<td width=\"35%\">"+msg[i].cornet+"</td>"+//
					                     "<td width=\"65%\">"+msg[i].trombone+"</td>"+//
					                   "</tr>";
						 
					 }
					 
				 }
				 
				 var total=msg.length-1;
				 
				 var sy=18-total;//可添加
				 
				 groupphone=groupphone+"<p>共有<span>"+total+"</span>位成员，还可设置<span>"+sy+"</span>位。</p>";
				
				 $(".cornetHead").html(groupphone);
				 $(".cornetTab").html(phones);
				 
				 $("#ishandle").show();
				 $("#nohandle").hide();
				 
			}else{//未办理
				//说明用户是副号
				if(state.state=='2')
				{
					var groupphone="";//我的短号
					var currentPhone=data.currentPhone;
					
					var phones="<tr>"+//
		                           "<th width=\"35%\">短号</th>"+//
		                           "<th width=\"65%\">长号</th>"+//
		                       "</tr>";
					
					 for(var i =0;i<msg.length;i++){
						 
	                     if(msg[i].trombone==currentPhone){
							 
							 groupphone=groupphone+"<div class=\"cornetNo\">我的短号: "+msg[i].cornet+"</div>";
							 
						 }else{
							
							 phones=phones+"<tr>"+////成员
						                     "<td width=\"35%\">"+msg[i].cornet+"</td>"+//
						                     "<td width=\"65%\">"+msg[i].trombone+"</td>"+//
						                   "</tr>";
							 
						 }
						 
					 }
					 
					 var total=msg.length-1;
					 
					 var sy=18-total;//可添加
					 
					 groupphone=groupphone+"<p>共有<span>"+total+"</span>位成员。</p>";
					
					 $(".cornetHead").html(groupphone);
					 $(".cornetTab").html(phones);
					 
					 $("#ishandle").show();
					 $("#nohandle").hide();
					 $(".footerBtn").hide();
					 
					
				}else
				{
					$("#ishandle").hide();
					$("#nohandle").show();
				}
			

				
			}	 
			
			 jy.loadingHide();
         
	});
	
}

var AGENTNO=sys.getHttpParam("AGENTNO");

function handleQy(){
	
	msgConfirm("提醒","<br><br><p align='center'>确定要办理亲友网吗？</p>",function(){
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	   	
		$.ajax({
	        url: sys.getBaseUrl() + "call/relativesNetHandle.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {AGENTNO:AGENTNO},
	        success: function (data) {
	        	
	        	   if(data.state=='1'){
	        		   
	        		   jy.ReMoveUserMsg();
	        		   flash();
	        		   msgTips("办理状态","办理成功！",function(){});
	 
	        	   }else{
	        		   
	        		   msgTips("办理状态",data.msg,function(){});
	        		   
	        	   }
	        	   
	        }
	    });
		
	});
	
}

function relativesNetDetail(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/call/relativesNetDetail.jsp?AGENTNO="+AGENTNO,1);
	
}
