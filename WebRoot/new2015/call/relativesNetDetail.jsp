<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/call/relativesNetDetail.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>亲友网</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/call.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main mainMarginBottom mainMarginTop">
    <div class="cornetHead">
        <div class="cornetNo">我的短号: ----</div>
        <p>共有<span>--</span>位成员，还可设置<span>--</span>位。</p>
        <div class="corUpgrade" style="display:none">
            <a href="javascript:" onClick="upgrade();" class="corUpBtn">升级</a>
            <span class="corUpQ"></span>
            <div class="corUpA">
                <div class="corUpABg"></div>
                <div class="corUpAMain">
                    <p>升级后资费更低、群内亲友更多！</p>
                    <p>亲友网现升级支持全省19人跨市组网，群内成员省内长短号互打免费，每月仅需5元起。</p>
                </div>
            </div>
        </div>
    </div>
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="cornetTab">
        <tr>
            <th width="20%">删除</th>
            <th width="20%">短号</th>
            <th width="60%">长号</th>
        </tr>
    </table>
    <div class="footerBtn">
        <div class="main">
            <div class="ads-c-48 f-l"><a href="javascript:" onClick="add();" class="blueBtn" >完成</a></div>
            <div class="ads-c-48 f-r"><a href="javascript:" onClick="goToRelativesNet();" class="blueBtn">取消</a></div>
        </div>
    </div>
    <div class="nomain hide">亲~您暂未开通亲友网哦！</div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','tfAlert','data'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/call/relativesNetDetail");
    });
</script>

</html>
