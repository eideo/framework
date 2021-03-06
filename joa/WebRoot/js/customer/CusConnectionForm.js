var CusConnectionForm = function(connId) {
	this.connId = connId;
	var fp = this.setup();
	var window = new Ext.Window({
		id : 'CusConnectionFormWin',
		title : '交往纪录信息',
		width : 500,
		height : 420,
		minHeight:419,
		minWidth:499,
		modal : true,
		layout : 'anchor',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [this.setup()],
		buttons : [{
			text : '保存',
			iconCls : 'btn-save',
			handler : function() {
				var fp = Ext.getCmp('CusConnectionForm');
				if (fp.getForm().isValid()) {
					fp.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.Msg.alert('操作信息', '成功保存信息！');
							Ext.getCmp('CusConnectionGrid').getStore().reload();
							window.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : action.result.msg,
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
							//window.close();
						}
					});
				}
			}
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			handler : function() {
				window.close();
			}
		}]
	});
	window.show();
};

CusConnectionForm.prototype.setup = function() {

	var formPanel = new Ext.FormPanel({
				url : __ctxPath + '/customer/saveCusConnection.do',
				layout : 'form',
				id : 'CusConnectionForm',
				frame : true,
				defaults : {
					width : 400,
					anchor : '98%,98%'
				},
				formId : 'CusConnectionFormId',
				defaultType : 'textfield',
				items : [{
							name : 'cusConnection.connId',
							id : 'connId',
							xtype : 'hidden',
							value : this.connId == null ? '' : this.connId
						}, {
							xtype:'hidden',//'客户ID'
							name : 'cusConnection.customer.customerId',
							id : 'customerId'
						}, {
							xtype:'container',
							height:26,
							layout:'column',
							style:'padding-left:0px;',
							items:[{
								xtype:'label',
								width:101,
								style:'padding-left:0px;padding-top:3px;',
								text: '客户*:'
							},{
								xtype:'textfield',
								readOnly:true,
								width:260,
								name:'customerName',
								id: 'customerName'
							},{
								xtype:'button',
								iconCls:'btn-mail_recipient',
								text:'选择客户',
								handler:function(){
									CustomerSelector.getView(function(customerId,customerName){
										Ext.getCmp('customerId').setValue(customerId);
										Ext.getCmp('customerName').setValue(customerName);
										Ext.getCmp('contactor').getStore().reload({params:{'Q_customer.customerId_L_EQ':customerId}});
									},true).show();
								}
							}]
						},{
							fieldLabel : '联系人*',
							name : 'cusConnection.contactor',
							id : 'contactor',
							xtype : 'combo',
							mode : 'local',
							editable : false,
							triggerAction : 'all',
							store: new Ext.data.SimpleStore({
											method:'post',
											url:__ctxPath+'/customer/findCusLinkman.do',
											fields : ['linkmanId', 'fullname']
										}),
							displayField:'fullname',
							valueField:'linkmanId',
							enableKeyEvent:true,
							listeners:{
									focus:function(combo){
										var customerId = Ext.getCmp('customerId').value;
										if(customerId ==null || customerId =='' || customerId =='undefined'){
											Ext.Msg.alert('提示信息','请先选择所属客户!');
										}
									}
							}
						}, {
							xtype:'container',
							height:26,
							layout:'column',
							style:'padding-left:0px;',
							defaultType:'label',
							items:[{
								text : '开始交往日期*:',
								width:101,
								style:'padding-left:0px;padding-top:3px;'
							},{
								text : '从',
								style:'padding-left:0px;padding-top:3px;'
							},{
								//fieldLabel : '开始交往日期    从:',
								xtype:'datefield',
								width: 149,
								format:'Y-m-d',
								readOnly:true,
								name : 'cusConnection.startDate',
								id : 'startDate'
							},{
								text : '至',
								style:'padding-left:0px;padding-top:3px;'
							},{
								//fieldLabel : '结束交往日期',
								xtype:'datefield',
								width: 149,
								format:'Y-m-d',
								readOnly:true,
								name : 'cusConnection.endDate',
								id : 'endDate'
							}]
						},{
							fieldLabel : '交往内容*',
							height:180,
							xtype:'textarea',
							name : 'cusConnection.content',
							id : 'content'
						}, {
							fieldLabel : '备注',
							xtype:'textarea',
							name : 'cusConnection.notes',
							id : 'notes'
						}
				]
			});

	if (this.connId != null && this.connId != 'undefined') {
		formPanel.getForm().load({
			deferredRender : false,
			url : __ctxPath + '/customer/getCusConnection.do?connId='
					+ this.connId,
			waitMsg : '正在载入数据...',
			success : function(form, action) {
				// Ext.Msg.alert('编辑', '载入成功！');
				var result = action.result.data;
				Ext.getCmp('customerName').setValue(result.customer.customerName);
				var startDate = getDateFromFormat(result.endDate,'yyyy-MM-dd HH:mm:ss');
				var endDate = getDateFromFormat(result.startDate,'yyyy-MM-dd HH:mm:ss');
				Ext.getCmp('startDate').setValue(new Date(startDate));
				Ext.getCmp('endDate').setValue(new Date(endDate));
			},
			failure : function(form, action) {
				// Ext.Msg.alert('编辑', '载入失败');
			}
		});
	}
	return formPanel;

};
