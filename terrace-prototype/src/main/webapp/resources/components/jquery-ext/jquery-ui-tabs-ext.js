(function(a) {
	a
			.widget(
					"ui.tabs",
					a.ui.tabs,
					{
						options : {
							cache : true,
							heightStyle : "content",
							beforeLoad : function(b, c) {
								if (c.tab.data("loaded")) {
									b.preventDefault();
									return
								}
								c.jqXHR.success(function() {
									c.tab.data("loaded", true)
								})
							},
							activate : function(f, g) {
								var c = g.newTab.find("a.ui-tabs-anchor").attr(
										"href");
								var d = c.substring(0, 1) == "#";
								var e = g.newTab.parent().find(
										"span.refresh-active");
								if (!d) {
									e.show()
								} else {
									e.hide()
								}
								var b = g.oldTab.attr("_activeCounter");
								if (b == undefined) {
									b = 1
								}
								g.newTab
										.attr("_activeCounter", (Number(b) + 1))
							}
						},
						_create : function() {
							var b = this;
							b._super();
							var c = b.element.find("ul > li");
							if (c.size() > 0) {
								b.element.show()
							} else {
								b.element.hide()
							}
							var d = a('<span style="float:right;" class="refresh-active hide"><i class="icon-refresh icon-white" style="cursor:pointer"></i></span>');
							this.tablist.append(d);
							d.click(function() {
								b.reload()
							});
							b.element.delegate(
									"ul.ui-tabs-nav > span > i.icon-refresh",
									"click", function(f) {
										var e = a(this).closest("li");
										if (e.hasClass("ui-tabs-active")) {
											b.reload()
										}
										f.stopPropagation()
									});
							b.element.delegate("ul.ui-tabs-nav > li.closable",
									"dblclick", function(e) {
										b.remove();
										e.stopPropagation()
									});
							b.element
									.delegate(
											"ul.ui-tabs-nav > li.closable > a.closable-icon",
											"click", function(f) {
												var e = a(this).parent().index(
														"li");
												b.remove(e);
												f.stopPropagation()
											});
							if (this.options.disableItemsExcludeFirst == "true") {
								for (i = 1; i < this.tabs.length; i++) {
									this.disable(i)
								}
							}
						},
						remove : function(c) {
							var d = this;
							if (c == undefined) {
								c = d.options.active
							}
							var g = d.element.children(".ui-tabs-nav").find(
									"li:eq(" + c + ")");
							var f = g.attr("aria-controls");
							g.remove();
							a("#" + f).remove();
							var b = 0;
							d.element.children(".ui-tabs-nav").find("li").each(
									function(j, k) {
										var h = a(k).attr("_activeCounter");
										if (h) {
											if (Number(h) > b) {
												b = Number(h);
												c = j
											}
										}
									});
							d._activate(c);
							d.refresh();
							var e = d.element.find("ul > li");
							if (e.size() > 0) {
								d.element.show()
							} else {
								d.element.hide()
							}
						},
						add : function(c, e, d) {
							var b = this._getIndex(c);
							if (b >= 0) {
								this._activate(b);
								return
							}
							a(
									"<li class='closable' title='双击可关闭当前项'><a href='"
											+ c
											+ "'><span>"
											+ e
											+ "</span></a><a href='#' style='padding:1px;cursor:pointer' class='closable-icon'><span class='ui-icon ui-icon-close'></span></a></li>")
									.appendTo(
											this.element
													.find(" > ul.ui-tabs-nav"));
							this.refresh();
							this.tablist.find("li.refresh-active").show();
							if (d == undefined || d) {
								this._activate(this.tabs.length - 1)
							}
						},
						reload : function(e, b) {
							if (e == undefined) {
								e = {}
							}
							if (b == undefined) {
								b = this.options.active
							}
							var f = this.tabs.eq(b);
							var c = f.find("a.ui-tabs-anchor");
							var d = c.attr("href");
							f.data("loaded", false);
							if (e.parameters) {
								a.each(e.parameters, function(g, h) {
									d = AddOrReplaceUrlParameter(d, g, h)
								});
								c.attr("href", d)
							}
							if (e.title) {
								c.find(" > span").html(e.title)
							}
							this.load(this.options.active)
						}
					})
})(jQuery);