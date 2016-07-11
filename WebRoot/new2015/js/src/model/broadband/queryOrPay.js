var broadbandAccount="";
var otype="";
define(function(require) {
	
	 jy.innertHead('缴费查询或支付');
		
		jy.innertFood(1);
	
	$.ajax({
		url:sys.getBaseUrl()+"/onlinepay/orderlist.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var stutes=data.status;
			if(stutes=="1"){
				broadbandAccount=data.broadbandAccount;
				$("#broadbandAccount").val(data.broadbandAccount);
				var list=eval("(" + data.list + ")");
				if(list.SubscriptOrders.length>0){
				var comtent="";
				$.each(list.SubscriptOrders,function(i,j){
					comtent +='<li><div class="qopResultLeft f-l"><p>订单编号:<a href="javascript:;" onclick="queryOrder(\''+j.OrderID+'\')">'+j.OrderID+'</a></p>'
                    +'<h4>'+j.ProductName+'</h4>'
                    +'<p>下单日期：'+j.OrderID.substring(0,4)+"-"+j.OrderID.substring(4,6)+"-"+j.OrderID.substring(6,8)+" "+j.OrderID.substring(8,10)+":"+j.OrderID.substring(10,12)+'</p>';
           
                    if(j.PayResult=="失败"){
                    comtent +='<p>订单状态：未支付</p></div><div class="qopResultRight f-r">'
                    	+'<a href="javascript:;" class="aBtn aBtn-green"  onclick="submitOrder(\''+j.OrderID+'\')">订单支付</a>'
                    	+'<a href="javascript:;" class="aBtn aBtn-red" onclick="deleteorder(\''+j.OrderID+'\')" style="display: none;">取消订单</a></div></li>';
                    }else{
                    	 comtent +='<p>订单状态：已支付</p></div><div class="qopResultRight f-r">';
                    }
				});
				$(".qopResult").append(comtent);
				}else{
					$(".qopResult").append("<li>暂无订单</li>");
				}
			}else if(stutes=="2"){
				window.location.href="onlinePayment.jsp";
			}
		}
	});
	
	if(IsPC()){
		otype="web";
	}else{
		otype="wap";
	}
	
});

function queryOrder(orderid){
	window.location.href=sys.getBaseUrl()+"/onlinepay/queryorder.do?prodid="+orderid+"&broadbandAccount="+broadbandAccount;
}

function submitOrder(orderid){
	var url=sys.getBaseUrl()+"orderbypay/pay.do?orderid="+orderid+"&broadbandAccount="+broadbandAccount+"&otype="+otype;
	window.open(url);
}

function deleteorder(orderid){
	$.ajax({
		url:sys.getBaseUrl()+"/onlinepay/orderlist.do",
		type:"post",
		dataType:"json",
		success:function(data){}
	});
}


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