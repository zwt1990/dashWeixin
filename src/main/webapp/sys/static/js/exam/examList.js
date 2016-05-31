$(function(){
	initEvent();
	initData();
	
})
function initEvent(){
	var data=[{name:'123',age:'19'},{name:'1223',age:'29'}]
	   var t=_.template($("#questionTemplate").html(),data);
       $("#table_test").html(t);
}
function initData(){
	 $('#examResult').dataTable( {
		  "ajaxSource":  "../exam/getExamResults",
		  "columns": [
		    { "data": "examName" },
		    { 
		    	"data": "createDate",
		    	"render" : function(data, type, full, meta) {
					return formatDate(data);
				}
		    },
		    { "data": "takeTime" },
		    { "data": "totalScore" },
		    { "data": "minScore" },
		    { "data": "maxScore" },
		    { "data": "aveScore" },
		    { "data": "num" },
		    { "data": "id",
	    	 "render": function ( data, type, full, meta ) {
	    	      return '<div class=\"fa fa-search-plus\" style=\"cursor:pointer\" onclick=\"showDetailResult('+data+')\"></div>';
	    	    }
		    }
		  ]
		} );
	 
		 $('#userResult').dataTable({
		"columns" : [ {
			"data" : "userName"
		}, {
			"data" : "examName"
		}, {
			"data" : "examDate",
			"render" : function(data, type, full, meta) {
				return formatDate(data);
			}

		}, {
			"data" : "score"

		}, ]
	});
	
}
function getLocalTime(nS) { 
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' '); 
	} 

function formatDate(timeZone) { 
	var now=new Date(timeZone)
	var year=now.getFullYear(); 
	var month=now.getMonth()+1; 
	var date=now.getDate(); 
	var hour=now.getHours(); 
	var minute=( now.getMinutes() < 10 ? '0'+ now.getMinutes() :now.getMinutes()); 
	var second=( now.getSeconds() < 10 ? '0'+ now.getSeconds() :now.getSeconds()); 
	return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second; 
	} 
function showDetailResult(id){
	if(!id){
		alert("参数错误");
		return;
	}
	var table = $('#userResult').DataTable( );
	table.ajax.url('../exam/getDetailResults?examId='+id).load();
}

