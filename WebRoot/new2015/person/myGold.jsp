<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>我的金币</title>
<link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
         <div class="index-login index-login-in" >
             
        </div>
        
        <dl class="icon-tab-list myGoldList">
        	<dt class="icon-jt-down myGoldGo">
                <a href="javascript:" onclick="goMyGoldDetail()">
                    <span class="icon bg-cover gold-icon gold-icon-1"></span>
                    <span class="fem-12">金币明细</span>
                </a>
            </dt>
            <dd></dd>

            <dt class="icon-jt-down unfold" data-ajaxfnc="goldDetailList2">
            	<span class="icon bg-cover gold-icon gold-icon-2"></span>
                <span class="fem-12">金币兑换记录</span>
            </dt>
            <dd class="hide">
            	<div class="gold-list is-load-occ" id="gold-list2"></div>
            </dd>
            
            
            <dt class="icon-jt-down unfold" >
            	<span class="icon bg-cover gold-icon gold-icon-3"></span>
                <span class="fem-12">金币兑换</span>
            </dt>
            <dd class="hide">
            	<p class="pd1em">
                	<span class="color-red">推荐您用金币兑换：</span>
                </p>
                <div class="gold-exchange-list lh-16">
                    <div class="ads-row">
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic4.jpg"></p>
                            <p>30元话费充值</p>
                            <p>金币：30</p>
                            <a href="javascript:" onclick="exchange(1)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic5.jpg"></p>
                            <p>50元话费充值</p>
                            <p>金币：50</p>
                            <a href="javascript:" onclick="exchange(2)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic6.jpg"></p>
                            <p>100元话费充值</p>
                            <p>金币：100</p>
                            <a href="javascript:" onclick="exchange(3)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic7.jpg"></p>
                            <p>300M流量直充</p>
                            <p>金币：20</p>
                            <a href="javascript:" onclick="exchange(4)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic8.jpg"></p>
                            <p>500M流量直充</p>
                            <p>金币：30</p>
                            <a href="javascript:" onclick="exchange(5)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                        <div class="ads-c-6 pd1em text-center">
                            <p><img src="${pageContext.request.contextPath}/new2015/images/PIC/pic9.jpg"></p>
                            <p>1000M流量直充</p>
                            <p>金币：50</p>
                            <a href="javascript:" onclick="exchange(6)" class="gold-exchange-btn radius-05">兑换</a>
                        </div>
                    </div>
                </div>
            </dd>
        </dl>
    </div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/dist/common/JSBridge.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','swiper','tmpl'], function(a) {
		seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/mygold");
	});
</script>

</html>
