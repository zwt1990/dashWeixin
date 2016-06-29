 $(function(){
      initData();
  })
 function getCategory(){
	 return $("#category").val();  
 function getUrl(){
	 return $("#url").val();
 }
 function getName(){
	 return $("#name").val();
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
