/**
 * 
 */
$.terrace = {
		initCalendar : function() {

	        var date = new Date();
	        var d = date.getDate();
	        var m = date.getMonth();
	        var y = date.getFullYear();

	        var calendar = $('#calendar').fullCalendar({
	            header: {
	                left: 'prev,next today',
	                center: 'title',
	                right: 'month,agendaWeek,agendaDay'
	            },
	            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	            monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	            dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	            dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	            today: ["今天"],
	            buttonText: {
	            	 today: '本月',
	            	 month: '月',
	            	 week: '周',
	            	 day: '日',
	            	 prev: '上一月',
	            	 next: '下一月'
	            	 },
	            events: "sys/calendar/load",
	            eventDrop: function(event, delta) {
	                moveCalendar(event);
	            },
	            eventClick: function(event, delta) {
	                viewCalendar(event);
	            },
	            loading: function(bool) {
	                if (bool) $('#loading').show();
	                else $('#loading').hide();
	            },
	            editable: true,
	            selectable: true,
	            selectHelper: true,
	            select: function(start, end, allDay) {
	                openNewCalendarForm(start, end);
	                calendar.fullCalendar('unselect');
	            }
	        });

	        $('span.fc-button-prev').before('<span class="fc-button fc-button-add fc-state-default fc-corner-left fc-corner-right">新增</span>');

	        $(".fc-button-add").click(function() {
	            openNewCalendarForm();
	        });

	        function openNewCalendarForm(start, end) {
	            var url =  "sys/calendar/new";
	            if(start) {
	                start = $.fullCalendar.formatDate(start, "yyyy-MM-dd HH:mm:ss");
	                end = $.fullCalendar.formatDate(end, "yyyy-MM-dd HH:mm:ss");
	                url = url + "?start=" + start + "&end=" + end;
	            }
	            $.app.modalDialog("新增提醒事项", url, {
	                width:370,
	                height:430,
	                ok : function(modal) {

	                    var form = modal.find("#editForm");
	                    if(!form.validationEngine('validate')) {
	                        return false;
	                    }
	                    var url = "sys/calendar/new";
	                    $.post(url, form.serialize(), function() {
	                        calendar.fullCalendar("refetchEvents");
	                    });

	                    return true;
	                }
	            });
	        }

	        function moveCalendar(event) {
	            var url = "sys/calendar/move";
	            var id = event.id;
	            var start = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd HH:mm:ss");
	            var end = $.fullCalendar.formatDate(event.end, "yyyy-MM-dd HH:mm:ss");
	            url = url + "?id=" + id;
	            url = url + "&start=" + start + "&end=" + end;

	            $.post(url, function() {
	                calendar.fullCalendar("refetchEvents");
	            });
	        }

	        function viewCalendar(event) {
	            var url = "sys/calendar/view/" + event.id;
	            $.app.modalDialog("查看提醒事项", url, {
	                width:370,
	                height:250,
	                noTitle : false,
	                okBtn : false,
	                closeBtn : false
	            });
	        }
	        $("body").on("click", ".btn-delete-calendar", function() {
	            var $this = $(this);
	            $.app.confirm({
	                title : '确认删除提醒事项吗？',
	                message : '确认删除提醒事项吗？',
	                ok : function() {
	                    var url = "sys/calendar/delete?id=" + $this.data("id");
	                    $.post(url, function() {
	                        calendar.fullCalendar("refetchEvents");
	                        $.app.closeModalDialog();
	                    });
	                }
	            });

	        });
	    }
};