<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/discount/index_1.appcache">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<title>特惠广场</title>
<link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css" />
</head>
<body>
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


        <div class="discount-list">
        	<ul class="myPriUsed myShow" id="discountTC">

            </ul>
        </div>
        
        
    </div>    
    
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main'], function(a) {
		seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/discount/index");
	});
</script>
</html>
