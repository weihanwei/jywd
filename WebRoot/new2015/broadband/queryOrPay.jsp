<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>宽带在线缴费查询或支付</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../css/broadband.css" rel="stylesheet" type="text/css"/>
</head>
<%
String path=request.getContextPath();
%>
<body>
<div class="main mainMargin">
    <div class="qoptop">
        <img src="../images/broadband/pic1.jpg" alt=""/>
    </div>
    <div class="pd1em broadband-index">
        <div class="h2em"></div>
        <div class="table pay-bar">
            <div class="table-cell pay-bar-icon-wrap active">
                <div class="radius-icon-div pr">
                    <span class="text">验证</span>
                    <span class="radius-icon-span"><b class="fem-15"><img src="../images/broadband/icon_ok.png"
                                                                          class="icon-ok"></b></span>
                </div>
            </div>
            <div class=" table-cell active-half">
                <div class="radius-icon-bar active"></div>
            </div>
            <div class=" table-cell pay-bar-icon-wrap  active">
                <div class="radius-icon-div pr">
                    <span class="text">选择</span>
                    <span class="radius-icon-span"><b class="fem-15"><img src="../images/broadband/icon_ok.png"
                                                                          class="icon-ok"></b></span>
                </div>
            </div>
            <div class="table-cell active-half">
                <div class="radius-icon-bar active"></div>
            </div>
            <div class="table-cell pay-bar-icon-wrap  active">
                <div class="radius-icon-div pr">
                    <span class="text">查询或支付</span>
                    <span class="radius-icon-span"><b class="fem-15"><img src="../images/broadband/icon_ok.png"
                                                                          class="icon-ok"></b></span>
                </div>
            </div>
        </div>
        <div class="h2em"></div>
        <div class="ads-row">
            <div class="ads-c-12">
                <input type="text" class="ltextput J_footHide" placeholder="请输入宽带账号（或手机号码）" value="" id="broadbandAccount" readonly="readonly">
            </div>
        </div>
    </div>

    <div class="qopMain">
        <div class="qopTitle lh-20">查询结果：</div>
        <ul class="qopResult">
        <!-- 
            <li>
                <div class="qopResultLeft f-l">
                    <p>订单编号:20150802</p>
                    <h4>[20M]光宽带包年</h4>
                    <p>下单日期：2015-08-02 11:29:41</p>
                    <p>订单状态：未支付</p>
                </div>
                <div class="qopResultRight f-r">
                    <a href="#" class="aBtn aBtn-green">订单支付</a>
                    <a href="#" class="aBtn aBtn-red">取消订单</a>
                </div>
            </li>
            <li>
                <div class="qopResultLeft f-l">
                    <p>订单编号:20150802</p>
                    <h4>[20M]光宽带包年</h4>
                    <p>下单日期：2015-08-02 11:29:41</p>
                    <p>订单状态：未支付</p>
                </div>
                <div class="qopResultRight f-r">
                    <a href="#" class="aBtn aBtn-green">订单支付</a>
                    <a href="#" class="aBtn aBtn-red">取消订单</a>
                </div>
            </li>
            -->
        </ul>
        <div class="h2em"></div>
    </div>
</div>
</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge','tfAlert', 'main', 'swiper'], function (a) {
        jy.navShow(".broadNav ul li", ".broadshow");
        seajs.use("<%=path%>/new2015/js/"+SCRIPT_PATH+"/model/broadband/queryOrPay");
    });
</script>

</html>
