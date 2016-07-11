<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="format-detection" content="telephone=no" />
    <title>消息详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body class="mesbodyBg">
<div class="main">
    <div class="mesDetMain">
        <h2 class="mesDetTitle lh-12">${msg.TITLE}</h2>
        <span class="lh-14">${msg.STARTTIME} 至 ${msg.ENDTIME}</span>
        <div class="mesDetText">${msg.CONTENT}</div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main'], function(a) {
    	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/messagesDetail");
    });
</script>

</html>
