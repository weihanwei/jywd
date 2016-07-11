<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="${pageContext.request.contextPath}/new2015/manifest/flow/overlayPackage.appcache">
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>流量叠加包</title>
    <link href="${pageContext.request.contextPath}/new2015/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/flow.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/new2015/css/bigzp.css" rel="stylesheet" type="text/css"/>
</head>
<body class="ovebg">
<div class="main">
    <img src="${pageContext.request.contextPath}/new2015/images/flow/banner01.jpg">
    <!--content start-->
    <div class="oveContent ovebg" >
        <div class="oveText" id="manage1">
           
            <p></p>
        </div>
        <div class="oveDraw pr" id="manage2">
           	<img src="${pageContext.request.contextPath}/new2015/images/bigzp/1.png" id="shan-img" style="display:none;" />
    		<img src="${pageContext.request.contextPath}/new2015/images/bigzp/2.png" id="sorry-img" style="display:none;" />
			
			<div class="banner" style="background-image:url(/jywd/new2015/images/common/overlaydrawbg.png);background-size:100% 100%;">
			<div class="turnplate" style="background-image:url(${pageContext.request.contextPath}/new2015/images/bigzp/turnplate-bg.png);background-size:100% 100%;">
				<canvas class="item" id="wheelcanvas" width="422px" height="422px"></canvas>
				<img class="pointer" src="${pageContext.request.contextPath}/new2015/images/bigzp/turnplate-pointer.png"/>
			</div>
            </div>
        </div>
        <div class="openMain overMain">
            <div class="overTitle">活动规则：</div>
            <p>1、活动仅限揭阳移动客户参与，其中4G随心王、未更换4G USIM卡、未实名登记、转品牌未生效客户不可参与；</p>
            <p>2、活动优惠方式是扣取客户10元叠加包费用后，再返回客户相应优惠额度的话费；</p>
            <p>3、每个手机号码每天仅限1次抽奖机会；</p>
            <p>4、10元叠加包内含100M国内通用流量，办理成功后所含流量半小时内生效，下个月结日（全球通客户下月1日）失效 。</p>
        </div>

    </div>
    <!--content start-->
</div>
</body>
<script src="${pageContext.request.contextPath}/new2015/js/lib/seajs/seajs.js"></script>
<script src="${pageContext.request.contextPath}/new2015/js/jquery-1.10.2.js"></script>

<script type="text/javascript">
seajs.use(['jquery','JSBridge','main','tfAlert','recommend','data'], function(a) {
	
	seajs.use("/jywd/new2015/js/awardRotate");
	seajs.use("/jywd/new2015/js/"+SCRIPT_PATH+"/model/flow/overlayPackage");
});
   
</script>
</html>