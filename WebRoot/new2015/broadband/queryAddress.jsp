<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>查询地址</title>
<link href="../css/reset.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/swiper.css" rel="stylesheet" type="text/css" />
<link href="../css/broadband.css" rel="stylesheet" type="text/css" />
<%
String path=request.getContextPath();
%>
</head>
<body>
	<div class="main">
	
        <div class="banner pr">
        	<div class="swiper-container" id="slider" >
                <ul class="swiper-wrapper" style="height:auto" id="banner">

                     </ul>
                 <div class="swiper-pagination banner-pagination"></div>
            </div>
        </div>
        <div class="pd1em broadband-index">
        	
        	
            
            <p class="color_red lh-15">
            	<span class="fem-12">查询说明：</span>
            </p>
            <div class="h1em"></div>
            <div class="lh-15">
            <p class="pr"><span class="pa">1、</span>&nbsp;&nbsp;&nbsp;&nbsp;请选择对应的区域并输入您的居住小区关键字；</p>
			<p class="pr"><span class="pa">2、</span>&nbsp;&nbsp;&nbsp;&nbsp;如能查询到您所居住的小区，请点击<a href="broadbandInstall.jsp" class="a-text-link">申请安装登记</a>进行宽带报装登记。</p>
            </div>
            <div class="h1em"></div>
            
            
            
            <div class="select-items">
            	<span class="select-items-text">请选择所在区域</span>
                <select id="jkaddress">	
                <option value="榕城">榕城</option>
				<option value="普宁">普宁</option>
				<option value="揭西">揭西</option>
				<option value="惠来">惠来</option>
				<option value="揭东">揭东</option></select>
            </div>
            <div class="h1em"></div>
            <input type="text"  class="ltextput J_footHide" id="xiaoqu" placeholder="请输入小区名称" value="" >
            <div class="h1em"></div>
            <div style="background-color: rgba(194, 192, 195, 0.13);display: none" id="querysu">
            <table align="center" border="1px" cellpadding="0" cellspacing="0" width="100%" >
            	<thead style="background-color: rgba(197, 197, 197, 0.37);">
            <tr>
            <th>楼盘名称</th>
            <th>归属区域</th>
            <tr>
            </tr>
            <tbody style="text-align: center;">
            <tr id="queryxq">
            
            </tr>
            </tbody>
            </table>
            
            </div>
            
             <div class="h1em"></div>
              <div class="h1em"></div>
            <a href="javascript:" class="aBtn aBtn-blue" onclick="queryxqinfo();"><span class="fem-12">查询</span></a>
            <div class="h1em"></div>
            <a href="broadbandInstall.jsp" class="aBtn aBtn-green"><span class="fem-12">申请安装登记</span></a>
            <div class="h2em"></div>
        </div>
        
    </div>    
    
</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
seajs.use(['jquery','JSBridge','main','swiper','tfAlert'], function(a) {
	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/broadband/queryAddress");
});
	
</script>

</html>
