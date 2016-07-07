$(function(){
	initData();
})
function initData(){
    $.ajax({
	     type: 'POST',
	     url: 'getMainInfo' ,
    	data: {
   		configId:getQueryString('configId')
    	} ,
   dataType: 'json',
   success: function(data){
	   var html_menus=_.template($("#menus-temp").html(),data.menu);
	   $("#side-menu").append(html_menus);
   }
	});
}