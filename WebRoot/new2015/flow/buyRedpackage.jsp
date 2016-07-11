<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/buyRedpackage.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>购买流量红包</title>
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
        <span>选择红包</span>
        <em>2</em>
        <span>生成链接</span>
        <em>3</em>
        <span>发送链接</span>
    </div>
    <div class="buyMain">
        <div class="buyMainbg"></div>
        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="buyMainTab">
            <tr>
                <td colspan="2">请选择购买的红包类型：</td>
            </tr>
            <tr>
                <td>个数:</td>
                <td>
                    <ul class="buyType">
                        <li class="f-l">
                            <input type="radio" name="number" id="num1" onclick="changeNumber(1);" checked/>
                            <label for="num1">1个</label>
                        </li>
                        <li class="f-l">
                            <input type="radio" name="number" id="num2" onclick="changeNumber(5);"/>
                            <label for="num2">5个</label>
                        </li>
                        <li class="f-l">
                            <input type="radio" name="number" id="num3" onclick="changeNumber(10);"/>
                            <label for="num3">10个</label>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>大小:</td>
                <td>
                    <ul class="buyType" id="sizes">
                        
                    </ul>
                </td>
            </tr>
            <tr>
                <td>价格:</td>
                <td id="price">--元</td>
            </tr>
        </table>
    </div>
    <a href="javascript:" onClick="javascript:buy();" class="buyVerifyBtn">确认购买流量红包</a>
    <div class="buySureReg">
        <input type="checkbox" name="buyRegText" id="buyRegText" onChange="javascript:checkReadText()"/>
        <label for="buyRegText">我已阅读并理解此活动规则</label>
    	<p>1、	客户可以多次购买流量红包，每次购买的流量红包派发邀请或购买者直接兑换的有效期均为7天，逾期未被领取或未成功兑换的红包流量将在24小时内自动兑换到您绑定的号码。</p>
        <p>2、	流量红包兑换成功后所含流量立即生效，有效期至下一月结日，为省内通用流量，可用于2G/3G/4G网络。</p>
        <p>3、	客户成功购买红包后，系统将生成唯一的领取页面链接，可将该链接分享给微信好友或朋友圈，分享次数不限，好友抢红包先抢先得，抢完即止。同时购买者也可将红包流量直接兑换给好友。</p>
        <p>4、	流量红包活动仅限揭阳移动客户参加，其中4G随心王客户、未实名登记/未更换4G USIM客户及转品牌未生效客户无法参加。</p>
    </div>
    <div class="h2em"></div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">

   seajs.use(['jquery','JSBridge','main','recommend','data'], function(a) {
       seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/buyRedpackage");
    });
</script>

</html>
