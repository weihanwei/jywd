<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>流量套餐详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main pr">
    <div class="pDetHead pr">
        <div class="pDetHeadImg f-l">
            <img src="${pageContext.request.contextPath}/new2015/images/flow/pic4.jpg" alt="套餐图片" />
        </div>
        <div class="pDetHeadMain f-l">
            <h3>手机流量5元套餐</h3>
            <p>价格：5元/月</p>
            <p>适用品牌：全球通、神州行、动感地带</p>
            <p>适用范围：全国</p>
        </div>
    </div>
    <div class="pDetMain">
        <div class="pDetTitle">优惠介绍</div>
        <div class="pDetText">
            <p>赠送200M流量/月，连续奖励3个月，奖励流量为国内通用流量，下一月结日（全球通次月1日）生效；优惠期间客户不能取消或降低套餐。</p>
            <h4 class="pDetLittleTitle">套餐包含</h4>
            <p>国内通用：30M</p>
            <p>省内通用：0M</p>
            <p>额外赠送：200M国内通用流量/月</p>
        </div>
    </div>
    <div class="footerBtn">
        <div class="main">
            <a href="#" class="greenBtn">办理</a>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/index/index");
    });
</script>
</html>