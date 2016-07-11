/*我的金币*/
define(function(require) {
	var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
	jy.innertHead('我发起的红包');

	jy.innertFood(4);
    jy.navShow(".myPriNav ul li", ".myShow");
	getMyshareRedpackage();
	//获取我发起的通话红包
	getMyshareCallRedpackage();
	
});
//查看我发起的红包详情
function getMyshareRedpackage(){
	
	$.ajax({
        url: sys.getBaseUrl() + "flow/getMyshareRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        success: function (data) {
        	
        	if(data.length > 0){
        		$('#flowRedpackageList').html('');
            	$.each(data, function(i, item){   
            		if(item.SHARESTATUS == 0){
            			 var FSTR ="";
	              			     FSTR  = "<a href='"+'javascript:toShareRedpackage("'+item.ID+'" ,"'+item.COUNTSTATUS+'")'+"'>";
            			var str = "<li>"+FSTR+"<h3>"+item.WECHATNAME+"的流量红包<span class='f-r'>"+
    					item.FLOWSIZE+"M</span></h3><p >有效期至"+item.VALIDITYTIME+"<span class='f-r'>未分享"+
    					"</span></p></a></li>";
            			$('#flowRedpackageList').append(str);
            		}else{
            			var q = item.QUANTITY;
                		var r = item.REMAININGNUMBER;
                		var no = 0;
                			no = q-r;
                			 var FSTR ="";
  	              			     FSTR  = "<a href='"+'javascript:toShareRedpackage("'+item.ID+'" ,"'+item.COUNTSTATUS+'")'+"'>";
                		var str = "<li>"+FSTR+"<h3>"+item.WECHATNAME+"的流量红包<span class='f-r'>"+
    					item.FLOWSIZE+"M</span></h3><p >有效期至"+item.VALIDITYTIME+"<span class='f-r'>已领取"+no+"/"+item.QUANTITY
    					+"个</span></p></a></li>";
                		$('#flowRedpackageList').append(str);
            		}
            	}); 
        	}else{
        		var str = ' <div class="noMain">亲~您暂时还没有分享的红包哦！</div>' ;
        		$('#flowRedpackageList').append(str);
        	}
        	
        }
    });
}


//获取我发起的通话红包
function getMyshareCallRedpackage(){
	
	var param={};
	param.type=0;
	$.ajax({
        url: sys.getBaseUrl() + "call/getMyshareCallRedpackage.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data:param,
        success: function (data) {
        	$('#callRedpackageList').html('');
        	var state =data.state;
        	var callRedPackages = data.resultPackageList;
        	if(('0'==state) && callRedPackages.length>0)
        	{
               /* <li>
                <a href="#">
                    <h3>张三的流量红包<span class="f-r">10M</span></h3>
                    <p>2015-07-25<span class="f-r">已领取4/10个</span></p>
                </a>
            </li>*/
            	$.each(callRedPackages, function(i, item){   
            		
            			var str = '<li><a></a><h3>'+item.mobileB+'领取了'+item.name+'红包</h3><p>'+item.time1+'</p></li>';
            			$('#callRedpackageList').append(str);
            	}); s
        	}else
        	{
        		$('#callRedpackageList').append('<div class="noMain">亲~您暂时还没有分享的红包哦！</div>');
        	}
        	
   
        	
        }
    });
}


var flowRedPacketId="";//获取红包id

function reShareRedpackage(cnumber){
	flowRedPacketId = cnumber;
	$.ajax({
        url: sys.getBaseUrl() + "flow/shareRedenvelope.do",
        type: "post",
        asyne: false,
        dataType: "json",
        data: {flowRedPacketId:flowRedPacketId},
        success: function (data) {
        	   if(data.msgs == "success"){
        		  msgTips("分享状态","分享成功",function(){
        			  sys.goUrl(sys.getBaseUrl()+"new2015/person/mySendRedpackets.jsp",1);
        		  }); 
        	   }else{
        		   msgTips("分享状态","该红包无法被分享",function(){});
        	   }

        }
    });
}
function toShareRedpackage(cnumber,countStarus){
	flowRedPacketId = cnumber;
	var countStatus = countStarus;
	sys.goUrl(sys.getBaseUrl()+"new2015/flow/shareRedpackage.jsp?orderId="+flowRedPacketId+"&countStatus="+countStatus,1);
}
