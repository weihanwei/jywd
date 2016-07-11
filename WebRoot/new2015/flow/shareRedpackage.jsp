<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/shareRedpackage.appcache">
<html>
<head> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>流量红包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body class="bodybg">
<div class="main" id="mainDiv">
   
</div>
<div class="shareBtnPad main">
    <a href="javascript:" onClick="javascript:buyRedpackage();" class="aBtn aBtn-green"><span class="fem-12">我要发流量红包</span></a>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','tfAlert','JSBridge','main'], function(require) {
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent"); 
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare");
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/shareRedpackage");
    });
</script>

</html>
