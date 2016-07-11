define(function(require) {

    jy.innertHead('亲友网详情');

    jy.innertFood(2);

    flash();

});
function corUpQA(){
    $(".corUpQ").on("click" , function() {
        $(".corUpA").toggle();
    });
}
corUpQA();

var AGENTNO=sys.getHttpParam("AGENTNO");

function add(){
	
	var dobje=[];
	var flag=0;
	
	$(".corDetLongInp").each(function(){ 
		
    	var dd={};
	    var input = $(this).find("input");
	    var longmunber  = input.val(); 
        var  shortmunber =$(this).parent().prev().text();
        if(longmunber!=""){
            dd.longmunber=longmunber;
            dd.shortmunber=shortmunber;
            dobje.push(dd);
            flag++;
        }

    });
	
	if(flag==0){
		$.alerts.alert("请填写需要添加的号码！","温馨提示");
	}else{
		
		msgConfirm("提醒","<br><br><p align='center'>确定要添加所填成员吗？</p>",function(){
			msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		   	
			$.ajax({
		        url: sys.getBaseUrl() + "call/relativesNetAdd.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {phones:JSON.stringify(dobje),AGENTNO:AGENTNO},
		        success: function (data) {
		        	
	            	jy.ReMoveUserMsg();
		        	
		        	var msghtml="";
		        	
		        	var j=0;
		        	
		        	 for(var i =0;i<data.length;i++){
                          
		        		 j++;
		        		  msghtml=msghtml+"<br>"+j+"."+data[i].msg;
						 
					   };
		        	
					   msgTips("办理状态",msghtml,function(){ goToRelativesNet();});
					  
					   
		        }
		    });
			
		});
		
	}
	
}

function del(cornet,trombone){
	
	msgConfirm("提醒","<br><br><p align='center'>确定要删除"+trombone+"该成员么</p>",function(){
	
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		
		 $.ajax({
		        url: sys.getBaseUrl() + "call/relativesNetDel.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {cornet:cornet},
		        success: function (data) {
		        	   
                       if(data.state=='1'){
                    	   jy.ReMoveUserMsg();
                    	   flash(); 
                    	   msgTips("办理状态","<br>"+data.msg,function(){});
                       }else{
                    	   msgTips("办理状态","<br>"+data.msg,function(){});
                       }
                       
		        }
		    });
		
	});
}

function change(inpObj){
	
	var phone=$(inpObj).val();

	if(isPhone(phone) == false){
		$.alerts.alert("请填写正确的手机号！","温馨提示");
		$(inpObj).val("");  
	}
	
}

function upgrade(){
	
	msgConfirm("提醒","<br><br><p align='center'>确定要升级吗？</p>",function(){
		
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		
		 $.ajax({
		        url: sys.getBaseUrl() + "call/relativesNetUpgrade.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data:{AGENTNO:AGENTNO} ,
		        success: function (data) {
		        	jy.ReMoveUserMsg();
		        	 msgTips("办理状态","<br>"+data.msg,function(){});
                       
		        }
		    });
		
	});
	
}


