<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>订单详情</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/broadband.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="pd1em broadband-index">
        <div class="payChoose">
            <div class="qopTitle">账号信息：</div>
            <div class="h1em"></div>
            <ul class="payList">
                <li>
                    宽带账号：<span id="broadbandAccount"></span>
                </li>
                <li>
                    宽带类型：<span id="BroadbandType"></span>
                </li>
                <li>
                    用户名：<span id="UserName"></span>
                </li>
                <li>
                    绑定手机：<span id="BoundPhone"></span>
                </li>
                <li>
                    订单状态：<span id="PayResult"></span>
                </li>
                <li>
                    安装地址：<span id="Address"></span>
                </li>
            </ul>
            <div class="h1em"></div>
            <div class="qopTitle">套餐信息：</div>
            <div class="h1em"></div>
            <ul class="payList">
                <li>
                    套餐名称：<span id="ProductName">[4M]光宽带包年360元赠送一年使用</span>
                </li>
                <li>
                    套餐开始时间：<span id="HandlePackage_StartTime">2016-5-1</span>
                </li>
                <li>
                    套餐结束时间：<span id="endpackage_time">2017-5-1</span>
                </li>
                <li>
                    套餐类型：<span id="ProductType">独立宽带</span>
                </li>
                <li>
                    套餐介绍：<span class="payIntroduce" id="Remark">中国移动宽带覆盖区域，客户可享受4M 宽带包年赠送一年使用期优惠（合计使用 24个月）。</span>
                </li>
                <li>
                    套餐费用：<span id="Cost">360元</span>
                </li>
                <li>
                    套餐带宽：<span id="Bandwidth">36M</span>
                </li>
            </ul>
        </div>
        <div class="h2em"></div>
        <a href="javascript:" class="aBtn aBtn-blue" style="width: 48%; float: left;margin-left: 5px;" id="zforder"><span class="fem-12">订单支付</span></a>
 <a href="javascript:history.go(-1);" class="aBtn aBtn-blue" style="width: 48%; float: left;margin-left: 5px;"><span class="fem-12">返回</span></a>
        <div class="h2em"></div>
        <div class="h2em"></div>
    </div>

</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main'], function (a) {
        seajs.use("../new2015/js/"+SCRIPT_PATH+"/model/broadband/orderdetail");
        jy.navShow(".broadNav ul li", ".broadshow");
    });
    var data='${data}';
   
</script>

</html>
