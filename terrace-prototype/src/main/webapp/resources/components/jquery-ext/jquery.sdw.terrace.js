/**
 * 
 */
$.terrace = {
		  /**
	     * 当前显示的模态窗口队列
	     */
	    _modalDialogQueue:null,
	    /**
	     * 模态窗口
	     * @title 标题
	     * @param url
	     * @param settings
	     */
	    modalDialog : function(title, url, settings) {

	        $.terrace.waiting();
	        var defaultSettings = {
	            title : title,
	            closeText : "关闭",
	            closeOnEscape:false,
	            height:300,
	            width:600,
	            modal:true,
	            noTitle : false,
	            close: function() {
	                $(this).closest(".ui-dialog").remove();
	            },
	            _close : function(modal) {
	                $(modal).dialog("close");
	                if($.terrace._modalDialogQueue.length > 0) {
	                    $.terrace._modalDialogQueue.pop();
	                }
	            },
	            buttons:{
	                '确定': function() {
	                    if(settings.ok) {
	                        if(settings.ok($(this))) {
	                            settings._close(this);
	                        }
	                    } else {
	                        settings._close(this);
	                    }
	                    if(settings.callback) {
	                        settings.callback();
	                    }
	                },
	                '关闭': function () {
	                    settings._close(this);
	                    if(settings.callback) {
	                        settings.callback();
	                    }
	                }
	            }
	        };
	        if(!settings) {
	            settings = {};
	        }
	        settings = $.extend(true, {}, defaultSettings, settings);

	        if(!settings.ok) {
	            delete settings.buttons['确定'];
	        }

	        $.ajax({
	            url: url,
	            headers: { table:true }
	        }).done(function (data) {
	                $.terrace.waitingOver();
	                var div = $("<div></div>").append(data);
	                var dialog = div.dialog(settings)
	                    .closest(".ui-dialog").data("url", url).removeClass("ui-widget-content")
	                    .find(".ui-dialog-content ").removeClass("ui-widget-content").focus();

	                if(settings.noTitle) {
	                    dialog.closest(".ui-dialog").find(".ui-dialog-titlebar").addClass("no-title");
	                }
	                dialog.closest(".ui-dialog").focus();
	                if(!$.terrace._modalDialogQueue) {
	                    $.terrace._modalDialogQueue = new Array();
	                }
	                $.terrace._modalDialogQueue.push(dialog);
	                $.table.initTable(div.find(".table"));
	            });
	    },
	    waiting : function(message, isSmall) {
	        if(!message) {
	            message = "装载中...";
	        }

	        message = '<img src="resources/images/loading.gif" '+ (isSmall ? "width='20px'" : "") +'/> ' + message;
	        if(!isSmall) {
	            message = "<h4>"+message+"</h4>";
	        }
	        $.blockUI({
	            fadeIn: 700,
	            fadeOut: 700,
	            showOverlay: false,
	            css: {
	                border: 'none',
	                padding: '15px',
	                backgroundColor: '#eee',
	                '-webkit-border-radius': '10px',
	                '-moz-border-radius': '10px',
	                opacity:1,
	                color: '#000',
	                width: isSmall ? "40%" : "30%"

	            },
	            message: message
	        });
	    },
	    waitingOver: function () {
	        $.unblockUI();
	    },
		initCalendar : function() {

	        var date = new Date();
	        var d = date.getDate();
	        var m = date.getMonth();
	        var y = date.getFullYear();

	        var calendar = $('#calendar').fullCalendar({
	            header: {
	                left: 'prev,next today',
	                center: 'title',
	                right: 'month,agendaWeek,agendaDay'
	            },
	            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	            monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	            dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	            dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	            today: ["今天"],
	            buttonText: {
	            	 today: '本月',
	            	 month: '月',
	            	 week: '周',
	            	 day: '日',
	            	 prev: '上一月',
	            	 next: '下一月'
	            	 },
	            events: "sys/calendar!load",
	            eventDrop: function(event, delta) {
	                moveCalendar(event);
	            },
	            eventClick: function(event, delta) {
	                viewCalendar(event);
	            },
	            loading: function(bool) {
	                if (bool) $('#loading').show();
	                else $('#loading').hide();
	            },
	            editable: true,
	            selectable: true,
	            selectHelper: true,
	            select: function(start, end, allDay) {
	                openNewCalendarForm(start, end);
	                calendar.fullCalendar('unselect');
	            }
	        });

	        $('span.fc-button-prev').before('<span class="fc-button fc-button-add fc-state-default fc-corner-left fc-corner-right">新增</span>');

	        $(".fc-button-add").click(function() {
	            openNewCalendarForm();
	        });

	        function openNewCalendarForm(start, end) {
	            var url =  "sys/calendar!create";
	            if(start) {
	                start = $.fullCalendar.formatDate(start, "yyyy-MM-dd HH:mm:ss");
	                end = $.fullCalendar.formatDate(end, "yyyy-MM-dd HH:mm:ss");
	                url = url + "?start=" + start + "&end=" + end;
	            }
	            $.terrace.modalDialog("新增提醒事项", url, {
	                width:370,
	                height:430,
	                ok : function(modal) {

	                    var form = modal.find("#editForm");
	                    if(!form.validationEngine('validate')) {
	                        return false;
	                    }
	                    var url = "sys/calendar!create";
	                    $.post(url, form.serialize(), function() {
	                        calendar.fullCalendar("refetchEvents");
	                    });

	                    return true;
	                }
	            });
	        }

	        function moveCalendar(event) {
	            var url = "sys/calendar/move";
	            var id = event.id;
	            var start = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd HH:mm:ss");
	            var end = $.fullCalendar.formatDate(event.end, "yyyy-MM-dd HH:mm:ss");
	            url = url + "?id=" + id;
	            url = url + "&start=" + start + "&end=" + end;

	            $.post(url, function() {
	                calendar.fullCalendar("refetchEvents");
	            });
	        }

	        function viewCalendar(event) {
	            var url = "sys/calendar/view/" + event.id;
	            $.terrace.modalDialog("查看提醒事项", url, {
	                width:370,
	                height:250,
	                noTitle : false,
	                okBtn : false,
	                closeBtn : false
	            });
	        }
	        $("body").on("click", ".btn-delete-calendar", function() {
	            var $this = $(this);
	            $.terrace.confirm({
	                title : '确认删除提醒事项吗？',
	                message : '确认删除提醒事项吗？',
	                ok : function() {
	                    var url = "sys/calendar/delete?id=" + $this.data("id");
	                    $.post(url, function() {
	                        calendar.fullCalendar("refetchEvents");
	                        $.terrace.closeModalDialog();
	                    });
	                }
	            });

	        });
	    }
	    
};
/**
 * jQuery.layout
 */
