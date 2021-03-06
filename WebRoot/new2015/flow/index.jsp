<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/index.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>流量专区</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bodybg">
<div class="main">
    <!--bananer start-->
    <div class="banner pr">
        <div class="swiper-container" id="slider">
            <ul class="swiper-wrapper" style="height:auto" id="banner">
      
            </ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div>
    <!--bananer end-->
    <!--content start-->
    <div class="adv-table-wrap">
        <table class="flowTab" cellspacing="0" cellpadding="0" border="0">
            <tr>
                <td  class="fRedpackage" rowspan="2">
                    <a href="javascript:buyRedpackage();">
                        <div class="flowTitle" >缺流量？快来购买流量红包！</div>
                        <p>进入查看更多</p>
                        <div class="flowImg">
                            <img src="${pageContext.request.contextPath}/new2015/images/flow/pic1.jpg" />
                        </div>
                    </a>
                </td>
                <td class="fPackage">
                    <a href="javascript:" onclick="packagedo();" >
                        <div class="fMain">
                            <div class="flowTitle">流量套餐</div>
                            <p>套餐业务最优惠！</p>
                        </div>
                        <div class="flowImg">
                            <img src="${pageContext.request.contextPath}/new2015/images/flow/pic2.jpg" />
                        </div>
                    </a>
                </td>
            </tr>
            <tr>
                <td class="pr fAdd">
                    <a href="javascript:" onclick="overlayPackage();">
                        <div class="fMain">
                            <div class="flowTitle blue">10元手机流量叠加包</div>
                            <p>天天特惠！</p>
                        </div>
                        <div class="f-r flowImg noborder">
                            <img src="${pageContext.request.contextPath}/new2015/images/flow/pic5.jpg" />
                        </div>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <!--content start-->
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/flow/index");
    });
</script>
</html>