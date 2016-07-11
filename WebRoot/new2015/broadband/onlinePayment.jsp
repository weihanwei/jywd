<%@page import="com.lenovocw.utils.RandomNumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>宽带在线缴费验证</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="../css/broadband.css" rel="stylesheet" type="text/css"/>
</head>
<%
String path=request.getContextPath();
RandomNumUtil rnum=RandomNumUtil.Instance();
String key=rnum.getString();
%>
<body>
<div class="main">

    <div class="banner pr">
        <div class="swiper-container" id="slider">
            <ul class="swiper-wrapper" style="height:auto" id="banner">

            </ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div>
    <div class="pd1em broadband-index">
        <div class="h2em"></div>
        <div class="table pay-bar">
            <div class="table-cell pay-bar-icon-wrap active">
                <div class="radius-icon-div pr">
                    <span class="text">验证</span>
                    <span class="radius-icon-span"><b class="fem-15"><img src="../images/broadband/icon_ok.png"
                                                                          class="icon-ok"></b></span>
                </div>
            </div>
            <div class=" table-cell active-half">
                <div class="radius-icon-bar"></div>
            </div>
            <div class=" table-cell pay-bar-icon-wrap ">
                <div class="radius-icon-div pr">
                    <span class="text">选择</span>
                    <span class="radius-icon-span"><b class="fem-15">2</b></span>
                </div>
            </div>
            <div class="table-cell">
                <div class="radius-icon-bar"></div>
            </div>
            <div class="table-cell pay-bar-icon-wrap">
                <div class="radius-icon-div pr">
                    <span class="text">查询或支付</span>
                    <span class="radius-icon-span"><b class="fem-15">3</b></span>
                </div>
            </div>
        </div>
        <div class="h2em"></div>
        <p class="color_red lh-15">
            <span class="fem-12">在线续费：</span>
        </p>

        <div class="broadNav">
            <ul>
                <li class="f-l on" tvalue="kd">宽带账号</li>
                <li class="f-l" tvalue="sj">手机号码</li>
            </ul>
        </div>
        <div class="h1em"></div>
        <div class="broadshow">
            <div class="ads-row">
                <div class="ads-c-12">
                    <input type="text" class="ltextput J_footHide" placeholder="请输入宽带账号" value="" name="kdzh">
                </div>
            </div>
            <div class="h1em"></div>
            <div class="ads-row">
                <div class="ads-c-12">
                    <input type="text" class="ltextput J_footHide" placeholder="请输入身份证号" value="" name="sfz">
                </div>
            </div>
        </div>
        <div class="broadshow hide">
            <div class="ads-row">
                <div class="ads-c-12">
                    <input type="text" class="ltextput J_footHide" placeholder="请输入手机号码" value="" name="sjhm" id="sjhm">
                </div>
            </div>
            <div class="h1em"></div>
            <div class="ads-row">
                <div class="ads-c-8 pr-1em">
                    <input type="text" class="ltextput J_footHide" placeholder="请输入验证码" name="yzm">
                </div>
                <div class="ads-c-4"><a href="javascript:" class="aBtn aBtn-green" onclick="javascript:getRandomyzm(this);"><span class="fem-12">获取验证码</span></a>
                </div>
            </div>
        </div>
        <div class="h2em"></div>
        <a href="javascript:" class="aBtn aBtn-blue" onclick="sumbitDD()"><span class="fem-12">提交</span></a>

        <div class="h1em"></div>
        <a href="javascript:" class="aBtn aBtn-red" onclick="queryDD();"><span class="fem-12">订单查询</span></a>

        <div class="h2em"></div>
    </div>


</div>

</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge','tfAlert', 'main', 'swiper'], function (a) {
		jy.innertHead('宽带在线缴费验证');
        jy.initSlider();
        jy.navShow(".broadNav ul li", ".broadshow");
        seajs.use("<%=path%>/new2015/js/"+SCRIPT_PATH+"/model/broadband/onlinePayment");
    });
    var key="<%=key%>";
</script>

</html>