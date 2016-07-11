<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/4g/introduce.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <title>4G介绍</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/new2015/css/4g.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">


    <div class="banner pr">
        <div class="swiper-container" id="slider" >
            <ul class="swiper-wrapper" style="height:auto" id="banner">
            </ul>
            <div class="swiper-pagination banner-pagination"></div>
        </div>
    </div><!--banner end-->

    <dl class="intro">
        <dt class="intrFast pr">
            <i class="bg-cover"></i>
            <span>有多快？</span>
        </dt>
        <!--intrFast start-->
        <dd class="hide">
            <p>4G是第四代移动通信技术的简称，其网络速度可达3G网络速度的10倍。</p>
            <!--网络优势 start-->
            <dl class="intrInner">
                <dt class="pr">网络优势</dt>
                <dd>
                    <i><img src="${pageContext.request.contextPath}/new2015/images/4g/icon_1.gif" /></i>
                    打开一个网页
                    <span class="f-r"><em>0.025</em>秒</span>
                </dd>
                <dd>
                    <i><img src="${pageContext.request.contextPath}/new2015/images/4g/icon_2.gif" /></i>
                    下载一首5M音乐
                    <span class="f-r"><em>0.5</em>秒</span>
                </dd>
                <dd>
                    <i><img src="${pageContext.request.contextPath}/new2015/images/4g/icon_3.gif" /></i>
                    下载25M视频
                    <span class="f-r"><em>2</em>秒</span>
                </dd>
                <dd>
                    <i><img src="${pageContext.request.contextPath}/new2015/images/4g/icon_4.gif" /></i>
                    下载750M电影
                    <span class="f-r"><em>1</em>分<em>20</em>秒</span>
                </dd>
                <dd>
                    <i><img src="${pageContext.request.contextPath}/new2015/images/4g/icon_5.gif" /></i>
                    下载DVD质量视频
                    <span class="f-r"><em>0.025</em>分</span>
                </dd>
            </dl>
            <!--网络优势 end-->
            <!--测速比拼 start-->
            <dl class="intrInner">
                <dt class="pr">测速比拼</dt>
                <dd>
                    <img src="${pageContext.request.contextPath}/new2015/images/4g/cs.gif" />
                </dd>
            </dl>
            <!--测速比拼 end-->
            <p>*数据来源于实验室测试环境，实际使用时间取决于功能差异及网络环境，可能有所不同。</p>
        </dd>
        <!--intrFast end-->

        <dt class="intrDo pr">
            <i class="bg-cover"></i>
            <span>如何办？</span>
        </dt>
        <!--intrDo start-->
        <dd class="hide">
            <ul class="intrDoMain">
                <li>
                    <img src="${pageContext.request.contextPath}/new2015/images/4g/icon_11.gif" />
                    <span>
                        <em>1.<a href="javascript:" onclick="hk();">免费换USIM卡</a></em>
                        已开放在线免费换卡。但如您需急用，可移步到沟通100服务厅
                    </span>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/new2015/images/4g/icon_12.gif" />
                    <span>
                        <em>2.<a href="javascript:" onclick="tc4G();">办4G套餐</a></em>
                        2015年1月1日起，首次办理或更改4G上网套餐等客户即可享受<i class="textBlue">4G省内0元1G优惠包</i>
                    </span>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/new2015/images/4g/icon_13.gif" />
                    <span>
                        <em>3.<a href="javascript:" onclick="hj();">购4G手机</a></em>
                        选择支持TD-LTE的终端才可接入4G网络服务，请选择<i class="textRed">“4G终端”</i>进行筛选
                    </span>
                </li>
            </ul>
        </dd>
        <!--intrDo end-->

        <dt class="intrUse pr">
            <i class="bg-cover"></i>
            <span>哪能用？</span>
        </dt>
        <!--intrUse start-->
        <dd class="intrUseMain hide">
            <p>中国移动4G商用以来，覆盖已超过300个城市，4G用户达4000万，今年内建成70万个4G基站。其中，广东移动今年建成7万个4G基站，4G网络已深度覆盖广东所有城市和乡镇，包括全省8条地铁线路、所有3A以上景区、200多个交通站点以及500多所大中专院校。</p>
            <p class="intrUseImg">
                <img src="${pageContext.request.contextPath}/new2015/images/4g/jz.gif" />
            </p>
        </dd>
        <!--intrUse end-->

        <dt class="intrQ pr">
            <i class="bg-cover"></i>
            <span>有疑问？</span>
        </dt>
        <!--intrQ start-->
        <dd class="hide">
            <dl class="intrQMain">
                <dt class="pr">
                    <em>1</em>
                    <span>为什么要换4G卡?</span>
                </dt>
                <dd>答：亲，这是4G网络鉴权需要，更换4GUSIM卡才能连接4G网络。并且USIM卡有更高等级的加密功能，安全性更强，更不易被破解，保证身份隐私不被窃取。</dd>
                <dt class="pr">
                    <em>2</em>
                    <span>4G速度这么快，一旦超流量，会不会扣到连房子都被拿走？</span>
                </dt>
                <dd>答：4G真心不贵：</dd>
            </dl>
        </dd>
        <!--intrQ end-->
    </dl>

</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main','swiper'], function(a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/4g/introduce");
    });
</script>
</html>
