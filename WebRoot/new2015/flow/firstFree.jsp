<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>流量叠加包首单免费</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">

    <img src="${pageContext.request.contextPath}/new2015/images/flow/banner02.jpg" />
    <div class="fFContent">
        <p>请选择办理的套餐档次：</p>
        <ul class="fFPackage" id="freePackageUl">
        </ul>
        <a href="javascript:" class="greenBtn" onclick="toBuyFreePackage();">确定购买</a>
    </div>
    <div class="fFRule">
        <div class="fFRuleTitle pr">活动规则</div>
        <div class="fFMain">
            <table cellpadding="0" cellspacing="0" border="0" width="100%" class="fFTab" id="firstFreePackageRole">
                <tr>
                    <th width="25%">首单免费流量<br/>叠加包类型</th>
                    <th width="20%">赠送话费<br/>（元）</th>
                    <th width="55%">活动规则</th>
                </tr>
            </table>
        </div>
        <div class="fFTip">来晚了也没关系，下载广东移动10086APP，流量叠加包天天8折优惠！</div>
        <a href="http://gd.10086.cn/personal/others/yyt/?WT.mc_id=GDALAPPG0301DXO0001" class="fFDown pr">点我去下载！</a>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/firstFree");
    });
</script>
</html>
