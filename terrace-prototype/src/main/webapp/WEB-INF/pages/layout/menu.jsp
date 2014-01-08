<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${base}/resources/components/jquery/1.7.2/jquery.js"></script>
<script type="text/javascript" src="${base }/resources/components/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<%-- <script type="text/javascript" src="${base }/resources/components/assets/js/bootstrap-tag.min.js"></script> --%>
<script src="${base }/resources/components/aceui/js/typeahead-bs2.min.js"></script>
<script type="text/javascript" src="${base }/resources/components/jquery-ext/src/jquery.sdw.menu.js"></script>
<script type="text/javascript">
		$(function() {
			$("#navMenu").navMenu('${base}/layout!menu', 'icon-desktop');
		});
</script>
</head>
<body>
	<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					/* try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					} */
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->
				<div id="navMenu"></div>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					/* try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					} */
				</script>
			</div>
</body>
</html>