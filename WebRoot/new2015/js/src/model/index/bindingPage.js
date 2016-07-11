define(function (require) {


})

function onSubmitBunding() {


    var jyn = $("#bindPhone").val();

    var jym = "";
    


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

    
    jym = $("input[name='bindYZM']").val();;

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
    sys.showLoading();
    $.ajax({
        url: sys.getBaseUrl() + "bunding/bundingUserWeiXinInfo.do",
        type: "post",
 	    cache : false, 
	    async : false, 
        dataType: "json",
        data: {jyn: jyn, jym: jym, key: key},
        success: function (data) {

            sys.hideLoading();

            if (data.retype == '0') {
            	
               //绑定成功
              // $.alerts.alert("微信账号绑定手机号成功", "提示");
            	 sys.goUrl(sys.getBaseUrl()+"new2015/flow/openRedpackage.jsp",0); 
            } else {
                $.alerts.alert(data.retmsg, "提示");
                return false;
            }

        }
    });

}

function getRandomPassword(obj) {

    var jyn = $("#bindPhone").val();
    
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
