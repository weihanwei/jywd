define(function(require) {
	jy.innertFood(2);
	 getcode();
	//引入jquery.autocomplete
	 
	//获取Banner
    $.ajax({
		
		url :sys.getBaseUrl()+"index/getBannerByArea.do",
		type:"post",
		dataType:"json",
		data:{area:'4'},
		success :function(data){
			
			var banner="";
			
			 $.each(data,function(i,j){

				 var src=sys.getBaseUrl()+j.ICON;
				 
				 banner=banner+"<li class=\"swiper-slide\">"+//
                 "<img onclick=\"banner('"+j.ID+"','"+j.URL+"','"+j.TYPE+"','"+j.NAME+"');\" src="+src+">"+//
              "</li>";
		             
			   });
			
			 $("#banner").html(banner);
			 
			//加载滑动广告
		    jy.initSlider();
		}
	
    });
	    
	 
	var jqAuto = require("jquery/jquery.autocomplete");
	
	var addresslist="";
	var jkaddress=$("#jkaddress").val();
	$(".select-items-text").text(jkaddress);
	$.ajax({
		url:sys.getBaseUrl()+"/broadband/queryaddress.do",
		type:"post",
		dataType:"json",
		data:{address:jkaddress},
		success:function(data){
		   var status=data.status;
		 
		   if(status=="1"){
			   addresslist=data.data;
			  
			   //var branch="";
			  var list= eval("(" + addresslist + ")");
			  addresslist=list;
			  var suggestions1 = new Array(); 
			  $.each(addresslist,function(i,j){
				  suggestions1[i]=j.name;
			  });
			  console.log(suggestions1);
			  var data1=new Array();
			  data1[0]=jkaddress;
			  var data = {suggestions:suggestions1,data:data1};
				
				
				
						var input = $("#selectxq");
			            var onAutocompleteSelect =function(value, data) {    
						 //根据返回结果自定义一些操作  
						 console.log(value);
			            };   
			            var options = {  
			                lookup: data,//获取数据的后台页面  
			                width: input.outerWidth(),//提示框的宽度  
			                delimiter: /(,|;)\s*/,//分隔符  
			                onSelect: onAutocompleteSelect,//选中之后的回调函数  
			                deferRequestBy: 0, //单位微秒      			          
			                noCache: false //是否启用缓存 默认是开启缓存的  
			            };  
			            a1 = input.autocomplete(options);  
						a1.isLocal = true;
			 
		   }
		}
		});
	
    $("#jkaddress").bind("change",function(){
    	$("#selectxq").val("");
    	var jkaddress=$("#jkaddress").val();
    	$(".select-items-text").text(jkaddress);
    	
    	$.ajax({
    		url:sys.getBaseUrl()+"/broadband/queryaddress.do",
    		type:"post",
    		dataType:"json",
    		data:{address:jkaddress},
    		success:function(data){
    		   var status=data.status;
    		 
    		   if(status=="1"){
    			   addresslist=data.data;
    			  
    			   //var branch="";
    			  var list= eval("(" + addresslist + ")");
    			  addresslist=list;
    			  var suggestions1 = new Array(); 
    			  $.each(addresslist,function(i,j){
    				  suggestions1[i]=j.name;
    			  });
    			  console.log(suggestions1);
    			  var data1=new Array();
    			  data1[0]=jkaddress;
    			  var data = {suggestions:suggestions1,data:data1};
    				
    				
    				
    						var input = $("#selectxq");
    			            var onAutocompleteSelect =function(value, data) {    
    						 //根据返回结果自定义一些操作  
    						 console.log(value);
    			            };   
    			            var options = {  
    			                lookup: data,//获取数据的后台页面  
    			                width: input.outerWidth(),//提示框的宽度  
    			                delimiter: /(,|;)\s*/,//分隔符  
    			                onSelect: onAutocompleteSelect,//选中之后的回调函数  
    			                deferRequestBy: 0, //单位微秒      			          
    			                noCache: false //是否启用缓存 默认是开启缓存的  
    			            };  
    			            a1 = input.autocomplete(options);  
    						a1.isLocal = true;
    		   }
    		}
    		});
    });	
    
    access();
});

function getcode(){
	 $("#code").attr("src",sys.getBaseUrl()+"broadband/getLogCode.do?time="+new Date());
	}

function submitBZ(){
	var xm=$('input[name="khname"]').val();
	var dh=$('input[name="khdh"]').val();
	var qy=$('#jkaddress').val();
	var xq=$('#selectxq').val();
	var xxdz=$('input[name="xxdz"]').val();
	var code=$('input[name="code"]').val();
	var AGENTNO=sys.getHttpParam("AGENTNO");
	
	if(xm!=""&&dh!=""&xq!=""&&code!=""){
		$.ajax({
    		url:sys.getBaseUrl()+"/broadband/applicationJK.do",
    		type:"post",
    		dataType:"json",
    		data:{xm:xm,dh:dh,qy:qy,xq:xq,xxdz:xxdz,code:code,AGENTNO:AGENTNO},
    		success:function(data){
    			var status=data.status;
    			if(status=="1"){
    				$.alerts.alert(data.message,"提示");
    				$('input[name="khname"]').val("");
    				$('input[name="khdh"]').val("");    		
    				$('#selectxq').val("");
    				$('input[name="xxdz"]').val("");
    				getcode();
    				$('input[name="code"]').val("");
    				
    			}
    			}
    		});
		
	}else if(xm==""){
		$.alerts.alert("用户姓名不能为空","提示");
		
	}else if(dh==""){
		$.alerts.alert("电话号码不能为空","提示");
	}else if(xq==""){
		$.alerts.alert("请选择区域","提示");
	}else if(xq==""){
		$.alerts.alert("验证码不能为空","提示");
	}
	
}

function banner(id,url,type,name){
	
	var tourl="";
	
	if(type=="0"){
		tourl=sys.getBaseUrl()+url;
	}else{
		tourl=url;
	}
	sys.goUrl(sys.getBaseUrl()+"/new2015/index/messagesIframe.jsp?id="+id,0);
	
}