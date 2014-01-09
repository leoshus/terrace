<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TERRACE</title>
<!-- basic styles -->
<%-- <link rel="stylesheet" href="${base }/resources/components/aceui/css/bootstrap.min.css"/> --%>
<link rel="stylesheet" type="text/css" href="${base}/resources/components/bootstrap/3.0.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${base }/resources/components/aceui/css/font-awesome.min.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${base }/resources/components/aceui/css/ace.min.css" />
<link rel="stylesheet" href="${base }/resources/components/aceui/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${base }/resources/components/aceui/css/ace-skins.min.css" />

<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-xtabpanel/2.0/xTabPanel.css" />
<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-layout/1.2.0/css/jquery.layout.css">
<link class="skincss" rel="stylesheet" type="text/css" title="start" href="${base}/resources/components/jquery-ui/1.10.3/css/start/jquery-ui-1.10.3.custom.min.css"
	media="screen, projection" />
<link class="skincss" rel="alternate stylesheet" type="text/css" title="ui-lightness"
	href="${base}/resources/components/jquery-ui/1.10.3/css/ui-lightness/jquery-ui.min.css" media="screen, projection">
	
<!-- ace scripts -->
<script src="${base}/resources/components/jquery/1.7.2/jquery.js"></script>

<script type="text/javascript" src="${base }/resources/components/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base }/resources/components/jquery-xtabpanel/2.0/xTabPanel.js"></script>
</head>

<body>
	<!-- dropdown-menu -->
	<ul id="tabs-menu" class="dropdown-menu">
        <li><a class="close-current" href="#">关闭</a></li>
        <li><a class="close-others" href="#">关闭其他</a></li>
        <li><a class="close-all" href="#">关闭所有</a></li>
        <li class="divider"></li>
        <li><a class="close-left-all" href="#">关闭当前左边的所有</a></li>
        <li><a class="close-right-all" href="#">关闭当前右边的所有</a></li>
    </ul>
    <!-- /.dropdown-menu -->
<div class="ui-layout-west">
	<%@include file="menu.jsp" %>
</div>

<div class="ui-layout-east">

</div>


<div class="ui-layout-north">
	 <%@include file="header.jsp"%>
</div>


<div class="ui-layout-south">
	
</div>


<div id="mainContent" style="overflow: hidden; margin: 0; padding: 0; width: 100%;">
	<div id="navTab"></div>
</div>

