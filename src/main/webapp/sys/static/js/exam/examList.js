$(function(){
	initEvent();
	initData();
	
})
function initEvent(){
}
function initData(){
	 $('#examResult').dataTable( {
		  "ajaxSource":  "../exam/getExamResults",
		  "columns": [
		    { "data": "examName" },
		    { "data": "createDate" },
		    { "data": "takeTime" },
		    { "data": "totalScore" },
		    { "data": "minScore" },
		    { "data": "maxScore" },
		    { "data": "aveScore" },
		    { "data": "num" }
		  ]
		} );
	 $('#userResult').dataTable( {
		  "ajaxSource":  "../exam/getDetailResults?examId=2",
		  "columns": [
		    { "data": "userName" },
		    { "data": "examName" },
		    { "data": "examDate" },
		    { "data": "score" },
		  ]
		} );
}


