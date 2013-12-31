<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/script-header.jsp" %>
<%@ include file="/common/script-footer.jsp" %> 
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en" xmlns="http://www.w3.org/1999/html"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <style>

    legend {
        cursor: pointer;
    }
    .fc-button-add {
        margin-right: 10px!important;
    }

    #loading {
        position: absolute;
        top: 5px;
        right: 5px;
    }

    .ui-dialog {
        overflow: visible!important;
    }
    .ui-dialog-content {
        overflow: visible!important;
    }

    #calendar {
        width: 750px;
        margin: 0 auto;
    }
</style>
<link rel="stylesheet" type="text/css" href="${base}/resources/components/fullcalendar/1.6.4/fullcalendar.css" />
<link rel="stylesheet" type="text/css" href="${base}/resources/components/fullcalendar/1.6.4/fullcalendar.print.css" />
<script type="text/javascript" src="${base}/resources/components/fullcalendar/1.6.4/fullcalendar.min.js?_=${buildVersion}"></script>
<script type="text/javascript" src="${base}/resources/components/jquery-ext/jquery.sdw.terrace.js?_=${buildVersion}"></script>
<script type="text/javascript">
$(function() {
    //$.app.initCommonBtn();
    $("legend").click(function() {
        var next = $(this).next();
        if(next.is(":hidden")) {
            $(this).find("i").removeClass("icon-double-angle-up");
            $(this).find("i").addClass("icon-double-angle-down");
            next.slideDown(300);
        } else {
            next.slideUp(300);
            $(this).find("i").removeClass("icon-double-angle-down");
            $(this).find("i").addClass("icon-double-angle-up");
        }
    });
    $.terrace.initCalendar();

});
</script>
</head>
<body>
	<div  style="margin: 5px;margin-top: 10px;">
    <div class="row-fluid tool ui-toolbar">
        <div style="padding-left: 10px;">
            <a class="btn btn-link btn-view-info" data-toggle="tooltip" data-placement="bottom" title="点击查看个人资料/修改密码">
                <sys:showLoginUsername/>，欢迎您！
            </a>
            <span class="muted">|</span>
            &nbsp;
            <span class="muted">
                您有
                <a class="btn btn-link btn-view-message no-padding" data-toggle="tooltip" data-placement="bottom" title="点击查看未读消息">
                    <span class="badge badge-important">${messageUnreadCount}条</span>
                </a>
                未读消息
            </span>
        </div>
    </div>
    <br/>

    <fieldset>
        <legend>
            我的日历

            (<span class="badge badge-important" data-toggle="tooltip" data-placement="bottom" title="最近三天，您有${calendarCount}个提醒提醒事项">${calendarCount}个</span>)
            <i class="icon-double-angle-down"></i>
        </legend>

        <div id='calendar'></div>

    </fieldset>
</body>
</html>