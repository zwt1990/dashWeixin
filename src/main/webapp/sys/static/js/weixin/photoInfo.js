 $(function(){
	 initTable();
	 initEvent();
 });
 
 function initEvent(){
	 $("#category").change(function(){
		 if( $(this).val()=='3'){
			 $('#price').show();
		 }else{
			 $('#price').hide();
		 }
	 })
	 
 }
 function getCategory(){
	 return $("#category").val();  
 }
 function getUrl(){
	 return $("#url").val();
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
	// $("#url").val('');
	 $("#name").val('');
	 $("#file").val('');
	 $("#price").val('');
 }
 function next(){
	 upladFile(clearForm);
 }
 
 function valdiate(){
//	 if(!getUrl()){
//		 showErrMsg("请输入链接地址");
//		 return false;
//	 }
	 if(getCategory()=="2"&&!getName()){
		 showErrMsg("请输入分类名称");
		 return false;
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
     var FileController = "../wxphoto/uploadImgs";                    // 接收上传文件的后台地址 
     // FormData 对象
     var form = new FormData();
     form.append("category", getCategory());                        // 可以增加表单数据
     form.append("file", fileObj);  
 //    form.append("url", getUrl());  
     form.append("name", getName());  
     form.append("price", $("#price").val()); 
     form.append("configId",getQueryString('id')); 
     // XMLHttpRequest 对象
     var xhr = new XMLHttpRequest();
     xhr.open("post", FileController, true);
     xhr.onload = function (data) {
    	 showInfoMsg("上传图片成功");
    	 if(callback instanceof Function){
    		 callback();
    	 }else{
    		 $('#modal-form').modal('hide');
    	 }
    	 table.api().ajax.reload();
     };
     xhr.send(form);
 }
 
 
 
 var table 
 function initTable(){
 	 table = $('#wxPhotoTable').dataTable( {
 		  "ajaxSource":  "../wxphoto/getPhotoByConfigId?wxconfig="+getQueryString('id'),
 		  "columns": [
 		    { "data": "name" },
 		    { "data": "category",
 		    	"render": function(data, type, full, meta) {
 		    		if(data==1){
 		    			return "轮播图片"
 		    		}
 		    		if(data==2){
 		    			return "菜单图片"
 		    		}
 		    		if(data==3){
 		    			return "详情图片"
 		    		}
		    		 return '详情图片';
				}
 		    	},
 		    { "data": "createTime" },
 		    { "data": "path" ,
 		    	"render": function(data, type, full, meta) {
		    		 return '<img  style=\"max-width:20px;max-height:20px\"  src=\"../sys/static'+data+'\"/>';
				}
 		    },
 		    { 
 		    	"data": "id",
 		    	"render": function(data, type, full, meta) {
 		    		if(full.category==3){
 		    			 return '<div class=\"fa fa-edit\"   style=\"cursor:pointer\" onclick=\"toDetailPG('+data+')\"></div>';
 		    		}else{
 		    			return "";
 		    		}
 		    		
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
		        	 layer.msg('删除 中', 1, 1);
		        }, no: function(){
		           // layer.msg('奇葩', 1, 13);
		        }
		    }
		});
 }
 function deteteImg(id){
	 $.ajax({
	        type: 'POST',
	        url: '../wxphoto/deletePhoto',
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
 function toDetailPG(data){
	 location.href="photoDetailInfo.html?id="+data;
 }