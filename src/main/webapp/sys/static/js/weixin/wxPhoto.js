$(function(){
	initTable();
});
var table 
function initTable(){
	 table = $('#wxPhotoTable').dataTable( {
		  "ajaxSource":  "../weixin/getConfigs",
		  "columns": [
		    { "data": "appId" },
		    { "data": "name" },
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-search\"   style=\"cursor:pointer\" onclick=\"toWXPhotoPG('+data+')\"></div>';
				}
		    },
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-edit\"   style=\"cursor:pointer\" onclick=\"toAddPhotoPG('+data+')\"></div>';
				}
		    }
		  ]
		} );
}

function toAddPhotoPG(data){
	location.href="photoInfo.html?id="+data;
}
function toWXPhotoPG(data){
	parent.location.href="../weixin/photo.html?id="+data;
}