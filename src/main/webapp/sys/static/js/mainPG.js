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
		   $("#name").html(data.user.name);
		   $("#role").html(data.user.role.name);
		   $("#side-menu").append(html_menus);
		   $("#parentFrame").attr("src",data.menu[0].menus[0].url);
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