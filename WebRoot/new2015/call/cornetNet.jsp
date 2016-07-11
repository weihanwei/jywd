<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/call/cornetNet.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>短号网</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/call.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="main">
    <div class="relaBox" id="ishandle" style="display: none;">
        <div class="relativeHead">
            <span>我的短号：</span>
            <input type="text" id="cornet" name="cornet" class="J_footHide"/>
            <em class="relHeadBtn" onclick="updateDh(this);">修改</em>
        </div>
        <div class="relativeHead relHeadPadding">
            <span>套餐：</span>
            <select id="tc">
                <option value="5">5元套餐</option>
                <option value="10">10元套餐</option>
            </select>
            <em class="relHeadBtn" onclick="updateTc(this);">修改</em>
        </div>
        <ul class="relativeMain">
            <li>
                <h5>集群详情</h5>
                 <p id="groupName">----------</p>
            </li>
        </ul>
        
    </div>
    <div class="relaBox hide" id="nohandle" style="display: none;">
        <div class="relaNoHead">
            <p>您尚未加入短号网！</p>
            <div class="addInput">
                <input type="text" name="addPhone" placeholder="请输入目标网号码" class="J_footHide" />
            </div>
            <span class="relaBtn" onclick="handleDh();">点击加入</span>
        </div>
        <ul class="relaNoNav">
            <li class="on">商品介绍及资费</li>
            <li>办理方式</li>
        </ul>
        <div class="relaCorBox">
            <div class="relaCor">
                <img src="${pageContext.request.contextPath}/new2015/images/call/pic2.jpg" />
            </div>
            <div class="relaCor hide">
                <img src="${pageContext.request.contextPath}/new2015/images/call/pic2_1.jpg" />
            </div>
        </div>

    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','tmpl','tfAlert','recommend'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/call/cornetNet");
    });
</script>

</html>
