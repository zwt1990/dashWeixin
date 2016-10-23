Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
        "q+": Math.floor((this.getMonth() + 3) / 3),  
        "S": this.getMilliseconds()  
    }  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}  
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