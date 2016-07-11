<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/call/relativesNet.appcache">
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
<div class="main">
   <div class="relaBox" id="ishandle" style="display: none;" >
    <div class="cornetHead">
        <div class="cornetNo">我的短号: ----</div>
        <p>共有<span>--</span>位成员，还可设置<span>--</span>位。</p>
    </div>
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="cornetTab">
        <tr>
            <th width="35%">短号</th>
            <th width="65%">长号</th>
        </tr>
    </table>
    <div class="footerBtn">
        <div class="main">
            <a href="javascript:" onClick="javascript:relativesNetDetail();" class="blueBtn">修改</a>
        </div>
    </div>
   
  </div>
  
  <div class="relaBox" id="nohandle" style="display: none;">
        <div class="relaNoHead">
            <p>您尚未办理家庭网</p>
            <span class="relaBtn" onclick="handleQy();">点击办理</span>
        </div>
        <ul class="relaNoNav">
            <li class="on">商品介绍及资费</li>
            <li>办理方式</li>
        </ul>
        <div class="relaCorBox">
            <div class="relaCor">
                <img src="${pageContext.request.contextPath}/new2015/images/call/pic1.jpg" />
            </div>
            <div class="relaCor hide">
                <img src="${pageContext.request.contextPath}/new2015/images/call/pic1_1.jpg" />
            </div>
        </div>
   </div>
   <div class="nomain hide">亲~您暂未开通亲友网哦！</div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','recommend','data'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/call/relativesNet");
    });
</script>

</html>
