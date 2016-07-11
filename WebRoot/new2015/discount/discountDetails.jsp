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
    <title>专享特惠详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/discount.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main pr">
    <!--bananer start-->
    <div class="banner pr">
        <div class="swiper-container" id="slider">
            <ul class="swiper-wrapper" style="height:auto">
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/PIC/pic2.jpg" />
                </li>
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/PIC/pic2.jpg" />
                </li>
                <li class="swiper-slide">
                    <img src="${pageContext.request.contextPath}/new2015/images/PIC/pic2.jpg" />
                </li>
            </ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div>
    <!--bananer end-->

    <div class="disDetail">
        <div class="disDetHead">
            <div class="disDetTitle" id="NAME">----------------</div>
            <p id="TYPE">业务类型：------</p>
            <p id="STARTTIME">开始时间：--------- --------</p>
            <p id="ENDTIME">结束时间：--------- --------</p>
        </div>
        <div class="disDetMain">
            <div class="disDetMainTitle">活动内容</div>
            <p>尊敬的客户：</p>
            <p class="disText" id="INTRODUCE">---------------------</p>
        </div>
    </div>
    <div class="footerBtn">
        <div class="main">
            <a href="javascript:" onclick="toHandle();" class="greenBtn">在线办理</a>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
		seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/discount/discountDetails");
    });
</script>
</html>