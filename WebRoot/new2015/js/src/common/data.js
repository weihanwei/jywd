/*ajax请求方法*/
//NGBoss用户基本信息，获取用户等级CRM_IM_274，level 等级，brand 品牌类型
function myPrivilegeMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url: sys.getBaseUrl()+"person/myPrivilegeMSG.do",
		data:'',
		dataType:'json',
		success: function(data){
			ZHData.set("myPrivilegeMSG",data);  
			ZHData.callback(index); 
	    }
   });
}

function indexMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url: sys.getBaseUrl()+"index/indexMSG.do",
		data:'',
		dataType:'json',
		success: function(data){
			ZHData.set("indexMSG",data);  
			ZHData.callback(index); 
	    }
   });
}

function index4GMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url :sys.getBaseUrl()+"handle4G/indexMSG.do",
		data:'',
		dataType:'json',
		success: function(data){
			ZHData.set("index4GMSG",data);  
			ZHData.callback(index); 
	    }
   });
}

function myBillMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url :sys.getBaseUrl()+"person/myBillMSG.do",
		data:'',
		dataType:'json',
		success: function(data){

		    if(data.datastatus==0)
		    {
				ZHData.set("myBillMSG",data);  
				ZHData.callback(index);
		    }else
		    {
		    	$(".myBillEcharts").html('<div class="noMain">亲~暂无您的消费数据！</div>');
		    }

	    }
   });
}

function indexCallMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url :sys.getBaseUrl()+"call/indexMSG.do",
		data:'',
		dataType:'json',
		success: function(data){
			ZHData.set("indexCallMSG",data);  
			ZHData.callback(index); 
	    }
   });
}


function relativesNetMSG(index){
	
	$.ajax({
		type:"POST",
		timeout : 60000,
		url :sys.getBaseUrl()+"call/relativesNetMSG.do",
		data:'',
		dataType:'json',
		success: function(data){
			ZHData.set("relativesNetMSG",data);  
			ZHData.callback(index); 
	    }
   });
}

!function(){
	
//字段依赖表 string 和 json
var DependenceTable = [
	 //账单测试字段
	{
	fieldName:"myPrivilegeMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"myPrivilegeMSG", //依赖获取数据方法
	fieldTime:72000 //单位（秒）独立过期时间，设置的话则改字段以此为准
	},
	{
	fieldName:"indexMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"indexMSG", //依赖获取数据方法
	fieldTime:1300 //单位（秒）独立过期时间，设置的话则改字段以此为准
	},
	{
	fieldName:"index4GMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"index4GMSG", //依赖获取数据方法
	fieldTime:1300 //单位（秒）独立过期时间，设置的话则改字段以此为准
	},
	{
	fieldName:"myBillMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"myBillMSG", //依赖获取数据方法
	fieldTime:72000 //单位（秒）独立过期时间，设置的话则改字段以此为准
	},
	{
	fieldName:"indexCallMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"indexCallMSG", //依赖获取数据方法
	fieldTime:1300 //单位（秒）独立过期时间，设置的话则改字段以此为准
	},
	{
	fieldName:"relativesNetMSG", //字段名
	fieldType:"json", //类型
	fieldFnc:"relativesNetMSG", //依赖获取数据方法
	fieldTime:1300 //单位（秒）独立过期时间，设置的话则改字段以此为准
	}
	 
];	

/*数据类*/
var ZHData = function(){
	this.deadline  = 180*1000;//全局缓存时间 单位(s)
	this.callback_param = [];
	this.callback_index = 0;
	this.init();
	return this;
}


ZHData.prototype = {
	//数据刷新初始化，为需要刷新的数据做从新读取操作
	init : function(){
		
	},
	//获取当前时间搓
	getTime : function(){
	  return new Date().getTime();
	},
	callback : function(index){
		this.get(this.callback_param[index]["field"],this.callback_param[index]["callback"]);
	},
	//获取字段值
	get : function(field,callback){
		
		var self =  this;
		var time = sys.getCookie(field+"_time");
		
		var data = sys.getCookie(field);
		var time2 = self.getFieldTime(field);//字段配置的缓存时间
		
		var deadline = self.deadline;
		var now = new Date().getTime();
		
		//如果字段配置的缓存时间不为空
		if(time2!=null){
			deadline = time2*1000;
		}
		//如果为空或时间过期
		if(time == ""|| now-time>deadline){
			this.callback_param.push({"field":field,"callback":callback});
			self.getfieldFnc(field,function(fncName){
				//捕捉错误，防止意外					
				try {
			// 此处是可能产生例外的语句
				eval ( fncName+"("+self.callback_index+")");
			} catch(error) {
			// 此处是负责例外处理的语句
				} finally {
			// 此处是出口语句
				self.callback_index ++;
			}
			
			});
			return;
		}
		
		var type = self.isDefaultJson(field);
		
		//判断是否json
		if(type==true){
		  typeof callback =="function" && callback(JSON.parse(data));
		}else{
		  typeof callback =="function" && callback(data);
		}
		
	},
	//设置字段值
	set:function(field,value,callback){
		var self = this;
		sys.setCookie(field+"_time",new Date().getTime());
		var type = self.isDefaultJson(field);
		//判断是否json
		if(type==true){
			sys.setCookie(field+"_type","json");	
		 	sys.setCookie(field,JSON.stringify(value));
		}else{
		 sys.setCookie(field+"_type","string");	
		 sys.setCookie(field,value);
		}
		
		typeof callback =="function" && callback();
		
	},
	//删除字段值
	remove:function(field){
		sys.removeStorage(field);
		sys.removeStorage(field+"_time");
		sys.removeStorage(field+"_type");
	},
	//获取依赖请求方法
	getfieldFnc : function(field,callback){
		for(var i = 0;i<DependenceTable.length;i++){
			if(field==DependenceTable[i].fieldName){
				typeof callback == "function" && callback(DependenceTable[i].fieldFnc);
			}
		}
	},
	
	//是否默认json
	isDefaultJson : function(field){
		var data = false;
			for(var i= 0;i<DependenceTable.length;i++){
				if(field==DependenceTable[i].fieldName){
					if(DependenceTable[i].fieldType == "json"){
						data= true;
					}
				}
			}
			return data;
	},
	//是否json
	isJson : function(obj){
	var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
	return isjson;
	},
	//获取字段配置的过期时间
	getFieldTime:function(field){
		var data = null;
		for(var i= 0;i<DependenceTable.length;i++){
			if(field==DependenceTable[i].fieldName){
				if(typeof DependenceTable[i].fieldTime != undefined){
					data = DependenceTable[i].fieldTime;
				}
			}
		}
		return data;
	},
	//清除所有缓存
	clearData : function(callback){
		for(var i =0;i<DependenceTable.length;i++){
			this.remove(DependenceTable[i]["fieldName"]);
			if(i==DependenceTable.length-1){
				typeof callback == "function" &&callback();
			}
		}
	}
}
	window.ZHData = new ZHData();

}();
