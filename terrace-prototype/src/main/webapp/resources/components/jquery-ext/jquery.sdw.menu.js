/**
 * 
 */
(function($){
	$.fn.navMenu = function(url,flag){
		//var navMenu = $(this);
		var menuTop = $('<ul class="nav nav-list"></ul>');
		//$(this).append('<ul class="nav nav-list"><li class="active"><a href="#"><i class="icon-dashboard"></i><span class="menu-text"> Dashboard </span></a></li>');
		$.getJSON(url,{},function(data){
			//alert(JSON.stringify(data));
			/*$.each(data,function(i){
				//alert(this.title);
				menuTop.append('<li><a href="#"><i class="'+ flag +'"></i><span class="menu-text"> '+ this.name +' </span></a></li>');
			});*/
			$.each(data,function(i){
				var menuTmp = null, menuTmp1=null,menuTmp2=null;
				if(this.children != null && this.children != undefined && this.children != ""){
					 menuTmp = $('<li><a href="#" class="dropdown-toggle"><i class="icon-desktop"></i><span class="menu-text"> '+ this.name+' </span><b class="arrow icon-angle-down"></b></a><ul class="submenu"></ul></li>');
					$.each(this.children,function(i){
						if(this.children != null && this.children != undefined && this.children != ""){
							 menuTmp1 = $('<li><a href="#" class="dropdown-toggle"><i class="icon-double-angle-right"></i>'+ this.name +'<b class="arrow icon-angle-down"></b></a><ul class="submenu"></ul></li>');
							
							$.each(this.children,function(i){
								if(this.children != null && this.children != undefined && this.children != ""){
									 menuTmp2 = $('<li><a href="#" class="dropdown-toggle"><i class="icon-double-angle-right"></i>'+ this.name +'<b class="arrow icon-angle-down"></b></a><ul class="submenu"></ul></li>');
									$.each(this.children,function(i){
										if(this.children != null && this.children != undefined && this.children != ""){
											
										}else{
											menuTmp2.find("ul").append('<li><a href="#"><i class="icon-leaf"></i>'+ this.name +'</a></li>');
											
										}
									});
									menuTmp1.find("ul").append(menuTmp2);
								}else{
									menuTmp1.find("ul").append('<li><a href="#"><i class="icon-leaf"></i>'+ this.name +'</a></li>');
								}
							});
							//alert(menuTmp1.html());
							//alert(menuTmp.find("ul").html());
							menuTmp.find("ul").append(menuTmp1);
						}else{
							menuTmp.find("ul").append('<li><a href="#" class="dropdown-toggle"><i class="icon-double-angle-right"></i>'+ this.name +'<b class="arrow icon-angle-down"></b></a></li>');
						}
					});
					menuTop.append(menuTmp);
				}else{
					menuTop.append('<li><a href="#"><i class="'+ flag +'"></i><span class="menu-text"> '+ this.name +' </span></a></li>');
				}
				
			});
		});
		$(this).append(menuTop);
	};
})(jQuery);
