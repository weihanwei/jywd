<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>套餐使用情况</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="main">
    <dl class="combo">
        <dt class="BorderB">
            <i class="comboicon1 bg-cover f-l"></i>
            剩余语音分钟
            <span class="f-r" id="yy">----分钟</span>
        </dt>
        <dd class="hide">
            <p>“我爱我家”手机免费长途服务</p>
            <div class="comboDetails pr">套餐总量：15分钟<span>剩余量：10分钟</span></div>
        </dd>
        <dt class="BorderB">
            <i class="comboicon2 bg-cover f-l"></i>
            剩余手机流量
            <span class="f-r" id="ll">------M</span>
        </dt>
        <dd class="hide">
            <p>19元4G流量王</p>
            <div class="comboDetails pr">套餐总量：1324.00M<span>剩余量：1299.50M</span></div>
        </dd>
       
    </dl>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','tmpl'], function(a) {
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/comboDetails");
    });
</script>

</html>
