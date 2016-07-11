<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/myReceiveRedpackets.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>我领到的红包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body class="myRecRedpBg">
<div class="main">
    <div class="myPriNav">
        <ul>
            <li class="f-l on">流量红包</li>
            <li class="f-l">通话红包</li>
        </ul>
    </div>
    <div class="myPriMain">
        <div class="myShow pb-5" id="flowDiv">
            <div class="myRecFooter" id="button">
                <a href="javascript:;" onClick="buyRedpackage()"  class="myRecBtn f-l">我也要发流量红包</a>
            </div>
            <!--myRecFooter end-->
        </div>
        <div class="myShow pb-5 hide" id="callDiv">
            <!--myRecRedp and myRecDetails start--><!--
            <div class="myRecRedp" data-ajaxfnc="receiveList2" >
                <h4>张三的通话红包<span class="f-r">2015-7-25</span></h4>
                <p>10个10MB通话红包，已领取2个</p>
            </div>
            --><!--myRecRedp and myRecDetails end-->

            <!--myRecFooter start-->
            <div class="myRecFooter">
                <a href="javascript:" onClick="javascript:sendCallRed();" class="myRecBtn">我也要发通话红包</a>
            </div>
            <!--myRecFooter end-->
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','tmpl'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/myReceiveRedpackets");
    });
</script>
</html>
