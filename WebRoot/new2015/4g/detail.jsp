<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/4g/detail.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>4G套餐</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/4g.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
<input type="hidden" name="hiddensPhone" value="${sessionScope.phone}" />
    <div class="package-detail">
        <ul class="package-list">
            <li>
                <div class="ads-row">
                    <div class="ads-c-2">
                        <span class="icon"><img src="${pageContext.request.contextPath}/new2015/images/4g/package_img.png"></span>
                    </div>
                    <div class="ads-c-10">
                        <p class="package-name fem-12">${map.NAME}</p>

                        <div class="package-info">
                            价格：${map.PRICE}元/月<br>
                            适用品牌：${map.APPLY}<br>
                            适用地市：全市<br>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div class="package-introduction pd1em">
        <div class="ptitle">
            <span class="fem-12 blue2">套餐描述</span>
        </div>
        <div class="h1em"></div>
        <div class="ptext text-indent lh-16">
            ${map.INTRODUCE}
        </div>
    </div>

    <div class="btn_fixed">
        <div class="main">
            <div class="pd1em"><a href="javascript:" onclick="toHandle('${map.ID}','${map.NAME}');" class="aBtn aBtn-green"><span class="fem-12">办理</span></a></div>
        </div>
    </div>
    <div class="btn_fixed_height"></div>

</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
	seajs.use(['jquery','JSBridge','main'], function(a) {
		seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/4g/detail");
	});
	 //var sessionPhone='<%=request.getSession().getAttribute("phone")%>';
</script>
</html>
