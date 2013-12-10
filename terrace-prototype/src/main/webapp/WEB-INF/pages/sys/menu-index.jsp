<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/script-header.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#menuTabs").tabs();
	});
</script>
</head>
<body>
		<div class="container">
		<div class="row" id="menuTabs">
				<ul>
					<li><a href="#menuIndexListTab"><span>列表查询</span></a></li>
				</ul>
				<div id="menuIndexListTab">
					<div class="row">
						<div class="toolbar">
							<div class="toolbar-inner">
								<button type="button" class="btn btn-default" id="menuAddBtn">
									<i class="glyphicon glyphicon-plus-sign"></i> 添加
								</button>
								<button type="button" class="btn btn-default" id="menuDeleteBtn">
									<i class="glyphicon glyphicon-trash"></i> 删除
								</button>
								<div class="btn-group-sm pull-right">
									<button type="button" class="btn btn-default" title="展开全部"
										onclick="$('#menuListDiv').find('div.treeclick.tree-plus').each(function(){$(this).click();})">
										<i class="glyphicon glyphicon-plus"></i>
									</button>
									<button type="button" class="btn btn-default" title="收拢全部"
										onclick="$('#menuListDiv').find('div.treeclick.tree-minus').each(function(){$(this).click();})">
										<i class="glyphicon glyphicon-minus"></i>
									</button>
									<!-- <div class="divider-vertical"></div> -->
									<button type="button" class="btn btn-default" title="高级查询"
										onclick="$('#menuListDiv').jqGrid('advSearch');">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<table id="menuListDiv"></table>
						<div id="menuListDivPager"></div>
					</div>
				</div>
		</div>
	</div>
	<%@ include file="/common/script-footer.jsp" %>
	<!-- ${base}/sys/menu!treeGridData -->
	<script type="text/javascript">
		$(function(){
			$("#menuListDiv").tableGrid({
				url : "${base}/sys/menu!treeGridData",
				colNames : ['操作', '代码', '名称', '菜单URL', '类型', '展开标识', '禁用标识', '图标样式', '排序号' ],
				colModel : [{
					name : 'operation',
					align : 'center',
					sortable : false,
					hidedlg : true,
					search : false,
					width : 25,
					formatter : function(cellValue,options,rowdata,action){
						return $.jgrid.buildButtons([{
							title : "编辑",
							icon : "glyphicon glyphicon-pencil",
							onclick : "$('#" + $(this).attr("id") + "').jqGrid('editRow','" + rowdata.id + "')"
						}]);
					}
				},{
					name : 'code',
					align : 'center',
					hidedlg : true,
					width : 80,
					formatter : function(cellValue,options,rowdata,action){
						return $.jgrid.buildLink({
							text : cellValue,
							onclick : "$.popupViewDialog('${base}/sys/menu!viewTabs?id="+ options.rowId +"')"
						});
					}
				},{
                    name : 'title',
                    align : 'left'
                }, {
                    name : 'url',
                    align : 'left'
                }, {
                    name : 'type.title',
                   // index : 'type',
                    width : 80,
                    align : 'center'
                    //stype : 'select',
                    //searchoptions : {
                      // value : enumsContainer['menuTypeEnum']
                   //}
                }, {
                    name : 'initOpen'
                    //formatter : booleanFormatter
                }, {
                    name : 'disabled'
                    //formatter : booleanFormatter
                }, {
                    name : 'style',
                    hidden : true,
                    align : 'left'
                }, {
                    name : 'orderRank',
                    width : 60,
                    sorttype : 'number'
                } ],
                cmTemplate : {
                	sortable : false
                },
                sortorder : "desc",
                sortname : "orderRank",
                delRow : {
                    url : "${base}/sys/menu!doDelete"
                },
                addRow : {
                    url : "${base}/sys/menu!inputTabs"
                },
                editRow : {
                    url : "${base}/sys/menu!inputTabs",
                    labelCol : 'title'
                },
                treeGrid : true,
                treeGridModel : 'adjacency',
                ExpandColumn : 'url',
                caption : "菜单列表"
			});
			
			$("#menuAddBtn").click(function() {
                $("#menuListDiv").jqGrid('addRow');
            });

            $("#menuDeleteBtn").click(function() {
                $("#menuListDiv").jqGrid('delRow');
            });
		});
	</script>
</body>
</html>