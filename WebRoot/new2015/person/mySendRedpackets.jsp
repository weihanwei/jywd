<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>我发起的红包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <div class="myPriNav">
        <ul>
            <li class="f-l on">流量红包</li>
            <li class="f-l">通话红包</li>
        </ul>
    </div>
    <div class="myPriMain">
        <ul class="mySendRedp myShow" id="flowRedpackageList">
        </ul>
        <ul class="mySendRedp myShow noDetails hide" id="callRedpackageList"><!--
            <li>
                <a href="#">
                    <h3>张三的流量红包<span class="f-r">10M</span></h3>
                    <p>2015-07-25<span class="f-r">已领取4/10个</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <h3>张三的流量红包<span class="f-r">10M</span></h3>
                    <p>2015-07-25<span class="f-r">已领取4/10个</span></p>
                </a>
            </li>
        --></ul>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','tmpl'], function(a) {
    	
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/mySendRedpackets");
    	
    });
</script>

</html>
