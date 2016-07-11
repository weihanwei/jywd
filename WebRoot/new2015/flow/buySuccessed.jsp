<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/buySuccessed.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>购买红包成功</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <div class="buyed">
        <div class="buyedImg">
            <img id="imgUrl" src="${pageContext.request.contextPath}/new2015/images/flow/buyedbg.png" />
        </div>
        <div class="pd1em">
            <div class="buyedTitle">恭喜您！</div>
            <p>已成功购买<span>${number}</span>个${size}M流量红包！</p>
            <div class="buyedText">
                <p>按提示选择好友，好友即可收到消息拆红包。</p>
                <p>如果发送至好友群，则先抢先得，抢完即止。</p>
            </div>
        </div>
        <div class="pd1em">
            <!-- <a href="javascript:" onClick="javascript:shareRedpackage('${ordersId}');" class="aBtn aBtn-blue"><span class="fem-12">点击分享给朋友</span></a> -->
            <a href="javascript:;" onClick="weChatCallBack()" id="timeLineShare" class="aBtn aBtn-blue"><span class="fem-12">点击页面右上角按钮，发送给好友</span></a>
            <div class="h1em"></div>
            <a href="javascript:" onClick="javascript:buyRedpackage();" class="aBtn aBtn-green"><span class="fem-12">返回流量红包首页</span></a>
        </div>
        <div class="h2em"></div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    
    seajs.use(['jquery','tfAlert','JSBridge','main'], function(require) {
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/weChat/merchandiseShare");
	    seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/weChat/wechatEvent");
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/buySuccessed");
    });
</script>

</html>
