var AccessProducts="";
var starttime="";
var otype="";
var ProductName="";
var Cost="";
var Bandwidth="";
var username="";
var UserStatusdata=[{"name":"US10","value":"正在使用"},{"name":"US20","value":"正式销户"},{"name":"US21","value":"有效期到期销户"},{"name":"US22","value":"预约销户"},{"name":"US23","value":"欠费销户"},{"name":"US24","value":"强制退网"}];
define(function(require) {
	jy.innertHead('家宽在线续费');
	
	jy.innertFood(1);
	//初始数据
	init();
	if(IsPC()){
		otype="web";
	}else{
		otype="wap";
	}
	
	
});

function init(){
	var paylist="";
	//sys.showLoading();
	$.ajax({//判断用户是否登录成功
		url:sys.getBaseUrl()+"/onlinepay/getinfo.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var status=data.status;
			if(status=="1"){
				var datainfo=eval("(" + data.data + ")");
				$("#BroadbandAccount").text(datainfo.BroadbandAccount);
				$("#UserName").text(datainfo.UserName.substring(0,1)+"**");
				username=datainfo.UserName;
				$.each(UserStatusdata,function(i,j){
					if(j.name==datainfo.UserStatus){
						$("#UserStatus").text(j.value);
								
					}
				});
				
				$("#HandlePackage_EndTime").text(datainfo.HandlePackage_EndTime.substring(0,11));
				$("#Address").text(datainfo.Address.replace(/\\/g, ""));
				$("#NextPackage_StartTime").text(datainfo.NextPackage_StartTime.substring(0,11));
				var year=datainfo.Validity;
				
				$("#NextPackage_EndTime").text();
				starttime=datainfo.NextPackage_StartTime.substring(0,11);
				AccessProducts=datainfo.AccessProducts;
				var conetntul="";
				$.each(AccessProducts,function(i,j){
					if(paylist!=j.Bandwidth){
					if(i==0){
						conetntul+="<li><input type='radio' name='payPackage' id='"+j.Bandwidth+"M' checked/><label for='"+j.Bandwidth+"M'>"+j.Bandwidth+"M</label></li>";										
					}else{
						conetntul+="<li><input type='radio' name='payPackage' id='"+j.Bandwidth+"M'/><label for='"+j.Bandwidth+"M'>"+j.Bandwidth+"M</label></li>";
					}
					}
				});			
				$("#paypackageli").append(conetntul);
				 $(":radio[name='payPackage']").click(function(){
					 
					 var value=$(this).attr("id");
					 paychange(value);
				 });
				//初始化第一个list
				var firstpaylist=AccessProducts[0].Bandwidth;
				contentselect="";
				  $.each(AccessProducts,function(h,v){
					  if(v.Bandwidth==firstpaylist){
						  contentselect +="<option value='"+v.ProductID+"'>"+v.ProductName+"</option>";
					  }
				  });
				  $("#taocanlist").html(contentselect);
				  $(".payListChooseText").text(AccessProducts[0].ProductName);
				  $("#firstpayListChoose").text(AccessProducts[0].ProductName);
				  $("#ProductType").text(AccessProducts[0].ProductType);
				  $("#Remark").text(AccessProducts[0].Remark);
				  $("#Cost").text(AccessProducts[0].Cost+"元");
				//sys.hideLoading();
			}else if(status=="0"){
				$.alerts.alert(data.msg,"提示");
			
			}
			}
		});
}

function paychange(value){
	contentselect="";
	  $.each(AccessProducts,function(h,v){
		  var tvalue=v.Bandwidth+"M";
		  if(tvalue==value){
			 
			  contentselect +="<option value='"+v.ProductID+"'>"+v.ProductName+"</option>";
			  $(".payListChooseText").text(v.ProductName);
			  $("#firstpayListChoose").text(v.ProductName);
			  $("#ProductType").text(v.ProductType);
			  $("#Remark").text(v.Remark);
			  $("#Cost").text(v.Cost+"元");
			  			 
		  }
	  });
	  $("#taocanlist").html(contentselect);
	 
	
}

function sumbitorder(){
	
	msgTips("状态","<br>正在提交,请稍后 <br><br><div class='loading_bar'><span></span></div>",function(){},false);
	
	var broadbandAccount=$("#BroadbandAccount").text();
	var prodid=$("#taocanlist").val();
	var prodname=$("#taocanlist").text();
	var Bandwidth=$(":radio[name='payPackage']:checked").attr("id");
	var Cost= $("#Cost").text();
	var mark=$("#Remark").text();
	var ProductType=$("#ProductType").text();
	$.ajax({//判断用户是否登录成功
		url:sys.getBaseUrl()+"/onlinepay/sumbitOrder.do",
		type:"post",
		dataType:"json",
		data:{broadbandAccount:broadbandAccount,prodid:prodid,prodname:prodname,Bandwidth:Bandwidth,Cost:Cost,mark:mark,ProductType:ProductType,username:username},
		success:function(data){
			
			$("#msg_fixboxbg,#msg_fixbox").hide(0);	
			var status=data.status;
			if(status=="1"){//订单提交成功
				var datainfo=eval("(" + data.data + ")");
				var orderid=datainfo.do1.OrderID;
				$("#orderid").text(orderid);
				$("#popupay").show();
			}else{
				$.alerts.alert(data.msg,"提示");
			}
			}
	});
}

//支付
function submitOrder(){
	var orderid=$("#orderid").text();
	var broadbandAccount=$("#BroadbandAccount").text();
	var ProductName=$(".payListChooseText").text();
	var Bandwidth=$(":radio[name='payPackage']:checked").attr("id");
	
	var Cost= $("#Cost").text();
	var NextPackage_StartTime=$("#NextPackage_StartTime").text();
	var url=sys.getBaseUrl()+"orderbypay/pay1.do?orderid="+orderid+"&broadbandAccount="+broadbandAccount+"&otype="+otype;
	  url +="&ProductName="+ProductName+"&Bandwidth="+Bandwidth+"&Cost="+Cost+"&NextPackage_StartTime="+NextPackage_StartTime;
	//window.open(url);
	window.location.href=url;
	$("#popupay").hide();
}
//订单列表
function queryorderlist(){
	window.location.href="queryOrPay.jsp";
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


