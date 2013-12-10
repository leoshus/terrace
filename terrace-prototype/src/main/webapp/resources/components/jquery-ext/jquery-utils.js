function AddOrReplaceUrlParameter(f, a, e) {
	var d = f.indexOf("?");
	if (d == -1) {
		f = f + "?" + a + "=" + e
	} else {
		var g = f.split("?");
		var h = g[1].split("&");
		var c = "";
		var b = false;
		for (i = 0; i < h.length; i++) {
			c = h[i].split("=")[0];
			if (c == a) {
				h[i] = a + "=" + e;
				b = true;
				break
			}
		}
		if (!b) {
			f = f + "&" + a + "=" + e
		} else {
			f = g[0] + "?";
			for (i = 0; i < h.length; i++) {
				if (i > 0) {
					f = f + "&"
				}
				f = f + h[i]
			}
		}
	}
	return f
}
function toggle_multi_select(b, a) {
	select = $(b);
	if (select.attr("multiple") == true
			|| select.attr("multiple") == "multiple") {
		select.attr("multiple", false);
		if (a) {
			$(a).find("i").removeClass("icon-minus-sign");
			$(a).find("i").toggleClass("icon-plus-sign")
		}
	} else {
		select.attr("multiple", true);
		if (a) {
			$(a).find("i").removeClass("icon-plus-sign");
			$(a).find("i").toggleClass("icon-minus-sign")
		}
	}
}
function fileDownload(a) {
	if (a && a != "") {
		window.open(WEB_ROOT + "/sys/attachment-file!download?id=" + a)
	}
}
(function(b) {
	b.assert = function(d, c) {
		if (!d) {
			if (window.console) {
				console.debug(c)
			} else {
				alert(c)
			}
		}
	};
	b.assertNotBlank = function(d, c) {
		if (d == undefined || b.trim(d) == "") {
			b.assert(false, c);
			return
		}
	};
	b.fn.ajaxGetUrl = function(d) {
		b.assertNotBlank(d);
		var c = b(this);
		b.get(d, null, function(e, f) {
			c.html(e);
			c.attr("url", d)
		});
		return c
	};
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
String.prototype.startWith = function(b) {
	var a = new RegExp("^" + b);
	return a.test(this)
};
String.prototype.endWith = function(b) {
	var a = new RegExp(b + "$");
	return a.test(this)
};