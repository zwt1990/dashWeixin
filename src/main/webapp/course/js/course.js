$(function(){
	queryDayCourses('');
})

function toPreDay() {
	queryDayCourses('pre');
}

function toNextDay() {
	queryDayCourses('next');
}

function queryDayCourses(op) {
	$.ajax({
		type : 'POST',
		url : '../course/getCourses',
		data : {
			date : $("#today").val(),
			op:op
		},
		dataType : 'json',
		success : function(data) {
			buildNavBarData(data.date);
			buildCourseData(data.courses);
			initEvent();
		}
	});
}
function initEvent(){
	var comid = '818883';
	
	$('title').text($('.active').text());
	
	$('#book_div input').on('keypress',function(e){
		if(e.keyCode == 13){
			return false;
		}
	})
	
	$('#wait_book_div input').on('keypress',function(e){
		if(e.keyCode == 13){
			return false;
		}
	})
	$('[name="bookBtn"]').on('click',function(){
		class_id = $(this).attr('class_id');
		$('#bookClassid').val(class_id);
		$('#book_div').modal('show');

	})
}

function buildNavBarData(data){
	$("#today").val(data.dateStr)
	$("#todayDate").html(data.shortDate);
	$("#todayWeek").html(data.chineseWeek);
}

function buildCourseData(data){
	var html_tmp = _.template($("#course-temp").html(), data);
	$("#dataTable tbody").html(html_tmp);
}

function valiEmpty(){
	if(!$("#mobile").val()){
		alert("请输入手机号");
		return false;
	}
	if(!$("#userName").val()){
		alert("请输入姓名");
		return false;
	}
	return true;
}

function submitAppoint(){
	if(!valiEmpty()){
		return;
	}
	$.ajax({
		type : 'POST',
		url : '../course/submitAppoint',
		data : {
			courseId : $('#bookClassid').val(),
			name:$("#userName").val(),
			phone:$("#mobile").val()
		},
		dataType : 'json',
		success : function(data) {
			if(data.status){
				$('#book_div').modal('hide');
				alert("预约成功");
			}
			alert(data.msg)
			
		}
	});
}