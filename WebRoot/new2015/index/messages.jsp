<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>消息中心</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="headBox">
    <div class="main">
        <div class="header">
            <span class="f-l" onclick="history.go(-1)">返回</span>
            <em class="headerMargin">消息中心</em>
            <span class="f-r" id="J_edit">编辑</span>
        </div>
    </div>
</div>
<div class="main messBox mainMarginTop">
    <div class="messNav">
        <div class="messNavHead">全部消息<span id="boxAllNoticeCount"><!--共4条--></span></div>
        <ul class="messNavList" id="messageTypeUl">
            <!--<li>通知类<span>共1条</span></li>
            <li>优惠类<span>共1条</span></li>
            <li>精品类<span>共1条</span></li>
            <li>生活类<span>共1条</span></li>
        --></ul>
        <ul class="messNavList messNavLove">
            <li><i></i>我的收藏<span id="myCollCountSpan"></span><input type="hidden" value="myColl" /></li>
        </ul>
    </div>
    <div class="messMain">
        <input type="hidden" id="hiddenNoticeType"/>
        <div class="messHead pl-1em pr-1em">
            <div class="f-l ads-c-60">
                <i class="messAll"><img src="${pageContext.request.contextPath}/new2015/images/index/allicon.png" /></i>全部消息<span id="allNoticeCount">条</span>
            </div>
            <div class="f-r ads-c-40 text-right">
                <span id="noReadNoticeCount"></span>未读
            </div>
        </div>
        <ul class="messList">

        </ul>
        <div class="messPopuBg hide"></div>
        <div class="nomain" id="noDataDiv">暂无消息！</div>

    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main'], function(a) {
    	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/messages");
    });
</script>

</html>
