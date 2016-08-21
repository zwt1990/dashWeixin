$(function(){
	initTable();
});
var table 
function initTable(){
	 table = $('#wxConfigTable').dataTable( {
		  "ajaxSource":  "../weixin/getConfigs",
		  "columns": [
		    { "data": "appId" },
		    { "data": "appSecret" },
		    { "data": "token" },
		    { "data": "name" },
		    { "data": "originalId"},
		    { 
		    	"data": "id",
		    	"render": function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-edit\"   style=\"cursor:pointer\" onclick=\"toAddMenuPG('+data+')\"></div>';
				}
		    }
		  ]
		} );
}

function validate(){
	if(!$("#appId").val()){
		showErrMsg("请输入appId!");
		return false;
	}
	if(!$("#appSecret").val()){
		showErrMsg("请输入appSecret!");
		return false;
	}
	if(!$("#token").val()){
		showErrMsg("请输入Token");
		return false;
	}
	if(!$("#name").val()){
		showErrMsg("请输入name");
		return false;
	}
	if(!$("#originalId").val()){
		showErrMsg("请输入originalId");
		return false;
	}
	return true;
}
function addConfig(){
	if(!validate()){
		return ;
	}
	alert($("#appId").val())
	$.ajax({
        type: 'POST',
        url: '../weixin/addConfig',
        data: {
        	'appId':$("#appId").val(),
        	'appSecret':$("#appSecret").val(),
        	'token':$("#token").val(),
        	'name':$("#name").val(),
        	'originalId':$("#originalId").val()
        },
        dataType: "json",
        success: function(data) {
            if (!data.status) {
            	showErrMsg(data.msg);
                return;
            }
            $('#modal-form').modal('hide');
            showInfoMsg("创建成功");
            table.api().ajax.reload();
        }
    });
}
function toAddMenuPG(data){
	location.href="wxMenus.html?id="+data;
}