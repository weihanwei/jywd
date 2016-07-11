define(function(require) {

});


function handle(){

    var jyn=$("#jyn").val();
    
    var jym=$("#code").val();
    
    var tourl=sys.getHttpParam("tourl");
    
    var id=sys.getHttpParam("id");
    
    var AGENTNO=sys.getHttpParam("AGENTNO");
    
    tourl=sys.getBaseUrl() +tourl+"?id="+id+"&AGENTNO="+AGENTNO;

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
    
    
    if (jym == '') {
        $.alerts.alert("动态密码不能为空！", "提示");
        return false;
    } else {
    	jym = encrypt(jym, key);
    }

    $.ajax({
        url: sys.getBaseUrl() + "login/login.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {jyn: jyn, jym: jym, key: key, logintype:'1'},
        success: function (data) {
 
        	 if (data.retype == '0') {
        		 sys.goUrl(tourl, 0);
             } else {
                 $.alerts.alert(data.retmsg, "提示");
                 return false;
             }

        }
    });

	
}


//获取随机密码
function getRandomPassword(obj) {
   
	var jyn=$("#jyn").val();
	
     jyn = encrypt(jyn, key);
   
    $.ajax({
        url: sys.getBaseUrl() + "login/getRandomPassword.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {jyn: jyn, key: key},
        success: function (data) {
 
        	 if (data.status != '1') {
                 $.alerts.alert("短息发送失败！", "提示");
             } else {
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

function isPhone(str) {
    var re = /^(1[0-9])\d{9}$/;
    var flag = re.test(str);
    return flag;
}