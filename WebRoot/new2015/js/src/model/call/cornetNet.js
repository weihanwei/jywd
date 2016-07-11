define(function(require) {
	
	jy.innertHead('短号网');
	
	jy.innertFood(2);

    //面板间切换
    jy.navShow(".relaNoNav li",".relaCorBox .relaCor");

	flash();
    
    access();


});

function handleDh(){
	
	var targetPhone=$("input[name='addPhone']").val();
	
	var AGENTNO=sys.getHttpParam("AGENTNO");
	
	if(targetPhone==""){
		$.alerts.alert("请输入目标号码！", "提示");
	}else{
		
		if(isPhone(targetPhone)){

			msgConfirm("提醒","<br><br><p align='center'>确定要加入短号网吗？</p>",function(){
				msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
			   	
				$.ajax({
			        url: sys.getBaseUrl() + "call/cornetNetHandle.do",
			        type: "post",
			        asyne: false,
			        dataType: "json",
			        data: {targetPhone:targetPhone,AGENTNO:AGENTNO},
			        success: function (data) {
			        	
		            	   jy.ReMoveUserMsg();
		            	   
						   msgTips("办理状态",data.msg,function(){});
						   
			        }
			    });
				
			});
			
		}else{
			
			$.alerts.alert("请输正确的手机号码！", "提示");
			
		}
		
	}
	
}

function updateDh(){
	
	var dh=$("#cornet").val();
	
	if(isNum(dh)==false){
		
		$.alerts.alert("请输正确的短号！", "提示");
		
		return false;
		
	}else{
		
		msgConfirm("提醒","<br><br><p align='center'>确定要修改短号吗？</p>",function(){
			msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		   	
			$.ajax({
		        url: sys.getBaseUrl() + "call/cornetNetUpdateDh.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {dh:dh},
		        success: function (data) {
		        	
	            	   jy.ReMoveUserMsg();
		        	
					   msgTips("办理状态",data.msg,function(){});
					   
		        }
		    });
			
		});
		
	}
	
}


function updateTc(){
	
	var tc=0;
	
	var select = document.getElementById("tc");  
	
	var index=select.selectedIndex;
	
	tc=select.options[index].value;

	
	msgConfirm("提醒","<br><br><p align='center'>确定修改短号网套餐吗？</p>",function(){
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	   	
		$.ajax({
	        url: sys.getBaseUrl() + "call/cornetNetUpdateTc.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {tc:tc},
	        success: function (data) {
	        	   
            	   jy.ReMoveUserMsg();
				   msgTips("办理状态",data.msg,function(){});
				   flash();
				   
				   
	        }
	    });
		
	});
		
}

function flash(){
	
	 jy.loadingShow();
	
	 $.ajax({
			
			url :sys.getBaseUrl()+"call/cornetNetMSG.do",
			type:"post",
			dataType:"json",
			data:'',
			success :function(data){
			
				if(data.state=='1'){
					
					$("#groupName").html(data.groupName);
					$("#cornet").val(data.cornet);
					
					$("#ishandle").show();
					$("#nohandle").hide();
					
					
					var select = document.getElementById("tc");  
					
					for(var i = 0; i < select.options.length; i++){
					   if(data.tc == select.options[i].value){
							select.options[i].selected = 'selected';
							break;
						 }
					}
					
				}else{
					
					$("#ishandle").hide();
					$("#nohandle").show();
					
				}
				
				 jy.loadingHide();
				
			  }
		});
	
}

function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}


function isNum(str) {
    var re = /^[0-9]*$/;
    var flag = re.test(str);
    return flag;
}
