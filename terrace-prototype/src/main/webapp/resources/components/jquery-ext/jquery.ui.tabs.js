/**
 * 拓展jQuery UI tabs
 * 1.【select( event, ui )】select 类型：tabsselect ，点击选项卡时触发该事件。
 * 2.【*load( event, ui )】load类型：tabsload ，一个远程（ajax）选项卡的内容被加载完成后触发该事件。
 * 3.【show( event, ui )】show类型：tabsshow ，当选项卡显示后触发该事件。
 * 4.【add( event, ui )】add类型：tabsadd ，当一个选项卡被添加后触发该事件。
 * 5.【remove( event, ui )】remove 类型tabsremove ，当一个选项卡被删除后触发该事件。
 * 6.【enable( event, ui )】enable 类型tabsenable ，当一个选项卡可用时触发该事件。
 * 7.【disable( event, ui )】disable类型tabsdisable，当一个选项卡不可用时触发该事件。
 * 8.【*activate( event, ui )】disable类型tabsactivate，当一个选项卡被激活时触发该事件。
 * 9.【*beforeActivate( event, ui )】disable类型tabsbeforeactivate。
 * 10.【*beforeLoad( event, ui )】disable类型tabsbeforeload，仅适用于AJAX请求之前。
 * 11.【*create( event, ui )】 disable类型tabscreate，当一个选项卡被创建时触发该事件。
 */
(function($){
	$.Widget(
			"ui.tabs",
			$.ui.tabs,
			{
				options : {
					cache : true,
					heightStyle : "content",
					beforeLoad : function(event,ui){
						if(ui.tab.data("loaded")){
							event.preventDefault();
							return ;
						}
						ui.jqXHR.success(function(){
							ui.tab.data("loaded",true);
						});
					},
					activate : function(event,ui){
						var hrefs = ui.newTab.find("a.ui-tabs-anchor").attr("href");
						var flag = hrefs.substring(0,1) == "#";
						var refreshActive = ui.newTab.parent().find("span.refresh-active");
						if(!flag){
							refreshActive.show();
						}else{
							refreshActive.hide();
						}
						var activeCounter = ui.oldTab.attr("_activeCounter");
						if(activeCounter == undefined){
							activeCounter = 1;
						}
						ui.newTab.attr("_activeCounter",(Number(activeCounter) + 1));
					}
				},
				_create : function(){
					this._super();
					var li = this.element.find("ul > li");
					if(li.size() > 0){
						this.element.show();
					}else{
						this.element.hide();
					}
					var target = $('<span style="float:right;" class="refresh-active hide"><i class="icon-refresh icon-white" style="cursor:pointer"></i></span>');
					this.tablist.append(target);
					target.click(function(){
						this.reload();
					});
					this.element.delegate("ul.ui-tabs-nav > span > i.icon-refresh","click",
							function(event){
						var lis = $(this).closest("li");
						if(lis.hasClass("ui-tabs-active")){
							this.reload();
						}
						event.stopPropagation();
					});
					this.element.delegate("ul.ui-tabs-nav > li.closable","dblclick",
							function(event){
						event.remove();
						event.stopPropagation();
					});
					this.element.delegate("ul.ui-tabs-nav > li.closable > a.closable-icon","click",
							function(event){
						var liNum = $(this).parent().index("li");
						this.remove(liNum);
						event.stopPropagation();
					});
					if(this.options.disableItemsExcludeFirst == "true"){
						for(var i = 1 ; i < this.tabs.length; i++){
							this.disable(i);
						}
					}
				},
				remove : function(event){
					if(event == undefined){
						event = this.options.activate;
					}
					var li = this.element.children(".ui-tabs-nav").find("li:eq(" + event + ")");
					var controls = li.attr("aria-controls");
					li.remove();
					$("#" + controls).remove();
					var num = 0;
					this.element.children(".ui-tabs-nav").find("li").each(function(k,v){
						var activeCounter = $(v).attr("_activeCounter");
						if(activeCounter){
							if(Number(activeCounter) > num){
								num = Number(activeCounter);
								event = k;
							}
						}
					});
					this._activate(event);
					this.refresh();
					var lis = this.element.find("ul > li");
					if(lis.size() > 0){
						this.element.show();
					}else{
						this.element.hide();
					}
				},
				add : function(url,data,event){//FIXME parameters
					var indexs = this._getIndex(url);
					if(indexs >= 0){
						this._activate(indexs);
						return ;
					}
					$("<li class='closable' title='双击可关闭当前项'><a href='" + url + "'><span>" + data 
							+ "</span></a><a href='#' style='padding:1px;cursor:pointer' class='closable-icon'><span class='ui-icon ui-icon-close'></span></a></li>")
							.appendTo(this.element.find(" > ul.ui-tabs-nav"));
					this.refresh();
					this.tablist.find("li.refresh-active").show();
					if(event == undefined || event){
						this._activate(this.tabs.length - 1);
					}
				},
				reload : function(event,ui){
					if(event == undefined){
						event = {};
					}
					if(ui == undefined){
						ui = this.options.active;
					}
					var tabs = this.tabs.eq(ui);
					var aLink = tabs.find("a.ui-tabs-anchor");
					var href = anchor.attr("href");
					tabs.data("loaded",false);
					if(event.parameters){
						$.each(event.parameters,function(name,param){
							href = AddOrReplaceUrlParameter(href,name,param);
						});
						aLink.attr("href",href);
					}
					if(event.title){
						href.find(" > span").html(event.title);
					}
					this.load(this.options.active);
				}
			}
	);	
})(jQuery);