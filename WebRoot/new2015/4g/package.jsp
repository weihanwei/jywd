<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/4g/package.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>4G套餐</title>
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
		
        <div class="package-wrap pr">
        	<div class="left pa">
            	<ul class="package-type">
                	<li class="hover"><a href="javascript:" onclick="changeType(this,'飞享套餐');">4G<br>飞享套餐</a></li>
                    <li><a href="javascript:" onclick="changeType(this,'流量王');">4G<br>流量王</a></li>
                    <li><a href="javascript:" onclick="changeType(this,'上网套餐');">4G上网<br>套餐</a></li>
                    </ul>
            </div>
            <div class="right">
            	<ul class="package-list">
                
                </ul>
            </div>
        </div>
    </div>    
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','swiper','recommend'], function(a) {
		seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/4g/package");
	});
</script>
</html>
