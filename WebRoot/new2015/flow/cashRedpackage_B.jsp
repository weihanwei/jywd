<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/cashRedpackage_B.appcache">
<head> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>流量红包兑换</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main buybodybg">
    <div class="buybg">
        <img src="${pageContext.request.contextPath}/new2015/images/flow/buybg.png" alt="图片" />
    </div>
    <div class="buyHead">
        <em>1</em>
        <span>输入揭阳移动号码</span>
        <em>2</em>
        <span>点击确认兑换</span>
    </div>
    <div class="buyMain">
        <div class="buyMainbg"></div>
        <div class="buyMainTab">
            <div class="cashMain">
                <input type="text" name="number" placeholder="请输入使用红包流量的号码" value=""  id="mobileNo" class="cashInput J_footHide" />
                <div class="cashFlow"><span id="flowSize"></span>MB</div>
                <p id="sendName"></p>
            </div>
            <div class="cashDeadline" id="TimeTEXT"></div>
        </div>
    </div>
    <a href="javascript:" onClick="javascript:cashRedpackage();" class="buyVerifyBtn">确认兑换至此号码</a>
    <div class="openMain">
        <div class="openTitle">流量红包兑换规则：</div>
        <p>1、仅支持兑换流量到揭阳移动客户号码，其中4G随心王客户、未实名登记/未更换4G USIM客户及转品牌未生效客户无法参加。</p>
        <p>2、抢到的红包流量须在7天内兑换给揭阳移动号码，逾期未兑换的红包流量将在24小时内自动兑换到红包发起者绑定的号码。</p>
        <p>3、兑换成功后的流量立即生效，有效期至下一月结日。</p>
        <p>4、该红包流量为省内通用流量，可用于2G/3G/4G网络。</p>
    </div>
    <div class="h2em"></div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','tmpl'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/cashRedpackage_B");
    });
</script>

</html>