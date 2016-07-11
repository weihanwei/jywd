//
// Usage:
//		tfAlert( message, [title, bottontext,callback] )
//		tfConfirm( message, [title, bottontext,callback] )
//		tfPrompt( message, [value, title, bottontext,callback] )
// 
//
define(['jquery'],function(require, exports, module) {
(function($) {
	
	$.alerts = {
		
		verticalOffset: -100,                // 垂直偏移量从中心的对话框屏幕,以像素为单位
		horizontalOffset: 0,                // 水平偏移的对话框从中心屏幕,像素/
		repositionOnResize: true,           // 对话框在窗口内调整大小
		overlayOpacity: 0.6,                // 透明度水平的覆盖
		overlayColor: '#000',               // 底色的叠加
		cancelButton: '取消', 				// 文本的取消按钮
		dialogClass: null,                  // 如果指定,这个类将被应用到所有的对话框
		
		// 公共方法
		
		alert: function(message, title,btntxt, callback) {
			if( title == null ) title = 'Alert';
			if(btntxt == null) btntxt = '确定';
			$.alerts._show(btntxt,title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title,btntxt, callback) {
			if( title == null ) title = 'Confirm';
			if(btntxt == null) btntxt = '确定';
			$.alerts._show(btntxt,title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirmCancle: function(message, title,btntxt, callback) {
			if( title == null ) title = 'Confirm';
			if(btntxt == null) btntxt = '确定';
			$.alerts._show(btntxt,title, message, null, 'confirmCancle', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title,btntxt, callback) {
			if( title == null ) title = 'Prompt';
			if(btntxt == null) btntxt = '确定';
			$.alerts._show(btntxt,title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		// 私有方法
		
		_show: function(btntxt,title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
	
			var pos = 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><a id="popup_ok" class="alertbtn">' + btntxt + '</a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><a id="popup_cancel" class="conf btncl">' + $.alerts.cancelButton + '</a><a id="popup_ok" class="conf">' + btntxt + '</a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						//if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><a id="popup_cancel" class="conf btncl">' + $.alerts.cancelButton + '</a><a id="popup_ok" class="conf">' + btntxt + '</a></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						//if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
				case 'confirmCancle':
					$("#popup_message").after('<div id="popup_panel"><a id="popup_cancel" class="conf btncl">' + $.alerts.cancelButton + '</a><a id="popup_ok" class="conf">' + btntxt + '</a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			//var top = - ($("#popup_container").outerHeight() / 2);
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			
			$("#popup_container").css({
				marginTop: -$("#popup_container").outerHeight() / 2 + 'px',
				left: left + 'px'
			});
			
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	module.exports = {
		// Shortuct functions
		tfAlert : function(message, title, btntxt, callback) {
			$.alerts.alert(message, title, btntxt, callback);
		},
		
		tfConfirm : function(message, title, btntxt, callback) {
			$.alerts.confirm(message, title, btntxt, callback);
		},
		
		tfConfirmCancle : function(message, title, btntxt, callback) {
			$.alerts.confirmCancle(message, title, btntxt, callback);
		},
			
		tfPrompt : function(message, value, title, btntxt, callback) {
			$.alerts.prompt(message, value, title, btntxt, callback);
		}
	}
	
})(jQuery);
});