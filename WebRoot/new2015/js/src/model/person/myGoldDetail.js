define(function(require) {

    jy.innertHead('金币明细');

    jy.innertFood(4);
    
    $.ajax({
		
		url :sys.getBaseUrl()+"person/myGiveGoldRecord.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			var htmlvar = "";
			console.log(data);
			if(data.length == 0){
				htmlvar = "<div class=\"noMain\">亲~您暂无赠送金币记录哦！</div>";
			}
			
			for(var i = 0; i < data.length; i++){
				htmlvar = htmlvar + "<li>"
							            +"<p><span>办理号码:</span>"+data[i].PHONE+"</p>"
							            +"<p>"+data[i].CAUSE+"</p>"
							            +"<p class='myBDetaTime'><span>办理时间：</span>"+data[i].TIME+"</p>"
							            +"<p class='myBMoney'><span>金币:</span> +"+data[i].NUM+"</p>"
							      + "</li>";
			}
			$(".myBDeta").html(htmlvar);
		}
    
    });
    
});