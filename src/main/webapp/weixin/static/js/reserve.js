$(function(){
	initForm();
});

function initForm() {
	$.ajax({
		type : 'POST',
		url : '../reserve/initForm',
		data : {
			formId : getQueryString("formId")
		},
		dataType : 'json',
		success : function(data) {
			if (data.status) {
				var data = data.data;
				if (data.imgUrl) {
					$("#headImg").attr("src", "../sys/static"+data.imgUrl);
				} else {
					$("#headImg").attr("src",
							" ../sys/static/bootstrap/img/p8.jpg");
				}
				document.title = data.title;
				var projects = data.dictionarys;
				var optionStr = "";
				for (var i = 0; i < projects.length; i++) {
					optionStr += ("<option value=\""+projects[i].value+"\">" + projects[i].key + "</option>");
				}
				$("#project").html(optionStr);
				if( data.link){
					$("#share_link").data("link", data.link);
					$("#share_link").show();
				}
			}
		}
	});
}

function validate() {
	if (!$("#name").val()) {
		alert("请输入姓名");
		return false;
	}
	if (!$("#mobile").val()||$("#mobile").val().length!=11) {
		alert("请输入正确手机号");
		return false;
	}
	if (!$("#project").val()) {
		alert("请选择预约项目");
		return false;
	}
	if (!$("#date").val()) {
		alert("请选择预约日期");
		return false;
	}
	return true;
}

function toLinkPg(){
	var link=$("#share_link").data('link');
	if(link){
		location.href=link;
	}
}

var clicked = true;
function submitReserve() {
	if (!validate()) {
		return;
	}
	if (!clicked) {
		return;
	}
	clicked = false;
	var param = {
		name : $("#name").val(),
		mobile : $("#mobile").val(),
		project : $("#project").val(),
		resDateStr : $("#date").val(),
		sex : $("#sex").val(),
		formId:getQueryString("formId")
	};
	$.ajax({
		type : 'POST',
		url : '../reserve/submitReserve',
		data : param,
		dataType : 'json',
		success : function(data) {
			clicked = true;
			if (data.status) {
				alert("预约成功");
			}else{
				alert(data.msg);
			}
		}
	});
}