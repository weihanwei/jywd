define(function (require, exports, module) {
    function init() {
        var sys = {
        		getWXAPPID: function (){
//                   return 'wx46e693cb4ef86d4d';//测试
                   return 'wx9bde70f51e1a2f17';//移动给的
             //      return 'wx29998f8e2e8ac02a';//2016-2-16张志强给的
                },
        		//跳转url,login=1是登陆后才能跳转
        		goUrl: function (url, login, flash) {

                //请求唯一标识，作为日志记录id
                var time = "Request_" + new Date().getTime();

                //是否允许下拉刷新
                if (typeof(flash) == "undefined" || flash == "") {
                    flash = "true";
                }

                if (login == 1) {
                	
                    //如果需要登陆，则判断登陆状态才能跳转
                    if (sys.isLogin()==true) {
                    	
                    	window.location.href = url;
                    	
                    } else {
                    	
                    	window.location.href = sys.getBaseUrl()+"new2015/index/login.jsp";
                    }
                } else {
                	
                	window.location.href = url;
                	
                }
            },
            isLogin: function (){
            	
            	var flag=false;
            	
               $.ajax({
            	   url:sys.getBaseUrl()+"/login/isLogin.do",
            	   type: "post",
            	   cache : false, 
            	   async : false, 
                   dataType: "json",
                   success: function (data) {

                	  if(data.islogin=="1"){
                		  flag=true;
                	  }else{
                		  flag=false;
                	  }
                   }
               });
               
               return flag;
              
            },
            //输出一级页面头部
            Indexheader: function (type) {

                var temp_head = "";
                var temp_head2 = "";
                temp_head = '<span class="icon_login" onClick="sys.goUrl(\'' + sys.getBaseUrl() + 'new2015/index/person.html\',1)" ></span>';
                temp_head2 = '<span class="icon_msg" onClick="sys.goUrl(\'' + sys.getBaseUrl() + 'new2015/msg/index_1.html\',1)" ></span>';
                temp_head = '<a href="javascript:" class="icon_back" onclick="sys.goJavascriptBack()"><b></b></a>';
                var temp_head2 = '<div class="headerBar ads_row"><div class="ads_c_3 hleft">' + temp_head + '</div><div class="ads_c_6"><div class="pagetitle">' + document.title + '</div></div><div class="ads_c_3 hright"></div></div><div class="headerBarHeight"></div>';

                $(".main").prepend(temp_head2);

            },
            isObjEmpty: function (objstr) {
                //是否为空
                if ((typeof objstr == "undefined") || objstr == "" || objstr == "null")    return true;
                return false;
            },
            getPhoneContacts: function () {
                if ((typeof jsb == "undefined" )) {
                    return "";
                } else {
                    return jsb.getPhoneContacts();
                }
            },
            getBaseUrl: function () {
                //返回带http://xxx.xxx.xx.xx:xxx/yyyy/
                return "http://" + window.location.host + "/jywd/";
                var jstr = sys.getJsb();
                if (jstr == null)  return "http://" + window.location.host + "/jywd/";
                var jn = JSON.parse(jstr);
                return jn.url;
            },
            //json字符串转对象
            strToObject: function (str) {
                return JSON.parse(str);
            },
            //对象转json字符串
            objectToStr: function (obj) {
                return JSON.stringify(obj);
            },
            //url参数
            getHttpParam: function (name) {

                var url = window.location.href;
                return url.GetValue(name);
            },
            //设cookie值
            setCookie: function (key, value) {
                sys.setStorage(key, value);
            },
            //取cookie
            getCookie: function (key) {
                return sys.getStorage(key);
            },
            //删除cookie
            removeCookie: function (key) {
            },
            //本地存储
            setStorage: function (key, value) {
                var storage = window.localStorage;
                if (storage) {
                } else {
                    sys.logMsg("getStorage", "window.localStorage is null");
                    return;
                }

                storage.setItem(key, value);
            },
            //删除存储
            removeStorage: function (key) {
                var storage = window.localStorage;
                if (storage) {
                } else {
                    sys.logMsg("getStorage", "window.localStorage is null");
                    return;
                }
                storage.removeItem(key);
            },
            //读本地存储
            getStorage: function (key) {
                var storage = window.localStorage;
                if (storage) {
                } else {
                    sys.logMsg("getStorage", "window.localStorage is null");
                    return;
                }
                return storage.getItem(key);
            },
            //---------------------------本地化存储-----------------------------//
            //根html id设值
            setAssignment: function (id, value) {
                var tagName = $("#" + id)[0].tagName.toUpperCase();
                if (tagName == "IMG") {
                    $("#" + id).attr("src", value);
                } else if (tagName == "SELECT" || tagName == "INPUT") {
                    $("#" + id).val(value);
                } else {
                    $("#" + id).html(value);
                }
            },
            //修改A标签
            setAssignmentAUrl: function (id, url) {
                $("#" + id).attr("href", url);
            },
            //隐藏元素
            setNone: function (id) {
                $("#" + id).css("display", "none");
            },
            setReadonly: function (id) {
                //不可编辑
                $("#" + id).attr("readOnly", true);
            },
            setReadonlyNode: function (id) {
                //可编辑
                $("#" + id).attr("readOnly", false);
            },
            //显示元素
            setDisplay: function (id) {
                $("#" + id).css("display", "");
            },
            //打开页面
            openUrl: function (url) {
                var timstamp = new Date().getTime();
                var nurl = sys.openUrlArg(url, "_t", timstamp);

                if (typeof window.jsb != "undefined") {
                    //window.jsb.addGobackCount();
                }

                window.location.replace(nurl);
            },
            goBack: function () {
                if (typeof jsb == "undefined") {
                    history.back(-1);
                } else {
                    $.BridgeGoBack();
                }
            },
            //--------------------------首页 end------------------------------//
            //是否为空
            isEmpty: function (vid, str) {
                var ise = $.trim($("#" + vid).val()) != "";
                if (!ise) {
                    return false;
                }
                return true;
            },
            strDecode: function (str) {
                //对字符串进行解码
                return decodeURIComponent(str);
            },
            strEncode: function (str) {
                //对字符串进行编码
                return encodeURIComponent(str);
            },
            //将数字转为对应的单,取整
            cov: function (i) {
                return i + "";
            },//type=1 跳到成为会员 type=2到个人 信息

            //返回https://
            getHttps: function () {
                return "https://";
            },
            //返回http://
            getHttp: function () {
                return "http://";
            },
            //是否微信
            is_weixn: function () {
                var ua = navigator.userAgent.toLowerCase();
                if (ua.match(/MicroMessenger/i) == "micromessenger") {
                    return true;
                } else {
                    return false;
                }
            },
            goJavascriptBack: function () {
                history.go(-1);
            },
            //显示loadding
            showLoading: function (loadText) {
                var ltext = '<div class="common-load-layer" id="common-load-layer" style="opacity:1">' +//
                                '<div class="searchLoad loadMarginT">' +//
                                    '<div class="ball-spin-fade-loader">' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '<div></div>' +//
                                        '</div>' +//
                                '</div>' +//
                                '<div class="common-load-text" id="common-load-text">加载中...</div>' +//
                            '</div>';
                if (!$("#common-load-layer")[0]) {
                    $("body").append(ltext);
                }
                $("#common-load-text").html(loadText);
                //$("#common-load-layer").css("opacity", 1);
            },
            hideLoading: function () {
                var lay = $("#common-load-layer");
                lay.css("display", "none");
            },
            //接口调试
            consloeAjax: function (data) {
                try {
                    console.log(JSON.stringify(data));
                } catch (e) {
                    console.log(data);
                }
            }
        };
        window.sys = sys;
        ;
        (function ($) {
            var b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
                a256 = '',
                r64 = [256],
                r256 = [256],
                i = 0;
            var UTF8 = {
                /**
                 * Encode multi-byte Unicode string into utf-8 multiple single-byte characters
                 * (BMP / basic multilingual plane only)
                 *
                 * Chars in range U+0080 - U+07FF are encoded in 2 chars, U+0800 - U+FFFF in 3 chars
                 *
                 * @param {String} strUni Unicode string to be encoded as UTF-8
                 * @returns {String} encoded string
                 */
                encode: function (strUni) {
                    // use regular expressions & String.replace callback function for better efficiency
                    // than procedural approaches
                    var strUtf = strUni.replace(/[\u0080-\u07ff]/g, // U+0080 - U+07FF => 2 bytes 110yyyyy, 10zzzzzz
                        function (c) {
                            var cc = c.charCodeAt(0);
                            return String.fromCharCode(0xc0 | cc >> 6, 0x80 | cc & 0x3f);
                        })
                        .replace(/[\u0800-\uffff]/g, // U+0800 - U+FFFF => 3 bytes 1110xxxx, 10yyyyyy, 10zzzzzz
                        function (c) {
                            var cc = c.charCodeAt(0);
                            return String.fromCharCode(0xe0 | cc >> 12, 0x80 | cc >> 6 & 0x3F, 0x80 | cc & 0x3f);
                        });
                    return strUtf;
                },
                /**
                 * Decode utf-8 encoded string back into multi-byte Unicode characters
                 *
                 * @param {String} strUtf UTF-8 string to be decoded back to Unicode
                 * @returns {String} decoded string
                 */
                decode: function (strUtf) {
                    // note: decode 3-byte chars first as decoded 2-byte strings could appear to be 3-byte char!
                    var strUni = strUtf.replace(/[\u00e0-\u00ef][\u0080-\u00bf][\u0080-\u00bf]/g, // 3-byte chars
                        function (c) { // (note parentheses for precence)
                            var cc = ((c.charCodeAt(0) & 0x0f) << 12) | ((c.charCodeAt(1) & 0x3f) << 6) | (c.charCodeAt(2) & 0x3f);
                            return String.fromCharCode(cc);
                        })
                        .replace(/[\u00c0-\u00df][\u0080-\u00bf]/g, // 2-byte chars
                        function (c) { // (note parentheses for precence)
                            var cc = (c.charCodeAt(0) & 0x1f) << 6 | c.charCodeAt(1) & 0x3f;
                            return String.fromCharCode(cc);
                        });
                    return strUni;
                }
            };
            while (i < 256) {
                var c = String.fromCharCode(i);
                a256 += c;
                r256[i] = i;
                r64[i] = b64.indexOf(c);
                ++i;
            }
            function code(s, discard, alpha, beta, w1, w2) {
                s = String(s);
                var buffer = 0,
                    i = 0,
                    length = s.length,
                    result = '',
                    bitsInBuffer = 0;
                while (i < length) {
                    var c = s.charCodeAt(i);
                    c = c < 256 ? alpha[c] : -1;
                    buffer = (buffer << w1) + c;
                    bitsInBuffer += w1;
                    while (bitsInBuffer >= w2) {
                        bitsInBuffer -= w2;
                        var tmp = buffer >> bitsInBuffer;
                        result += beta.charAt(tmp);
                        buffer ^= tmp << bitsInBuffer;
                    }
                    ++i;
                }
                if (!discard && bitsInBuffer > 0) result += beta.charAt(buffer << (w2 - bitsInBuffer));
                return result;
            }

            var Plugin = $.base64 = function (dir, input, encode) {
                return input ? Plugin[dir](input, encode) : dir ? null : this;
            };
            Plugin.btoa = Plugin.encode = function (plain, utf8encode) {
                plain = Plugin.raw === false || Plugin.utf8encode || utf8encode ? UTF8.encode(plain) : plain;
                plain = code(plain, false, r256, b64, 8, 6);
                return plain + '===='.slice((plain.length % 4) || 4);
            };
            Plugin.atob = Plugin.decode = function (coded, utf8decode) {
                coded = String(coded).split('=');
                var i = coded.length;
                do {
                    --i;
                    coded[i] = code(coded[i], true, r64, a256, 6, 8);
                } while (i > 0);
                coded = coded.join('');
                return Plugin.raw === false || Plugin.utf8decode || utf8decode ? UTF8.decode(coded) : coded;
            };
        }(jQuery));
    }

    exports.init = init;
});