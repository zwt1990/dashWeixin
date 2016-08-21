$(function(){
	initTable();
});
var table;
function initTable(){
	table=  $('#wxReserveTable').dataTable( {
		  "ajaxSource":  "../reserve/queryForms",
		  "columns": [
		    { "data": "consumerName" },
		    { "data": "title" },
		    { "data": "projects"},
		    { "data": "resCounts"},
		    { "data": "id",
		    	"render" : function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-edit\"   style=\"cursor:pointer\" onclick=\"showH5('+data+')\"></div>';
				}
		    	
		    
		    },
		    { 
		    	"data": "id",
		    	"render" : function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-search\"   style=\"cursor:pointer\" onclick=\"toResDetail('+data+')\"></div>';
				}
		    }
		  ]
		} );
}
function showDlg(configId){
	$('#modal-form').modal('show');
	
}
function clearDlg(){
	$("#consumerName").val('');
	$("#title").val('');
	$('#file').val("");
}

function valdiate(){
	if(!$("#consumerName").val()){
		showErrMsg("请输入客户名");
	}
	if(!$("#title").val()){
		showErrMsg("请输入页面标题");
	}
	 if(!$('#file').val()){
		 showErrMsg("请选择图片");
		 return false;
	 }
	 return true;
}

function upladFile(callback) {
	 if(!valdiate()){
		 return ;
	 }
    var fileObj = $('#file')[0].files[0]; // 获取文件对象
    var FileController = "../reserve/addForm";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("file", fileObj);  
    form.append("title", $("#title").val()); 
    form.append("link", $("#link").val());
    form.append("consumerName", $("#consumerName").val());  
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (data) {
   	 showInfoMsg("上传图片成功");
   	 if(callback instanceof Function){
   		 callback();
   	 }else{
   		 $('#modal-form').modal('hide');
   	  		clearDlg();
   	  	 table.api().ajax.reload();
   	 }
 
    };
    xhr.send(form);
}
function showH5(data){
	parent.location.href="../weixin/reserve.html?formId="+data;
}
 function toResDetail(data){
	 location.href="reserveInfo.html?id="+data;
 }