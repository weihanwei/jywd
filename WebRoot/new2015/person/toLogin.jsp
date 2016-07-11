<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lenovocw.utils.RandomNumUtil"%> 
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>欢迎进入揭阳移动微店--店长推荐登录</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bodybg">
<div class="main">
    <div class="login-wrap toScaned">
        <div class="toScanedText">您已经成功扫描二维码，请输入您手机号码和验证码即可进入揭阳微店。</div>
        <div class="h1em"></div>
        <div class="ads-row">
            <div class="ads-c-12"><input type="hidden" id="tcid" value="${param.tcid}"><input type="hidden" id="dzid" value="${param.id}"><input type="hidden" id="dhhm" value="${param.dhhm}"><input type="text" class="ltextput" id="jyn" placeholder="请输入用户手机号码"></div>
        </div>
        <!---type msg-->
        <div class="showContent">
            <div class="ads-row">
                <div class="ads-c-8 pr-1em">
                    <input type="text" class="ltextput" id="code" placeholder="短信验证码">
                </div>
                <div class="ads-c-4"><a href="javascript:" onclick="getRandomPassword(this);" class="aBtn aBtn-green"><span class="fem-12">点击获取</span></a>
                </div>
            </div>
        </div>

        <!--typechange end-->

        <div class="ads-row">
            <div class="ads-c-12"><a href="javascript:" onclick="toLogin();" class="aBtn aBtn-blue"><span class="fem-12">确认登录</span></a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main','tfAlert'], function (a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/" + SCRIPT_PATH + "/model/person/toLogin");
    });
    
    <%
    RandomNumUtil rnum=RandomNumUtil.Instance();
    String key=rnum.getString();
    %>
    
    var key='<%=key%>';
    
</script>

</html>
