define(function(require) {
	
    jy.innertHead('流量套餐');
	//加载滑动广告
	//jy.initSlider();
	
	jy.innertFood(1);
	
	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBannerByArea.do",
		type:"post",
		dataType:"json",
		data:{area:'3'},
		success :function(data){
			
			var banner="";
			
			 $.each(data,function(i,j){

				 var src=sys.getBaseUrl()+j.ICON;
				 
				 banner=banner+"<li class=\"swiper-slide\">"+//
                                  "<img onclick=\"banner('"+j.ID+"','"+j.URL+"','"+j.TYPE+"','"+j.NAME+"');\" src="+src+">"+//
                               "</li>";

			   });
			
			 $("#banner").html(banner);
			 
			//加载滑动广告
		    jy.initSlider();
		}
	
    });
		
	$.ajax({
        url: sys.getBaseUrl() + "flow/getFlowTc.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: '',
        success: function (data) {
        	
        	  var msg=data.msgs;
  
        	  var mainTC="";
        	  
			   $.each(msg,function(i,j){
				   
				   
				   //套餐
				   var mainTCList="";
				   
				   var mainTCHead="<li class=\"f-l\">"+//
							          "<!--showpart start-->"+//
							            "<div class=\"pr packageText\">"+//
							               "<div class=\"pa packageImg\">"+//
                                               "<div class=\"packageImgBox\">"+//
                                                  "<img src=\"../images/flow/packageicon.jpg\" alt=\"套餐icon\"/>"+//
                                               "</div>"+//
							               "</div>"+//
							                 "<div class=\"packageMain\">";
				   
				   var mainTCMain="<h5>"+j.MAINTC+"</h5>"+
				                   "<p>"+ j.TCDESCRIPTION+"</p>";
				   
				   var mainTCFoot="";
				   
				   //赠送套餐去掉
				   //赠送套餐
				   
/*				   $.each(j.giveTC,function(i1,j1){

					   mainTCMain=mainTCMain+"<p class=\"pDetMainText overEll\">"+j1+"</p>";
			             
				   });*/
		             
/*				   if(j.giveTC.length>0){
					   
					   mainTCFoot="<div class=\"hint\">"+//
				                       "<p>赠送只能</p>"+//
				                       "<p>3选1</p>"+//
				                   "</div>";
					   
				   }*/
				   
				   mainTCFoot=mainTCFoot+"</div>"+//
							                "<div class=\"pa packageBtn\">"+//
							                   "<a href=\"javascript:\" onclick=\"handleJudge('"+j.MAINTC+"');\" class=\"pHandleBtn\">办理</a>"+//
							                "</div>"+//
							             "</div></li>";
				   
				   mainTCList=mainTCHead+mainTCMain+mainTCFoot;
				   
				   mainTC=mainTC+mainTCList;
				   
			   });
			   
			   $(".package").html(mainTC);
           
        }
    });
	
    access();
	
});

function packageDetails(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/packageDetails.jsp",1);
	
}
function handleJudge(mainTC){
  handleFlow(mainTC,"noGive");
}
function handleJudgebak(mainTC,giveTC){
	
	var giveTCS=giveTC.split(',');	 
	
	if(giveTCS.length>1){
		
		
        var index=1;
    	
    	var giveMain="";
    	
    	var giveFood="";
        
    	var giveHead="<div class=\"packagePopupHeader\">"+//
					     "<i></i>选择赠送内容"+//
					     "<span class=\"packagePopupClose\"></span>"+//
					 "</div>"+//
					 "<dl class=\"packagePopupMain\">";
		
		$.each(giveTCS,function(i1,j1){
			
			if(index==1){
				
				giveMain=giveMain+"<dd class=\"packageLabelRadio pr\">"+//
			      "<input type=\"radio\" class=\"pPopupInput\" checked name=\"sel01\" id=\"sel00"+index+"\" />"+//
			      "<label class=\"packageRadioIcon\" for=\"sel00"+index+"\"></label>"+//
			      "<label class=\"packageRadioText\" for=\"sel00"+index+"\">"+j1+"</label>"+//
			  "</dd>";
				
			}else{
				
				giveMain=giveMain+"<dd class=\"packageLabelRadio pr\">"+//
			      "<input type=\"radio\" class=\"pPopupInput\" name=\"sel01\" id=\"sel00"+index+"\" />"+//
			      "<label class=\"packageRadioIcon\" for=\"sel00"+index+"\"></label>"+//
			      "<label class=\"packageRadioText\" for=\"sel00"+index+"\">"+j1+"</label>"+//
			  "</dd>";
				
			}
			

			index++;
			
		});
		
		giveMain=giveMain+"</dl>";
		
		giveFood="<a href=\"javascript:\" onClick=\"handleFlow('"+mainTC+"','isGive');\" class=\"packagePopupBtn\">确认办理<\/a>";
		
		$(".packagePopup").html(giveHead+giveMain+giveFood).show();
        $(".popupbg").show();

        $(".packagePopupClose,.packagePopupBtn").on("click",function(){
            $(".packagePopup,.popupbg").hide();
        });
		
		//调用popup函数
		//popup(".pHandleBtn",".packagePopup");
		
		
	}else{
	    
		handleFlow(mainTC,"noGive");
		
	}
	
}

function handleFlow(mainTC,giveState){
	
	var	giveTC="";
	
    var AGENTNO=sys.getHttpParam("AGENTNO");
	
	if(giveState=='isGive'){
		
	   giveTC=$("input[name='sel01']:checked").next().next().text();
	   
	}
	
	msgConfirm("提醒","<br><br><p align='center'>确认为"+sessionPhone+"办理"+mainTC+"吗？</p>",function(){
		msgTips("办理状态","<br>正在处理,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	   	
		$.ajax({
	        url: sys.getBaseUrl() + "flow/handleFlowTc.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {mainTC:mainTC,giveTC:giveTC,AGENTNO:AGENTNO},
	        success: function (data) {
            	jy.ReMoveUserMsg();
	        	msgTips("办理状态",data.msg,function(){});
	        	
	        }
		
		});
		
	});
	
}

function banner(id,url,type,name){
	
	var tourl="";
	
	if(type=="0"){
		tourl=sys.getBaseUrl()+url;
	}else{
		tourl=url;
	}
	sys.goUrl(sys.getBaseUrl()+"/new2015/index/messagesIframe.jsp?tourl="+tourl+"&type="+type+"&id="+id,0);
	
}

function popup(obj, showobj,mainTC){
    //#判断事件,pc端或移动端
    var hasTouch = navigator.userAgent.match(/mobile/i);
    var hasStart;
    if (hasTouch) {
        hasStart = 'touchstart';
    }
    else {
        hasStart = 'click';
    }
    $(obj).on(hasStart, function(){
        $(showobj).show();
        $(".popupbg").show();
    });
    $(".packagePopupClose").on(hasStart,function(){
        $(showobj).hide();
        $(".popupbg").hide();
    });
    $(".packagePopupBtn").on(hasStart,function(){
        var giveFood="<a href=\"javascript:\" onClick=\"handleFlow('"+mainTC+"','isGive');\" class=\"packagePopupBtn\">确认办理<\/a>";
        $(".packagePopup").append(giveFood);
    });

}
//调用popup函数
//popup(".pHandleBtn",".packagePopup","23");
