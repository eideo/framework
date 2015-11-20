Ext.ns('BookManageView');

var BookManageView=function(){
	
	var selectedNode;
	
	var bookView=new BookView();
	
    var treePanel = new Ext.tree.TreePanel({
				region : 'west',
				id : 'leftBookManagePanel',
				title : '图书类别',
				collapsible : true,
				split : true,
				width : 200,
				height : 800,
				tbar:new Ext.Toolbar({items:[{
				    text: '添加类别',
				    iconCls:'add-info',
				    handler: function() {
				    	var bookTypeForm = Ext.getCmp('bookTypeForm');
				    	if(bookTypeForm==null){
				    		new BookTypeForm();
				    	}else{
				    		bookTypeForm.getForm().reset();
				    	}
				    }
				},{
						xtype:'button',
						iconCls:'btn-refresh',
						text:'刷新',
						handler:function(){
							treePanel.root.reload();
						}
					}
					]}),
				loader : new Ext.tree.TreeLoader({
							url : __ctxPath + '/admin/treeBookType.do'
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				rootVisible : false,
				listeners : {
					'click' : function(node){
						if (node != null) {
							
							bookView.setTypeId(node.id);
							
							var bView=Ext.getCmp('BookView');
							if(node.id==0){
								bView.setTitle('所有图书列表');
							}else{
								bView.setTitle('['+node.text+']列表');
							}
					    	var bGrid = Ext.getCmp('BookGrid');
					    	var store=bGrid.getStore();
					    	
					    	store.url=__ctxPath+'/admin/listBook.do';
					    	store.baseParams={'Q_bookType.typeId_L_EQ':node.id==0?null:node.id};
					    	store.params={start:0,limit:25};
					    	store.reload({params:{start:0,limit:25}});
					    }
					}
				}
			});

	function contextmenu(node, e) {
		selectedNode = new Ext.tree.TreeNode({
					id : node.id,
					text : node.text
		});
		treeMenu.showAt(e.getXY());
	}
	//树的右键菜单的
	treePanel.on('contextmenu', contextmenu, treePanel);

	// 创建右键菜单
	var treeMenu = new Ext.menu.Menu({
				id : 'treeMenu',
				items : [
				         {
							text : '添加类别',
							scope : this,
							iconCls:'btn-add',
							handler : createNode
						},{
							text : '修改类别',
							scope : this,
							iconCls:'btn-edit',
							handler : editNode
						},{
							text : '删除类别',
							scope : this,
							iconCls:'btn-delete',
							handler : deleteNode
						}
                  ]
			});

	//新建目录
	function createNode() {		
		new BookTypeForm(null);
		
	};
	//编辑目录
	function editNode() {
		var typeId=selectedNode.id;
		if(typeId>0){
			new BookTypeForm(typeId);
		}else{
			Ext.MessageBox.show({
				title : '操作信息',
				msg : '该处不能被修改',
				buttons : Ext.MessageBox.OK,
				icon : 'ext-mb-error'
			});
		}
	};
	//删除目录，子目录也一并删除
	function deleteNode() {
		var typeId=selectedNode.id;
		Ext.Ajax.request({
			url:__ctxPath+'/admin/multiDelBookType.do',
			params:{ids:typeId},
			method:'post',
			success:function(result,request){
				Ext.Msg.alert('操作信息','成功删除目录！');
				treePanel.root.reload();
			},
			
			failure:function(result,request){
				Ext.MessageBox.show({
					title : '操作信息',
					msg : '信息保存出错，请联系管理员！',
					buttons : Ext.MessageBox.OK,
					icon : 'ext-mb-error'
				});
			}
			
		});
	};		
			

	var panel = new Ext.Panel({
		id:'BookManageView',
		title : '图书管理',
		iconCls : 'menu-book-manage',
		layout : 'border',
		height : 800,
		items : [treePanel,bookView.getView()]
	});
	
	return panel;
};