$.layouts = {
		initLayout: function () {
			$(".index-panel").layout({
	            name : "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
		            // options.defaults apply to ALL PANES - but overridden by pane-specific settings
		            ,
		            defaults : {
		                size : "auto",
		                minSize : 50,
		                paneClass : "pane" // default = 'ui-layout-pane'
		                ,
		                resizerClass : "resizer" // default = 'ui-layout-resizer'
		                ,
		                togglerClass : "toggler" // default = 'ui-layout-toggler'
		                ,
		                buttonClass : "button" // default = 'ui-layout-button'
		                ,
		                contentSelector : ".content" // inner div to auto-size so only it scrolls, not the entire pane!
		                ,
		                contentIgnoreSelector : "span" // 'paneSelector' for content to 'ignore' when measuring room for content
		                ,
		                togglerLength_open : 35 // WIDTH of toggler on north/south edges - HEIGHT on east/west edges
		                ,
		                togglerLength_closed : 35 // "100%" OR -1 = full height
		                ,
		                hideTogglerOnSlide : true // hide the toggler when pane is 'slid open'
		                ,
		                togglerTip_open : "Close This Pane",
		                togglerTip_closed : "Open This Pane",
		                resizerTip : "Resize This Pane",
		                fxName : "slide" // none, slide, drop, scale
		                ,
		                fxSpeed : "slow" // slow, normal, fast, 1000, nnn
		            },
		            north : {
		                spacing_open : 5 // cosmetic spacing
		                ,
		                size : 31,
		                minSize : 25,
		                maxSize : 120,
		                togglerAlign_open : "right" // top/left, bottom/right, center, OR...
		                ,
		                togglerAlign_closed : "right" // 1 => nn = offset from top/left, -1 => -nn == offset from bottom/right
		                ,
		                resizable : false,
		                slidable : false,
		                fxName : "none"
		            },
		            south : {
		                maxSize : 18,
		                minSize : 18,
		                spacing_closed : 0 // HIDE resizer & toggler when 'closed'
		                ,
		                spacing_open : 1 // cosmetic spacing
		                ,
		                togglerLength_closed : -1 // "100%" OR -1 = full width of pane
		                ,
		                slidable : false // REFERENCE - cannot slide if spacing_closed = 0
		                ,
		                initHidden : false,
		                resizable : false,
		                fxName : "none"
		            },
		            west : {
		                size : 200,
		                spacing_closed : 21 // wider space when closed
		                ,
		                togglerLength_closed : 21 // make toggler 'square' - 21x21
		                ,
		                togglerAlign_closed : "top" // align to top of resizer
		                ,
		                togglerLength_open : 0 // NONE - using custom togglers INSIDE west-pane
		                ,
		                togglerTip_open : "Close West Pane",
		                togglerTip_closed : "Open West Pane",
		                resizerTip_open : "Resize West Pane",
		                slideTrigger_open : "mouseover" // default
		                ,
		                initClosed : false,
		                onresize_end : function() {
		                    $("#menuContentDiv").height($("#menuContentDiv").parent("div").height() - 28);
		                    $("#menuAccordion").accordion("refresh");
		                }
		            },
		            east : {
		                size : 250,
		                spacing_closed : 0 // wider space when closed
		                ,
		                togglerLength_closed : 0 // make toggler 'square' - 21x21
		                ,
		                togglerAlign_closed : "top" // align to top of resizer
		                ,
		                togglerLength_open : 0 // NONE - using custom togglers INSIDE east-pane
		                ,
		                togglerTip_open : "Close East Pane",
		                togglerTip_closed : "Open East Pane",
		                resizerTip_open : "Resize East Pane",
		                fxName : "none",
		                fxSpeed : "normal",
		                initClosed : true
		            },
		            center : {
		                paneSelector : "#mainContent" // sample: use an ID to select pane instead of a class
		                ,
		                onresize_end : function() {
		                    $(window).trigger('resize.tabpanel');
		                }
		            }
		        });
		}
	};

