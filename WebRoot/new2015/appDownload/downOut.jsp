<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/appDownload/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="format-detection" content="telephone=no" />
    <title>APP下载</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body >
<div class="main">
    <div class="outMain">
        <h2>温馨提示</h2>
        <p>请点击微信右上角按钮，然后再弹出的菜单中，点击在Safari中打开，即可安装</p>
    </div>
    <div class="outImg">
        <img src="${pageContext.request.contextPath}/${downData.ICON}" />
    </div>
    <div class="outFoot">
        <a onclick="onClickDownButton('${downData.IOSDOWNURL}');" class="blueBtn">点击安装</a>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">

    seajs.use(['jquery','JSBridge','main','swiper','tfAlert'], function(a) {

    });
   
    	function onClickDownButton(url)
		{
		   var userAgent = navigator.userAgent; 
		    if(userAgent.indexOf("Safari") > -1){
		    
		         window.location.href=(url);
		         
		    }else
		    {
		         $.alerts.alert("请点击微信右上角按钮，然后再弹出的菜单中，点击在Safari中打开，即可安装！", "提示");
		    }
		}
</script>

</html>
