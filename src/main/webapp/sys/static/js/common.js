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