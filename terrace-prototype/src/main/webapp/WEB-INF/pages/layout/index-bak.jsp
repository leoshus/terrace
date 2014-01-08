<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <%@ include file="/common/script-header.jsp" %>
<%@ include file="/common/script-footer.jsp" %> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Terrace</title>
<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link rel="stylesheet" href="${base }/resources/components/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${base }/resources/components/assets/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-xtabpanel/2.0/xTabPanel.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${base }/resources/components/assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- fonts <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" /> -->

<!-- ace styles -->
<link rel="stylesheet" href="${base }/resources/components/assets/css/ace.min.css" />
<link rel="stylesheet" href="${base }/resources/components/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${base }/resources/components/assets/css/ace-skins.min.css" />

<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-layout/1.2.0/css/jquery.layout.css?_=${buildVersion}">
<!--[if lte IE 8]>
  <link rel="stylesheet" href="${base }/resources/components/assets/css/ace-ie.min.css" />
<![endif]-->

<script src="${base }/resources/components/assets/js/ace-extra.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="${base }/resources/components/assets/js/html5shiv.js"></script>
	<script src="${base }/resources/components/assets/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	
	<div class="index-panel">
		<div class="tabs-bar tabs-fix-top" id="mainContent">
		<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			

			<div class="main-content" id="navTab"></div>

			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; Choose Skin</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-navbar" /> <label class="lbl"
							for="ace-settings-navbar"> Fixed Navbar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-sidebar" /> <label class="lbl"
							for="ace-settings-sidebar"> Fixed Sidebar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-breadcrumbs" /> <label class="lbl"
							for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-rtl" /> <label class="lbl"
							for="ace-settings-rtl"> Right To Left (rtl)</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-add-container" /> <label class="lbl"
							for="ace-settings-add-container"> Inside <b>.container</b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
		</div>
	<!-- /.main-container -->
	
	<div class="ui-layout-north index-header">
        <%@include file="header.jsp"%>
    </div>

    <div class="ui-layout-south">
        <%@include file="footer.jsp"%>
    </div>
    <div class="ui-layout-west menu">
        <%@include file="menu.jsp"%>
    </div>
</div>
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
    
    
	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<!-- <![endif]-->

<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${base }/resources/components/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${base }/resources/components/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
	<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${base }/resources/components/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="${base }/resources/components/assets/js/bootstrap.min.js"></script>
	<script
		src="${base }/resources/components/assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="${base }/resources/components/assets/js/excanvas.min.js"></script>
		<![endif]-->

	<script
		src="${base }/resources/components/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script
		src="${base }/resources/components/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script
		src="${base }/resources/components/assets/js/jquery.slimscroll.min.js"></script>
	<script
		src="${base }/resources/components/assets/js/jquery.easy-pie-chart.min.js"></script>
	<script
		src="${base }/resources/components/assets/js/jquery.sparkline.min.js"></script>


	<!-- ace scripts -->

	<script
		src="${base }/resources/components/assets/js/ace-elements.min.js"></script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		$(function() {
			$("#navMenu").navMenu('${base}/layout!menu', 'icon-desktop');

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
			
			$.layouts.initLayout();
		});
	</script>
	<%-- <script type="text/javascript" src="${base }/resources/components/jquery-ext/jquery.sdw.menu.min.js"></script> --%>
	<script type="text/javascript" src="${base }/resources/components/jquery-ext/src/jquery.sdw.menu.js"></script>
	<script type="text/javascript" src="${base }/resources/components/jquery-ext/jquery.sdw.terrace.js"></script>
	
	<script src="${base}/resources/components/jquery-layout/1.2.0/js/jquery.layout-1.2.0.js?_=${buildVersion}"></script>

	<script type="text/javascript"
		src="${base }/resources/components/jquery-xtabpanel/2.0/xTabPanel.js"></script>
	<script type="text/javascript">
		var WEB_ROOT = '${pageContext.request.contextPath}';
	</script>
</body>
</html>
