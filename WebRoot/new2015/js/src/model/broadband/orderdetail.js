var otype="";
define(function(require) {
	 jy.innertHead('订单详细');
		jy.innertFood(1);
		
	var datalist=eval("(" + data + ")");
	$("#broadbandAccount").text(datalist.broadbandAccount);
	$("#BroadbandType").text(datalist.BroadbandType);
	$("#UserName").text(datalist.UserName.substring(0,1)+"**");
	$("#BoundPhone").text(datalist.BoundPhone);
	$("#Address").text(datalist.Address);
	
	var kdinfo=datalist.list;
	
	$("#ProductName").text(kdinfo.ProductName);
	$("#HandlePackage_StartTime").text(datalist.HandlePackage_StartTime.substring(0,10));
	$("#ProductType").text(kdinfo.ProductType);
	$("#Remark").text(kdinfo.Remark);
	$("#Cost").text(kdinfo.Cost+"元");
	$("#Bandwidth").text(kdinfo.Bandwidth+"M");
	if(kdinfo.PayResult=="失败"&&kdinfo.NGResult=="失败"){
		$("#PayResult").text("未支付");
		
	}else if(kdinfo.PayResult=="成功"&&kdinfo.NGResult=="成功"){
		$("#PayResult").text("订单办理成功");
		$("#zforder").hide();
	}
	
	var orderID=kdinfo.OrderID;
	
	if(IsPC()){
		otype="web";
	}else{
		otype="wap";
	}
	
	$("#zforder").bind("click",function(){
		var url=sys.getBaseUrl()+"orderbypay/pay.do?orderid="+orderID+"&broadbandAccount="+datalist.broadbandAccount+"&otype="+otype;
		window.open(url);
		
	});
	
});

function IsPC()  
{  
           var userAgentInfo = navigator.userAgent;  
           var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
           var flag = true;  
           for (var v = 0; v < Agents.length; v++) {  
               if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }  
           }  
           return flag;  
}