/*
 * 扩展validatebox,添加验证两次密码功能
 *  @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

$.extend($.fn.validatebox.defaults.rules, {    
    minLength: {    
        validator: function(value, param){    
            return value.length >= param[0];    
        },    
        message: '密码不能长度小于6'   
    }    
}); 

/*
 * 扩展comboTree和Tree 使其支持平滑数据格式
 *  @requires jQuery,EasyUI
 */
$.fn.tree.defaults.loadFilter=function(data, parent){
	var opt = $(this).data().tree.options;
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField || 'pid';
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textField];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * 防止panel/window/dialog组件超出浏览器边界
 *
 * @requires jQuery,EasyUI
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;


/*
 * lu.loadFilter = { loadFilter : function(data, parent) { var opt = $(this).data().tree.options; var idField, textField, parentField; if (opt.parentField) { idField = opt.idField || 'id'; textField = opt.textField || 'text'; parentField = opt.parentField || 'pid'; var i, l, treeData = [], tmpMap = []; for (i = 0, l = data.length; i < l; i++) { tmpMap[data[i][idField]] = data[i]; } for (i = 0, l = data.length; i < l; i++) { if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) { if (!tmpMap[data[i][parentField]]['children']) tmpMap[data[i][parentField]]['children'] = []; data[i]['text'] = data[i][textField]; tmpMap[data[i][parentField]]['children'].push(data[i]); } else { data[i]['text'] = data[i][textField]; treeData.push(data[i]); } } return treeData; } return data; } }; $.extend($.fn.combotree.defaults, lu.loadFilter); $.extend($.fn.tree.defaults, lu.loadFilter);
 */
/*$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});
*/