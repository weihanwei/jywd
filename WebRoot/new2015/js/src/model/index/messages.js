define(function(require) {
	 //jy.innertHead('消息中心');
		
	 jy.innertFood(1);
	 
	 var obj=$(".messNav").find(".on");
	 
	 
	 
	 //查询消息列表
	 change(obj,null);
	 //查询消息分类列表
	 getMessagesType();


    //点击编辑
    $("#J_edit").on("click", function(){
    	
	 
   	 
        var licount = $(".messList li").length;
        if(licount > 0){
            var text = $(this).html();
            if(text == "编辑"){
                $(this).html("完成");
                $(".messList").addClass("messEdit");
                $(".messEditBtn").show();
                return false;
            }else if(text == "完成"){
                $(this).html("编辑");
                $(".messList").removeClass("messEdit");
                $(".messEditBtn").hide();
                return false;
            }
        }
    });

    //点击显示全部标题
    $(".messAll").on("click", function(){
        $(".messPopuBg").removeClass("hide");
        $(".messMain").animate({left: "75%"},500);
        $(".messNav").css("box-shadow","2px 0 5px #666").animate({left: "0"},500);
    });
    
    //点击全部消息返回效果
    $(".messNavHead").on("click", function(){
        $(".messPopuBg").addClass("hide");
        $(".messMain").animate({left: "0"},500);
        $(".messNav").css("box-shadow","").animate({left: "-75%"},500);
        $("#hiddenNoticeType").val('');
        change($(".messNav").find(".on"),null);
    });


});

function change(obj,type){
	//var $obj=$(obj);
	
	$("#noDataDiv").hide();
	$(".messNav").find(".on").removeClass("on");
	
	
	//查询全部通知消息数据
    $.ajax({
		
		url :sys.getBaseUrl()+"notice/getMessagesByType.do",
		type:"post",
		dataType:"json",
		data:{type:type},
		success :function(data){
			var _data = data.messages;
			$("#allNoticeCount").text(data.total.total_count+"条");	
			if(data.total.unread_count==0)
			{
				$("#noReadNoticeCount").text("无");	
			}else
			{
				$("#noReadNoticeCount").text(data.total.unread_count+"条");	
			}
			
			
			 var msgs="";
			 var title="";
			 
			 if(_data.length>0){
				 
				 msgs="";
					
				 $.each(_data,function(i,j){
					 		
					   if(j.READCOUNT =='0'){
						   title='<div class="messListTitle " onclick="messagesDetail(\''+j.ID+'\');">';
					   }else{
						   title='<div class="messListTitle readed" onclick="messagesDetail(\''+j.ID+'\');">';
					   }
					   
					   //收藏的消息，只显示收藏图标
					   if(j.COLLCOUNT !='0')
					   {
						   title+='<i class="messLove bg-cover"></i>';
					   }else
					   {
						   if(j.ISIMPORTANCE =='1')
						   {
							   title+='<i class="messImportant bg-cover"></i>';
						   }
					   }
					   title+=j.TITLE+'</div>';
					 
			            msgs=msgs+"<li>"+ title +//
                                    "<span>"+j.CREATETIME+"</span>"+//
                                    "<p>"+j.CONTENT+"</p>"+//
                                    "<div class='messEditBtn'>";
			            if(j.COLLCOUNT !='0')
			            {
			            	
			            	msgs=msgs+"<span class='messLoveBtn hui' onclick=\"collect('"+j.ID+"','"+obj+"','"+1+"');\">收藏</span>";
			            }else
			            {
			            	msgs=msgs+"<span class='messLoveBtn' onclick=\"collect('"+j.ID+"','"+obj+"','"+0+"');\">收藏</span>";
			            }
                                        
			            msgs=msgs+     "<span class='messDelBtn' onclick=\"del('"+j.ID+"','"+obj+"');\">删除</span>"+//
                                    "</div>"+//
                                "</li>";
				   });
				 
				 
			  }else{
				  
				  $("#noDataDiv").show();
				  
			  }
			 
			 $(".messList").html(msgs);
			 
			 
			 if($('#J_edit').html() == "完成"){
	                $(".messList").addClass("messEdit");
	                $(".messEditBtn").show();
	            }
			 
		}
	
    }); 
}

function getMessagesType()
{
    $.ajax({
		url :sys.getBaseUrl()+"notice/getMessagesType.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			var noticeType=data.typeInfoList;
			var messageTotal =data.total;
			//messageTypeUl
			
			 $('#boxAllNoticeCount').text("共"+messageTotal.total_count+"条");
			 if(noticeType.length>0){
				 var liUrl="";
				 $.each(noticeType,function(i,j){
					 liUrl+='<li>'+j.LABEL+'<span>共'+j.NOTICECOUNT+'条</span><input type="hidden" value="'+j.VALUE+'"></li>';
				 });
				 $('#messageTypeUl').html(liUrl);
			 }
			 $('#myCollCountSpan').text("共"+messageTotal.collCount+"条");
			 
			 
			    //点击隐藏标题
			    $(".messPopuBg,.messNavList li").on("click", function(){
			        $(".messPopuBg").addClass("hide");
			        $(".messMain").animate({left: "0"},500);
			        $(".messNav").css("box-shadow","").animate({left: "-75%"},500);
			        var messageType=$(this).children('input').val();
			        $("#hiddenNoticeType").val(messageType);
			        change($(".messNav").find(".on"),messageType);
			    });
			 
		}
	
    });
}

function messagesDetail(id){
	
	sys.goUrl(sys.getBaseUrl()+"notice/messagesDetail.do?id="+id,1);
}

function del(id,obj){
	
	msgConfirm("提醒","<br><br><p align='center'>确定要删除这条消息吗？</p>",function(){
		
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		
		 $.ajax({
		        url: sys.getBaseUrl() + "notice/delNotice.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {id:id},
		        success: function (data) {
		        	
                       if(data.state=='1'){
                    	   msgTips("删除状态","<br>删除成功",function(){
                    		   // 刷新数据，主要是刷新收藏条数
                    		   change(obj,$('#hiddenNoticeType').val());
                               getMessagesType();
                    	   });
                       }else{
                    	   msgTips("删除状态","<br>服务器繁忙",function(){});
                       }
                       
		        }
		    });
		
	});
	
}

/**
 * @param id 
 * @param obj
 * @param flag 1:已经收藏    0：取消收藏
 * @return
 */
function collect(id,obj,flag){
	
	var confirmMessag="";
	if(1==flag)
	{
		confirmMessag="<br><br><p align='center'>确定要取消收藏这条消息吗？</p>";
	}else
	{
		confirmMessag="<br><br><p align='center'>确定要收藏这条消息吗？</p>";
	}
    msgConfirm("提醒",confirmMessag,function(){
		
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
		
		 $.ajax({
		        url: sys.getBaseUrl() + "notice/collectNotice.do",
		        type: "post",
		        asyne: false,
		        dataType: "json",
		        data: {id:id,flag:flag},
		        success: function (data) {
		        	
                       if(data.state=='1'){
                    	   var messageVar="";
                    		if(1==flag)
                    		{
                    			confirmMessag="<br>成功取消收藏";
                    		}else
                    		{
                    			confirmMessag="<br>收藏成功";
                    		}
                    	   msgTips("收藏状态",confirmMessag,function(){
                    		   // 刷新数据，主要是刷新收藏条数
                    		   change(obj,$('#hiddenNoticeType').val());
                               getMessagesType();
                    	   });
                       }else{
                    	   msgTips("收藏状态","<br>服务器繁忙",function(){});
                       }
                       
		        }
		    });
		
	});
	
}
