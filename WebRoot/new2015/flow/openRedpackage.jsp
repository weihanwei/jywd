<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/openRedpackage.appcache">
<head> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>流量红包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body class="openbodybg">
<div class="main">
    <div class="openTopImg">
        <img src="${pageContext.request.contextPath}/new2015/images/flow/opentopImg.png" alt="图片" />
    </div>
    <div class="openBtnbg pr">
        <img src="${pageContext.request.contextPath}/new2015/images/flow/openbtnbg.png" alt="点击背景图" />
        <a href="javascript:" onClick="javascript:openRedpackage('${redpackageId}');" class="openBtn"></a>
    </div>
    <div class="openMain">
        <div class="openTitle">抢流量红包规则：</div>
        <p>1、好友派发的同一个红包链接(不论派发多次)，每人只能抢红包一次，先抢先得，抢完即止。</p>
        <p>2、抢到的红包流量须在7天内兑换给揭阳移动号码，逾期未兑换的红包流量将在24小时内自动兑换到红包发起者绑定的号码。</p>
        <p>3、该红包所含流量成功兑换后立即生效，有效期至下一月结日，为省内通用流量，可用于2G/3G/4G网络。</p>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
seajs.use(['jquery','JSBridge','main'], function(require) {
    seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/openRedpackage");
});
</script>

</html>