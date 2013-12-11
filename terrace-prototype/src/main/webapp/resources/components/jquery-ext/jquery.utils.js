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
	
	$.fn.refreshClosest = function(data){
		var tab = $(this).closest("div.ui-tabs");
		if(tab.size() == 1){
			tab.tabs("reload");
			return;
		}
		var content = $(this).closest("div.ui-dialog-content");
		if(content.size() == 1){
			var url = $.attr("_url");
			if(data){
				$.each(data,function(name,value){
					url = AddOrReplaceUrlParameter(url,name,value);
				});
			}
			content.load(url);
			content.attr("_url",url);
			return ;
		}
		self.location.reload();
	};
	
	$.fn.refreshClosestDialog = function(data){
		var content = $(this).closest("div.ui-dialog-content");
		if(content.size() == 1){
			var url = content.attr("_url");
			if(data){
				$.each(data,function(name,value){
					url = AddOrReplaceUrlParameter(url,name,value);
				});
			}
			content.load(url);
			content.attr("_url",url);
			return ;
		}
	};
	
	$.resetCalculateGridWidth = function(){
		$("div.ui-jqgrid:visible").each(function(){
			var width = $(this).parent().width();
			if(width){
				$("#" + $(this).attr("id").substr(5)).jqGrid("setGridWidth",width - 2,false);
			}
		});
	};
	
	$.jAlert = function(message,options){
		options = $.extend({
			modal : true,
			title : "提示",
			overlay_opacity : 0.4,
			type : "warning"
		},options);
		$.Zebra_Dialog(message,options);
	};
	
	$.ajaxPostURL = function(options){
		if(!options.url){
			alert("[url] option is required.");
		}
		var opt = jQuery.extend({
			data : {},
			publishMessage : true
		},options);
		if(opt.confirm){
			if(!confirm(opt.confirm)){
				return false;
			}
		}
		
		$.post(encodeURI(opt.url),opt.data,function(resp,flag){
			if(!resp.type){
				publishErrorContentPage(resp);
				return;
			}
			if(resp.type == "success"){
				if(opt.pulishMessage){
					top.$.publishMessage(resp.message);
				}
				if(opt.successCallback){
					opt.successCallback(resp);
				}
			}else{
				top.$.publishError(resp.message);
				if(opt.failureCallback){
					opt.failureCallback(resp);
				}
			}
		},"json");
	};
	
	var arrays = {
			dir1 : "up",
			dir2 : "left",
			firstpos1 : 25,
			firstpos2 : 25
	};
	$.publishMessage = function(message){
		$.pnotify({
			title : "操作提示",
			text : message,
			addclass : "stack-bottomright",
			type : "info",
			closer_hover : false,
			history : false,
			width : "400px",
			delay : 2000,
			styling : "jqueryui",
			stack : arrays
		});
	};
	
	$.publishError = function(message,flag){
		var str = "错误提示";
		if(flag){
			str = str + + "&nbsp;<font size='-2'><a href='javascript:showDetail()'>查看异常明细</a></font>";
		}
		$.pnotify({
			title : str,
			text : message,
			addclass : "stack-bottomright",
			history : true,
			type : "error",
			closer_hover : false,
			sticker_hover : false,
			history : false,
			width : "600px",
			delay : 30000,
			styling : "jqueryui",
			stack : arrays
		});
	};
	$.toggleAdvanceSearch = function(data){
		var target = $(data);
		var i = target.find("i");
		if(i.hasClass("icon-chevron-down")){
			i.removeClass("icon-chevron-down");
			i.addClass("icon-chevron-up");
			target.closest("form").find("div.advanceSearchDiv").show();
		}else{
			i.removeClass("icon-chevron-up");
			i.addClass("icon-chevron-down");
			target.closest("form").find("div.advanceSearchDiv").hide();
		}
	};
	$.fn.findFormElements = function(){
		return this.find(":input[type='text'], :input[type='password'], :input[type='radio'], :input[type='checkbox'], :input[type='file'], select , textarea");
	};
})(jQuery);
