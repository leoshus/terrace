/**
 * 添加或替换url里的参数
 */
function AddOrReplaceUrlParameter(url,name,value){
	var index = url.indexOf("?");
	if(index == -1){
		url = url + "?" + name +"=" + value;
	}else{
		var arrays = url.split("?");
		var params = arrays[1].split("&");
		var str = "";
		var flag = false;
		for (var i = 0; i < params.length;i++){
			str = params[i].split("=")[0];
			if(str == name){
				params[i] = name + "=" + value;
				flag = true;
				break;
			}
		}
		if(!flag){
			url = url + "&" + name + "=" + value;
		}else{
			url = arrays[0] + "?";
			for(var i = 0;i < params.length;i++){
				if(i > 0){
					url = url + "&";
				}
				url = url + params[i];
			}
		}
	}
	return url;
}

function toggle_multi_select(target,sign){
	var select = $(target);
	if(select.attr("multiple") == true || select.attr("multiple") == "multiple"){
		select.attr("multiple", false);
		if(sign){
			$(sign).find("i").removeClass("icon-minus-sign");
			$(sign).find("i").toggleClass("icon-plus-sign");
		}
		
	}else{
		select.attr("multiple", true);
		if(sign){
			$(sign).find("i").removeClass("icon-plus-sign");
			$(sign).find("i").toggleClass("icon-minus-sign");
		}
	}
}
function fileDownload(id) {
	if (id && id != "") {
		window.open(WEB_ROOT + "/sys/attachment-file!download?id=" + id);
	}
}
(function($){
	$.assert = function(flag,message){
		if(!flag){
			if(window.console){
				console.debug(message);
			}else{
				alert(message);
			}
		}
	};
	$.assertNotBlank = function(target,message){
		if(target == undefined || $.trim(target) == ""){
			$.assert(flase,message);
			return;
		}
	};
	
	$.fn.ajaxGetUrl = function(url){
		$.assertNotBlank(url);
		var ajaxData = $(this);
		$.get(url,null,function(data,flag){
			ajaxData.html(data);
			ajaxData.attr("url",url);
		});
	};
})(jQuery);
(function(b) {
	b.fn.refreshClosest = function(f) {
		var e = b(this).closest("div.ui-tabs");
		if (e.size() == 1) {
			e.tabs("reload");
			return
		}
		var d = b(this).closest("div.ui-dialog-content");
		if (d.size() == 1) {
			var c = d.attr("_url");
			if (f) {
				b.each(f, function(g, h) {
					c = AddOrReplaceUrlParameter(c, g, h)
				})
			}
			d.load(c);
			d.attr("_url", c);
			return
		}
		self.location.reload()
	};
	b.fn.refreshClosestDialog = function(e) {
		var d = b(this).closest("div.ui-dialog-content");
		if (d.size() == 1) {
			var c = d.attr("_url");
			if (e) {
				b.each(e, function(f, g) {
					c = AddOrReplaceUrlParameter(c, f, g)
				})
			}
			d.load(c);
			d.attr("_url", c);
			return
		}
	};
	b.resetCalculateGridWidth = function() {
		b("div.ui-jqgrid:visible").each(
				function() {
					var c = b(this).parent().width();
					if (c) {
						b("#" + b(this).attr("id").substr(5)).jqGrid(
								"setGridWidth", c - 2, false)
					}
				})
	};
	b.jAlert = function(d, c) {
		c = b.extend({
			modal : true,
			title : "提示",
			overlay_opacity : 0.4,
			type : "warning"
		}, c);
		b.Zebra_Dialog(d, c)
	};
	b.ajaxPostURL = function(c) {
		if (!c.url) {
			alert("[url] option is required.")
		}
		var d = jQuery.extend({
			data : {},
			publishMessage : true
		}, c);
		if (d.confirm) {
			if (!confirm(d.confirm)) {
				return false
			}
		}
		b.post(encodeURI(d.url), d.data, function(e, f) {
			if (!e.type) {
				publishErrorContentPage(e);
				return
			}
			if (e.type == "success") {
				if (d.publishMessage) {
					top.$.publishMessage(e.message)
				}
				if (d.successCallback) {
					d.successCallback(e)
				}
			} else {
				top.$.publishError(e.message);
				if (d.failureCallback) {
					d.failureCallback(e)
				}
			}
		}, "json")
	};
	var a = {
		dir1 : "up",
		dir2 : "left",
		firstpos1 : 25,
		firstpos2 : 25
	};
	b.publishMessage = function(c) {
		b.pnotify({
			title : "操作提示",
			text : c,
			addclass : "stack-bottomright",
			type : "info",
			closer_hover : false,
			history : false,
			width : "400px",
			delay : 2000,
			styling : "jqueryui",
			stack : a
		})
	};
	b.publishError = function(e, c) {
		var d = "错误提示";
		if (c) {
			d = d
					+ "&nbsp;<font size='-2'><a href='javascript:showDetail()'>查看异常明细</a></font>"
		}
		b.pnotify({
			title : d,
			text : e,
			addclass : "stack-bottomright",
			history : true,
			type : "error",
			closer_hover : false,
			sticker_hover : false,
			history : false,
			width : "600px",
			delay : 30000,
			styling : "jqueryui",
			stack : a
		})
	};
	b.toggleAdvanceSearch = function(c) {
		var e = b(c);
		var d = e.find("i");
		if (d.hasClass("icon-chevron-down")) {
			d.removeClass("icon-chevron-down");
			d.addClass("icon-chevron-up");
			e.closest("form").find("div.advanceSearchDiv").show()
		} else {
			d.removeClass("icon-chevron-up");
			d.addClass("icon-chevron-down");
			e.closest("form").find("div.advanceSearchDiv").hide()
		}
	};
	b.fn.findFormElements = function() {
		return this
				.find(":input[type='text'], :input[type='password'], :input[type='radio'], :input[type='checkbox'], :input[type='file'], select , textarea")
	}
})(jQuery);
