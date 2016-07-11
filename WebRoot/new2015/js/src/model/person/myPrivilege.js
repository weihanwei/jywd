/*我的金币*/
var data_4g_map = "";
var data_top_map = "";
var data_other_map = "";
var data_4g_totalCount= "";
var data_top_totalCount= "";
var data_other_totalCount= "";

define(function(require) {
	
    jy.innertHead('我的优惠');
    
    jy.innertFood(4);

    //绑定tab切换事件
    jy.switchTabList(".myPri dt.text-overflow");

    jy.loadingShow();
    
    ZHData.get("myPrivilegeMSG",function(data){

				data_4g_map=data.data_4g_list;
				data_top_map=data.data_top_list;
				data_other_map=data.data_other_list;
				
				data_4g_totalCount=data.data_4g_list.length;
				data_top_totalCount=data.data_top_list.length;
				data_other_totalCount=data.data_other_list.length;
				
				var activity="";
				var type=4;
				
				if(data_4g_totalCount=='0'){
					
					activity=activity+"<div class=\"noMain\">亲~您暂无4G购机优惠哦！</div>";
					
				}else{
					activis = data_4g_map;
					
					for(var i = 0; i < activis.length; i ++){
						
						var j = i+1;
								
						activity = activity + "<dt class='text-overflow' onclick='showhidediv(\""+j+"\")' ><span>优惠"+j+"</span>"+activis[i].activityName+"</dt>"
						+ "<dd id='"+j+"'>"
				            + "<div class='myPriTitle'>内容简介：</div>"
				            + "<p>"+activis[i].activityIntro+"</p>"
				            + "<a href=\"javaScript:\" onclick=\"goPrivilegeDetails('"+encodeURI(encodeURI(activis[i].activityName))+"','"+encodeURI(encodeURI(activis[i].beginTime))+"','"+encodeURI(encodeURI(activis[i].endTime))+"','"+encodeURI(encodeURI(activis[i].activityContent))+"');\" class=\"myPriA\">更多信息</a>"
			            +"</dd>";
				   }
					
				}
				
				$(".myPri").html(activity);
				
				jy.loadingHide();
	
    });
   
});    

//绑定tab切换事件
function flash(obj,type){
	var activis = "";
	var totalCount = "";
	
	if(type == '4'){
		activis = data_4g_map;
		totalCount = data_4g_totalCount;
	}
	if(type == '5'){
		activis = data_top_map;
		totalCount = data_top_totalCount;
	}
	if(type == '6'){
		activis = data_other_map;
		totalCount = data_other_totalCount;
	}
	
	var $oldobj=$(".searchNav").find(".on");
	
	var $newobj=$(obj);
	
	$oldobj.removeClass("on");
	
	$newobj.addClass("on");
	
	var activity="";
	
	if(totalCount=='0'){
		if(type == '4'){
			activity=activity+"<div class=\"noMain\">亲~您暂无4G购机优惠哦！</div>";
		}
		if(type == '5'){
			activity=activity+"<div class=\"noMain\">亲~您暂无流量与宽带优惠哦！</div>";
		}
		if(type == '6'){
			activity=activity+"<div class=\"noMain\">亲~您暂无充值与其他优惠哦！</div>";
		}
		
	}else{
		for(var i = 0; i < activis.length; i ++){
			
			var j = i+1;
					
			activity = activity + "<dt class='text-overflow' onclick='showhidediv(\""+j+"\")' ><span>优惠"+j+"</span>"+activis[i].activityName+"</dt>"
			+ "<dd id='"+j+"'>"
	            + "<div class='myPriTitle'>内容简介：</div>"
	            + "<p>"+activis[i].activityIntro+"</p>"
	            + "<a href=\"javaScript:\" onclick=\"goPrivilegeDetails('"+encodeURI(encodeURI(activis[i].activityName))+"','"+encodeURI(encodeURI(activis[i].beginTime))+"','"+encodeURI(encodeURI(activis[i].endTime))+"','"+encodeURI(encodeURI(activis[i].activityContent))+"');\" class=\"myPriA\">更多信息</a>"
            +"</dd>";
	   }
		
	}
	
	$(".myPri").html(activity);
	

}

function goPrivilegeDetails(activityName,beginTime,endTime,activityContent){
	
/*	activityName=encodeURI(encodeURI(activityName));
	beginTime=encodeURI(encodeURI(beginTime));
	endTime=encodeURI(encodeURI(endTime));
	activityContent=encodeURI(encodeURI(activityContent));*/
	
	sys.goUrl(sys.getBaseUrl() + "new2015/person/myPrivilegeDetails.jsp?activityName="+activityName+"&beginTime="+beginTime+"&endTime="+endTime+"&activityContent="+activityContent, 1);
	
}

function showhidediv(id) {
	
	var sbtitle = document.getElementById(id);
	if (sbtitle) {
		if (sbtitle.style.display == 'block') {
			sbtitle.style.display = 'none';
		} else {
			sbtitle.style.display = 'block';
		}
	}
} 