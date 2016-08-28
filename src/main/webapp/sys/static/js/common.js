function getQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
var r = window.location.search.substr(1).match(reg); 
if (r != null) return unescape(r[2]); return null; 
} 
function showErrMsg(msg){
	toastr.error(msg);
}
function showInfoMsg(msg){
	toastr.info(msg);
}
function showConfirm(msg,callback){
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    dialog: {
	        msg: msg,
	        btns: 2,                    
	        type: 4,
	        btn: ['确认','取消'],
	        yes: function(){
	        	if(callback instanceof Function){
	        		callback();
	        	}
	        }, no: function(){
	           // layer.msg('奇葩', 1, 13);
	        }
	    }
	});
}