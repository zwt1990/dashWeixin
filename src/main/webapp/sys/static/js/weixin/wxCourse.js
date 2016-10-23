$(function(){
	initTable();
});
var table 
function initTable(){
	 table = $('#courseTable').dataTable( {
		  "ajaxSource":  "../course/allCourses",
		  "columns": [
		    { "data": "name" },
		    { "data": "coach" },
		    { 
		    	"data": "startDate",
		    	"render": function(data, type, full, meta) {
		    		 return new Date(data).format("yyyy-MM-dd hh:mm");
				}
		    },
		    { 
		    	"data": "endDate",
		    	"render": function(data, type, full, meta) {
		    		 return new Date(data).format("yyyy-MM-dd hh:mm");
				}
		    },
		    { "data": "duration" },
		    { "data": "createTime" ,
		    	"render":function(data){
		    		return new Date(data).format("yyyy-MM-dd hh:mm");
		    	}
		    },
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-search\"   onclick=\"toDetail('+data+')\" style=\"cursor:pointer\"></div>';
				}
		    },
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-remove\"   style=\"cursor:pointer\"></div>';
				}
		    }
		  ]
		} );
}
function getDataParam(){
	var name=$("#name").val();
	if(!name){
		showErrMsg("请输入课程名称");
		return false;
	}
	var number=$("#number").val();
	if(!number){
		showErrMsg("请输入课程人数");
		return false;
	}
	var startDate=$("#startDate").val();
	if(!startDate){
		showErrMsg("请输入课程开始时间");
		return false;
	}
	var endDate=$("#endDate").val();
	if(!endDate){
		showErrMsg("请输入课程结束时间");
		return false;
	}
	var coach=$("#coach").val();
	var obj={
			name:name,
			startDateStr:fmtDate(startDate),
			endDateStr:fmtDate(endDate),
			number:number,
			coach:coach
	}
	console.log(obj);
	return obj
}

function fmtDate(date){
	return date.split("T")[0]+" "+date.split("T")[1]+":00";
}

function  clearForm(){
	$("#name").val('');
	$("#number").val('');
	$("#startDate").val('');
	$("#endDate").val('');
	$("#coach").val('');
}


function save(){
	if(!getDataParam()){
		return ;
	}
	$.ajax({
        type: 'POST',
        url: '../course/addCourse',
        data:getDataParam(),
        dataType: "json",
        success: function(data) {
            if (!data.status) {
            	showErrMsg(data.msg);
                return;
            }
            $('#modal-form').modal('hide');
            showInfoMsg("创建成功");
            clearForm();
            table.api().ajax.reload();
        }
    });
}

function toDetail(id){
	location.href="wxUserCourse.html?courseId="+id;
}