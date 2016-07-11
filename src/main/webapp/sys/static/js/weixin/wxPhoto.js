 $(function(){
      initData();
 });
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
	 $("#url").val('');
	 $("#name").val('');
	 $("#file").val('');
 }
 function next(){
	 upladFile(clearForm);
 }
 
 function valdiate(){
	 if(!getUrl()){
		 showErrMsg("请输入链接地址");
		 return false;
	 }
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
     var FileController = "../weixin/uploadImgs";                    // 接收上传文件的后台地址 
     // FormData 对象
     var form = new FormData();
     form.append("category", getCategory());                        // 可以增加表单数据
     form.append("file", fileObj);  
     form.append("url", getUrl());  
     form.append("name", getName());  
     form.append("configId",getQueryString('configId')); 
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
    	 initData();
     };
     xhr.send(form);
 }
 
 
 
  function initData(){
    $.ajax({
	     type: 'POST',
	     url: '../weixin/getImgconfigs' ,
	    data: {
	    		configId:getQueryString('configId')
	    } ,
	    dataType: 'json',
	    success: function(data){
	        var html_csl=_.template($("#carousel-temp").html(),data.cslImgs);
	        var html_menu=_.template($("#box-temp").html(),data.menuImgs);
	         var html_detail=_.template($("#box-temp").html(),data.detailImgs);
	         $("#carousel-imgs").html(html_csl);
	         $("#menu_imgs").html(html_menu);
	          $("#detail_imgs").html(html_detail);
	          $('.fancybox').fancybox({
	                openEffect: 'none',
	                closeEffect: 'none'
		            });
		    } 
		});
  }
