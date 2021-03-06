<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/cashSuccessed.appcache">
<head> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>流量红包兑换</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <div class="buyed">
        <div class="buyedImg">
            <img src="${pageContext.request.contextPath}/new2015/images/flow/buyedbg.png" />
        </div>
        <div class="pd1em">
            <div class="buyedTitle">恭喜您！</div>
            <p class="cashText">该流量红包已成功兑换给${cashMobile}</p>
        </div>
        <div class="pd1em">
            <a href="javascript:" onClick="javascript:toShareRedpackage('${redpackageId}');" class="aBtn aBtn-blue"><span class="fem-12">查看领取详情</span></a>
            <div class="h1em"></div>
             <a href="javascript:" onClick="javascript:buyRedpackage();" class="aBtn aBtn-green"><span class="fem-12">我要发流量红包</span></a>
        </div>
        <div class="h2em"></div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','tmpl'], function(a) {
	    seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/cashSuccessed");
	});
</script>

</html>
    