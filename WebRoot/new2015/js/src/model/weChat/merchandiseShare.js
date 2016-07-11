define(["tfAlert","http://res.wx.qq.com/open/js/jweixin-1.0.0.js"],function(require, exports, module) {
	var tfAlertCtr = require("tfAlert");
	var tfAlert = tfAlertCtr.tfAlert;
	var weChatShare = {};
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	weChatShare.ready = function(callback,hiddenMenu) {// default show online
		/*
		$.ajaxSettings.async = false;
		$.getJSON(basePath +'/weChat/signature.do', {"url":window.location.href}, function(data){ 
			para = data;
		});
		$.ajaxSettings.async = true;
		*/
		var url = document.URL;
		url = ReplaceAll(url,'&','@');
		$.ajax({
			url :sys.getBaseUrl()+'weChat/signature.do',
			//data : {"url":window.location.href.split("#")[0]},
			data : {"url":url},
			type : "post",
			async : true,
			cache : false,
			timeout : 60000, // 超时时间设置，单位毫秒
			dataType : "json",
			success : function(response) {
				if(response.status!="0"){
					tfAlert(response.error+"["+response.status+"]","错误提示", "确定");
				}
				var para = response;
				shareConfig(para,hiddenMenu,callback);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tfAlert("wechat signature exception happened", "错误提示", "确定");
			}
		});
		
	};
	function ReplaceAll(str, sptr, sptr1){
	        while (str.indexOf(sptr) >= 0){
	           str = str.replace(sptr, sptr1);
	        }
	        return str;
	 }
	function shareConfig(para,hiddenMenu,callBack){
		if(para.status=="0"){
			wx.config({
			      debug: false,
			      appId: para.appid,
			      timestamp: para.timestamp,
			      nonceStr: para.nonceStr,
			      signature: para.signature,
			      jsApiList: [
			        'onMenuShareTimeline',
			        'onMenuShareAppMessage'
			      ]
			    });
				wx.ready(function (){
					if(hiddenMenu){
						wx.hideOptionMenu();
					}
					//call back
					if(callBack){
						callBack();
					}
				});
				
				wx.error(function(res){
					wx.hideOptionMenu();
					tfAlert(JSON.stringify(res),"错误提示", "确定");
				});
		}
	}	
	exports.weChatShare = weChatShare;
});