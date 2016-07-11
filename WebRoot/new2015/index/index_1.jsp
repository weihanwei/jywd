<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index/index_1.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>首页</title>
<link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <input type="hidden" id="isLoginPageTo" value="${param.isLoginPageTo}">
	<div class="main">
    	<div class="head fem-12">
        	<div class="ads-row">
            	<div class="ads-c-6 ads">
                    <a href="javascript:" class="headIcon bg-cover"></a>&nbsp; 和商汇微店
                </div>
                <div class="ads-c-6 ads text-right">
                   <a href="javascript:" onClick="javascript:messages();"><span class="inTopIcon bg-cover inews pr"><i id="hi"></i></span></a>
                   <a href="javascript:" onClick="javascript:Search();"><span class="inTopIcon bg-cover isearch"></span></a>
                </div>
            </div>
        </div>
        
        <div class="index-login text-center hide" >
        	<p><a href="javascript:" onClick="jy.G('login.html')" class="btnLogin radius-10">点击登录</a></p>
            <p>推荐5人登录即可获得5个金币</p>
            <p class="yellow">登录更多惊喜</p>
        </div>

        <div class="banner pr">
            <div class="swiper-container" id="slider" >
                <ul class="swiper-wrapper" style="height:auto" id="banner">
                    
                </ul>
                <div class="swiper-pagination banner-pagination"></div>
            </div>
        </div>

        <div class="index-login index-login-in" id="user">
             <div class="index-user-info">
                 <div class="ads-row">
                    <div class="ads-c-8 fem-10">
                        <span class="icon-index-user bg-cover"></span>--------, 欢迎您
                    </div>
                    <div class="ads-c-4 fem-10 pr">
                         金币  <%----<span class="red pa indText">--</span>--%>
                    </div>
                </div>
            </div>
            <div class="ads-row pb-1em">
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
        
        <div class="main-icon" id="app">
        	
        </div><!--main-icon end-->
        
        <div class="h1em"></div>
        
    </div>    
    
     <!--news show start-->
    <div class="inewsShow" style="display: none;" >
        <div class="main pr">
            <div class="inewsShowBg"></div>
            <div class="inewsShowMain">
                <span class="inTopIcon bg-cover inewsClose"></span>
                <p>尊敬的客户：</p>
                <p>您当前有<em id="zs">--条</em>重要未读消息。</p>
                <p class="inewsImp" id="msg" onclick="goToMessageDetail()"><i class="inTopIcon bg-cover"></i>-------。</p>
                <input type="hidden" id="hiddenMsgId" />
            </div>
        </div>
    </div>
    <!--news show end-->

    <!--手势说明 start-->
    <div class="iHandleState hide">
        <div class="main bg-cover"></div>
    </div>
    <!--手势说明 end-->
    
</body>

<script src="/jywd/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
 
seajs.use(['jquery','JSBridge','main','swiper','data'], function(a) {
	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/index/index");
});
	
	
</script>

</html>
