define(function(require) {
	
    jy.innertHead('流量专区');
	
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
		
	
});


function packagedo(){
	
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/package.jsp?date="+new Date(),1);
	
}
function buyRedpackage(){
	sys.goUrl(sys.getBaseUrl()+"new2015/index/hint.html",1);
//	sys.goUrl(sys.getBaseUrl()+"new2015/flow/redpackageList.jsp",1);
//	 alert(checkLastActive());
}
function checkLastActive(){ 
	var check = true ;
	var str ='2015-12-28 00:00:00';
	str = str.replace(/-/g,"/");
	var date = new Date(str );
    var mydate=new Date();  
    if(mydate.getTime()<date.getTime()){  
    	return check ;
    }else{
    	check = false ;
    	return check;
    }
} 

function overlayPackage(){
	
	//暂时屏蔽
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/overlayPackage.jsp?ACTIVITYID=1",1);
	//sys.goUrl(sys.getBaseUrl()+"new2015/flow/tip.jsp",0);
	
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