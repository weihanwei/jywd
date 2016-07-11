<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/index.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>搜索</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="search">
        <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <div class="searchInput">
                        <input type="text" name="search" placeholder="请输入关键字搜索" value="" class="J_footHide"/>
                    </div>
                </td>
                <td>
                    <span class="searchBtn f-r">搜索</span>
                </td>
            </tr>
        </table>
    </div>
    <ul class="searchNav">
        <li class="on" onClick="selectLi(this,'0');">全部</li>
        <li onClick="selectLi(this,'1');">4G专区</li>
        <li onClick="selectLi(this,'2');">流量专区</li>
        <li onClick="selectLi(this,'3');">宽带专区</li>
        <li onClick="selectLi(this,'4');">通话专区</li>
    </ul>
    <div class="hide searchMain pr" id="0_searchMain">
    </div>
    <div class="hide searchMain pr" id="1_searchMain">
    </div>
    <div class="hide searchMain pr" id="2_searchMain">
    </div>
    <div class="hide searchMain pr" id="3_searchMain">
    </div>
    <div class="hide searchMain pr" id="4_searchMain">
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main','tfAlert'], function (a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/" + SCRIPT_PATH + "/model/index/search");
    });
</script>

</html>