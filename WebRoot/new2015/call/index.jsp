<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/call/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>通话专区</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/call.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <div class="banner pr">
        <div class="swiper-container" id="slider" >
            <ul class="swiper-wrapper" style="height:auto" id="banner">
                
            </ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div>
    <!--callMain start-->
    <div class="callMain">
        <div class="callUser" id="phone"><i class="bg-cover"></i>--------- , 欢迎您 。</div>
        <div class="callCard" id="msg4g">
            <i class="bg-cover"></i>
            ---------
            <span>---------</span>
        </div>
        <div class="callBalance" id="hf"><i class="bg-cover"></i>话费余额：-------元</div>
    </div>
    <!--callMain end-->
    <!--call start-->
    <div class="callIcon">
        <div class="ads-row">
            <div class="ads-c-4">
                <a class="icon-link text-overflow" href="javascript:" onclick="topUp();">
                    <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/icons/call01.png"></span>
                    话费充值
                </a>
            </div>
            <div class="ads-c-4">
                <a class="icon-link text-overflow" href="javascript:" onclick="callRedpackets();">
                    <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/icons/call02.png"></span>
                    通话红包
                </a>
            </div>
            <div class="ads-c-4">
                <a class="icon-link text-overflow" href="javascript:" onclick="cornetNet();">
                    <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/icons/03.png"></span>
                    短号网
                </a>
            </div>
            <div class="ads-c-4">
                <a class="icon-link text-overflow" href="javascript:" onclick="relativesNet();">
                    <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/icons/call03.png"></span>
                    亲友网
                </a>
            </div><!--
            <div class="ads-c-4 text-overflow">
                <a class="icon-link" href="#">
                    <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/icons/call04.png"></span>
                    短信包(未配)
                </a>
            </div>
            --><div class="ads-c-4 noborder">
            </div>
        </div>
    </div><!--main-icon end-->
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','data'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/call/index");
    });
</script>

</html>
