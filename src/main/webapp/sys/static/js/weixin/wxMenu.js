$(function() {
    initEvent();
    var updateOutput = function(e) {
        var list = e.length ? e: $(e.target),
        output = list.data('output');
    };
    $('#nestable2').nestable({
        group: 1,
        maxDepth: 2
    }).on('change', updateOutput);

    updateOutput($('#nestable2').data('output', $('#nestable2-output')));
    $('#nestable-menu').on('click',
    function(e) {
        var target = $(e.target),
        action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });
});

function initEvent() {
    $("#addBtn").click(function() {
        createHtmlUI();
        clearInput();
    });
    $("#saveBtn").click(function() {
        submitButtons();
    });
    optToast();
}

function clearInput() {
    $("#name").val('');
    $("#url").val('');
}

function validateLevel() {
    var objArr = $('#nestable2').nestable('serialize');
    if (objArr.length > 3) {
    	showErrMsg("一级菜单数量不能超过3 个!");
        return false;
    }
    var illeage = true;
    for (var i = 0; i < objArr.length; i++) {
        var children = objArr[i].children;
        
        if (children&&children.length >= 6) {
        	showErrMsg("二级菜单不能超过5个");
            illeage = false;
            break;
        }
    }
    return illeage;
}

function validateNums() {
    if (data.length >= 18) {
    	showErrMsg("菜单数量超过微信标准!");
        return false;
    }
    return true;
}

function submitButtons() {
	if(!validateNums()){
		return;
	}
	if(!validateLevel()){
		return;
	}
    $.ajax({
        type: 'POST',
        url: '../weixin/updateBtn?configId='+getQueryString('id'),
        data: JSON.stringify(getMenuButtons()),
        dataType: "json",
        contentType: "application/json",
        success: function(data) {
            if (data.code!=0) {
            	showErrMsg(data.msg);
                return;
            }
            showInfoMsg("创建成功");
            history.go(-1);
        }
    });

}
var data = [];
function createHtmlUI() {
    createItem();
    var html = _.template($("#menus-temp").html(), data);
    $(".dd-list").html(html);
}
function createItem() {
    var item = {
        id: data.length,
        name: $("#name").val(),
        url: $("#url").val()
    }
    data.push(item);
}

function getMenuButtons() {
    var menuBtns = [];
    var nestableData = $('#nestable2').nestable('serialize');
    for (var i = 0; i < nestableData.length; i++) {
        menuBtns.push(getItemById(nestableData[i].id, nestableData[i].children));
    }
    return menuBtns;
}

function getItemById(id, children) {
    var button;
    for (var i = 0; i < data.length; i++) {
        if (data[i].id == id) {
            button = {
                name: data[i].name,
                url: data[i].url,
                eventKey: '',
                type: 'view',
                subButtons: getSubButtons(children)
            };
            break;
        }
    }
    return button;
}
function getSubButtons(children) {
    if (children) {
        var buttons = [];
        for (var j = 0; j < children.length; j++) {
            for (var i = 0; i < data.length; i++) {
                if (data[i].id == children[j].id) {
                    var button = {}
                    button.name = data[i].name;
                    button.url = data[i].url;
                    button.eventKey = '';
                    button.type = 'view';
                    buttons.push(button);
                }
            }
        }
        return buttons;
    }
    return [];
}

function showErrMsg(msg){
	toastr.error(msg);
}
function showInfoMsg(msg){
	toastr.info(msg);
}
function optToast(){
	 toastr.options = {
			  "closeButton": true,
			  "debug": true,
			  "progressBar": false,
			  "positionClass": "toast-top-right",
			  "showDuration": "400",
			  "hideDuration": "1000",
			  "timeOut": "7000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
		}
}