<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/index.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>流量叠加包1</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <!--bananer start-->
    <div class="tipPMian">
        <div class="f-l pr-1em">
            <img src="${pageContext.request.contextPath}/new2015/images/common/smile.png" />
        </div>
        <div class="f-l">
            <h5 class="tipPTitle">亲~活动升级调整中，敬请期待哦！</h5>
            <p>您可以选择<a href="#" onclick="goToIndex()">返回首页</a></p>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main'], function(a) {
    });
    function goToIndex()
    {
        sys.goUrl(sys.getBaseUrl() + "new2015/index/index_1.jsp", 0);
    }
</script>
</html>