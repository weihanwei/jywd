/*我的金币*/
define(function(require) {
	
	jy.innertHead('我的金币');
	
	jy.innertFood(4);
	
	//绑定tab切换事件
	jy.switchTabList(".icon-tab-list dt.unfold");
	//加载状态占位
	jy.loadOccInit();
	
	fish();
	
});	

function fish(){
	$.ajax({
		url :sys.getBaseUrl()+"person/myGoldForRecordAndGoldCount.do",
		type:"post",
		dataType:"json",
		data:'',
		success :function(data){
			var phone = data.phone;
			
			console.log(data);
			var records=data.records;
			console.log(records);
			var goldCount=data.goldCount;
			console.log(goldCount);
			
			
			var htmlval="<div class='index-user-info'>"
					          +"<div class='ads-row'>"
					          	+"<div class='ads-c-12 fem-12'>"
					                +"<span class='icon-index-user bg-cover'></span>"+phone+"欢迎您 "
					            +"</div>"
					          +"</div>"
					    +"</div>"
					    +"<div class='ads-row'>"
					        +"<div class='ads-c-4'>"
					           +"<div class='menuwrap'>"
					                +"<span class='bg-cover index-menu-icon menu-icon-gold-1'></span>"
					                +"<span class='yellow'>总金币</span><br>"+goldCount.TOTALGOLD
					           +"</div>"
				            +"</div>"
					        +"<div class='ads-c-4'>"
					           +"<div class='menuwrap'>"
					                +"<span class='bg-cover index-menu-icon menu-icon-gold-2'></span>"
					                +"<span class='yellow'>可用金币</span><br>"+goldCount.REMAININGGOLD
				               +"</div>"
			               +"</div>"
					       +"<div class='ads-c-4'>"
					            +"<span class='ruleBtn' href=\"javascript:\" onclick=\"xieyiGold()\">金币规则</span>"
				            +"</div>"
			            +"</div>";
			
			$(".index-login").html(htmlval);
			
			var htmlrecord = "";
			records = eval(records);
			if(records.length == 0){
				htmlrecord = "<div class=\"noMain\">亲~您暂无金币兑换记录哦！</div>";
			}else{
				for(var i = 0; i < records.length; i++){
					htmlrecord = htmlrecord + "<div class='myGold'>"
					+"<p><span>兑换号码:</span>"+records[i].phone+"</p>"
					+"<p>"+records[i].changeContent+"<span class='f-r'>金币: <em>"+records[i].gold+"</em></span></p>"
					+"<div class='myGoldTime'><span>兑换时间：</span>"+records[i].time+"</div>"
					+"</div>";
				}
			}
			$("#gold-list2").html(htmlrecord);
		}
	});
}

function goMyGoldDetail(){
	sys.goUrl(sys.getBaseUrl() + "new2015/person/myGoldDetail.jsp", 1);
}

function xieyiGold(){
	
	sys.goUrl(sys.getBaseUrl() + "new2015/person/xieyiGold.jsp", 1);
	
}

function exchange(type){
	
	msgConfirm("提醒","<br><br><p align='center'>确认兑换"+name+"吗？</p>",function(){
		
		msgTips("兑换状态","<br><span id='status'>兑换中</span> <br><br><div class='loading_bar'><span></span></div>",function(){
	
	    },false);
		
		$.ajax({
			url :sys.getBaseUrl()+"person/myGoldExchange.do",
			type:"post",
			dataType:"json",
			data:{type:type},
			success :function(data){
				if(data.issuccess == '1'){
					msgTips("兑换状态","<br>"+data.msg,function(){
						fish();
					});
				}else{
					msgTips("兑换状态","<br>"+data.msg,function(){});
				}
			}
		});
	});
}
	
