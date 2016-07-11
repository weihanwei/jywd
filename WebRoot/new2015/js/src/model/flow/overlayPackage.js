define(function(require) {
    jy.innertHead('流量叠加包');
    //加载滑动广告
    //jy.initSlider();
    var wx = require("http://res.wx.qq.com/open/js/jweixin-1.0.0.js");  
	wx.hideOptionMenu();
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
        var r = window.location.search.substr(1).match(reg);  
        if (r != null) return unescape(r[2]);  
        return null;  
    }  
	var ACTIVITYID=getQueryString("ACTIVITYID");
	
	if(ACTIVITYID==null){
		
		ACTIVITYID=1;
		
	}
	
	 var turnplate={
	    		restaraunts:[],				//大转盘奖品名称
	    		colors:[],					//大转盘奖品区块对应背景颜色
	    		outsideRadius:192,			//大转盘外圆的半径
	    		textRadius:155,				//大转盘奖品位置距离圆心的距离
	    		insideRadius:68,			//大转盘内圆的半径
	    		startAngle:0,				//开始角度
	    		
	    		bRotate:false				//false:停止;ture:旋转
	    };
	    turnplate.restaraunts = ["50M免费流量包", "10闪币", "谢谢参与", "5闪币", "10M免费流量包", "20M免费流量包", "20闪币 ", "30M免费流量包", "100M免费流量包", "2闪币","20闪币 "];
		turnplate.colors = ["#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF","#FFF4D6"];

	    $(document).ready(function(){
	    	
	    	//动态添加大转盘的奖品与奖品区域背景颜色
	    	$.ajax({
				url:  sys.getBaseUrl() +"/bigzp/getLinkeRulesview.do?ACTIVITYID="+ACTIVITYID+"&date="+new Date(),
				secureuri : false, //是否需要安全协议，一般设置为false
				dataType : 'json', //返回值类型 一般设置为json
				beforeSend: function () {
					 //sys.showLoading();
					showLoadingTips(0,"请稍等。。。。");
		        },
		       	complete: function () {
		       		// sys.hideLoading();
		       		hideLoadingTips();
		       	},
				success : function(data, status, x){ //服务器成功响应处理函数
					//alert(data.msg);
					turnplate.bRotate = false;
					if (data.msg=="true") {
						var s=data.ACTIVITYNAME;
						s=s.replace("&lt;br&gt;","<br>");
						$("#manage1").html(s);
						turnplate.restaraunts=data.LinkeRuleslist;
						turnplate.colors = data.LinkeRuleslistcolor;
						drawRouletteWheel();
					} else {
						$("#manage1").html("活动筹划中。敬请期待。。。");
						$("#manage2").html("");
					}
				},
				error : function(data, status, e) {
					$.alerts.alert(e, "提示");
				}
			});
	    	
	    	//turnplate.restaraunts = ["50M免费流量包", "10闪币", "谢谢参与", "5闪币", "10M免费流量包", "20M免费流量包", "20闪币 ", "30M免费流量包", "100M免费流量包", "2闪币","20闪币 "];
	    	//turnplate.colors = ["#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF","#FFF4D6"];

	    	
	    	var rotateTimeOut = function (){
	    		$('#wheelcanvas').rotate({
	    			angle:0,
	    			animateTo:2160,
	    			duration:8000,
	    			callback:function (){
	    				alert('网络超时，请检查您的网络设置！');
	    			}
	    		});
	    	};

	    	//旋转转盘 item:奖品位置; txt：提示语;
	    	var rotateFn = function (item, txt,titleMsg){
	    		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
	    		if(angles<270){
	    			angles = 270 - angles; 
	    		}else{
	    			angles = 360 - angles + 270;
	    		}
	    		$('#wheelcanvas').stopRotate();
	    		$('#wheelcanvas').rotate({
	    			angle:0,
	    			animateTo:angles+7200,
	    			duration:16000,
	    			callback:function (){
	    				
	    				turnplate.bRotate = false;
	    				//alert(txt);
	    				if(txt.indexOf("谢谢参与")>=0){
	    					$.alerts.alert(txt,"提示");
	    				}else{
	    					
	    				    var AGENTNO=sys.getHttpParam("AGENTNO");
	    				    var phone="";
	    				    ZHData.get("indexMSG",function(data){
	    				    	phone=data.phone;
	    				    });
	    				    
	    				    titleMsg=titleMsg.replace("{phone}",phone);
	    					msgConfirmokno("提示","<br><br><p align='center'>"+titleMsg+"</p>","确认办理，即享优惠","手气不好，下次再来",function(){
	    						$.ajax({
	    			    			url:  sys.getBaseUrl() +"/bigzp/getHandleLuckyDraw.do?date="+new Date()+"&AGENTNO="+AGENTNO,
	    			    			secureuri : false, //是否需要安全协议，一般设置为false
	    			    			dataType : 'json', //返回值类型 一般设置为json
	    			    			beforeSend: function () {
	    								 //sys.showLoading();
	    								showLoadingTips(0,"请稍等。。。。");
	    					        },
	    					       	complete: function () {
	    					       		// sys.hideLoading();
	    					       		hideLoadingTips();
	    					       	},
	    			    			success : function(data, status, x){ //服务器成功响应处理函数
	    				            	jy.ReMoveUserMsg();
	    			    				$.alerts.alert(data.msg,"提示");
	    			    			},
	    			    			error : function(data, status, e) {
	    			    				$.alerts.alert(e,"提示");
	    			    			}
	    			    		});
	        				});
	    				}
	    				
	    				
	    			}
	    		});
	    	};

	    	$('.pointer').click(function (){
	    		if(turnplate.bRotate){
	    			return;
	    		}
	    		$.ajax({
	    			url:  sys.getBaseUrl() +"/bigzp/getLuckyDraw.do?ACTIVITYID="+ACTIVITYID+"&date="+new Date(),
	    			secureuri : false, //是否需要安全协议，一般设置为false
	    			dataType : 'json', //返回值类型 一般设置为json
	    			beforeSend: function () {
	   				 //sys.showLoading();
	   				showLoadingTips(0,"请稍等。。。。");
		   	        },
		   	       	complete: function () {
		   	       		// sys.hideLoading();
		   	       		hideLoadingTips();
		   	       	},
	    			success : function(data, status, x){ //服务器成功响应处理函数
	    				
	    				if (data.bl=="true") {
	    					//console.log(turnplate.bRotate);
	    		    		
	    		    		turnplate.bRotate = !turnplate.bRotate;
	    		    		
	    		    		//获取随机数(奖品个数范围内)
	    		    		//console.log("turnplate.restaraunts.length"+turnplate.restaraunts.length);
	    		    		//var item = rnd(1,turnplate.restaraunts.length);
	    		    		var item = data.number;
	    		    		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
	    		    		//console.log("item="+item);
	    		    		//console.log("turnplate.restaraunts[item-1] ="+turnplate.restaraunts[item-1]);
	    		    		rotateFn(item, turnplate.restaraunts[item-1],data.titleMsg);
	    				} else {
	    					turnplate.bRotate = false;
	    					$.alerts.alert(data.msg,"提示");
	    				}
	    			},
	    			error : function(data, status, e) {
	    				$.alerts.alert(e,"提示");
	    			}
	    		});
	    	});
	    	
	        access();
	    });

	    function rnd(n, m){
	    	var random = Math.floor(Math.random()*(m-n+1)+n);
	    	return random;
	    	
	    }


	    //页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
	    window.onload=function(){
	    	
	    };

	    function drawRouletteWheel() {    
	      var canvas = document.getElementById("wheelcanvas");    
	      if (canvas.getContext) {
	    	  //根据奖品个数计算圆周角度
	    	  var arc = Math.PI / (turnplate.restaraunts.length/2);
	    	  var ctx = canvas.getContext("2d");
	    	  //在给定矩形内清空一个矩形
	    	  ctx.clearRect(0,0,422,422);
	    	  //strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式  
	    	  ctx.strokeStyle = "#FFBE04";
	    	  //font 属性设置或返回画布上文本内容的当前字体属性
	    	  ctx.font = '16px Microsoft YaHei';
              //ctx.zIndex = 10;
	    	  for(var i = 0; i < turnplate.restaraunts.length; i++) {       
	    		  var angle = turnplate.startAngle + i * arc;
	    		  ctx.fillStyle = turnplate.colors[i];
	    		  ctx.beginPath();
	    		  //arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）    
	    		  ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);    
	    		  ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
	    		  ctx.stroke();  
	    		  ctx.fill();
	    		  //锁画布(为了保存之前的画布状态)
	    		  ctx.save();   
	    		  
	    		  //----绘制奖品开始----
	    		  ctx.fillStyle = "#E5302F";
	    		  var text = turnplate.restaraunts[i];
	    		  var line_height = 17;
	    		  //translate方法重新映射画布上的 (0,0) 位置
	    		  ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);
	    		  
	    		  //rotate方法旋转当前的绘图
	    		  ctx.rotate(angle + arc / 2 + Math.PI / 2);
	    		  
	    		  /** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
	    		  if(text.indexOf("M")>0){//流量包
	    			  var texts = text.split("M");
	    			  for(var j = 0; j<texts.length; j++){
	    				  ctx.font = j == 0?'bold 20px Microsoft YaHei':'16px Microsoft YaHei';
	    				  if(j == 0){
	    					  ctx.fillText(texts[j]+"M", -ctx.measureText(texts[j]+"M").width / 2, j * line_height);
	    				  }else{
	    					  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
	    				  }
	    			  }
	    		  }else if(text.indexOf("M") == -1 && text.length>6){//奖品名称长度超过一定范围 
	    			  text = text.substring(0,6)+"||"+text.substring(6);
	    			  var texts = text.split("||");
	    			  for(var j = 0; j<texts.length; j++){
	    				  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
	    			  }
	    		  }else{
	    			  //在画布上绘制填色的文本。文本的默认颜色是黑色
	    			  //measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
	    			  ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
	    		  }
	    		  
	    		  //添加对应图标
	    		  if(text.indexOf("闪币")>0){
	    			  var img= document.getElementById("shan-img");
	    			  img.onload=function(){  
	    				  ctx.drawImage(img,-15,10);      
	    			  }; 
	    			  ctx.drawImage(img,-15,10);  
	    		  }else if(text.indexOf("谢谢参与")>=0){
	    			  var img= document.getElementById("sorry-img");
	    			  img.onload=function(){  
	    				  ctx.drawImage(img,-15,10);      
	    			  };  
	    			  ctx.drawImage(img,-15,10);  
	    		  }
	    		  //把当前画布返回（调整）到上一个save()状态之前 
	    		  ctx.restore();
	    		  //----绘制奖品结束----
	    	  }     
	      } 
	    }

		
});
