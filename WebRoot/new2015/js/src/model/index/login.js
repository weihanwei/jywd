define(function (require) {

    $("input[name=logintype]").change(function () {
        var v = $(this).val();
        $(".showContent").eq(v).addClass("show").removeClass("hide").siblings(".showContent").removeClass("show").addClass("hide");
    })

})

function login() {

    var logintype = $("input[name='logintype']:checked").val();

    var jyn = $("#jyn").val();

    var jym = "";
    
    //var checked=$("#checked1").attr("checked");

    if(!$("#checked1").is(':checked')){
    	
    	$.alerts.alert("请勾选登录协议！", "提示");
        return false;
    	
    }

    if (jyn == '') {
        $.alerts.alert("手机号不能为空！", "提示");
        return false;
    } else {
        if (isPhone(jyn) == false) {
            $.alerts.alert("手机号不正确！", "提示");
            return false;
        }
        jyn = encrypt(jyn, key);
    }

    if (logintype == '0') {

        jym = $("#jym1").val();

        if (jym == '') {
            $.alerts.alert("密码不能为空！", "提示");
            return false;
        } else {
            if (isNum(jym) == false) {
                $.alerts.alert("密码不正确！", "提示");
                return false;
            }
            jym = encrypt(jym, key);
        }

    } else {

        jym = $("#jym2").val();

        if (jym == '') {
            $.alerts.alert("密码不能为空！", "提示");
            return false;
        } else {
            if (isNum(jym) == false) {
                $.alerts.alert("密码不正确！", "提示");
                return false;
            }
            jym = encrypt(jym, key);
        }

    }

    sys.showLoading();

    $.ajax({
        url: sys.getBaseUrl() + "login/login.do",
        type: "post",
 	    cache : false, 
	    async : false, 
        dataType: "json",
        data: {jyn: jyn, jym: jym, key: key, logintype: logintype},
        success: function (data) {

            sys.hideLoading();

            if (data.retype == '0') {
            	
            	jy.ReMoveUserMsg();
            	
                sys.goUrl(sys.getBaseUrl() + "new2015/index/index_1.jsp?isLoginPageTo=1", 0);

            } else {
                $.alerts.alert(data.retmsg, "提示");
                return false;
            }

        }
    });

}

function getRandomPassword(obj) {

    var jyn = $("#jyn").val();

    if (jyn == '') {
        $.alerts.alert("手机号不能为空！", "提示");
        return false;
    } else {
        if (isPhone(jyn) == false) {
            $.alerts.alert("手机号不正确！", "提示");
            return false;
        }
        jyn = encrypt(jyn, key);
    }

    $.ajax({
        url: sys.getBaseUrl() + "login/getRandomPassword.do",
        type: "post",
 	    cache : false, 
	    async : false, 
        dataType: "json",
        data: {jyn: jyn, key: key},
        success: function (data) {

            if (data.status != '1') {
                $.alerts.alert("短息发送失败！", "提示");
            } else {
            	$('#receiveMessInfo').show();
            	setTimeout(function(){$('#receiveMessInfo').hide();}, 20000);
                time(obj);
            }

        }
    });

}

var wait = 60;
function time(obj) {

    if (wait == 0) {

        obj.setAttribute("onclick", "getRandomPassword(this);"); 
        obj.innerHTML = "<span class=\"fem-12\">点击获取</span>";
        wait = 60;
    } else {
    	obj.removeAttribute("onclick");
        obj.innerHTML = "重新发送(" + wait + ")";
        wait--;
        setTimeout(function () {
                time(obj);
            },
            1000);
    }
}

function xy(){
	
    sys.goUrl(sys.getBaseUrl() + "new2015/index/xieyi.jsp", 0);
	
}

function isNum(str) {
    var re = /^[0-9]*$/;
    var flag = re.test(str);
    return flag;
}

function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}

function goToBundingPhone()
{
	var localUrl = sys.getBaseUrl()+"bunding/toBundingUserPhone.do";
	localUrl = encodeURIComponent(localUrl) ;
    var webchatUrl1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sys.getWXAPPID()+"&redirect_uri=";
    var webchatUrl2 = "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	var retUrl = webchatUrl1+localUrl+webchatUrl2;
	window.location.href=retUrl;
	// sys.goUrl(sys.getBaseUrl()+"bunding/toBundingUserPhone.do", 1);
}

