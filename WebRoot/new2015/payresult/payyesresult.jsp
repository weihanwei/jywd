<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>支付提示页面</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="tipPMian">
        <div class="f-l pr-1em">
      
    	   <img src="${pageContext.request.contextPath}/new2015/images/common/play_yes.png" />
    	   
        </div>
        <div class="f-l">
            <h5 class="tipPTitle">您宽带账号：<%=request.getAttribute("account") %>,已成功付款<%=request.getAttribute("kdcost") %><%=request.getAttribute("productname") %></h5>
            <p>您所充值的费用将在10分钟内到账。</p>
            
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main'], function(a) {
        jy.innertHead('宽带支付提示页面');

        jy.innertFood(2);

    });
</script>
</html>