<script src="${base}/resources/components/jquery/1.7.2/jquery.js"></script>
	<!-- inline scripts related to this page -->

	<script type="text/javascript">
	 var outerLayout, innerLayout;
		/*
		*#######################
		* INNER LAYOUT SETTINGS
		*#######################
		*
		* These settings are set in 'list format' - no nested data-structures
		* Default settings are specified with just their name, like: fxName:"slide"
		* Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
		*/
		layoutSettings_Inner = {
			applyDefaultStyles:				true // basic styling for testing & demo purposes
		,	minSize:						20 // TESTING ONLY
		,	spacing_closed:					14
		,	north__spacing_closed:			8
		,	south__spacing_closed:			8
		,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
		,	south__togglerLength_closed:	-1
		,	fxName:							"slide" // do not confuse with "slidable" option!
		,	fxSpeed_open:					1000
		,	fxSpeed_close:					2500
		,	fxSettings_open:				{ easing: "easeInQuint" }
		,	fxSettings_close:				{ easing: "easeOutQuint" }
		,	north__fxName:					"none"
		,	south__fxName:					"drop"
		,	south__fxSpeed_open:			500
		,	south__fxSpeed_close:			1000
		//,	initClosed:						true
		,	center__minWidth:				200
		,	center__minHeight:				200
		};


		/*
		*#######################
		* OUTER LAYOUT SETTINGS
		*#######################
		*
		* This configuration illustrates how extensively the layout can be customized
		* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
		*
		* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
		* All default settings (applied to all panes) go inside the defaults:{} key
		* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
		*/
		var layoutSettings_Outer = {
			name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
			// options.defaults apply to ALL PANES - but overridden by pane-specific settings
		,	defaults: {
				size:					"auto"
			,	minSize:				50
			,	paneClass:				"pane" 		// default = 'ui-layout-pane'
			,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
			,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
			,	buttonClass:			"button"	// default = 'ui-layout-button'
			,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
			,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
			,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
			,	togglerLength_closed:	35			// "100%" OR -1 = full height
			,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
			,	togglerTip_open:		"Close This Pane"
			,	togglerTip_closed:		"Open This Pane"
			,	resizerTip:				"Resize This Pane"
			//	effect defaults - overridden on some panes
			,	fxName:					"slide"		// none, slide, drop, scale
			,	fxSpeed_open:			750
			,	fxSpeed_close:			1500
			,	fxSettings_open:		{ easing: "easeInQuint" }
			,	fxSettings_close:		{ easing: "easeOutQuint" }
		}
		,	north: {
				spacing_open:			1			// cosmetic spacing
			,	togglerLength_open:		0			// HIDE the toggler button
			,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
			,	resizable: 				false
			,	slidable:				false
			//	override default effect
			,	fxName:					"none"
			}
		,	south: {
				maxSize:				200
			,	spacing_closed:			0			// HIDE resizer & toggler when 'closed'
			,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
			,	initClosed:				true
			//	CALLBACK TESTING...
			,	onhide_start:			function () { return confirm("START South pane hide \n\n onhide_start callback \n\n Allow pane to hide?"); }
			,	onhide_end:				function () { alert("END South pane hide \n\n onhide_end callback"); }
			,	onshow_start:			function () { return confirm("START South pane show \n\n onshow_start callback \n\n Allow pane to show?"); }
			,	onshow_end:				function () { alert("END South pane show \n\n onshow_end callback"); }
			,	onopen_start:			function () { return confirm("START South pane open \n\n onopen_start callback \n\n Allow pane to open?"); }
			,	onopen_end:				function () { alert("END South pane open \n\n onopen_end callback"); }
			,	onclose_start:			function () { return confirm("START South pane close \n\n onclose_start callback \n\n Allow pane to close?"); }
			,	onclose_end:			function () { alert("END South pane close \n\n onclose_end callback"); }
			//,	onresize_start:			function () { return confirm("START South pane resize \n\n onresize_start callback \n\n Allow pane to be resized?)"); }
			,	onresize_end:			function () { alert("END South pane resize \n\n onresize_end callback \n\n NOTE: onresize_start event was skipped."); }
			}
		,	west: {
				size:					200
			,	spacing_closed:			21			// wider space when closed
			,	togglerLength_closed:	21			// make toggler 'square' - 21x21
			,	togglerAlign_closed:	"top"		// align to top of resizer
			,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
			,	togglerTip_open:		"Close West Pane"
			,	togglerTip_closed:		"Open West Pane"
			,	resizerTip_open:		"Resize West Pane"
			,	slideTrigger_open:		"click" 	// default
			,	initClosed:				false
			//	add 'bounce' option to default 'slide' effect
			,	fxSettings_open:		{ easing: "easeOutBounce" }
			}
		,	east: {
				size:					250
			,	spacing_closed:			21			// wider space when closed
			,	togglerLength_closed:	21			// make toggler 'square' - 21x21
			,	togglerAlign_closed:	"top"		// align to top of resizer
			,	togglerLength_open:		0 			// NONE - using custom togglers INSIDE east-pane
			,	togglerTip_open:		"Close East Pane"
			,	togglerTip_closed:		"Open East Pane"
			,	resizerTip_open:		"Resize East Pane"
			,	slideTrigger_open:		"mouseover"
			,	initClosed:				true
			//	override default effect, speed, and settings
			,	fxName:					"drop"
			,	fxSpeed:				"normal"
			,	fxSettings:				{ easing: "" } // nullify default easing
			}
		,	center: {
				paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
			,	minWidth:				200
			,	minHeight:				200
			}
		};
	$(function() {
		// create the OUTER LAYOUT
		outerLayout = $("body").layout( layoutSettings_Outer );

		/*******************************
		 ***  CUSTOM LAYOUT BUTTONS  ***
		 *******************************
		 *
		 * Add SPANs to the east/west panes for customer "close" and "pin" buttons
		 *
		 * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
		 * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
		 *
		 * CSS will size and position the spans, as well as set the background-images
		 */

		// BIND events to hard-coded buttons in the NORTH toolbar
		/* outerLayout.addToggleBtn( "#tbarToggleNorth", "north" );
		outerLayout.addOpenBtn( "#tbarOpenSouth", "south" );
		outerLayout.addCloseBtn( "#tbarCloseSouth", "south" );
		outerLayout.addPinBtn( "#tbarPinWest", "west" );
		outerLayout.addPinBtn( "#tbarPinEast", "east" ); */

		// save selector strings to vars so we don't have to repeat it
		// must prefix paneClass with "body > " to target ONLY the outerLayout panes
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		var eastSelector = "body > .ui-layout-east"; // outer-east pane

		 // CREATE SPANs for pin-buttons - using a generic class as identifiers
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		// BIND events to pin-buttons to make them functional
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		outerLayout.addCloseBtn("#east-closer", "east");

		/* Create the INNER LAYOUT - nested inside the 'center pane' of the outer layout
		 * Inner Layout is create by createInnerLayout() function - on demand
		 *
			innerLayout = $("div.pane-center").layout( layoutSettings_Inner );
		 *
		 */

		// DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
		$("a").each(function () {
			var path = document.location.href;
			if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
			if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
		});
		
			tabpanel = new TabPanel(
					{
						id : 'jTabPanel',
						renderTo : "navTab",
						width : 1156,
						height : 550,
						border : true,
						defaultTab : 0,
						maxTab : 12,
						icon: '${base}/resources/components/jquery-xtabpanel/2.0/module_icon.gif',
						items : [ {
							id : 'workspace',
							title : 'Welcome',
							closable : false,
							html : '<iframe id="workspace" name="workspace" src="layout!welcome" width="100%" height="100%" frameborder="0"></iframe>',
							position : {
								a : '0px -110px',
								b : '-22px -110px'
							}
						} ]
					});
			
		//	$.layouts.initLayout();
	});
	</script>
	<%-- <script type="text/javascript" src="${base }/resources/components/jquery-ext/jquery.sdw.menu.min.js"></script> --%>
	<script type="text/javascript" src="${base }/resources/components/jquery-ext/src/jquery.sdw.menu.js"></script>
	<script type="text/javascript" src="${base }/resources/components/jquery-ext/jquery.sdw.terrace.js"></script>
	
	<script src="${base}/resources/components/jquery-layout/1.2.0/js/jquery.layout-1.2.0.js"></script>
	<script src="${base}/resources/components/jquery-ui/1.10.3/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript"
		src="${base }/resources/components/jquery-xtabpanel/2.0/xTabPanel.js"></script>
	<script type="text/javascript">
		var WEB_ROOT = '${pageContext.request.contextPath}';
	</script>
</body>
</html>
