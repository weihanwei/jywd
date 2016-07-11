<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@page import="com.lenovocw.utils.RandomNumUtil"%> 
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index/bindingPage.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>消息详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/login.css" rel="stylesheet" type="text/css" />
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
            <div class="ads-c-20">手机号码:</div>
            <div class="ads-c-80 inputW-90"><input type="text" class="ltextput" placeholder="请输入手机号码" id="bindPhone"><span class="red">*</span></div>
        </div>
        <!---type msg-->
        <div class="showContent">
            <div class="ads-row">
                <div class="ads-c-20">动态密码:</div>
                <div class="ads-c-6 inputW-90">
                    <input type="text" class="ltextput f-l" placeholder="短信验证码" name="bindYZM"><span class="red">*</span>
                </div>
                <div class="ads-c-3"><a href="javascript:" onclick="getRandomPassword(this);" class="aBtn aBtn-green"><span class="fem-12">点击获取</span></a>
                </div>
            </div>
        </div>

        <div class="ads-row">
            <div class="ads-c-12"><a href="javascript:" onclick="onSubmitBunding();" class="aBtn aBtn-blue"><span class="fem-12">确认登记</span></a>
            </div>
        </div>
    </div>
</div>
<div class="hide loginHint" id="receiveMessInfo">
                <div class="main">
                    <div class="loginHintMain">动态密码已发送到您的手机上，请注意查收！</div>
                </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','tfAlert'], function(a) {
    	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/bindingPage");
    });
     var key="<%=key%>";
</script>

</html>
