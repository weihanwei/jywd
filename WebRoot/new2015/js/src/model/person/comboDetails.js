/*我的金币*/
define(function(require) {
	
jy.innertHead('套餐使用情况');

jy.innertFood(4);

$.ajax({
		
		url :sys.getBaseUrl()+"person/comboDetailsMSG.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
		
			var ll=data.ll+"M";
			
			var yy=data.yy+"分钟";
			
			$("#ll").html(ll);
			
			$("#yy").html(yy);
			   
		}
		
    });
	
});