define(function(require) {
	
    jy.innertHead('精品下载');
	
	jy.innertFood(4);
	
	$.ajax({
		
		url :sys.getBaseUrl()+"index/appDownloadMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			var downAppListMap=data;
			var downApp="";
			var androidurl;
			var iosurl;
			
			$.each(downAppListMap,function(i,j){

				var src=sys.getBaseUrl()+j.ICON;
				androidurl=j.DOWNURL;
				iosurl=j.IOSDOWNURL;				
				downApp=downApp+"<li>"+//
                "<div class=\"appImg\">" +//
                   "<img src="+src+">" +//
                "</div>"+//
                "<span>"+j.NAME+"</span>"+//
                "<div class='appDownBtn'>";//
                 if(iosurl)
                 {
                	 downApp=downApp+ '<a onclick="down(\''+iosurl+'\',\''+j.ID+'\',\''+j.NAME+'\',\'IOS\')" class="iosBtn"><i></i>IOS</a>';
                 }
                 if(androidurl)
                 {
                	 downApp=downApp+ '<a onclick="down(\''+androidurl+'\',\''+j.ID+'\',\''+j.NAME+'\',\'Android\')" class="androidBtn"><i></i>Android</a>';
                 }
				
                 downApp=downApp+ "</div>"+"</li>";

			});
			
			$(".appDown").html(downApp);
		  }
	});
	
});

function down(url,id,name,type){
	
     $.ajax({
		
		url :sys.getBaseUrl()+"Log/down.do",
		type:"post",
		dataType:"json",
		data:{id:id,name:name},
		success :function(data){ 
			   
		 }
	
    });
     
    //对于配置为itunes下载的页面，需要跳转到中转页面，提示用户使用safari浏览器打开
     if(('IOS'==type) && (url.indexOf('https://itunes.apple.com')>-1))
     {
    	 sys.goUrl(sys.getBaseUrl()+"index/appIosDownDetail.do?id="+id,0);
    	 
     }else
     {
    	 window.location.href=(url+"?type="+type);
     }
	
	
	
}

function banner(url,type,name){
	
	var tourl="";
	
	if(type=="0"){
		
		tourl=sys.getBaseUrl()+url;
		
	}else{
		
		tourl=url;
		
	}
	
	sys.goUrl(sys.getBaseUrl()+"/new2015/index/messagesIframe.jsp?tourl="+tourl,0);
	
}
