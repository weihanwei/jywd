// JavaScript Document
define(function(require) {
	require("./common/locautil").init(),//加载基础工具类
	require("fastclick/fastclick");//加载fastclick插件
	typeof FastClick !="undefined" && FastClick.attach(document.body);
	require("./common/common");//加载公共全局js
	
	
});
//, require("modules/map/main").init()