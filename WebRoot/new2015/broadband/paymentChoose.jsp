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
    <title>宽带在线续费选择</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../css/broadband.css" rel="stylesheet" type="text/css"/>
</head>
<%
String path=request.getContextPath();
%>
<body>
<div class="main">
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
                <div class="radius-icon-bar on"></div>
            </div>
            <div class=" table-cell pay-bar-icon-wrap  active">
                <div class="radius-icon-div pr">
                    <span class="text">选择</span>
                    <span class="radius-icon-span"><b class="fem-15"><img src="../images/broadband/icon_ok.png"
                                                                          class="icon-ok"></b></span>
                </div>
            </div>
            <div class="table-cell active-half">
                <div class="radius-icon-bar oning"></div>
            </div>
            <div class="table-cell pay-bar-icon-wrap">
                <div class="radius-icon-div pr">
                    <span class="text">查询或支付</span>
                    <span class="radius-icon-span"><b class="fem-15">3</b></span>
                </div>
            </div>
        </div>
        <div class="payChoose">
            <div class="h2em"></div>
            <div class="qopTitle">账号信息：</div>
            <div class="h1em"></div>
            <ul class="payList">
                <li>
                    宽带账号：<span id="BroadbandAccount"></span>
                </li>
                <li>
                    用户名：<span id="UserName"></span>
                </li>
                <li>
                    绑定手机：<span>***</span>
                </li>
                <li>
                    宽带状态：<span id="UserStatus"></span>
                </li>
                <li>
                    宽带到期时间：<span id="HandlePackage_EndTime"></span>
                </li>
                <li>
                    安装地址：<span id="Address"></span>
                </li>
            </ul>
            <div class="h1em"></div>
            <div class="qopTitle">套餐信息：</div>
            <div class="h1em"></div>
            <table cellpadding="0" cellspacing="0" border="0" width="100%" class="payTab">
                <tr>
                    <td>宽带选择：</td>
                    <td>
                        <ul id="paypackageli">
                        <!--  
                            <li>
                                <input type="radio" name="payPackage" id="4M" checked/>
                                <label for="4M">4M</label>
                            </li>
                            <li>
                                <input type="radio" name="payPackage" id="6M"/>
                                <label for="6M">6M</label>
                            </li>
                            <li>
                                <input type="radio" name="payPackage" id="12M"/>
                                <label for="12M">12M</label>
                            </li>
                            <li>
                                <input type="radio" name="payPackage" id="20M"/>
                                <label for="20M">20M</label>
                            </li>
                            <li>
                                <input type="radio" name="payPackage" id="50M"/>
                                <label for="50M">50M</label>
                            </li>
                            <li>
                                <input type="radio" name="payPackage" id="100M"/>
                                <label for="100M">100M</label>
                            </li>
                            -->
                        </ul>
                    </td>
                </tr>
            </table>
            <ul class="payList">
                <li class="pr">
                    套餐选择：
                    <div class="payListChoose">
                        <div class="payListChooseText" >[4M]光宽带包年360元赠送一年使用[4M]光宽带包年360元赠送一年使用</div>
                        <select id="taocanlist">
                        
                          <option>[4M]光宽带包年360元赠送一年使用1</option>
                            <option>[4M]光宽带包年360元赠送一年使用2</option>
                           
                        </select>
                    </div>
                </li>
                <li class="text-overflow">
                    所选套餐：<span id="firstpayListChoose">[4M]光宽带包年360元赠送一年使用[4M]光宽带包年360元赠送一年使用</span>
                </li>
                <li>
                    套餐类型：<span id="ProductType">独立宽带</span>
                </li>
                <li>
                    套餐介绍：<span class="payIntroduce" id="Remark">中国移动宽带覆盖区域，客户可享受4M 宽带包年赠送一年使用期优惠（合计使用 24个月）。</span>
                </li>
                <li>
                    套餐开始时间：<span id="NextPackage_StartTime"></span>
                </li>
                <li>
                    套餐结束时间：<span id="NextPackage_EndTime">2017-5-1</span>
                </li>
                <li>
                    套餐费用：<span id="Cost">360元</span>
                </li>
            </ul>
        </div>
        <div class="h2em"></div>
        <a href="javascript:" class="aBtn aBtn-blue" onclick="sumbitorder();"><span class="fem-12">提交</span></a>

        <div class="h2em"></div>
    </div>

</div>
<div id="popupay" style="display: none;">
<!--弹出支付 start-->
<div class="toBePopu">
    <div class="payPopuHead"><i class="bg-cover"></i>宽带在线支付</div>
    <div class="toBePopuMain">
        <div class="payPopu">订单号： <span id="orderid"></span></div>
    </div>
    <div class="toBePopuBtn">
        <a href="#" class="aBtn aBtn-blue"  onclick="javascript:submitOrder();">订单支付</a>
    </div>
</div>
<div class="popupbg"></div>

</div>

</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'tfAlert','main'], function (a) {
        seajs.use("<%=path%>/new2015/js/"+SCRIPT_PATH+"/model/broadband/paymentChoose");
        seajs.use("<%=path%>/new2015/js/"+SCRIPT_PATH+"/model/broadband/userdatas");
        jy.navShow(".broadNav ul li", ".broadshow");
    });
</script>

</html>
