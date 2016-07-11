define(function (require) {
    jy.innertHead('搜索');

    jy.innertFood(1);
    
    $(".searchBtn").on(hasStart, function () {
    	 pro();
    });
    
});

//#判断事件,pc端或移动端
var hasTouch = navigator.userAgent.match(/mobile/i);
var hasStart;
if (hasTouch) {
    hasStart = 'touchstart';
}
else {
    hasStart = 'click';
}

var globalType=0;

//显示加载圆圈
function pro(){
	
	var keyword=$("input[name=search]").val();
	
	if(keyword==''){
		$.alerts.alert("请输入关键字！","提示");
		return false;
	}
	
    var proText = "<div class='searchLoad'>" +//
                        "<div class='ball-spin-fade-loader'>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                            "<div></div>" +//
                        "</div>" +//
                    "</div>";

    $(".searchMain").html(proText);
    
    Search(keyword,globalType);
    
}

function selectLi(obj,type){
	
	var $newobj=$(obj);
	
	var $oldobj=$(".searchNav").find(".on");
	
	$oldobj.removeClass("on");
	
	$newobj.addClass("on");
	
	globalType=type;
	
	 $('.searchMain').hide();
	 $('#'+type+"_searchMain").show();
	
}

function Search(keyword,type){
	
	var showDivId =type+"_searchMain";
	
	$.ajax({
		url :sys.getBaseUrl()+"index/searchMSG.do",
		type:"post",
		asyne :false,
		dataType:"json",
		data:{type:type,keyword:keyword},
		success :function(data){
			
			//设置全部区域的数据
			var proText="";
			
			if(data.length>0){
				
				proText=proText+"<div class=\"left-icon-list\">"+//
                                  "<ul>";
				var src;
				$.each(data,function(i,j){
					
					src=sys.getBaseUrl()+j.ICON;
					
					proText=proText+"<li class=\"icon-jt-right\">"+//
                                      "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                                        "<span class=\"icon\">"+//
                                          "<img src="+src+">"+//
                                         "</span>"+//
                                         "<p>"+j.NAME+"</p>"+//
                                         "<p class=\"text-overflow\">"+j.DETAILS+"</p>"+//
                                      "</a>"+//
                                    "</li>";
				   });
				
				proText=proText+"</ul>"+//
                                "</div>";
				
			}else{
				
				proText="<div class=\"nomain\">很抱歉，搜索不到您要的结果！</div>";
			    
			}
            
		    $("#0_searchMain").html(proText);
			
		
		//设置其他区域的数据
		var one_Html="";
		var one_count=0;
		
		var two_Html="";
		var two_count=0;
		
		var three_Html="";
		var three_count=0;
		
		var four_Html="";
		var four_count=0;
		
		
		if(data.length>0){
			
			one_Html=one_Html+"<div class=\"left-icon-list\">"+//
                              "<ul>";
			
			two_Html=two_Html+"<div class=\"left-icon-list\">"+//
            "<ul>";
			
			three_Html=three_Html+"<div class=\"left-icon-list\">"+//
            "<ul>";
			
			four_Html=four_Html+"<div class=\"left-icon-list\">"+//
            "<ul>";
			
			var src;
			$.each(data,function(i,j){
				
				src=sys.getBaseUrl()+j.ICON;

				if('1'==j.TYPE)
				{
					one_Html=one_Html+"<li class=\"icon-jt-right\">"+//
	                    "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
	                      "<span class=\"icon\">"+//
	                        "<img src="+src+">"+//
	                       "</span>"+//
	                       "<p>"+j.NAME+"</p>"+//
	                       "<p class=\"text-overflow\">"+j.DETAILS+"</p>"+//
	                    "</a>"+//
	                  "</li>";
					one_count=one_count+1;
					
				}else if('2'==j.TYPE)
				{
					two_Html=two_Html+"<li class=\"icon-jt-right\">"+//
                    "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                      "<span class=\"icon\">"+//
                        "<img src="+src+">"+//
                       "</span>"+//
                       "<p>"+j.NAME+"</p>"+//
                       "<p class=\"text-overflow\">"+j.DETAILS+"</p>"+//
                    "</a>"+//
                  "</li>";
					two_count=two_count+1;
					
				}else if('3'==j.TYPE)
				{
					three_Html=three_Html+"<li class=\"icon-jt-right\">"+//
                    "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                      "<span class=\"icon\">"+//
                        "<img src="+src+">"+//
                       "</span>"+//
                       "<p>"+j.NAME+"</p>"+//
                       "<p class=\"text-overflow\">"+j.DETAILS+"</p>"+//
                    "</a>"+//
                  "</li>";
					three_count=three_count+1;
				}else
				{
					four_Html=four_Html+"<li class=\"icon-jt-right\">"+//
                    "<a href=\"javascript:\" onclick=\"goAPP('"+j.URL+"','"+j.ISNETWORK+"','"+j.NAME+"');\">"+//
                      "<span class=\"icon\">"+//
                        "<img src="+src+">"+//
                       "</span>"+//
                       "<p>"+j.NAME+"</p>"+//
                       "<p class=\"text-overflow\">"+j.DETAILS+"</p>"+//
                    "</a>"+//
                  "</li>";
					four_count=four_count+1;
				}

			   });
			
			one_Html=one_Html+"</ul>"+//
                            "</div>";
			two_Html=two_Html+"</ul>"+//
            "</div>";
			three_Html=three_Html+"</ul>"+//
            "</div>";
			four_Html=four_Html+"</ul>"+//
            "</div>";
			
		}
		
		if(one_count==0)
		{
			one_Html="<div class=\"nomain\">很抱歉，搜索不到您要的结果！</div>";
		}
		
		if(two_count==0)
		{
			two_Html="<div class=\"nomain\">很抱歉，搜索不到您要的结果！</div>";
		}
		
		if(three_count==0)
		{
			three_Html="<div class=\"nomain\">很抱歉，搜索不到您要的结果！</div>";
		}
		
		if(four_count==0)
		{
			four_Html="<div class=\"nomain\">很抱歉，搜索不到您要的结果！</div>";
		}

		 $("#1_searchMain").html(one_Html);
		 $("#2_searchMain").html(two_Html);
		 $("#3_searchMain").html(three_Html);
		 $("#4_searchMain").html(four_Html);
		
		 $('.searchMain').hide();
		 $('#'+showDivId).show();
		}
		
		});
}

function goAPP(url,type,name){
	
	if(type=='1'){
		
		jy.G(sys.getBaseUrl()+url,1,name, '');
		
	}else{
		
		var uuid="";
		
		//app功能
	    $.ajax({
			
			url :sys.getBaseUrl()+"login/getUUID.do",
			type:"post",
			dataType:"json",
	     	cache : false, 
	    	async : false, 
			data:'',
			success :function(data){
				uuid=data.UUID;
			  }
		
	    });
		
	    url=url+"&uuid="+uuid;
	    
		jy.G(url,1,name, '');
		
	}
	
}