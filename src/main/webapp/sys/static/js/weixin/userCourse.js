$(function(){
	initTable();
});
var table 
function initTable(){
	 table = $('#userCourseTable').dataTable( {
		  "ajaxSource":  "../course/getUserCourses?courseId="+getQueryString("courseId"),
		  "columns": [
		    { "data": "name" },
		    { "data": "phone" },
		    { "data": "courseName" },
		    { "data": "notes" },
		    { "data": "createTime" ,
		    	"render":function(data){
		    		return new Date(data).format("yyyy-MM-dd hh:mm");
		    	}
		    },
		  ]
		} );
}