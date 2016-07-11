<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/myBill.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>我的账单</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body class="myRecRedpBg">
<div class="main">
<dl class="icon-tab-list">
        <dt class="icon-jt-down">
            <span class="icon bg-cover gold-icon gold-icon-4"></span>
            <span class="fem-12">近6个月消费图</span>
        </dt>
        <dd>
            <div class="myBillEcharts pr">
                <div id="main" class="myBillBar"></div>
            </div>
        </dd>
    </dl>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/echarts/echarts-all.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','tmpl','data'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/myBill");
    });
</script>

</html>
