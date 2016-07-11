<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/receiveRedpackage.appcache">
<head> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>领取红包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body class="buybodybg">
<div class="main">
    <div class="buybg">
        <img src="${pageContext.request.contextPath}/new2015/images/flow/buybg.png" alt="图片" />
    </div>
    <div class="recebg receMarB pr">
        <img src="${pageContext.request.contextPath}/new2015/images/flow/receivebg.png" />
        <div class="receMain">
            <div class="receTitle">恭喜您！</div>
            <p >成功领取来自<span id="sendWechatName"></span>的<span id="flowSize"></span><span>MB流量红包，于7天内(不含领取当天)兑换有效。</span></p>
        </div>
    </div>
    <a href="javascript:" onClick="cashRedpackage();" class="buyVerifyBtn receMarB">立即兑换流量</a>
    <a href="javascript:" onClick="javascript:toShareRedpackage('${redpackageId}');" class="buyVerifyBtn receMarB">查看领取详情</a>
     <a href="javascript:" class="buyVerifyBtn receMarB" onClick="buyRedpackage();">我要发流量红包</a> 
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main'], function(require) {
	    seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/receiveRedpackage");
	});
</script>

</html>
