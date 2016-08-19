$(function(){
	initData();
});
function initData(){
    $.ajax({
	     type: 'POST',
	     url: '../manange/getMainInfo' ,
    	data: {
   		configId:getQueryString('configId')
    	} ,
   dataType: 'json',
   success: function(data){
	   if(data.status){
		   var data=data.data;
		   var html_menus=_.template($("#menus-temp").html(),data.menu);
		   $("#side-menu").append(html_menus);
	   }else{
		   location.replace("../login.html");
	   }
	  
   }
	});
}

function showPage(url){
	  $.ajax({
		     type: 'POST',
		     url: '../manange/getShowPage' ,
	    	data: {
	   		url:url
	    	} ,
	   dataType: 'json',
	   success: function(data){
		   if(data.status){
			   $("#parentFrame").attr("src",data.data);
		   }else{
			   location.replace("../login.html");
		   }
		  
	   }
		});
	
	
	
	
}