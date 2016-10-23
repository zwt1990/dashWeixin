$(function(){
	initTable();
});
var table 
function initTable(){
	 table = $('#wxPhotoTable').dataTable( {
		  "ajaxSource":  "../wxphoto/getPhotos",
		  "columns": [
		    { "data": "appId" },
		    { "data": "appName" },
		    { "data": "photoName" },
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-search\"   style=\"cursor:pointer\" onclick=\"toWXPhotoPG('+data+')\"></div>';
				}
		    },
		    { "data": "createTime" ,
		    	"render":function(data){
		    		return new Date(data).format("yyyy-MM-dd");
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