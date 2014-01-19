<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/static-version.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var WEB_ROOT = '${pageContext.request.contextPath}';
</script>
<script
	src="${base}/resources/components/jquery/1.7.2/jquery.js?_=${buildVersion}"></script>
<script
	src="${base}/resources/components/jquery-ui/1.10.3/js/jquery-ui-1.10.3.custom.js?_=${buildVersion}"></script>
<!-- jQuery ui themes -->
<%-- <link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-ui/1.10.3/css/start/jquery-ui-1.10.3.custom.min.css?_=${buildVersion}"/>
<link rel="alternate stylesheet" type="text/css" href="${base}/resources/components/jquery-ui/1.10.3/css/ui-lightness/jquery-ui.min.css?_=${buildVersion}"> --%>
<link rel="stylesheet" href="${base}/resources/components/jquery-ui/1.10.3/css/aceui/jquery-ui-1.10.3.full.min.css?_=${buildVersion}" />
<%-- <link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-ui/1.10.3/css/ui-lightness/jquery-ui.min.css?_=${buildVersion}">
<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery-ui/1.10.3/css/ui-lightness/jquery.ui.theme.css?_=${buildVersion}"> --%>

<!-- BootStrap -->
<link rel="stylesheet" type="text/css"
	href="${base}/resources/components/bootstrap/3.0.2/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${base }/resources/components/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<!-- zTree css-->
<link rel="stylesheet" type="text/css"
	href="${base}/resources/components/jquery-ztree/3.5/css/zTreeStyle/zTreeStyle.css?_=${buildVersion}">
<!-- jQuery ext css -->
<link rel="stylesheet" type="text/css"
	href="${base}/resources/components/jquery-ext/jquery-ui-dialog-ext.css?_=${buildVersion}">
<!-- jqGrid -->
<link rel="stylesheet" type="text/css" href="${base}/resources/components/jquery.jqGrid/4.5.2/css/ui.jqgrid.css?_=${buildVersion}"> 