<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lenovocw.utils.RandomNumUtil"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/toBeShopManager.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>我要做店长</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bodybg">
<%
RandomNumUtil rnum=RandomNumUtil.Instance();
String key=rnum.getString();
%>
<div class="headBox">
    <div class="main">
        <div class="header pr pr-6">
            <span class="f-l" onclick="history.go(-1)">返回</span>
            <em class="headerMargin">我要做店长</em>
            <span class="f-r mManualBtn"  onclick="gotoManagerManual()">店长手册</span>
        </div>
    </div>
</div>
<div class="main" id="J_HeadFoot">
    <div class="search">
        <p id="AGENTNO">代理编号:-----------</p>
        <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <div class="searchInput">
                        <input type="text" name="phone" placeholder="请输入被推荐人手机号码" value="" class="J_footHide" />
                    </div>
                </td>
                <td>
                    <span class="searchBtn f-r" onclick="queryUser();">查询</span>
                </td>
            </tr>
        </table>
    </div>
    <div class="common-change-wrap nobrBottom">
        <a href="#" class="ads-c-4 active"><span class="fem-10">我要做店长</span></a><a href="javascript:" onclick="toMySales();" class="ads-c-4"><span
            class="fem-10">我的销量</span></a>
    </div>
    <ul class="searchNav toNavWidth">
        <li onclick="flash(this,4);" class="on">宽带业务</li>
        <li  onclick="flash(this,1);">4G业务</li>
        <li onclick="flash(this,2);">上网业务</li>
        <li onclick="flash(this,3);">通话业务</li>
        <li onclick="flash(this,5);">登录推荐</li>
    </ul>
    <div class="toBeShop">
        
    </div>

</div>
<div class="toBePopu hide" id="J_toBe">
    <div class="toBePopuHead"><i class="bg-cover"></i>登记为店长</div>
    <div class="toBePopuMain">
        <div class="ads-row">
            <div class="ads-c-8 pr-1em">
                <input type="text" class="toBeInput" id="jym2" placeholder="短信验证码">
            </div>
            <div class="ads-c-4"><a href="javascript:" onclick="getRandomPassword(this);" class="aBtn aBtn-green"><span class="fem-12">点击获取</span></a>
            </div>
        </div>
        <div class="toBeAgree">
            <div class="ads-c-12 fem-12">
                <div class="checkbox-div">
                    <input type="checkbox" id="checked1" />
                    <label for="checked1">同意《<a href="javascript:" onclick="xy();" class="blue">代理协议</a>》</label>
                </div>
            </div>
        </div>
    </div>
    <div class="toBePopuBtn">
        <a href="javascript:" onclick="handleShopManager();" class="aBtn aBtn-blue">确认登记</a>
    </div>
</div>
<div class="toBePopu hide" id="J_toSearch">
    <div class="toSearch"><i class="bg-cover"></i>查询结果</div>
    <div class="toSearchMain loadImg">
    </div>
    <div class="toBePopuBtn">
        <a href="javascript:" onclick="submit();" class="aBtn aBtn-blue">确定</a>
    </div>
</div>
<div class="popupbg hide"></div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main', 'tmpl','tfAlert'], function (a) {
        seajs.use("${pageContext.request.contextPath}/new2015/js/" + SCRIPT_PATH + "/model/person/toBeShopManager");
    });
    
    var key='<%=key%>';
    
    var jyn='<%=request.getSession().getAttribute("phone")%>';
</script>

</html>
