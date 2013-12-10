<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/script-header.jsp"%>
<div class="container-fluid data-edit">
		<div class="row">
			<div class="toolbar">
				<div class="toolbar-inner">
					<button type="button" class="btn btn-submit" callback-tab="menuIndexTabs"
						callback-grid="menuListDiv">
						<i class="glyphicon glyphicon-ok"></i> 保存
					</button>
					<button type="button" class="btn btn-submit submit-post-close" callback-tab="menuIndexTabs"
						callback-grid="menuListDiv">
						<i class="glyphicon glyphicon-check"></i> 保存并关闭
					</button>
					<button type="reset" class="btn">
						<i class="glyphicon glyphicon-repeat"></i> 重置
					</button>
				</div>
			</div>
		</div>
		<form action="${base }/sys/menu!docreate" method="post">
		<input type="hidden" name="id" />
		<input type="hidden" name="version" />
		<div class="well">
			<div class="row">
				<div class="col-md-6">
					<!-- <s2:treeinput name="parentName" label="父节点" hiddenName="parentId" treeDataUrl="/sys/menu!list"
						readonly="true" value="%{parent.title}" hiddenValue="%{parent.id}" /> -->
						<input type="text" name="parentName"  />
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				代码:<input type="text" name="code"  value=""/>
					<!-- <s2:textfield name="code" label="代码" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<s2:textfield name="title" label="名称" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				名称:<input type="text" name="title"  value=""/>
					<!-- <s2:textfield name="url" label="菜单URL" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				类型:<input type="text" name="type" value=""/>
					<!-- <s2:select name="type" list="#application.enums.menuTypeEnum" label="类型" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				展开标识: <input type="text" name="initOpen"/>
					<!-- <s2:radio name="initOpen" list="#application.enums.booleanLabel" label="展开标识" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				禁用标识:<input type="text" name="disabled"/>
					<!-- <s2:radio name="disabled" list="#application.enums.booleanLabel" label="禁用标识" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				图标样式:<input type="text" name="style"/>
					<!-- <s2:textfield name="style" label="图标样式" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				排序号:<input type="text" name="orderRank" />
					<!-- <s2:textfield name="orderRank" label="排序号" /> -->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				描述:<input type="text" name="description"/>
					<!-- <s2:textfield name="description" label="描述" /> -->
					<input type="submit" value="提交" />
				</div>
			</div>
		</div>
		
		</form>
</div>
