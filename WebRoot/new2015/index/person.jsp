<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index/person.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>个人中心</title>
<link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
    	<div class="head fem-12">
        	<div class="ads-row">
                <div class="ads-c-7 ads">
                    <a href="javascript:" class="headIcon bg-cover"></a>&nbsp; 和商汇微店
                </div>
                <div class="ads-c-5 ads text-right">
                    <a href="javascript:" onClick="messages();"><span class="inTopIcon bg-cover inews pr"><i id="hi"></i></span></a>
                   <a href="javascript:" onClick="Search();"><span class="inTopIcon bg-cover isearch"></span></a>
                </div>
            </div>
        </div>
        
        <div class="index-login text-center hide" >
        	<p><a href="javascript:" onClick="jy.G('login.html')" class="btnLogin radius-10">点击登录</a></p>
            <p>首次登录即可获得5个金币</p>
            <p class="yellow">登录更多惊喜</p>
        </div>

         <div class="index-login index-login-in" id="user">
             <div class="index-user-info">
                 <div class="ads-row">
                    <div class="ads-c-70 fem-10">
                        <span class="icon-index-user bg-cover"></span>-------- ， 欢迎您        
                    </div>
                    <div class="ads-c-30 fem-10 pr">
                         --金币  <span class="red pa indText"></span>
                    </div>
                </div>
            </div>
            <div class="ads-row">
                <div class="ads-c-4">
                    <div class="menuwrap">
                        <span class="bg-cover index-menu-icon menu-icon1"></span>
                        <span class="yellow">话费余额</span><br>----元
                    </div>
                </div>
                <div class="ads-c-4">
                    <div class="menuwrap">
                        <span class="bg-cover index-menu-icon menu-icon2"></span>
                        <span class="yellow">流量余量</span><br>----M
                    </div>
                  
                </div>
                <div class="ads-c-4">
                     <div class="menuwrap">
                        <span class="bg-cover index-menu-icon menu-icon3"></span>
                        <span class="yellow">剩余语音</span><br>----分钟
                    </div>
                </div>
            </div>
        </div>
        
        <div class="person-menu-list">
        	<ul>
            	<li class="icon-jt-right">
                	<span class="icon bg-cover icon-1"></span>
                    <a href="javascript:" onclick="comboDetails();" class="fem-12">套餐使用情况</a>
                </li>
                <li class="icon-jt-right">
                	<span class="icon bg-cover icon-2"></span>
                    <a href="javascript:" onclick="myHandle();" class="fem-12">已办业务</a>
                </li>
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-3"></span>
                    <a href="javascript:" onclick="myGold();" class="fem-12">我的金币</a>
                </li>
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-4"></span>
                    <a href="javascript:" onclick="myBill();" class="fem-12">我的账单</a>
                </li>
                <li class="icon-jt-right">
                    <span class="icon bg-cover icon-5"></span>
                    <a href="javascript:" onclick="toBeShopManager();" class="fem-12">我要做店长</a>
                </li><!--
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-6"></span>
                    <a href="javascript:" onclick="myCoupon();" class="fem-12">我的电子优惠券</a>
                </li>
                 --><li class="icon-jt-right">
                	<span class="icon bg-cover icon-7"></span>
                    <a href="javascript:" onclick="myReceiveRedpackets();" class="fem-12">我领到的红包</a>
                </li>
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-8"></span>
                    <a href="javascript:" onclick="mySendRedpackets();" class="fem-12">我发起的红包</a>
                </li>
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-9"></span>
                    <a href="javascript:" onclick="myDownload();" class="fem-12">我的下载</a>
                </li>
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-11"></span>
                    <a href="javascript:" onclick="myPrivilege();" class="fem-12">我的优惠</a>
                </li><!--
                 <li class="icon-jt-right">
                	<span class="icon bg-cover icon-12"></span>
                    <a href="#" class="fem-12">营业厅查询(未配)</a>
                </li>
                --><li class="icon-jt-right">
                	<span class="icon bg-cover icon-13"></span>
                    <a href="javascript:" onclick="servicePassword();" class="fem-12">服务密码</a>
                </li>
                <li class="icon-jt-right">
                	<span class="icon bg-cover icon-14"></span>
                    <a href="javascript:" onclick="loginOut();" class="fem-12">退出</a>
                </li>

            </ul>
        </div>
        <div class="h1em"></div>
    </div>    
    
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">

seajs.use(['jquery','JSBridge','main','swiper','data'], function(a) {
	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/person");
});

</script>

</html>
