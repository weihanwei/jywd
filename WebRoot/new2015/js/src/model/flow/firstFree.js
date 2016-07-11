define(function(require) {
	
	//app功能
   $.ajax({
		url :sys.getBaseUrl()+"firstFree/flowPackageList.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			
               var packageListMap=data;
               
               //=====设置流量选则=====
               var checkflag=true;
			   var pacakageStr='';
			   
			   $.each(packageListMap,function(i,j){
                  
				   pacakageStr+='<li>';
				   pacakageStr+='<input type="radio" name="fFList" value="'+j.ID+'" id="fF_'+j.ID+'"';

				   if(j.RCOUNT<=0)
				   {
					   pacakageStr+=' disabled ';
					   
					   pacakageStr+='>';
					   pacakageStr+='<label for="fF_'+j.ID+'" class="gray">'+j.PNAME+'</label>';
					   
				   }else
				   { 
					   if(checkflag)
					   {
						   pacakageStr+=' checked ';
						   checkflag=false;
					   }
					   
					   pacakageStr+='>';
					   pacakageStr+='<label for="fF_'+j.ID+'">'+j.PNAME+'</label>';
				   }
					   
				  
				   pacakageStr+='</li>';
			   });
			   $("#freePackageUl").append(pacakageStr);
			   
			   
			   //=====设置规则=====
			   var rolepacakageStr='';
			   
			   $.each(packageListMap,function(i,j){
                  
				   if(i==0)
				   {
					   rolepacakageStr +='<tr>';
					   rolepacakageStr += '<td>';
					   rolepacakageStr +=     '<p>'+j.PNAME+'</p>';
					   rolepacakageStr +=     '<p>'+'流量叠加包'+'</p>';
					   rolepacakageStr += '</td>';
					   rolepacakageStr +='<td>'+j.PRICE+'</td>';
					   rolepacakageStr +='<td rowspan="3">';
					   rolepacakageStr +=  '<p>（1）本活动只针对揭阳移动幸运客户。</p>';
					   rolepacakageStr += '<p>（2）每位客户只享受一次首单免费。</p>';
					   rolepacakageStr +=  '<p>（3）首单免费采取先扣费再赠费的方式进行，所赠话费在客户成功办理叠加包后由系统自动赠送。</p>';
					   rolepacakageStr +=      '<p>（4）随心王客户暂时无法参加活动。</p>';
					   rolepacakageStr +=   '<p>（5）活动采取先购先得方式，购完即止。</p>';
					   rolepacakageStr +=  '</td>';
					   rolepacakageStr +=  '</tr>';
				   }else
				   { 
					   rolepacakageStr +='<tr>';
					   rolepacakageStr +='<td>';
					   rolepacakageStr +=   '<p>'+j.PNAME+'</p>';
					   rolepacakageStr += '<p>'+'流量叠加包'+'</p>';
					   rolepacakageStr += '</td>';
					   rolepacakageStr +='<td>'+j.PRICE+'</td>';
					   rolepacakageStr +='</tr>';
				   }
			   });
			   $("#firstFreePackageRole").append(rolepacakageStr);
		  }
	
    });
	
	
	
    jy.innertFood(1);
    
});

function toBuyFreePackage(){
	var packageId=$("input[name='fFList']:checked ");
	
	if($("input[name='fFList']:checked ").length<=0)
	{
		msgTips("消息提示","<br>"+"流量包已经领完,您可以点击页面下方地址下载app享受更多优惠!",function(){});
		return;
	}
	
	
	
	var paramPackageId=$(packageId).val();
	var  packageNameText=$(packageId).next().text();
	
    
	
	msgConfirm("提醒","<br><br><p align='center'>确定购买"+packageNameText+"流量叠加包吗？</p>",function(){
	
		msgTips("状态","<br><span id='status'>购买流量中</span> <br><br><div class='loading_bar'><span></span></div>",function(){
	
	    },false);
		
		 $.ajax({
				
				url :sys.getBaseUrl()+"firstFree/buyFreePackage.do",
				type:"post",
				dataType:"json",
				data:{packageId:paramPackageId},
				success :function(data){
					var state=data.state;
					
					msgTips("消息提示","<br>"+data.msg,function(){});
					
					//购买成功,需要将页面灰化，不让用户可以选择其他
					if("1"==state)
					{
						//成功以后
						
					}
						
						
				}
		    });
	
	});
}
