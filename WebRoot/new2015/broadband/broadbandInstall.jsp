<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>宽带报装</title>
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
            	<span class="fem-12">客户登记：</span>
            </p>
            <div class="h1em"></div>
            <div class="lh-15">
            <p class="pr"><span class="pa">1、</span>&nbsp;&nbsp;&nbsp;&nbsp;在“宽带小区”查询到居住小区的客户，可在此处进行宽带安装登记；</p>
			<p class="pr"><span class="pa">2、</span>&nbsp;&nbsp;&nbsp;&nbsp;请您填写所在区域、小区及您的姓名、联系号码，我们将尽快联系工作人员与您联系并上门安装；</p>
            
            <p class="pr"><span class="pa">3、</span>&nbsp;&nbsp;&nbsp;&nbsp;请尽可能详细地登记您的居住小区及联系方式。</p>
            <div class="h1em"></div>
            
            <p>您要选择的区域宽带是否已覆盖:</p>
            </div>
            <div class="h1em"></div>
            
            
            <div class="h1em"></div>
             <input type="text" class="ltextput J_footHide" placeholder="请输入您的姓名" value="" name="khname">
            <div class="h1em"></div>
             <input type="text" class="ltextput J_footHide" placeholder="请输入手机号码" value="" name="khdh">
            <div class="h1em"></div>
            <div class="select-items">
            	<span class="select-items-text">请选择所在区域</span>
                <select id="jkaddress">     
                <option value="榕城">榕城</option>
				<option value="普宁">普宁</option>
				<option value="揭西">揭西</option>
				<option value="惠来">惠来</option>
				<option value="揭东">揭东</option>
                </select>
            </div>
             <div class="h1em"></div>
             <input type="text" class="ltextput J_footHide" id="selectxq" placeholder="请输入小区名称" value="">
            <div class="h1em"></div>
           
            <input type="text" class="ltextput J_footHide" name="xxdz" placeholder="请输入详细地址" value="">
            <div class="h1em"></div>
            
            <div class="pr yzmBox">
           	 <input type="text" class="ltextput J_footHide f-l" placeholder="请输入校验码" maxlength="6" value="" name="code">
             <a href="javascript:" class="pa yzm"><img src="../images/temp/yzm.png" id="code" onClick="getcode()"></a>
            </div>
            <div class="h1em"></div>
            
            <a href="javascript:" class="aBtn aBtn-blue" onclick="submitBZ();"><span class="fem-12">提交</span></a>
            <div class="h2em"></div>
        </div>
        
    </div>    
    
</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
		seajs.use(['jquery','JSBridge','main','swiper','tfAlert','recommend'], function(a) {
			
		jy.innertHead('宽带报装');
		jy.initSlider();
		seajs.use("<%=path%>/new2015/js/"+SCRIPT_PATH+"/model/broadband/broadbandInstall");
	});
</script>

</html>
