<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(function() {
		//$("#menuTabs").tabs();
		tabsInit("#menuTabs");
	});
	function tabsInit(target, options) {
		var settings = jQuery.extend({
			cache : true
		}, options);
		$(target).tabs(settings);
	}
</script>
</head>
<body>
	<div id="menuTabs">
		<ul>
			<li><a href="${base}/sys/menu!create"> <span>基本信息</span>
			</a></li>
		</ul>
	</div>
</body>
</html>
