<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/toHandle.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>我要做店长</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bodybg">
<div class="main">


</div>
<!--二维码弹出框 start-->
<div class="toBePopu toBeTop hide">
    <div class="toPopuHanHead"><i class="bg-cover"></i>扫描二维码</div>
    <div class="toPopuHan">
        
    </div>
    <div class="toBePopuBtn">
        <a href="javascript:" onclick="submit();" class="aBtn aBtn-blue">确定</a>
    </div>
</div>
<!--二维码弹出框 end-->

<div class="popupbg hide"></div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/qrcode.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main', 'tmpl','recommend','tfAlert'], function (a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/" + SCRIPT_PATH + "/model/person/toHandle");
    });
</script>

</html>
