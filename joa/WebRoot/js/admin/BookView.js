Ext.ns('BookView');
/**
 * 图书列表
 */
var BookView = function() {
	
}
BookView.prototype.setTypeId=function(typeId){
	this.typeId=typeId;
	BookView.typeId=typeId;
};
BookView.prototype.getTypeId=function(){
    return this.typeId;
}
/**
 * 显示列表
 * 
 * @return {}
 */
BookView.prototype.getView = function() {
	return new Ext.Panel(
			{
				id : 'BookView',
				title : '图书列表',
				region:'center',
				autoScroll:true,
				items : [new Ext.FormPanel({
									height : 35,
									frame : true,
									id : 'BookSearchForm',
									layout : 'column',
									defaults : {
										xtype : 'label'
									},
									items : [
											{
												text : '请输入查询条件:'
											},
											{
												text : '书名'
											},
											{
												xtype : 'textfield',
												name : 'Q_bookName_S_LK'
											},
											{
												text : '作者'
											},
											{
												xtype : 'textfield',
												name : 'Q_author_S_LK'
											},
											{
												xtype : 'button',
												text : '查询',
												iconCls : 'search',
												handler : function() {
													var searchPanel = Ext
															.getCmp('BookSearchForm');
													var grid = Ext
															.getCmp('BookGrid');
													if (searchPanel.getForm()
															.isValid()) {
														searchPanel
																.getForm()
																.submit(
																		{
																			waitMsg : '正在提交查询',
																			url : __ctxPath + '/admin/listBook.do',
																			success : function(
																					formPanel,
																					action) {
																				var result = Ext.util.JSON
																						.decode(action.response.responseText);
																				grid
																						.getStore()
																						.loadData(
																								result);
																			}
																		});
													}

												}
											} ]
								}), this.setup() ]
					}
					
			);
};
/**
 * 建立视图
 */
BookView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
BookView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel(
			{
				columns : [
						sm,
						new Ext.grid.RowNumberer(),
						{
							header : 'bookId',
							dataIndex : 'bookId',
							hidden : true
						},
						{
							header : '图书类别',
							dataIndex : 'typeName'
						},
						{
							header : '书名',
							dataIndex : 'bookName'
						},
						{
							header : '作者',
							dataIndex : 'author'
						},
						{
							header : 'ISBN号',
							dataIndex : 'isbn'
						},
						{
							header : '出版社',
							dataIndex : 'publisher'
						},
						{
							header : '价格',
							dataIndex : 'price'
						},
						{
							header : '存放地点',
							dataIndex : 'location'
						},
						{
							header : '所属部门',
							dataIndex : 'department'
						},
						{
							header : '图书数量',
							dataIndex : 'amount'
						},
						{
							header : '管理',
							dataIndex : 'bookId',
							sortable : false,
							width : 50,
							renderer : function(value, metadata, record,
									rowIndex, colIndex) {
								var editId = record.data.bookId;
								var str = '<button title="删除" value=" " class="btn-del" onclick="BookView.remove(' + editId + ')"></button>';
								str += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="BookView.edit(' + editId + ')"></button>';
								return str;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});

	var store = this.store();
	store.load( {
		params : {
			start : 0,
			limit : 25
		}
	});
	var grid = new Ext.grid.GridPanel( {
		id : 'BookGrid',
		tbar : this.topbar(),
		store : store,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : cm,
		sm : sm,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar( {
			pageSize : 25,
			store : store,
			displayInfo : true,
			displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
			emptyMsg : "当前没有记录"
		})
	});

	grid.addListener('rowdblclick', function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
			BookView.edit(rec.data.bookId);
		});
	});
	return grid;

};

/**
 * 初始化数据
 */
BookView.prototype.store = function() {
	var store = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + '/admin/listBook.do'
		}),
		reader : new Ext.data.JsonReader( {
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'id',
			fields : [ {
				name : 'bookId',
				type : 'int'
			}

			,{name:'typeName',mapping:'bookType.typeName'}, 'bookName', 'author', 'isbn', 'publisher', 'price',
					'location', 'department', 'amount' ]
		}),
		remoteSort : true
	});
	store.setDefaultSort('bookId', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
BookView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar( {
		id : 'BookFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : [ {
			iconCls : 'btn-add',
			text : '添加图书',
			xtype : 'button',
			handler : function() {
				new BookForm();
				Ext.getCmp('BookForm').remove('bookSnContainer');
				Ext.getCmp('amoutContainer').remove('bookAmoutButton');
				
			}
		}, {
			iconCls : 'btn-del',
			text : '删除图书',
			xtype : 'button',
			handler : function() {

				var grid = Ext.getCmp("BookGrid");

				var selectRecords = grid.getSelectionModel().getSelections();

				if (selectRecords.length == 0) {
					Ext.Msg.alert("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for ( var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.bookId);
				}

				BookView.remove(ids);
			}
		} ]
	});
	return toolbar;
};

/**
 * 删除单个记录
 */
BookView.remove = function(id) {
	var grid = Ext.getCmp("BookGrid");
	Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request( {
				url : __ctxPath + '/admin/multiDelBook.do',
				params : {
					ids : id
				},
				method : 'post',
				success : function() {
					Ext.Msg.alert("信息提示", "成功删除所选记录！");
					grid.getStore().reload( {
						params : {
							start : 0,
							limit : 25
						}
					});
				}
			});
		}
	});
};

/**
 * 
 */
BookView.edit = function(id) {
	new BookForm(id);
}
