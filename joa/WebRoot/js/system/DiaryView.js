Ext.ns('DiaryView');
/**
 * TODO: add class/table comments列表
 */
var DiaryView = function() {
	return new Ext.Panel(
			{
				id : 'DiaryView',
				title : '日志列表',
				iconCls:'menu-diary',
				autoScroll : true,
				items : [
						new Ext.FormPanel(
								{
									height : 40,
									frame : true,
									id : 'DiarySearchForm',
									layout : 'column',
									defaults : {
										xtype : 'label'
									},
									items : [
											{
												text : '请输入查询条件:'
											},
											{
												text : '日期从：'
											},
											{
												xtype : 'datefield',
												fieldLabel : '日期从：',
												format : 'Y-m-d',
												// readOnly:true,
												width : 90,
												name : 'from'
											},
											{
												text : '到'
											},
											{
												xtype : 'datefield',
												format : 'Y-m-d',
												width : 80,
												name : 'to'
											},
											{
												text : '关键字'
											},
											{
												xtype : 'textfield',
												width : 80,
												name : 'diary.content'
											},
											{
												text : '日志类型'
											},
											{
												xtype : 'combo',
												width : 80,
												hiddenName : 'diary.diaryType',
												mode : 'local',
												width : 90,
												editable : false,
												triggerAction : 'all',
												store : [ [ '0', '个人日志' ],
														[ '1', '日志' ] ]
											},
											{
												xtype : 'button',
												text : '查询',
												iconCls : 'search',
												handler : function() {
													var searchPanel = Ext
															.getCmp('DiarySearchForm');
													var grid = Ext
															.getCmp('DiaryGrid');
													if (searchPanel.getForm()
															.isValid()) {
														searchPanel
																.getForm()
																.submit(
																		{
																			waitMsg : '正在提交查询',
																			url : __ctxPath + '/system/searchDiary.do',
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
											},
											{
												xtype : 'button',
												text : '重置',
												iconCls : 'search',
												handler : function() {
													var searchPanel = Ext
															.getCmp('DiarySearchForm');
													searchPanel.getForm()
															.reset();
												}
											} ]
								}), this.setup()

				]
			});
};
/**
 * 建立视图
 */
DiaryView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
DiaryView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel(
			{
				columns : [
						sm,
						new Ext.grid.RowNumberer(),
						{
							header : 'diaryId',
							dataIndex : 'diaryId',
							hidden : true
						},
						{
							header : '日期',
							dataIndex : 'dayTime'
						},
						{
							header : '日志类型',
							dataIndex : 'diaryType',
							renderer:function(value){
				          	return value=='0'?"个人日志":"日志";
				          }
						},
						{
							header : '管理',
							dataIndex : 'diaryId',
							width : 100,
							renderer : function(value, metadata, record,
									rowIndex, colIndex) {
								var editId = record.data.diaryId;
								var str = '<input type="button" title="删除" value=" " class="btn-del" onclick="DiaryView.remove(' + editId + ')"/>';
								str += '&nbsp;<input type="button" title="编辑" value=" " class="btn-edit" onclick="DiaryView.edit(' + editId + ')"/>';
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
			limit : 10
		}
	});
	var grid = new Ext.grid.GridPanel( {
		id : 'DiaryGrid',
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
			pageSize : 10,
			store : store,
			displayInfo : true,
			displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
			emptyMsg : "当前没有记录"
		})
	});

	grid.addListener('rowdblclick', function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
			DiaryView.edit(rec.data.diaryId);
		});
	});
	return grid;

};

/**
 * 初始化数据
 */
DiaryView.prototype.store = function() {
	var store = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + '/system/listDiary.do'
		}),
		reader : new Ext.data.JsonReader( {
			root : 'result',
			totalProperty : 'totalCounts',
			id : 'id',
			fields : [ {
				name : 'diaryId',
				type : 'int'
			}, 'dayTime', 'content', 'diaryType', 'userId' ]
		}),
		remoteSort : true
	});
	store.setDefaultSort('diaryId', 'desc');
	return store;
};

/**
 * 建立操作的Toolbar
 */
DiaryView.prototype.topbar = function() {
	var toolbar = new Ext.Toolbar( {
		id : 'DiaryFootBar',
		height : 30,
		bodyStyle : 'text-align:left',
		items : [ {
			iconCls : 'btn-add',
			text : '添加日志',
			xtype : 'button',
			handler : function() {
				var noticeform = Ext.getCmp('DiaryForm');
				new DiaryForm();
			}
		}, {
			iconCls : 'btn-del',
			text : '删除日志',
			xtype : 'button',
			handler : function() {

				var grid = Ext.getCmp("DiaryGrid");

				var selectRecords = grid.getSelectionModel().getSelections();

				if (selectRecords.length == 0) {
					Ext.Msg.alert("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for ( var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.diaryId);
				}

				DiaryView.remove(ids);
			}
		} ]
	});
	return toolbar;
};

/**
 * 删除单个记录
 */
DiaryView.remove = function(id) {
	var grid = Ext.getCmp("DiaryGrid");
	Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
		if (btn == 'yes') {
			Ext.Ajax.request( {
				url : __ctxPath + '/system/multiDelDiary.do',
				params : {
					ids : id
				},
				method : 'post',
				success : function() {
					Ext.Msg.alert("信息提示", "成功删除所选记录！");
					grid.getStore().reload( {
						params : {
							start : 0,
							limit : 10
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
DiaryView.edit = function(id) {
	new DiaryForm(id);
}