/**
 * FIXME js表格
 */
$.table = {

	    /**
	     * 初始化表格：全选/反选 排序
	     * @param table
	     */
	    initTable: function (table) {
	        if(!table || !table.length || table.attr("initialized") == "true") {
	            return;
	        }

	        table.attr("initialized", "true");

	        $.table.initSort(table);
	        $.table.initSearchForm(table);
	        if(table.is(".move-table")) {
	            $.movable.initMoveableTable(table);
	        }

	        //初始化table里的a标签
	        $.table.initTableBtn(table);
	        //初始化删除和修改按钮
	        $.table.initDeleteSelected(table);
	        $.table.initUpdateSelected(table);
	        $.table.initCreate(table);

	        //初始化checkbox
	        $.table.initCheckbox(table);
	        //初始化 按钮的状态
	        $.table.changeBtnState(table);


	    },
	    initCheckbox: function(table) {
	        var activeClass = "active";
	        //初始化表格中checkbox 点击单元格选中
	        table.find("td.check").each(function () {
	            var checkbox = $(this).find(":checkbox,:radio");
	            checkbox.off("click").on("click", function (event) {
	                var checked = checkbox.is(":checked");
	                if(!checked) {
	                    checkbox.closest("tr").removeClass(activeClass);
	                } else {
	                    checkbox.closest("tr").addClass(activeClass);
	                }
	                $.table.changeBtnState(table);
	                event.stopPropagation();
	            });
	            $(this).closest("tr").off("click").on("click", function (event) {
	                var checked = checkbox.is(":checked");
	                if(checked) {
	                    checkbox.closest("tr").removeClass(activeClass);
	                } else {
	                    checkbox.closest("tr").addClass(activeClass);
	                }
	                checkbox.prop("checked", !checked);
	                $.table.changeBtnState(table);
	            });
	        });
	        //初始化全选反选
	        table.find(".check-all").off("click").on("click", function () {
	            var checkAll = $(this);
	            if(checkAll.text() == '全选') {
	                checkAll.text("取消");
	                table.find("td.check :checkbox").prop("checked", true).closest("tr").addClass(activeClass);
	            } else {
	                checkAll.text("全选");
	                table.find("td.check :checkbox").prop("checked", false).closest("tr").removeClass(activeClass);
	            }
	            $.table.changeBtnState(table);
	        });
	        table.find(".reverse-all").off("click").on("click", function () {
	            table.find("td.check :checkbox").each(function () {
	                var checkbox = $(this);
	                var checked = checkbox.is(":checked");
	                if(checked) {
	                    checkbox.closest("tr").removeClass(activeClass);
	                } else {
	                    checkbox.closest("tr").addClass(activeClass);
	                }
	                checkbox.prop("checked", !checked);
	                $.table.changeBtnState(table);
	            });
	        });
	    },
	    changeBtnState : function(table) {
	        var hasChecked = table.find("td.check :checkbox:checked").length;
	        var btns = table.closest(".panel").find(".tool .btn").not(".no-disabled");
	        if(hasChecked) {
	            btns.removeClass("disabled");
	            btns.each(function() {
	                var btn = $(this);
	                var href = btn.data("btn-state-href");
	                if(href) {
	                    btn.attr("href", href);
	                }
	            });
	        } else {
	            btns.addClass("disabled");
	            btns.each(function() {
	                var btn = $(this);
	                var href = btn.attr("href");
	                if(href) {
	                    btn.data("btn-state-href", href);
	                    btn.removeAttr("href");
	                }
	            });
	        }
	    },
	    /**
	     * 初始化对应的查询表单
	     * @param table
	     */
	    initSearchForm : function(table) {
	        var id = $(table).attr("id");
	        var searchForm = table.closest("[data-table='" + id + "']").find(".search-form");

	        if(!searchForm.length) {
	            return;
	        }

	        searchForm.find(".btn").addClass("no-disabled");

	        searchForm.find(".btn-clear-search").click(function() {

	            if (table.data("async") == true) {
	                var resetBtn = searchForm.find("input[type='reset']");
	                if(!resetBtn.length) {
	                    searchForm.append("<input type='reset' style='display:none'>");
	                    resetBtn = searchForm.find("input[type='reset']");
	                }
	                resetBtn.click();
	            }
	            turnSearch(table, searchForm, true);
	        });

	        var turnSearch = function(table, searchForm, isSearchAll) {
	            var url = $.table.tableURL(table);
	            url = $.table.removeSearchParam(url, searchForm);
	            url = $.table.removePageParam(url, searchForm);
	            if(!isSearchAll) {
	                if(url.indexOf("?") == -1) {
	                    url = url + "?";
	                } else {
	                    url = url + "&";
	                }
	                url = url + searchForm.serialize();
	            }
	            $.table.reloadTable(table, url, null);
	        }

	        searchForm.off("submit").on("submit", function() {
	            turnSearch(table, searchForm, false);
	            return false;
	        });

	        if(searchForm.is("[data-change-search=true]")) {
	            searchForm.find(":input:not(:button,:submit,:reset)").off("change").on("change", function(e) {
	                // avoid double search issue, when you click search button after change any input
	                searchForm.off("submit").on("submit", function() {
	                    return false;
	                });
	                turnSearch(table, searchForm, false);
	            });
	        }

	        searchForm.find(".btn-search-all").off("click").on("click", function() {
	            turnSearch(table, searchForm, true);
	            return false;
	        });


	    },
	    /**
	     * 初始化sort
	     * @param table
	     */
	    initSort: function (table) {
	        if (!table.length) {
	            return;
	        }

	        //初始化排序
	        var prefix = $.table.getPrefix(table);

	        var sortURL = $.table.tableURL(table);

	        var sortBtnTemplate = '<div class="sort"><a class="{sort-icon}" href="#" title="排序"></a></div>';
	        table.find("[sort]").each(function () {
	            var th = $(this);
	            var sortPropertyName = prefix + "sort." + th.attr("sort");
	            var sortBtnStr = null;
	            var matchResult = sortURL.match(new RegExp(sortPropertyName + "=(asc|desc)", "gi"));
	            var order = null;
	            if (matchResult) {
	                order = RegExp.$1;
	                if (order == 'asc') {
	                    sortBtnStr = sortBtnTemplate.replace("{sort-icon}", "sort-hover icon-arrow-up");
	                } else if (order == 'desc') {
	                    sortBtnStr = sortBtnTemplate.replace("{sort-icon}", "sort-hover icon-arrow-down");
	                }
	            }
	            if (sortBtnStr == null) {
	                sortBtnStr = sortBtnTemplate.replace("{sort-icon}", "icon-arrow-down");
	            }
	            th.wrapInner("<div class='sort-title'></div>").append($(sortBtnStr));

	            //当前排序
	            th.prop("order", order);//设置当前的排序 方便可移动表格

	            th.addClass("sort-th").click(function () {
	                sortURL = $.table.tableURL(table);
	                //清空上次排序
	                sortURL = $.table.removeSortParam(sortURL);

	                if (!order) { //asc
	                    order = "asc";
	                } else if (order == "asc") { //desc
	                    order = "desc";
	                } else if (order == "desc") { //none
	                    order = "asc";
	                }

	                if (order) {
	                    sortURL = sortURL + (sortURL.indexOf("?") == -1 ? "?" : "&");
	                    sortURL = sortURL + sortPropertyName + "=" + order;
	                }

	                $.table.reloadTable(table, sortURL, null);
	            });

	        });
	    },
	    /**
	     * 分页
	     * @param pageSize
	     * @param pn
	     * @param child table的子
	     */
	    turnPage: function (pageSize, pn, child) {
	        var table = $(child).closest(".table-pagination").prev("table");
	        if(!table.length) {
	            table = $(child).closest("table");
	        }

	        var pageURL = $.table.tableURL(table);

	        //清空上次分页
	        pageURL = $.table.removePageParam(pageURL);


	        pageURL = pageURL + (pageURL.indexOf("?") == -1 ? "?" : "&");

	        var prefix = $.table.getPrefix(table);
	        pageURL = pageURL + prefix + "page.pn=" + pn;

	        if (pageSize) {
	            pageURL = pageURL + "&" + prefix + "page.size=" + pageSize;
	        }

	        $.table.reloadTable(table, pageURL, null);
	    },
	    /**
	     * 执行跳转
	     * @param table
	     * @param url
	     * @param backURL
	     */
	    reloadTable: function (table, url, backURL) {

	        if(!url) {
	            url = $.table.tableURL(table);
	        }

	        if (!backURL) {
	            backURL = url;
	        }
	        //modalDialog时 把当前url保存下来方便翻页和排序
	        table.closest(".ui-dialog").data("url", backURL);

	        if (table.data("async") == true) {
	            $.app.waiting();

	            var tableId = table.attr("id");
	            var containerId = table.data("async-container");
	            var headers = {};

	            if(!containerId) {//只替换表格时使用
	                headers.table = true;
	            } else {
	                headers.container = true;
	            }

	            $.ajax({
	                url: url,
	                async:true,
	                headers: headers
	            }).done(function (data) {
	                    if (containerId) {//装载到容器
	                        $("#" + containerId).replaceWith(data);
	                    } else {
	                        var pagination = table.next(".table-pagination");
	                        if(pagination.length) {
	                            pagination.remove();
	                        }
	                        table.replaceWith(data);
	                    }

	                    table = $("#" + tableId);
	                    table.data("url", backURL);
	                    $.table.initTable(table);

	                    var callback = table.data("async-callback");
	                    if(callback && window[callback]) {
	                        window[callback](table);
	                    }

	                    $.app.waitingOver();
	                });
	        } else {
	            window.location.href = url;
	        }
	    }
	    ,
	    /**
	     * 获取表格对于的url
	     * @param table
	     * @return {*}
	     */
	    tableURL : function(table) {
	        var $dialog = table.closest(".ui-dialog");

	        var url = table.data("url");
	        if(!url && $dialog.length) {
	            //modalDialog
	            url = $dialog.data("url");
	        }
	        if (!url) {
	            url = window.location.href;
	        }
	        //如果URL中包含锚点（#） 删除
	        if(url.indexOf("#") > 0) {
	            url = url.substring(0, url.indexOf("#"));
	        }

	        return url;
	    },
	    /**
	     *
	     * @param table
	     */
	    encodeTableURL : function(table) {
	        return encodeURIComponent($.table.tableURL(table));
	    }
	    ,
	    /**
	     * 获取传递参数时的前缀
	     * @param table
	     */
	    getPrefix : function(table) {
	        var prefix = table.data("prefix");
	        if (!prefix) {
	            prefix = "";
	        } else {
	            prefix = prefix + "_";
	        }
	        return prefix;
	    }
	    ,
	    removePageParam : function(pageURL) {
	        pageURL = pageURL.replace(/\&\w*page.pn=\d+/gi, '');
	        pageURL = pageURL.replace(/\?\w*page.pn=\d+\&/gi, '?');
	        pageURL = pageURL.replace(/\?\w*page.pn=\d+/gi, '');
	        pageURL = pageURL.replace(/\&\w*page.size=\d+/gi, '');
	        pageURL = pageURL.replace(/\?\w*page.size=\d+\&/gi, '?');
	        pageURL = pageURL.replace(/\?\w*page.size=\d+/gi, '');
	        return pageURL;
	    }
	    ,
	    removeSortParam : function(sortURL) {
	        sortURL = sortURL.replace(/\&\w*sort.*=((asc)|(desc))/gi, '');
	        sortURL = sortURL.replace(/\?\w*sort.*=((asc)|(desc))\&/gi, '?');
	        sortURL = sortURL.replace(/\?\w*sort.*=((asc)|(desc))/gi, '');
	        return sortURL;
	    },
	    removeSearchParam : function(url, form) {
	        $.each(form.serializeArray(), function() {
	            var name = this.name;
	            url = url.replace(new RegExp(name + "=.*?\&","g"), '');
	            url = url.replace(new RegExp("[\&\?]" + name + "=.*$","g"), '');
	        });
	        return url;
	    }
	    ,
	    //格式化url前缀，默认清除url ? 后边的
	    formatUrlPrefix : function(urlPrefix, $table) {

	        if(!urlPrefix) {
	            urlPrefix = $table.data("prefix-url");
	        }

	        if(!urlPrefix && $table && $table.length) {
	            urlPrefix = decodeURIComponent($.table.tableURL($table));
	        }

	        if(!urlPrefix) {
	            urlPrefix = currentURL;
	        }

	        if(urlPrefix.indexOf("?") >= 0) {
	            return urlPrefix.substr(0, urlPrefix.indexOf("?"));
	        }
	        return urlPrefix;
	    },

	    initDeleteSelected : function($table, urlPrefix) {
	        if(!$table || !$table.length) {
	            return;
	        }

	        var $btn = $table.closest("[data-table='" + $table.attr("id") + "']").find(".btn-delete:not(.btn-custom)");
	        urlPrefix = $.table.formatUrlPrefix(urlPrefix, $table);
	        $btn.off("click").on("click", function() {
	            var checkbox = $.table.getAllSelectedCheckbox($table);
	            if(!checkbox.length)  return;

	            $.app.confirm({
	                message: "确定删除选择的数据吗？",
	                ok : function() {
	                    window.location.href =
	                        urlPrefix + "/batch/delete?" + checkbox.serialize() + "&BackURL=" + $.table.encodeTableURL($table);
	                }
	            });
	        });
	    }
	    ,
	    initUpdateSelected : function($table, urlPrefix) {
	        if(!$table || !$table.length) {
	            return;
	        }
	        var $btn = $table.closest("[data-table='" + $table.attr("id") + "']").find(".btn-update:not(.btn-custom)");
	        urlPrefix = $.table.formatUrlPrefix(urlPrefix, $table);
	        $btn.off("click").on("click", function() {
	            var checkbox = $.table.getFirstSelectedCheckbox($table);
	            if(!checkbox.length)  return;
	            var id = checkbox.val();
	            window.location.href = urlPrefix + "/" + id + "/update?BackURL=" + $.table.encodeTableURL($table);
	        });
	    },
	    initCreate : function($table, urlPrefix) {
	        if(!$table || !$table.length) {
	            return;
	        }
	        var $btn = $table.closest("[data-table='" + $table.attr("id") + "']").find(".btn-create");

	        $btn.addClass("no-disabled");

	        $btn.off("click").on("click", function() {
	            var url =  $.table.formatUrlPrefix(urlPrefix, $table) + "/create";
	            if($btn.attr("href")) {
	                url = $btn.attr("href");
	            }
	            window.location.href = url + (url.indexOf("?") == -1 ? "?" : "&") + "BackURL=" + $.table.encodeTableURL($table);
	            return false;
	        });
	    },
	    initTableBtn : function($table, urlPrefix) {
	        if(!$table || !$table.length) {
	            return;
	        }
	        $table.closest("[data-table=" + $table.attr("id") + "]").find(".btn").not(".btn-custom,.btn-create,.btn-update,.btn-delete").each(function() {
	            var $btn = $(this);
	            var url = $btn.attr("href");
	            if(!url || url.indexOf("#") == 0 || url.indexOf("javascript:") == 0) {//没有url就不处理了
	                return;
	            }
	            $btn.off("click").on("click", function() {
	                window.location.href = url + (url.indexOf("?") == -1 ? "?" : "&") + "BackURL=" + $.table.encodeTableURL($table);
	                return false;
	            });
	        });

	        urlPrefix = $.table.formatUrlPrefix(urlPrefix, $table);
	        //支持双击编辑
	        if($table.hasClass("table-dblclick-edit")) {
	            $table.children("tbody").children("tr").off("dblclick").on("dblclick", function() {
	                var id = $(this).find(":checkbox[name=ids]").val();
	                window.location.href = urlPrefix + "/" + id + "/update?BackURL=" + $.table.encodeTableURL($table);
	            });
	        }

	    },
	    getFirstSelectedCheckbox :function($table) {
	        var checkbox = $("#table :checkbox:checked:first");
	        if(!checkbox.length) {

	            //表示不选中 不可以用，此时没必要弹窗
	            if($(this).hasClass(".no-disable") == false) {
	                return checkbox;
	            }

	            $.app.alert({
	                message : "请先选择要操作的数据！"
	            });
	        }
	        return checkbox;
	    },
	    getAllSelectedCheckbox :function($table) {
	        var checkbox = $table.find(":checkbox:checked");
	        if(!checkbox.length) {

	            //表示不选中 不可以用，此时没必要弹窗
	            if($(this).hasClass(".no-disable") == false) {
	                return checkbox;
	            }

	            $.app.alert({
	                message : "请先选择要操作的数据！"
	            });
	        }
	        return checkbox;
	    }
	}