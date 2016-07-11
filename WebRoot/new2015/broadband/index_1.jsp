<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>宽带专区</title>
<link href="../css/reset.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/swiper.css" rel="stylesheet" type="text/css" />
<link href="../css/broadband.css" rel="stylesheet" type="text/css" />
<%
String path=request.getContextPath();
%>
</head>
<body class="bodybg">
	<div class="main">
        <div class="banner pr">
        	<div class="swiper-container" id="slider" >
                <ul class="swiper-wrapper" style="height:auto" id="banner">
                    
                     </ul>
                 <div class="swiper-pagination banner-pagination"></div>
            </div>
        </div>
        <div class="h1em"></div>
        <div class="pd1em broadband-index">
        	<div class="ads-row broadband-ban">
            	<div class="ads-c-6">
        	<a href="onlinePayment.jsp">
                    <div class="bi-icon-items pr lh-14">
                        <div class="bi-icons bi-icons-1 bg-cover pa"></div>
                        <div class="btitle text-overflow"><b class="fem-12">宽带续费</b></div>
                        <div class="bsmalltext lh-12">
                            更快更好更省的选择，为您续。
                        </div>
                    </div>
                  </a>
                  </div>
                  <div class="ads-c-6">
                  <a href="queryAddress.jsp">
                    <div class="bi-icon-items pr lh-14 broadborderL">
                        <div class="bi-icons bi-icons-2 bg-cover pa"></div>
                        <div class="btitle text-overflow"><b class="fem-12">地址查询</b></div>
                        <div class="bsmalltext lh-12">
                            确定您周边是否有移动宽带覆盖。
                        </div>
                    </div>
                  </a>
                  </div>
            </div>
            <div class="ads-row">
            	<div class="ads-c-12">
                    <div class="bi-icon-items pr lh-14 broadborderT">
                    	<div class="broadband-ban-2">
                            <div class="bi-icons bi-icons-3 bg-cover pa"></div>
                            <div class="btitle text-overflow"><b class="fem-12">宽带报装</b></div>
                            <div class="bsmalltext lh-12">
                                在线报装，7*24小时不下线的服务，一键享有。
                            </div>
                        </div>
                       <a href="broadbandInstall.jsp" class="btn_bz radius-05">马上报装 <span class="col1_span1"></span></a>
                    </div>
                  </div>
            </div>
            
            <div class="bi-title pd1em mt-1em">
        		<span class="fem-12">宽带服务</span>
       		 </div>
            <div class="ads-row broadban-server-list broadbg">
            	<div class="ads-c-3 pr">
                	<a class="icon-link" href="#">
                        <div class="bi-icons bi-icons-4 bg-cover"></div>
                        覆盖查询
                     </a>
                </div>
                <div class="ads-c-3 pr">
                	<a class="icon-link" href="#">
                        <div class="bi-icons bi-icons-5 bg-cover"></div>
                        业务办理
                     </a>
                </div>
                <div class="ads-c-3 pr">
                	<a class="icon-link" href="#">
                        <div class="bi-icons bi-icons-6 bg-cover"></div>
                        自助续费
                     </a>
                </div>
                <div class="ads-c-3 pr">
                	<a class="icon-link" href="#">
                        <div class="bi-icons bi-icons-7 bg-cover"></div>
                        业务查询
                     </a>
                </div>
            </div>
        </div>
        
    </div>    
    
</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
		jy.innertHead('宽带专区');
        jy.innertFood(2);
        <%--jy.initSlider();--%>
		//获取Banner
	    $.ajax({
			
			url :sys.getBaseUrl()+"index/getBannerByArea.do",
			type:"post",
			dataType:"json",
			data:{area:'4'},
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
	
	
		function banner(id,url,type,name){
			
			var tourl="";
			
			if(type=="0"){
				tourl=sys.getBaseUrl()+url;
			}else{
				tourl=url;
			}
			sys.goUrl(sys.getBaseUrl()+"/new2015/index/messagesIframe.jsp?id="+id,0);
			
		}
	
</script>

</html>
