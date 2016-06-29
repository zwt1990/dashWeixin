$(function(){
	initTable();
});
function initTable(){
	 $('#wxConfigTable').dataTable( {
		  "ajaxSource":  "../weixin/getConfigs",
		  "columns": [
		    { "data": "appId" },
		    { "data": "appSecret" },
		    { "data": "token" },
		    { "data": "name" },
		    { "data": "originalId"},
		    { 
		    	"data": "id",
		    	"render" : function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-plus\"   style=\"cursor:pointer\" onclick=\"createBtn('+data+')\"></div>';
				}
		    }
		  ]
		} );
}

function createBtn(configId){
	if(configId){
		location.href="wxMenus.html?configId="+configId;
	}
}