function flash(){
	
	 jy.loadingShow();
	
	 ZHData.get("relativesNetMSG",function(data){
				
				var state=data.state;
				
				var msg=data.msgs;
				
				
				var a = ["552", "553", "554", "555","556","557","558","559","560","561","561","562","563","564","565","566","567","568"]; 
				
				if(state.state=='0'){//已办理
					
					var groupphone="<div class=\"cornetNo\">我的短号: "+msg[0].cornet+"</div>";//我的短号
					
					var phones="<tr>"+//
		                        "<th width=\"20%\">删除</th>"+//
		                        "<th width=\"20%\">短号</th>"+//
						        "<th width=\"60%\">长号</th>"+//
						       "</tr>";
					
					 var isAdd=0;
					 
					 for(var i =0;i<a.length;i++){
		                 
						    if(msg.length>1){
						    	
						    	isAdd=0;
	
							    for(var j=0;j<msg.length;j++){
								
									 if(msg[j].cornet==a[i]){
											
										     isAdd++;
										 
											 phones=phones+"<tr>"+//
													           "<td width=\"20%\">"+//
													               "<span class=\"corDetDelBtn usable\" onClick=\"del("+msg[j].cornet+","+msg[j].trombone+")\"></span>"+//
													           "</td>"+//
													           "<td width=\"20%\">"+//
													              "<div class=\"corDetInp\">"+//
													                  "<input type=\"text\" name=\"short1\" class='J_footHide' value="+msg[j].cornet+" />"+//
													              "</div>"+//
													           "</td>"+//
													           "<td width=\"60%\" class=\"corAlign\">"+msg[j].trombone+"</td>"+//
													        "</tr>";
									
									 }
									 	 
							    }
							    
							    if(isAdd==0){
									 
									   phones=phones+"<tr>"+//
											           "<td width=\"20%\">"+//
											               "<span class=\"corDetDelBtn\"></span>"+//
											           "</td>"+//
											           "<td width=\"20%\">"+a[i]+"</td>"+//
											           "<td width=\"60%\">"+//
											               "<div class=\"corDetLongInp\">"+//
											                   "<input type=\"text\" name=\"phone1\" onchange=\"change(this);\" placeholder=\"请输入手机号\" class='J_footHide' />"+//
											               "</div>"+//
											           "</td>"+//
											       "</tr>";
									   
							 }
						    	
						    }else{
						    	
						    	phones=phones+"<tr>"+//
						           "<td width=\"20%\">"+//
						               "<span class=\"corDetDelBtn\"></span>"+//
						           "</td>"+//
						           "<td width=\"20%\">"+a[i]+"</td>"+//
						           "<td width=\"60%\">"+//
						               "<div class=\"corDetLongInp\">"+//
						                   "<input type=\"text\" name=\"phone1\" onchange=\"change(this);\" placeholder=\"请输入手机号\"  class='J_footHide' />"+//
						               "</div>"+//
						           "</td>"+//
						       "</tr>";
						    	
						    }
						 
						    
							 
					 }	 
					 
					 var total=msg.length-1;
					 
					 var sy=18-total;//可添加
					 
					 groupphone=groupphone+"<p>共有<span>"+total+"</span>位成员，还可设置<span>"+sy+"</span>位。</p>";
					 
					 if('旧资费'==state.name)
					 {
						 groupphone=groupphone+"<div class=\"corUpgrade\">"+//
				           "<a href=\"javascript:\" onClick=\"upgrade();\" class=\"corUpBtn\">升级</a>"+//
				           "<span class=\"corUpQ\"></span>"+//
				           "<div class=\"corUpA\">"+//
				               "<div class=\"corUpABg\"></div>"+//
				               "<div class=\"corUpAMain\">"+//
				                   "<p>升级后资费更低、群内亲友更多！</p>"+//
				                   "<p>亲友网现升级支持全省19人跨市组网，群内成员省内长短号互打免费，每月仅需5元起。</p>"+//
				               "</div>"+//
				            "</div>"+//
				       "</div>";
					 }
					 

					
					 $(".cornetHead").html(groupphone);
					 $(".cornetTab").html(phones);
					 
					    $(".corUpQ").on("click" , function() {
					        $(".corUpA").toggle();
					    });
					 
				}else{//未办理
				
					$(".nomain").removeClass("hide");
	
					$(".cornetTab").hide();
					
					$(".cornetHead").hide();
					
					$(".footerBtn").hide();
					
					$(".corUpgrade").show();
				}	 
				
				 jy.loadingHide();
				
	         
		});
	
}

function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}
function goToRelativesNet()
{
	sys.goUrl(sys.getBaseUrl()+"new2015/call/relativesNet.jsp",1);
}
