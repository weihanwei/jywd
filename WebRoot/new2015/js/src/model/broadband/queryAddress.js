
define(function(require) {
	jy.innertHead('查询地址');
	jy.innertFood(2);
	
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
	
	//引入jquery.autocomplete
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
			 
			  var data1=new Array();
			  data1[0]=jkaddress;
			  var data = {suggestions:suggestions1,data:data1};
				
				
				
						var input = $("#xiaoqu");
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
    	$("#querysu").css("display","none");
    	$("#xiaoqu").val("");
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
    			  var data1=new Array();
    			  data1[0]=jkaddress;
    			  var data = {suggestions:suggestions1,data:data1};
    				
    				
    				
    						var input = $("#xiaoqu");
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

});


function queryxqinfo(){
	var qy=$("#jkaddress").val();
	var xq=$("#xiaoqu").val();
	if(xq!=""){
		$("#querysu").css("display","block");	
		$("#queryxq").html("<td>"+xq+"</td><td>"+qy+"</td>");
	}else{
		$.alerts.alert("请选择小区名称","提示");
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
