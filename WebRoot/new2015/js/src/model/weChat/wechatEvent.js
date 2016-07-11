define([ "tfAlert","http://res.wx.qq.com/open/js/jweixin-1.0.0.js"],function(require, exports, module) {
	
	var flowRedPacketId = decodeURI(decodeURI(sys.getHttpParam("orderId")));;
	var tfAlertCtr = require("tfAlert");
	var tfAlert = tfAlertCtr.tfAlert;
	var share = {};
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	share.shareLineTime = function(shareObj){
		if(shareObj != null){
			wx.onMenuShareTimeline({
			      title: shareObj.title,
			      link: shareObj.link,
			      desc:shareObj.desc,
			      imgUrl: shareObj.imgUrl,
			      trigger: function (res) {
			    	  //tfAlert("点击触发分享","提示", "确定");
			      },
			      success: function (res) {
			    		  shareRedpackage();
			      },
			      cancel: function (res) {
			    	  tfAlert("取消分享","提示", "确定");
			      },
			      fail: function (res) {
			        tfAlert(JSON.stringify(res),"错误提示", "确定");
			      }
			 });
			
			 wx.onMenuShareAppMessage({
			      title: shareObj.title,
			      link: shareObj.link,
			      desc:shareObj.desc,
			      imgUrl: shareObj.imgUrl,
			      trigger: function (res) {
			        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
			    	 // tfAlert("点击触发分享","提示", "确定");
			      },
			      success: function (res) {
			    		  shareRedpackage();
			      },
			      cancel: function (res) {
			    	  tfAlert("取消分享","提示", "确定");
			      },
			      fail: function (res) {
			        tfAlert(JSON.stringify(res),"错误提示", "确定");
			      }
			 });
			 wx.showOptionMenu();
		}
	};
	// 全局导出
	
	function shareRedpackage(){
		$.ajax({
	        url: sys.getBaseUrl() + "flow/shareRedenvelope.do",
	        type: "post",
	        asyne: false,
	        dataType: "json",
	        data: {flowRedPacketId:flowRedPacketId},
	        success: function (data) {
	        	   if(data.msgs == "success"){
	        		  msgTips("分享状态","分享成功",function(){}); 
	        	   }else{
	        		   msgTips("分享状态","该红包无法被分享",function(){});
	        		   
	        	   }
	        }
	    });
	}
	exports.shareFun = share;
});
