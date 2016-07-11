$(function(){
	initTable();
	var elem = document.querySelector('.js-switch');
    var switchery = new Switchery(elem, {
        color: '#1AB394'
    });
    var changeCheckbox = document.querySelector('.js-switch');
	  changeCheckbox.onchange = function() {
	    if(changeCheckbox.checked){
	    	$("#replyContext").val('');
	    	$("#replyContext").attr("disabled","disabled");
	    }else{
	    	$("#replyContext").removeAttr("disabled");
	    }
	  };
	
});
function initTable(){
	 $('#wxReplyTable').dataTable( {
		  "ajaxSource":  "../weixin/getReplays",
		  "columns": [
		    { "data": "appId" },
		    { "data": "name" },
		    { 
		    	"data": "customerFlag",
		    	"render" : function(data, type, full, meta) {
		    		 return data=='1'?"是":"否";
				}
		    },
		    { "data": "replyContext" },
		    { "data": "welContext" },
		    { 
		    	"data": "configId",
		    	"render" : function(data, type, full, meta) {
		    		 return '<div class=\"fa fa-edit\"   style=\"cursor:pointer\" onclick=\"showDlg('+data+')\"></div>';
				}
		    }
		  ]
		} );
}

function showDlg(configId){
	 $.ajax({
	        type: 'POST',
	        url: '../weixin/getReplays?configId='+configId,
	        dataType: "json",
	        contentType: "application/json",
	        success: function(data) {
	        	if(data.status){
	        		$("#configId").val(data.data.configId);
	        		$("#appId").val(data.data.appId);
	        		$("#name").val(data.data.name);
	        		$("#welContext").val(data.data.welContext);
	        		$("#replyContext").val(data.data.replyContext);
	        		if(data.data.customerFlag=='1'){
	        			$("#kfCheck").attr("checked","checked");
	        		}else{
	        			$("#kfCheck").removeAttr("checked");
	        		}
	        		$("#name").val(data.data.name);
	        		
	        		$('#modal-form').modal('show');
	        	}
	        	
	        }
	    });
}
function submitReply(){
	 var changeCheckbox = document.querySelector('.js-switch');
	 $.ajax({
	        type: 'POST',
	        url: '../weixin/cfgReply',
	        dataType: "json",
	        data:{
	        	'welContext':$("#welContext").val(),
        		'replyContext':$("#replyContext").val(),
        		'openCustomer':changeCheckbox.checked,
        		'configId':$("#configId").val()
	        },
	        success: function(data) {
	        	if(data.status){
	        		$('#modal-form').modal('hide');
	        		showInfoMsg("设置成功！");
	        	}
	        }
	    });
}
