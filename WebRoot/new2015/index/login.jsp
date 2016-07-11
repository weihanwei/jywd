<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lenovocw.utils.RandomNumUtil"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index/login.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
RandomNumUtil rnum=RandomNumUtil.Instance();
String key=rnum.getString();
%>
<div class="main">
 
    <div class="login-wrap">
        <div class="h1em"></div>
        <div class="ads-row">
            <div class="ads-c-12"><input type="text" id="jyn" class="ltextput" value="" placeholder="请输入手机号码"></div>
        </div>
        <div class="ads-row">
            <div class="ads-c-6 fem-12">
                <div class="radio-div">
                    <input type="radio" name="logintype" id="radius1" value="0" checked>
                    <label for="radius1">服务密码</label>
                </div>
            </div>
            <div class="ads-c-6 fem-12">
                <div class="radio-div">
                    <input type="radio" name="logintype" value="1" id="radius2">
                    <label for="radius2">动态密码</label>
                </div>
            </div>
        </div>

        <!--typechange-->


        <!---type password-->
        <div class="show showContent">
            <div class="ads-row">
                <div class="ads-c-12">
                    <input type="password" class="ltextput" id="jym1" placeholder="请输入服务密码" value="">
                </div>
            </div>
            <div class="ads-row">
            <!---<div class="ads-c-12 pl-1em">
                    <a href="#" class="forgetPassswor fem-12">忘记密码?</a>
                </div>-->
            </div>
        </div>

        <!---type msg-->
        <div class="hide showContent">
            <div class="ads-row">
                <div class="ads-c-8 pr-1em">
                    <input type="text" class="ltextput" id="jym2" placeholder="短信验证码">
                </div>
                <div class="ads-c-4"><a href="javascript:" onclick="getRandomPassword(this);" class="aBtn aBtn-green"><span class="fem-12">点击获取</span></a>
                </div>
            </div>
        </div>

        <!--typechange end-->

        <div class="ads-row">
            <div class="ads-c-12"><a  class="aBtn aBtn-blue" onclick="login();"><span class="fem-12">立即登录</span></a>
            </div>
        </div>
        <div class="ads-row">
            <div class="ads-c-12 fem-12">
                <div class="checkbox-div">
                    <input type="checkbox" id="checked1" checked>
                    <label for="checked1">同意《<a href="javascript:" onclick="xy();" class="blue">登录协议</a>》</label>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="hide loginHint" id="receiveMessInfo">
                <div class="main">
                    <div class="loginHintMain">动态密码已发送到您的手机上，请注意查收！</div>
                </div>
</div>
<!--<a onclick="goToBundingPhone();" class="blue">33333</a>
--></body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','swiper','tfAlert'], function(a) {
		seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/login");
	});
    
    var key="<%=key%>";

</script>

</html>
