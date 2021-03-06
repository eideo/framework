var ReMessageWin=function(){	
	var panel=this.initUIWin();
	var windowRe = new Ext.Window({
		id:'wind',
		title:'回复',
		width:280,
		height:200,
		x: 5,
        y:400,
		items:panel,
		buttons : [ {
			text : '发送',
			handler : function() {
				var message = Ext.getCmp('mmFormPanel');
				if (message.getForm().isValid()) {
					message.getForm().submit( {
						waitMsg : '正在 发送信息',
						success : function(message, o) {
							Ext.Msg.alert('操作信息', '信息发送成功！');
							windowRe.close();
						}
					});
				}
			}

		}, {
			text : '重置',
			handler : function() {
				var message1 = Ext.getCmp('mmFormPanel');
				message1.getForm().findField("content").reset();
			}
		} ]
	});
	windowRe.show();
}

ReMessageWin.prototype.initUIWin = function(){
	var mPanel = new Ext.form.FormPanel( {
		id : 'mmFormPanel',
		frame : true,
		bodyStyle : 'padding:5px 20px 0',
		width : 275,
		height : 180,
		defaultType : 'textarea',
		url : __ctxPath + '/info/sendShortMessage.do',
		reader : new Ext.data.JsonReader( {
			root : 'data'
		}, [ {
			name : 'userId',
			mapping : 'senderId'
		}, {
			name : 'userFullname',
			mapping : 'sender'
		} ]),
		layout:'absolute',
		items : [{
				xtype : 'hidden',
				name : 'userId',
				id : 'userId'
			}, {
				x:0,
				y:10,
				xtype:'label',
				text:'收信人:'
			},
			{
				    x:40,
				    y:10,
					xtype : 'field',
					width : 200,
					name : 'userFullname',
					id : 'userFullname',
					allowBlank : false,
					readOnly : true
			}, 
//			{    x:40,
//				    y:80,
//				    xtype : 'button',
//					text : '添加联系人 ',
//					width : 20,
//					handler : function() {
//				          new ContactWin();
//					}
//				}, {
//					x:120,
//					y:80,
//					xtype : 'button',
//					text : '清除联系人',
//					width : 20,
//					handler : function() {
//						var name = Ext.getCmp('userFullname');
//						name.reset();
//					}
//			
//		       },
		       {
		    	   x:0,
		    	   y:40,
		    	   xtype:'label',
		    	   text:'内容:'
		       }, { x:40,
		    	    y:40,
					id : 'sendContent',
					xtype : 'textarea',
					width : 200,
					name : 'content'
		       } ]
	});
	return mPanel;
}
