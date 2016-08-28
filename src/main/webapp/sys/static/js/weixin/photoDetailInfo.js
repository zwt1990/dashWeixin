 $(function(){
	 initTable();
	 initEvent();
 });
 
 function initEvent(){
	 $("#category").change(function(){
		 if($(this).val()==2){
			 $("#keyForm").show();
			 $("#valueForm").show();
			 $("#fileForm").hide();
		 }else{
			 $("#keyForm").hide();
			 $("#valueForm").hide();
			 $("#fileForm").show();
		 }
		 
	 });
 }
 
 function validate(){
	if( $("#category").val()==2){
		if(!$("#key").val()){
			showErrMsg("请输入参数名称");
			return false;
		}
		if(!$("#value").val()){
			showErrMsg("请输入参数值");
			return false;
		}
	}else{
		if(!$("#file").val()){
			showErrMsg("请上传图片");
			return false;
		}
	}
	return true;
 }
 
 function addPhotoExt(){
	 if(!validate()){
			return ;
		}
	 if($("#category").val()==2){
		 addExtData();
	 }else{
		 upladFile(clearForm);
	 }
	
 }
 
 function clearForm(){
	 $("#category").val(1);
	// $("#url").val('');
	 $("#value").val('');
	 $("#key").val('');
 }
 
 function addExtData(){
	 $.ajax({
	        type: 'POST',
	        url: '../wxphoto/addPhotoExt',
	        data: {
	        	'pConfigId':getQueryString('id'),
	        	'key':$("#key").val(),
	        	'value':$("#value").val(),
	        	'type':$("#category").val(),
	        },
	        dataType: "json",
	        success: function(data) {
	            if (!data.status) {
	            	showErrMsg(data.msg);
	                return;
	            }
	        	clearForm();
	            $('#modal-form').modal('hide');
	            showInfoMsg("创建成功");
	            table.api().ajax.reload();
	        }
	    });
 }
 
 function getCategory(){
	 return $("#category").val();  
 }
 function getName(){
	 return $("#name").val();
 }
 function toggleModal(){
	 clearForm();
	 $('#modal-form').modal('show');
 }
 
 function clearForm(){
	 $("#category").val(1);
	 $("#extKey").val('');
	 $("#extvalue").val('');
	 $("#file").val('');
 }
 function next(){
	 upladFile(clearForm);
 }
 
 function valdiate(){
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
     var FileController = "../wxphoto/addImgExt";                    // 接收上传文件的后台地址 
     // FormData 对象
     var form = new FormData();
     form.append("type", getCategory());                        // 可以增加表单数据
     form.append("file", fileObj);  
     form.append("pconfigId",getQueryString('id')); 
     // XMLHttpRequest 对象
     var xhr = new XMLHttpRequest();
     xhr.open("post", FileController, true);
     xhr.onload = function (data) {
    	 showInfoMsg("上传图片成功");
    	 if(callback instanceof Function){
    		 callback();
    		 $('#modal-form').modal('hide');
    	 }else{
    		 $('#modal-form').modal('hide');
    	 }
    	 clearForm();
    	 table.api().ajax.reload();
     };
     xhr.send(form);
 }
 
 
 
 var table 
 function initTable(){
 	 table = $('#wxPhotoTable').dataTable( {
 		  "ajaxSource":  "../wxphoto/getPhotoExts?pConfigId="+getQueryString('id'),
 		  "columns": [
 		    { "data": "type",
 		    	"render": function(data, type, full, meta) {
 		    		if(data==1){
 		    			return "轮播图片"
 		    		}
 		    		if(data==2){
 		    			return "参数属性"
 		    		}
 		    		if(data==3){
 		    			return "详情图片"
 		    		}
		    		 return '详情图片';
				}
 		    	},
 		    { "data": "extKey" },
 		    { "data": "extValue" ,
 		    	"render": function(data, type, full, meta) {
 		    		if(full.type!=2){
 		    			 return '<img  style=\"max-width:20px;max-height:20px\"  src=\"../sys/static'+data+'\"/>';
 		    		}
 		    		return data;
 		    		
				}
 		    },
 		   { 
 		    	"data": "id",
 		    	"render": function(data, type, full, meta) {
 		    		 return '<div class=\"fa fa-remove\"   style=\"cursor:pointer\" onclick=\"deletePhoto('+data+')\"></div>';
 				}
 		    }
 		  ]
 		} );
 }

 function deletePhoto(data){
	 $.layer({
		    shade: [0],
		    area: ['auto','auto'],
		    dialog: {
		        msg: '确认要删除该图片吗',
		        btns: 2,                    
		        type: 4,
		        btn: ['确认','取消'],
		        yes: function(){
		        	deteteImg(data);
		        	 layer.msg('删除中', 1, 1);
		        }, no: function(){
		        }
		    }
		});
 }
 function deteteImg(id){
	 $.ajax({
	        type: 'POST',
	        url: '../wxphoto/deletePhotoExt',
	        data: {
	        	'id':id,
	        },
	        dataType: "json",
	        success: function(data) {
	            if (!data.status) {
	            	showErrMsg(data.msg);
	                return;
	            }
	            showInfoMsg("删除成功");
	            table.api().ajax.reload();
	        }
	    });
 }