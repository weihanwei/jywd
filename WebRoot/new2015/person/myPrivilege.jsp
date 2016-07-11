<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/myPrivilege.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>我的优惠</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <ul class="searchNav myPriwidth">
        <li class="on" onclick="flash(this,4);">4G购机</li>
        <li onclick="flash(this,5);">流量与宽带</li> 
        <li onclick="flash(this,6);">充值与其他</li>
    </ul>
    <dl class="myPri">
    
    </dl>
    <div class="noMain hide">亲~您暂无优惠哦！</div>
    <div class="h5em"></div>
    <div class="loginHint b-4">
        <div class="main">
            <div class="loginHintMain">此查询结果仅供参考，实际情况以营业厅告知为准！</div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','tmpl','data'], function(a) {
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/myPrivilege");
    });
</script>

</html>
