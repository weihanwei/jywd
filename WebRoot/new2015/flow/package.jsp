<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/package.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>流量套餐</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <!--bananer start-->
    <div class="banner pr">
        <div class="swiper-container" id="slider">
            <ul class="swiper-wrapper" style="height:auto" id="banner"><!--
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/flow/banner01.jpg">
                </li>
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/flow/banner01.jpg">
                </li>
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/flow/banner01.jpg">
                </li>
            --></ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div>
    <!--bananer end-->
    <!--package start-->
    <ul class="package packageNew">
    
    </ul>
    <!--package start-->
</div>

<!--packagePopup start-->
<div class="packagePopup hide">
    
</div>

<div class="popupbg hide"></div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','recommend'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/package");
    });
    
     var sessionPhone='<%=request.getSession().getAttribute("phone")%>';
</script>
</html>