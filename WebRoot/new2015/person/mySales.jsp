<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/person/mySales.appcache">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>我的销量</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/swiper.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/person.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/date.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="search">
        <p id="AGENTNO">代理编号:----------</p>
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
        <a href="javascript:" onclick="toBeShopManager();" class="ads-c-4"><span class="fem-10">我要做店长</span></a><a href="#" class="ads-c-4 active"><span
            class="fem-10">我的销量</span></a>
    </div>
    <div class="mySalesTime">
        <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="40%">
                    <input type="text" name="starTime" placeholder="开始时间…" value="" class="mySTimeInput kbtn" id="starTime"/>
                </td>
                <td width="4%" align="center">
                    <span>-</span>
                </td>
                <td width="40%">
                    <input type="text" name="endTime" placeholder="结束时间…" value="" class="mySTimeInput kbtn" id="endTime"/>
                </td>
                <td width="16%">
                    <a href="javascript:" onclick="flash();" class="mySBtn">统计</a>
                </td>
            </tr>
        </table>
    </div>
    <dl class="mySalesMain">
    </dl>
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
<div id="datePlugin"></div>

</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/encrypt.js"></script>
<script type="text/javascript">
    seajs.use(['jquery', 'JSBridge', 'main', 'swiper', 'tmpl','tfAlert','iscroll','dates'], function (a) {
        <%--seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/common/dates");--%>
    	seajs.use("${pageContext.request.contextPath}/new2015/js/"+SCRIPT_PATH+"/model/person/mySales");
    });
    
     var key='<%=request.getSession().getAttribute("key")%>';
    
    var jyn='<%=request.getSession().getAttribute("phone")%>';
</script>

</html>
