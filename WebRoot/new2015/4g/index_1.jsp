<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/4g/index_1.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>4G专区首页</title>
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

        <div class="index-login index-login-in pb-1em" >
            <div class="index-user-info">
                <div class="ads-row pb-1em">
                   <div class="ads-c-9 fem-10" id="phone">
                       <span class="icon-index-user bg-cover"></span>-------, 欢迎您
                   </div>
                   <div class="ads-c-3 fem-10" id="jbAndJb">
                        金币  <%----<span class="red">--</span>--%>
                   </div>
               </div>
                <div class="ads-row">
                   <div class="ads-c-12 fem-10" id="simMSG">
                       <span class="icon-4gcard bg-cover"></span>----------- <span class="yellow">------------</span>      
                   </div>
               </div>
         </div>
		</div>
        
         <div class="rcommend-4g fem-12" id="tj" style="display: none;">
            <div class="r4g-title" id="tjbt">
              ---------
            </div>
            <div class="pr pt-1em pb-1em">
            <p id="tjtc">---------</p>
            <p></p>
            <p></p>
            <span class="rcommend-icon radius-05" id="istj">----</span>
            </div>
         </div>
        
        
	 <div class="pd1em adv-table-wrap">
        <table class="adv-table" cellspacing="10" cellpadding="10px">
        	<tr>
            	<td rowspan="2" class="left"><a href="javascript:" onclick="hk();"><img src="${pageContext.request.contextPath}/new2015/images/4g/pic1.jpg" ></a></td>
                <td style=" vertical-align:text-top"><a href="javascript:" onclick="js();"><img src="${pageContext.request.contextPath}/new2015/images/4g/pic2.jpg"></a></td>
          </tr>
            <tr>
                <td><a href="javascript:" onclick="tc4G();"><img src="${pageContext.request.contextPath}/new2015/images/4g/pic3.jpg"></a></td>
            </tr>
             <tr>
                <td colspan="2"><a href="javascript:" onclick="hj();"><img src="${pageContext.request.contextPath}/new2015/images/4g/pic4.jpg"></a></td>
            </tr>
      </table>
      </div>
      
    </div>    
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main','swiper','data'], function(a) {
		seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/4g/index");
	});
</script>
</html>
