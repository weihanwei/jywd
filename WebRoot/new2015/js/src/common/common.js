
function showLoadingTips(cancel,msg){
	var display = "block";
	if(cancel==0){
		display = "none";
	}
	var msg = msg||"";
	if(!$("#loadingTips")[0]){
	var temp = '<div class="loadingTips" id="loadingTips"><img src="../images/load.gif" /><a href="javascript:" style="display:'+display+'" onclick="hideLoadingTips()" id="loadingTipsAbtn">取消</a><p id="loadingTipsmsg" style="padding-top:0.5em; color:#fff; position:absolute; left:50%; transform:translateX(-50%);-webkit-transform:translateX(-50%); width:300px;">'+msg+'</p></div><div class="loadingTips_bg" id="loadingTips_bg"></div>'
	$("body").append(temp);
	}else{
		$("#loadingTipsAbtn").css("display",display);
		$("#loadingTipsmsg").html(msg);
	}
	$("#loadingTips,#loadingTips_bg").show(0);
}
function hideLoadingTips(){
	$("#loadingTips,#loadingTips_bg").hide(0);
}

define(function (require) {
    (function () {
        //IOS下弹出输入法时隐藏底部导航
        var u = navigator.userAgent;
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if(isiOS){
            $(".J_footHide").focus(function(){
                $(".headBox + .main").removeClass("mainMarginTop");
                $(".foot,.headBox").hide();
            }).blur(function(){
                $(".foot,.headBox").show();
                $(".headBox + .main").addClass("mainMarginTop");
            });
        };

        //#判断事件,pc端或移动端
        var hasTouch = navigator.userAgent.match(/mobile/i);
        var hasStart;
        if (hasTouch) {
            hasStart = 'touchstart';
        }
        else {
            hasStart = 'click';
        }

        var common = {
            //跳转url
            G: function (url,login, name, callback) {
                if(typeof callback !== "function"){
                    callback = {};
                }

                sys.goUrl(url,login);
                if(typeof name == "string" || typeof user == "string"){
                    jy.writeLog(url,login, name);
                }
            },
            //操作日志
            writeLog: function(url,login, name){
                $.ajax({
                    type: "POST",
                    url: sys.getBaseUrl()+"Log/operationLog.do",
                    asyne: true,
                    dataType: "json",
                    data: {url:url,login:login,name:name},
                    success :function(data){
                    },
                    error: function(){
                    }
                });
            },
            pageAjax: {},
            //封装ajax请求,option配置项,callback回调函数
            ajax: function (option, callback) {

                var DefaultOption = {
                    url: null, //请求url ,not null
                    type: "GET", //请求方式,GET/POST
                    loading: false, //是否显示loadding等待提示
                    dataType: "JSON", //请求返回格式
                    data: {}, //请求参数
                    async: false, //是否异步请求
                    before: $.noop, //发起请求前执行函数
                    complete: $.noop, //成功后执行函数
                    error: $.noop, //出错执行函数
                    loadtext: "加载中...", //loadding等待提示文字
                    callback: sys.consloeAjax
                }
                var option = $.extend(DefaultOption, option);
                var callback = typeof callback == "function" ? callback : option.callback;

                if (!option.url) {
                    console.log("empty url");
                    return;
                }
                $.ajax({
                    url: option.url,
                    dataType: option.dataType,
                    data: option.data,
                    async: option.async,
                    beforeSend: function () {
                        if (option.loading) {
                            sys.showLoading(option.loadtext);
                        }
                        option.before();
                    },
                    success: function (data) {
                        typeof callback == "function" && callback(data);
                    },
                    complete: function () {
                        if (option.loading) {
                            sys.hideLoading();
                        }
                    },
                    error: function (s, e) {
                        option.before(e);
                    }
                });

            },
            initSlider: function () {
                common.swiper = new Swiper('.swiper-container', {
                    pagination: '.swiper-pagination',
                    paginationClickable: true,
                    loop: true,
                    autoplay : 5000,
                    updateOnImagesReady: true,
                    autoplayDisableOnInteraction: false
                });
                //当只有一张轮播图时则不自动播放
                var liObj = $(".swiper-container .swiper-wrapper").find("li");
                var count = liObj.length;
                if(count == 3){
                    var imgHtml = liObj.eq(0).html();
                    $(".swiper-container").html(imgHtml);
                }

            },
            switchTabList: function (elem) {
                $(elem).each(function () {
                    var _this_ = $(this);
                    if (_this_.hasClass("active")) {
                        _this_.next().toggle();
                    }
                    _this_.on(hasStart, function () {
                        _this_.toggleClass("active").next().toggle();

                        var fcnName = _this_.attr("data-ajaxfnc");
                        if (fcnName) {
                            typeof jy["pageAjax"][fcnName] === "function" && jy["pageAjax"][fcnName]();
                            _this_.attr("data-ajaxfnc", "");
                            _this_.attr("data-old-ajaxfnc", fcnName);
                        }

                    });
                });
            },
            //面板间的却换
            navShow: function (nav, show) {
                $(nav).on(hasStart, function () {
                    //获取返回的当前下标值
                    var navId = $(this).index();
                    //所有导航移除样式on
                    $(nav).removeClass("on");
                    //当前导航添加样式on
                    $(this).addClass("on");
                    //隐藏所有的内容层
                    $(show).addClass("hide");
                    //显示当前的内容层
                    $(show).eq(navId).removeClass("hide");
                });
            },
            //初始化load占位
            loadOccInit: function () {
                var templat = '<div class="load-occ"><div class="inner"><div class="spiner"></div><div class="filler"></div><div class="masker"></div></div><div class="inner2"><div class="spiner"></div><div class="filler"></div><div class="masker"></div></div></div>';
                $(".is-load-occ").each(function () {
                    $(this).html(templat);
                });
            },
			//初始化select样式
			initComSelectStyle:function(){
				$(".select-items").each(function(){
					var select = $(this).find("select");
					var showObj = $(this).find(".select-items-text");
					var isSelect = $(this).attr("isSelect");
					showObj.html(select.val());
					select.on("change",function(){
						showObj.html(select.val());
					});
				});
			},
			innertHead:function(name){
				
				var mainDiv=$("body > .main").first();
				
				var divObj="<div class='headBox'>"+//
                              "<div class='main'>"+//
                                "<div class='header'>"+//
                                   "<span class='f-l' onclick='history.go(-1)'>返回</span>"+//
                                   "<em>"+name+"</em>"+//
                                "</div>"+//
                              "</div>"+//
                            "</div>";

                $("body").prepend(divObj).trigger("create");

				mainDiv.addClass("mainMarginTop");
			},
			innertFood:function(ind){
				
				var mainDiv=$("body > .main").last();
				
				var divObj="<div class='foot'>"+//
                             "<div class='main'>"+//
                               "<ul class='footNav'>"+//
						          "<li>"+//
						             "<a href='javascript:' onclick='index();'>"+//
						                 "<i class='bg-cover'></i>"+//
						                 "<p>首页</p>"+//
						             "</a>"+//
						           "</li>"+//
						           "<li>"+//
						              "<a href='javascript:' onclick='handle();'>"+//
						                 "<i class='bg-cover'></i>"+//
						                   "<p>业务办理</p>"+//
						               "</a>"+//
						           "</li>"+//
						           "<li>"+//
						               "<a href='javascript:' onclick='discount();'>"+//
						                  "<i class='bg-cover'></i>"+//
						                    "<p>特惠广场</p>"+//
						               "</a>"+//
						           "</li>"+//
						           "<li>"+//
						              "<a href='javascript:' onclick='person();'>"+//
						                 "<i class='bg-cover'></i>"+//
						                   "<p>我的移动</p>"+//
						               "</a>"+//
						           "</li>"+//
						       "</ul>"+//
						   "</div>"+//
						"</div>";

				$("body").append(divObj).trigger("create");

                $(".footNav li").eq(ind-1).addClass("on");

				mainDiv.addClass("mainMarginBottom");

			},
            popUpShow:function(popMain,leftBtn,rightBtn,callback){
                var poMainText = '<div class="packagePopup overPopu">'+//
                                    '<div class="overPopuTitle">办理</div>'+//
                                    '<div class="overPopuText">'+popMain+'</div>'+//
                                    '<div class="overPopuBtn">'+//
                                        '<a href="javascript:;" class="f-l overSure">'+leftBtn+'</a>'+//
                                        '<a href="javascript:;" class="f-r overClose">'+rightBtn+'</a>'+//
                                    '</div>'+//
                                '</div>';
                var poBgText = '<div class="popupbg"></div>';
                var poBgLenght = $("body").find(".popupbg");
                if(poBgLenght.length > 0){
                    $("body").append(poMainText);
                    poBgLenght.show();
                }else{
                    $("body").append(poMainText + poBgText);
                }
                $(".overClose").on(hasStart, function(){
                    $(".overPopu").hide();
                    $(".popupbg").hide();
                });
                $(".overSure").on(hasStart, function(){
                    callback();
                });
            },
            popUpHide:function(){
                $(".overPopu").hide();
                $(".popupbg").hide();
            },
        	getUserUuid:function(){
        		
        		var uuid="";
        		
        		//app功能
        	    $.ajax({
        			
        			url :sys.getBaseUrl()+"login/getUUID.do",
        			type:"post",
        			dataType:"json",
        	     	cache : false, 
        	    	async : false, 
        			data:'',
        			success :function(data){
        				uuid=data.UUID;
        			  }
        		
        	    });
        	    
        	    return uuid;
        	},
            loadingShow: function(){
                jy.loadingHide();
                var proText = "<div class='searchBox'>" +//
                                    "<div class='searchLoad seaLoadPos'>" +//
                                        "<div class='ball-spin-fade-loader'>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                            "<div></div>" +//
                                        "</div>" +//
                                    "</div>" +//
                                "</div>";
                $('body').append(proText);
            },
            loadingHide: function(){
                $(".searchBox").remove();
            },
            ReMoveUserMsg : function (){
        		var userMsg = ["myPrivilegeMSG","indexMSG", "index4GMSG", "index4GMSG","myBillMSG","myBillMSG","indexCallMSG","indexCallMSG","relativesNetMSG"];
    			for(var i =0;i<userMsg.length;i++){
    				var field=userMsg[i];
    				sys.removeStorage(field);
    				sys.removeStorage(field+"_time");
    				sys.removeStorage(field+"_type");
    			}
            }
        };

        window.jy = common;
    })();

  //单点登录
    !function(){
    var sso = function(){
    	this.init();
    	return this;
    	}
    sso.prototype = {
    	
    	init : function(){
    		
    		var str=location.href;
    		this.channelCode = str.GetValue("channelCode");
    		this.data = str.GetValue("data");
    		
    		//依赖url参数
    		if(this.channelCode==null||this.data==null){
    			return;	
    		}else{
    			
    			this.pointLogin();
    			return;	
    			
    		}

    	},
    	//请求code
    	pointLogin : function(){
    		
    		var _this = this;
    		
    	    sys.showLoading();
    	    
    		$.ajax({
    			type: "POST",
    			url:sys.getBaseUrl() + "login/pointLogin.do",
    			data: {channelCode:_this.channelCode,data:_this.data},
         	    cache : false, 
        	    async : false,   
    			success: function(data){
    				
    	            sys.hideLoading();
    				
    				var msg=eval("("+data+")"); 
    				
    				if(msg.state!='1'){
    					location.href=sys.getBaseUrl()+'new2015/index/login.jsp';
    					return;
    				}else{
    		        	jy.ReMoveUserMsg();
    				}

    			}
    	   });
    	}
    }

    window.ssoObj = new sso();
    }();

});

