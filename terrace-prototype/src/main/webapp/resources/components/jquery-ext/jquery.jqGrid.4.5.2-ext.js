/**
     * 构造jqGrid表格
     * @param target JQuery对象
     * @param options 定制化参数
     * 组件标准参数：
     *   直接参考jqGrid参数定义：http://www.trirand.com/jqgridwiki/doku.php?id=wiki:jqgriddocs
     * 扩展参数列表：
     *   queryForm：以查询表单构造Grid表格
     *   url：如果没有form对象，则直接以url构造Grid表格
     *   ondblClickEnabledRow：双击行项的回调函数
     *   maxHeight：设定Grid显示的最大高度，超过后则出现滚动条
     *   
     *   jquery multiselect :http://quasipartikel.at/multiselect_next/
     */
(function($){
	$.extend($.ui.multiselect,{
		defaults : {
			sortable : true,
			searchable : true,
			animated : "fast",
			show : "slideDown",
			hide : "slideUp",
			dividerLocation : 0.6,
			nodeComparator : function(a,b){
				var c = a.text(),d = b.text();
				return c == d ? 0 : (c < d ? -1 : 1);
			}
		},
		locale : {
			addAll : "添加全部",
			removeAll : "移除全部",
			itemsCount : "项已选"
		}
	});
	$.extend($.jgrid.defaults,{
		datatype : "json",
		loadonce : false,
		filterToolbar : {},
		ignoreCase : true,
		prmNames :{
			npage : "npage"
		},
		jsonReader : {
			repeatitems : false,
			root : "content",
			total : "totalPages",
			records : "totalElements",
		},
		treeReader : {
			level_field : "extraAttributes.level",
			parent_id_field : "extraAttributes.parent",
			leaf_field : "extraAttributes.isLeaf",
			expanded_field : "extraAttributes.expanded",
			loaded : "extraAttributes.loaded",
			icon_field : "extraAttributes.icon"
		},
		autowidth : true,
		rowNum : 15,
		page : 1,
		altclass : "evennumber",
		height : "auto",
		viewsortcols : [ true, "vertical", true ],
		mtype : "POST",
		rowList : [ 5, 10, 15, 20, 50, 100, 200 ],
		viewrecords : true,
		rownumbers : true,
		gridview : true,
		altRows : true,
		sortable : true,
		multiselect : true,
		multiSort : true,
		forceFit : false,
		shrinkToFit : true,
		sortorder : "desc",
		sortname : "createdDate",
		ajaxSelectOptions : {
			cache : true
		},
		loadError : function(e, f, c, d) {
			alert("表格数据加载处理失败,请尝试刷新或联系管理员!");
		}
	});
	$.extend($.jgrid.search,{
		multipleSearch : true,
		multipleGroup : true,
		width : 700,
		jqModal : false,
		searchOperators : true,
		stringResult : true,
		searchOnEnter : true,
		defaultSearch : "bw",
		operandTitle : "点击选择查询方式",
		operands : {
			eq : "=",
			ne : "!",
			lt : "<",
			le : "<=",
			gt : ">",
			ge : ">=",
			bw : "^",
			bn : "!^",
			"in" : "=",
			ni : "!=",
			ew : "|",
			en : "!@",
			cn : "~",
			nc : "!~",
			nu : "#",
			nn : "!#"
		}
	});
	$.extend($.jgrid.del,{
		serializeDelData : function(data) {
			data.ids = data.id;
			data.id = "";
			return data;
		},
		errorTextFormat : function(data) {
			var text = jQuery.parseJSON(data.responseText);
			return text.message;
		},
		afterComplete : function(data) {
			var arrays = new Array();
			var text = jQuery.parseJSON(data.responseText);
			if (text.type == "success") {
				top.$.publishMessage(text.message);
				arrays[0] = true;
			} else {
				top.$.publishError(text.message);
				arrays[0] = false;
			}
			return arrays;
		},
		ajaxDelOptions : {
			dataType : "json"
		}
	});
	$.fn.tableGrid = function(options){
		this.each(function(){
			if(typeof options === "string"){
				var method = $.jgrid.getMethod(options);
				if(!method){
					throw ("jqGrid ---No such method:"+options);
				}
				var data = $.makeArray(arguments).slice(1);
				return method.apply(this,data);
			}
			if(options.url == undefined && options.queryForm){
				$(options.queryForm).find(":text").each(function(){
					$(this).val($.trim($(this).val()));
				});
				options.url = $(options.queryForm).attr("ation") + "?" + $(options.queryForm).serialize();
			}
			var obj = this;
			var listId = $(this).attr("id");
			options.loadComplete = function(data){
				$(this).closest("div.ui-jqgrid-view").find("tr.ui-search-toolbar th.ui-th-column div").filter(":empty")
					.each(function(){
						$(this).attr("title","双击可快速清空查询条件");
						$(this).dblclick(function(){
							$(obj).trigger("clearToolbar");
						});
					});
			};
			
			if(!obj.tableGrid){
				var object = $(obj);
				options = $.extend({
					filterToolbar : {},
					columnChooser : true,
					exportExcelLocal : true
				},options);
				if(options.pager == undefined){
					options.pager = "#" + object.attr("id") + "Pager";
				}
				if(options.editRow){
					if(!options.ondblClickRow){
						options.ondblClickRow = function(rowid,iRow,iCol,e){
							e.jqGrid("editRow", rowid);
						};
					}
				}
				$.each(options.colModel,function(rowid,iCol){
					var option = {
						fixed : true,
						searchoptions : {
							searchhidden : true,
							sopt : ["bw", "bn", "eq", "ne", "cn","nc", "ew", "en" ],
							buildSelect : function(data){
								var djson = jQuery.parseJSON(data);
								if(djson == null){
									djson = data;
								}
								var optionText = "<select>";
								optionText += "<option value = ''></option>";
								for(var obj in djson){
									obj = obj + "";
									optionText += ("<option value = '" + obj + "'>"+ djson[obj] +"</option>");
								}
								optionText += "</select>";
								return optionText;
							}
						}
					};
					if(iCol.formatter == disabledFormatter || iCol.formatter == booleanFormatter){
						iCol = $.extend(iCol ,{
							width : 60,
							fixed : true,
							stype : "select",
							align : "center"
						});
						option.searchoptions = $.extend(option.searchoptions,{
							value : enumsContainer.booleanLabel
						});
					}
					if(!iCol.width){
						option.fixed = false;
					}
					if(iCol.stype == "select"){
						option.searchoptions = $.extend(option.searchoptions,{
							sopt : ["eq","ne"]
						});
					}
					if(iCol.sorttype == "date"){
						option = $.extend(option,{
							width : 140,
							align : "center"
						});
						option.searchoptions = $.extend(option.searchoptions,{
							sopt : [ "eq", "ne", "ge", "le","gt", "lt" ],
							dataInit : function(data){
								$(data).datepicker();
							}
						});
					}
					if(iCol.sorttype == "number"){
						option = $.extend(option,{
							width : 60,
							align : "center"
						});
						option.searchoptions = $.extend(option.searchoptions,{
							sopt : [ "eq", "ne", "ge", "le","gt", "lt" ]
						});
					}
					iCol.searchoptions = $.extend(option.searchoptions,iCol.searchoptions);
					iCol = $.extend(option,iCol);
					options.colModel[rowid] = iCol;
				});
				object.jqGrid(options);
				object.jqGrid("navGrid",options.pager,{
					edit : false,
					add : false,
					del : options.delRow == undefined ? false : true,
					beforeRefresh : function(){
						$(this).jqGrid("setGridParam",{
							datatype : "json"
						});
					}
				},{},{},{
					reloadAfterSubmit : true,
					url : options.delRow == undefined ? false : options.delRow.url
				},{});
				if(options.addRow){
					options.addRow = $.extend({
						caption : "",
						buttionicon : "ui-icon-plus",
						position : "first",
						title : "添加数据",
						onClickButton : function(){
							if(options.addRow.toTab){
								$(options.addRow.toTab).show();
								$(options.addRow.toTab).tabs("add",options.addRow.url,options.addRow.title);
							}else{
								var tmp = $(this).closest("div.ui-tabs");
								tmp.tabs("add",options.addRow.url,options.addRow.title);
							}
						}
					},options.addRow);
					object.jqGrid("navButtonAdd",options.pager,options.addRow);
				}
				if(options.filterToolbar){
					object.jqGrid("filterToolbar",options.filterToolbar);
				}
				if(options.columnChooser){
					object.jqGrid("navButtonAdd",options.pager,{
						caption : "",
						buttonicon : "ui-icon-battery-2",
						position : "last",
						title : "设定显示列和顺序",
						onClickButton : function(){
							var tmp = object.jqGrid("getGridParam","width");
							object.jqGrid("columnChooser",{
								width : 470,
								done : function(data){
									if(data){
										this.jqGrid("remapColumns",data,true);
										object.jqGrid("setGridWidth",tmp,false);
									}else{
										
									}
								}
							});
						}
					});
				}
				if(options.exportExcelLocal){//导出excel
					object.jqGrid("navButtonAdd",options.pager,{
						caption : "",
						buttonicon : "ui-icon-arrowthickstop-1-s",
						position : "last",
						title : "导出当前显示数据",
						onClickButton : function(){
							object.jqGrid("exportExcelLocal",options.exportExcelLocal);
						}
					});
				}
			}else{
				$(obj).jqGrid("setGridParam",{
					url : options.url,
					datatype : "josn",
					page : 1
				}).trigger("reloadGrid");
			}
		});
	};
	//-----------------------------FIXME--------------------------------
	$.extend($.jgrid,{
		buildButtons : function(data, f, c, g, e){
			str = "";
			$.each(data,function(){
				str += "<a class='btn-icon' href='javascript:void(0)' title='"
					+ this.title
					+ "' onclick=\""
					+ this.onclick
					+ ";event.stopPropagation();\" style='margin:2px'><i class='"
					+ this.icon
					+ "'></i></a>";
			});
			return str;
		},
		buildLink : function(data, f, c, g, e) {
			return '<a href="javascript:void(0)" onclick="'
					+ data.onclick + '">' + data.text + "</a>";
		}
	});
	
	$.jgrid.extend({
		refresh : function(){
			this.each(function(){
				var tmp = this;
				if(!tmp.tableGrid){
					return;
				}
				$(tmp).jqGrid("setGridParam",{
					datatype : "json"
				}).trigger("reloadGrid");
			});			
		},
		delRow : function(data){
			this.each(function(){
				var tmp = this;
				if(!tmp.tableGrid){
					return ;
				}
				$("#" + tmp.p.id + "Pager").find("span.ui-icon-trash:first").parent().click();//------------FIXME
			});
		},
		addRow : function(c){//data
			this.each(function(){
				if(!this.grid){
					return ;
				}
//				$("#" + this.p.id + "Pager").find("span.ui-icon-plus:first").parent().click();//---------------------FIXME
				$("#" + this.p.id + "Pager").find("span.ui-icon.ui-icon-newwin:first").parent().click();
			});
		},
		editRow : function(c,d){
			this.each(function(){
				if(!this.grid || !this.p.editRow){//-------------------------FIXME
					return ;
				}
				var h = $(this).jqGrid("getRowData",c);
				var e = h[this.p.editRow.labelCol];
				var g = $(e).text();
				if($(e).text() != ""){
					e = g;
				}
				if(this.p.editRow.toTab){
					$(this.p.editRow.toTab).show();
					$(this.p.editRow.toTab).tabs("add",this.p.editRow.url + (this.p.editRow.url.indexOf("?") > 0 ? "&" : "?") + "id=" + c,"编辑-"+e);
				}else{
					var f = $(this).closest("div.ui-tabs");
					f.tabs("add",this.p.editRow.url + (this.p.editRow.url.indexOf("?") > 0 ? "&" : "?") + "id=" + c, "编辑-"+ e);
				}
			});
		},
		advSearch : function(c) {
			this.each(function() {
				var d = this;
				if (!d.tableGrid) {
					return
				}
				$("#" + d.p.id + "Pager").find("span.ui-icon-search:first").parent().click();
			});
		},
		search : function(c) {
			this.each(function() {
				var f = this;
				if (!f.tableGrid) {
					return ;
				}
				var d = $(f).jqGrid("getGridParam", "url");
				for ( var e in c) {
					d = AddOrReplaceUrlParameter(d, e, c[e]);
				}
				$(f).jqGrid("setGridParam", {
					url : d,
					page : 1
				}).trigger("reloadGrid");
			});
		},
		exportExcelLocal : function(c) {
			this.each(function() {
				var g = this;
				if (!g.grid) {
					return;
				}
				if (!confirm("确认导出当前页面 " + g.p.caption
						+ " 数据为Excel下载文件？")) {
					return;
				}
				var f = new Array();
				f = $(g).getDataIDs();
				var m = g.p.colModel;
				var p = g.p.colNames;
				var l = "";
				for (k = 0; k < p.length; k++) {
					var n = m[k];
					if (n.hidedlg || n.hidden || n.disableExport) {
						continue;
					}
					l = l + p[k] + "\t";
				}
				l = l + "\n";
				for (i = 0; i < f.length; i++) {
					data = $(g).getRowData(f[i]);
					for (j = 0; j < p.length; j++) {
						var n = m[j];
						if (n.hidedlg || n.hidden || n.disableExport) {
							continue;
						}
						var h = $(data[n.name]).text();
						if (h == "") {
							h = data[n.name];
						}
						if (h == "null" || h == null) {
							h = "";
						}
						l = l + h + "\t";
					}
					l = l + "\n";
				}
				l = l + "\n";
				var d = $(
						'<form method="post" target = "_blank" action="'
								+ WEB_ROOT
								+ '/pub/grid!export"></form>')
						.appendTo($("body"));
				var o = $('<input type="hidden" name="exportDatas"/>')
						.appendTo(d);
				var e = $('<input type="hidden" name="fileName"/>')
						.appendTo(d);
				e.val(g.p.caption + ".xls");
				o.val(l);
				d.submit();
				d.remove();
			});
		},
		refreshRowIndex : function() {
			var c = $(this);
			$.each($(c).jqGrid("getDataIDs"), function(d, e) {
				$(c).find("#" + e).find("input,select").each(
						function() {
							var f = $(this).attr("name");
							$(this).attr(
									"name",
									f.substring(0, f.indexOf("[") + 1)
											+ d
											+ f.substring(f
													.indexOf("]"),
													f.length));
						});
			});
		},
		getAtLeastOneSelectedItem : function(l) {
			var h = $(this);
			var g = jQuery(h).jqGrid("getGridParam", "selarrrow");
			var f = [];
			var e = 0;
			for (var c = 0; c < g.length; c++) {
				var d = $("#jqg_" + jQuery(h).attr("id") + "_" + g[c])
						.is(":disabled");
				if (!d) {
					f[e] = g[c];
					e++;
				}
			}
			if (l) {
				jQuery(h)
						.find("table.jqsubgrid")
						.each(
								function() {
									var o = $(this)
											.jqGrid("getGridParam",
													"selarrrow");
									for (var m = 0; m < o.length; m++) {
										var n = $(
												"#jqg_"
														+ jQuery(this)
																.attr(
																		"id")
														+ "_" + g[m])
												.is(":disabled");
										if (!n) {
											f[e] = o[m];
											e++;
										}
									}
								});
			}
			if (f.length == 0) {
				$.jAlert("请至少选择一条行项目！");
				return false;
			} else {
				return f.join();
			}
		},
		
		getOnlyOneSelectedItem : function() {
			var h = $(this);
			var g = jQuery(h).jqGrid("getGridParam", "selarrrow");
			var f = [];
			var e = 0;
			for (var c = 0; c < g.length; c++) {
				var d = $("#jqg_" + jQuery(h).attr("id") + "_" + g[c])
						.is(":disabled");
				if (!d) {
					f[e] = g[c];
					e++;
				}
			}
			if (f.length == 0) {
				$.jAlert("请选取操作项目！");
				return false;
			} else {
				if (f.length > 1) {
					$.jAlert("只能选择一条操作项目！");
					return false;
				}
				return f.join();
			}
		},
		getSelectedItem : function() {
			var d = $(this);
			var c = jQuery(d).jqGrid("getGridParam", "selarrrow");
			return c.join();
		}
	});
	
	$.RefreshTriggerSourceGrid = function() {
		$(b).jqGrid("refresh");
	};
	$.SetupTriggerSourceGrid = function(c) {
		b = $(c);
	};
	$.triggerGridRowDblClick = function(c) {
		$(c).closest("tr.jqgrow").dblclick();
	}
})(jQuery);

function disabledFormatter(b, a, c) {
	if (b) {
		return '<span class="label label-warning">禁用</span>';
	} else {
		return "";
	}
}
function booleanFormatter(b, a, c) {
	if (b) {
		return "是";
	} else {
		return "否";
	}
}
function eraseCellValueLink(b) {
	var a = $(b);
	if (a.text() != "") {
		return a.text();
	} else {
		return b;
	}
};