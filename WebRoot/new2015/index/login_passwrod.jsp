<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/index.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>登陆</title>
<link href="../css/reset.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
    	<div class="forget-passwrod">
        	<div class="h2em"></div><div class="h2em"></div>
        	<div class="error-tips-img"><img src="../images/common/error_img_1.jpg"></div>
            <div class="h2em"></div>
            <p class="red2 fem-15 text-center">请确认您的需求</p>
            <div class="h2em"></div>
            <div class="pd1em">
                <a href="javascript:" class="aBtn aBtn-blue"><span class="fem-12">我忘了密码</span></a>
                <div class="h1em"></div>
                <a href="javascript:" class="aBtn aBtn-green"><span class="fem-12">我要改密码</span></a>
            </div>
             <div class="h2em"></div>
        </div>
    </div>
</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','../js/'+SCRIPT_PATH+'/main'], function(require) {
		seajs.use("../js/"+SCRIPT_PATH+"/model/index/login");
	});
</script>

</html>
