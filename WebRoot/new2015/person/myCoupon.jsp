<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>我的电子优惠券</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
    <div class="myPriHeader">
        <div class="myPriUser"><i class="bg-cover"></i>18666028668 ， 欢迎您 </div>
        <p>您共有1张优惠券</p>
    </div>
    <div class="myPriNav">
        <ul class="myCouponul">
            <li class="f-l on">可领取</li>
            <li class="f-l">未消费</li>
            <li class="f-l">已消费</li>
        </ul>
    </div>
    <div class="myPriMain">
        <ul class="myPriUsed myShow">
            <li>
                <a href="#">
                    <div class="myPriMainText pr">
                        <p>APP充值95折，优惠不任性</p>
                        <span>至2015年12月31日</span>
                        <i class="myPriNext"></i>
                    </div>
                    <div class="myPriMainImg">
                        <img src="${pageContext.request.contextPath}/new2015/images/person/pic1.jpg" />
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="myPriMainText pr">
                        <p>关注有礼话费送你</p>
                        <span>至2015年12月31日</span>
                        <i class="myPriNext"></i>
                    </div>
                    <div class="myPriMainImg">
                        <img src="${pageContext.request.contextPath}/new2015/images/person/pic2.jpg" />
                    </div>
                </a>
            </li>
        </ul>
        <ul class="myPriUsed myShow hide">
            <li>
                <a href="#">
                    <div class="myPriMainText pr">
                        <p>APP充值95折，优惠不任性</p>
                        <span>至2015年12月31日</span>
                        <i class="myPriNext"></i>
                    </div>
                    <div class="myPriMainImg">
                        <img src="${pageContext.request.contextPath}/new2015/images/person/pic1.jpg" />
                    </div>
                </a>
            </li>
        </ul>
        <ul class="myPriUsed myShow hide">
            <li>
                <a href="#">
                    <div class="myPriMainText pr">
                        <p>APP充值95折，优惠不任性</p>
                        <span>至2015年12月31日</span>
                        <i class="myPriNext"></i>
                    </div>
                    <div class="myPriMainImg">
                        <img src="${pageContext.request.contextPath}/new2015/images/person/pic1.jpg" />
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="myPriMainText pr">
                        <p>APP充值95折，优惠不任性</p>
                        <span>至2015年12月31日</span>
                        <i class="myPriNext"></i>
                    </div>
                    <div class="myPriMainImg">
                        <img src="${pageContext.request.contextPath}/new2015/images/person/pic1.jpg" />
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper','tmpl'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/myCoupon");
    });
</script>

</html>
