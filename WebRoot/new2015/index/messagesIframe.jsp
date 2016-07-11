<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index/messagesIframe.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>特惠广场详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/index.css" rel="stylesheet" type="text/css"/>
    <style>
        .iframeBox img{width: auto;
                       display: inline-block;
                       }
   </style>
</head>
<body>
<div class="iframeBox" id="iframeBoxDiv">
    <iframe id="contextIframeBox" src="" width="100%" height="100%" marginheight="0" marginwidth="0" class="mIframe"></iframe>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
    	jy.innertHead('特惠广场');jy.innertFood(3); jy.initSlider();
     	//var tourl=sys.getHttpParam("tourl");
     	var id =sys.getHttpParam("id");
     	//var type =sys.getHttpParam("type");
     	
     	       //0 表示是内网的自己配置的网页
     	       //查询配置的html数据并将其显示在数据页面
     	       $.ajax({
					url :sys.getBaseUrl()+"discount/getDiscountContent.do",
					type:"post",
					dataType:"json",
					data:{id:id},
					success :function(data){
					     var type =data.TYPE;
					     if(type=='0')
					     {
						      var contVar =$('<div/>').html(data.HTMLCONTENT).text();
						      $('#iframeBoxDiv').html(contVar);
					     }else
					     {
					         //非0表示是外网数据直接跳转
     	                     $(".mIframe").attr("src",data.URL);
					     }

					  }
			    });
     	    
 
     	
    });
</script>

</html>