String.prototype.GetValue= function(para) {
	var reg = new RegExp("(^|&)"+ para +"=([^&]*)(&|$)");
	var r = this.substr(this.indexOf("?")+1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}


function msgTips(title,msg,callback,showbtn){
	$("#msg_fixboxbg,#msg_fixbox").remove();
	
	if(!$("#msg_fixboxbg")[0]){
		var temp_html = '<div class="msg_fixboxbg" id="msg_fixboxbg"  style="display:none"></div><div class="msg_fixbox" id="msg_fixbox" style="display:none"><div class="msg_fixtitle" id="msg_fixtitle"></div><div class="msg_fixcontent" id="msg_fixcontent"></div><div class="msg_btn "><a href="javascript:" class="abtn" id="msg_fixbtn">确定</a></div></div>';
		$("body").append(temp_html);
	}
	var obtn = $("#msg_fixbtn");
	var otitle = $("#msg_fixtitle");
	var omsgcon = $("#msg_fixcontent");
	if(showbtn==false){
		$("#msg_fixbtn").hide(0);
	}else{
		$("#msg_fixbtn").show(0);
	}
	
	otitle.html(title);
	omsgcon.html(msg);
	
	$("#msg_fixbtn").unbind("click").bind("click",function(){
		$("#msg_fixboxbg,#msg_fixbox").hide(0);	
		
		typeof(callback)=="function" && callback();
	});
	$("#msg_fixboxbg,#msg_fixbox").show(0);
}

//*提示函数
function msgConfirm(title,msg,callback,isClick){
	$("#msg_fixboxbg,#msg_fixbox").remove();
	if(!$("#msg_fixboxbg")[0]){
		var temp_html = '<div class="msg_fixboxbg" id="msg_fixboxbg"  style="display:none"></div><div class="msg_fixbox" id="msg_fixbox" style="display:none"><div class="msg_fixtitle" id="msg_fixtitle"></div><div class="msg_fixcontent" id="msg_fixcontent"></div><div class="msg_btn ads_row" style="overflow:hidden;"><div class="ads_c_6 ads-c-48 f-l"><a href="javascript:" class="abtn" id="msg_fixbtn">确认</a></div><div class="ads_c_6 ads-c-48 f-r"><a href="javascript:" class="abtn" id="msg_fixbtn2">取消</a></div></div></div>';
		$("body").append(temp_html);
	}
	var obtn = $("#msg_fixbtn");
	var otitle = $("#msg_fixtitle");
	var omsgcon = $("#msg_fixcontent");
	
	otitle.html(title);
	omsgcon.html(msg);
	if(isClick==false){
		
	}
	
	$("#msg_fixbtn").unbind("click").bind("click",function(){
		$("#msg_fixboxbg,#msg_fixbox").hide(0);	
		
		typeof(callback)=="function" && callback();
	});
	
	$("#msg_fixbtn2").unbind("click").bind("click",function(){
		$("#msg_fixboxbg,#msg_fixbox").hide(0);	
	});
	
	$("#msg_fixboxbg,#msg_fixbox").show(0);
}

//*提示函数
function msgConfirmokno(title,msg,ok,no,callback,isClick){
	$("#msg_fixboxbg,#msg_fixbox").remove();
	if(!$("#msg_fixboxbg")[0]){
		//var temp_html = '<div class="msg_fixboxbg" id="msg_fixboxbg"  style="display:none"></div><div class="msg_fixbox" id="msg_fixbox" style="display:none"><div class="msg_fixtitle" id="msg_fixtitle"></div><div class="msg_fixcontent" id="msg_fixcontent"></div><div class="msg_btn  ads_row"><div class="ads_c_6" style="padding:0 10%"><a href="javascript:" class="abtn" id="msg_fixbtn">确认</a><a href="javascript:" class="abtn" id="msg_fixbtn2">取消</a></div></div></div>';
		var temp_html = '<div class="msg_fixboxbg" id="msg_fixboxbg"  style="display:none"></div><div class="msg_fixbox" id="msg_fixbox" style="display:none"><div class="msg_fixtitle" id="msg_fixtitle"></div><div class="msg_fixcontent" id="msg_fixcontent"></div><div class="overPopuBtn"> <a href="javascript:" class="f-l" id="msg_fixbtn" style="font-size: 1.2em;">确认办理，即享优惠</a> <a href="javascript:" class="f-r" id="msg_fixbtn2" style="font-size: 1.2em;">手气不好，下次再来</a> </div></div>';
		
		
		$("body").append(temp_html);
	}
	
	var obtn = $("#msg_fixbtn");
	var obtn1 = $("#msg_fixbtn2");
	var otitle = $("#msg_fixtitle");
	var omsgcon = $("#msg_fixcontent");
	
	
	otitle.html(title);
	omsgcon.html(msg);
	if(isClick==false){
		
	}
	
	$("#msg_fixbtn").unbind("click").bind("click",function(){
		$("#msg_fixboxbg,#msg_fixbox").hide(0);	
		
		typeof(callback)=="function" && callback();
	});
	
	$("#msg_fixbtn2").unbind("click").bind("click",function(){
		$("#msg_fixboxbg,#msg_fixbox").hide(0);	
	});
	
	$("#msg_fixboxbg,#msg_fixbox").show(0);
}

function person(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/person.jsp",1,'个人中心', '');
	
}

function index(){
	
	jy.G(sys.getBaseUrl()+"new2015/index/index_1.jsp",1,'首页', '');
	
}

function handle(){

	jy.G(sys.getBaseUrl()+"new2015/handle/index_1.jsp",0,'办理首页', '');
	
}

function discount(){

	jy.G(sys.getBaseUrl()+"new2015/discount/index_1.jsp",0,'专享特惠', '');
	
}
