var NewsForm = function(newsId) {
	this.newsId = newsId;
	var fp = this.setup();
	var window = new Ext.Window({
		id : 'NewsFormWin',
		title : 'News详细信息',
		width : 500,
		height : 494,
		modal : true,
		minWidth : 300,
		minHeight : 200,
		layout : 'anchor',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [this.setup()],
		buttons : [{
					text : '保存',
					iconCls:'btn-save',
					handler : function() {
						var fp = Ext.getCmp('NewsForm');
						//alert(Ext.Ajax.serializeForm(fp.getForm().getEl().dom));
						//return false;
						if (fp.getForm().isValid()) {
							fp.getForm().submit({
										method : 'post',
										waitMsg : '正在提交数据...',
										success : function(fp, action) {
											Ext.Msg.alert('操作信息', '成功信息保存！');
											Ext.getCmp('NewsGrid').getStore()
													.reload();
											window.close();
										},
										failure : function(fp, action) {
											Ext.MessageBox.show({
														title : '操作信息',
														msg : '信息保存出错，请联系管理员！',
														buttons : Ext.MessageBox.OK,
														icon : 'ext-mb-error'
													});
											window.close();
										}
									});
						}
					}
				}, {
					text : '取消',
					iconCls:'btn-delete',
					handler : function() {
						window.close();
					}
				}]
	});
	window.show();
};

NewsForm.prototype.setup = function() {
	var _url = __ctxPath+'/info/treeNewsType.do?opt=treeSelector';
	var newsTypeSelector =  new TreeSelector('newsTypeSelect',_url,'新闻类型','typeId');
	var formPanel = new Ext.FormPanel({
				url : __ctxPath + '/info/saveNews.do',
				layout : 'form',
				id : 'NewsForm',
				frame : true,
//				defaults : {
//					widht : 400,
//					anchor : '100%,100%'
//				},
				labelWidth : 55,   
				formId : 'NewsFormId',
				defaultType : 'textfield',
				items : [{
							name : 'news.newsId',
							id : 'newsId',
							xtype : 'hidden',
							value : this.newsId == null ? '' : this.newsId
						}, {
							xtype:'container',
							layout:'column',
							defaultType:'textfield',
							height:26,
							items:[
									{
										//fieldLabel : '类别ID',
										name : 'news.typeId',
										id : 'typeId',
										xtype : 'hidden'
									},{
										xtype:'label',
										text:'新闻类型:'
									},
										newsTypeSelector,
									{
										xtype:'label',
										text:'新闻作者:'
									},{
										name : 'news.author',
										width:'155',
										id : 'author'
									}
								]
							
						},
						{
							xtype:'container',
							layout:'column',
							defaultType:'textfield',
							height:26,
							items:[{
										xtype:'label',
										text:'新闻状态:'
									},
									{
										hiddenName:'news.status',
										id : 'status',
										xtype:'combo',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store:[['1','生效'], ['0','禁用']]
									},{
										xtype:'label',
										text:'新闻标题:'
									},{
										name : 'news.subject',
										width:'155',
										id : 'subject'
									}
									]
						},{
							xtype : 'container',
							layout : "column",
							defaultType : 'textfield',
							height:50,
							items:[
								{
									xtype:'label',
									text:'新闻图标:'
								},{
									xtype:'hidden',
									name : 'news.subjectIcon',//新闻图标
									width:'255',
									id : 'subjectIcon'
								},{
									id :'displayAtForm',
									xtype:'panel',
									html:'<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/images/default_newsIcon.jpg" border="0"/>'									
								},{
									xtype:'button',
									text:'选择',
									iconCls:'btn-choose',
									handler:function(){
										
									}
								},{
									xtype:'button',
									iconCls:'btn-upload',
									text:'上传',
									handler:function(){
										var subjectIcon = Ext.getCmp('subjectIcon');
										var dialog = App.createUploadDialog({
																file_cat : 'info/news',
																callback : uploadImage
															});
											//dialog.show('queryBtn');
										if(subjectIcon.value != '' && subjectIcon.value !=null && subjectIcon.value !='undefined'){
											var msg = '再次上传需要先删除原有图片,';
											Ext.Msg.confirm('信息确认', msg+'是否删除？', function(btn) {
												if (btn == 'yes') {
													//删除图片
													Ext.Ajax.request({
														url:__ctxPath + '/system/deleteFileAttach.do',
														method:'post',
														params:{filePath:subjectIcon.value},
														success:function(){
															var newsId = Ext.getCmp('newsId').value;
															if(newsId != '' &&  newsId !=null &&  newsId !='undefined'){
																Ext.Ajax.request({
																	url:__ctxPath + '/info/iconNews.do',
																	method:'post',
																	params:{newsId: newsId},
																	success:function(){
																		subjectIcon.setValue('');
																		//改为默认图标
																		Ext.getCmp('displayAtForm').body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/images/default_newsIcon.jpg" border="0"/>');
																		Ext.getCmp('NewsGrid').getStore().reload();
																		dialog.show('queryBtn');
																	}
																});
															}else{
																subjectIcon.setValue('');
																//改为默认图标
																Ext.getCmp('displayAtForm').body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/images/default_newsIcon.jpg" border="0"/>');
																dialog.show('queryBtn');
															}
														}
													});
												}
											})
										}else{
											dialog.show('queryBtn');
										}
									}
								},{
									xtype:'button',
									iconCls:'btn-del',
									text:'删除',
									handler:function(){
										var subjectIcon = Ext.getCmp('subjectIcon');
										if(subjectIcon.value != null && subjectIcon.value !='' && subjectIcon.value !='undefined'){
											var msg = '图片一旦删除将不可恢复,';
											Ext.Msg.confirm('确认信息',msg+'是否删除?',function(btn){
												if(btn == 'yes'){
													Ext.Ajax.request({
														url:__ctxPath + '/system/deleteFileAttach.do',
														method:'post',
														params:{filePath:subjectIcon.value},
														success:function(){
															var newsId = Ext.getCmp('newsId').value;
															if(newsId != '' && newsId !=null && newsId !='undefined'){
																	Ext.Ajax.request({
																	url:__ctxPath + '/info/iconNews.do',
																	method:'post',
																	params:{newsId:newsId},
																	success:function(){
																			subjectIcon.setValue('');
																			//这里改为默认图标
																			Ext.getCmp('displayAtForm').body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/images/default_newsIcon.jpg" border="0"/>');
																			Ext.getCmp('NewsGrid').getStore().reload();
																		}
																	});
															}else{
																subjectIcon.setValue('');
																//这里改为默认图标
																Ext.getCmp('displayAtForm').body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/images/default_newsIcon.jpg" border="0"/>');
															}
														}
													});
												}
											});
										}// end if
										else{
											Ext.Msg.alert('提示信息','您还未增加图标.');
										}
									}
								}]
						},{
							fieldLabel : '内容',
							name : 'news.content',
							id : 'content',
							xtype:'htmleditor',
							width:400,
							height:300
						}
				]
			});

	if (this.newsId != null && this.newsId != 'undefined') {
		formPanel.getForm().load({
					deferredRender : false,
					url : __ctxPath + '/info/getNews.do?newsId=' + this.newsId,
					waitMsg : '正在载入数据...',
					success : function(form, action) {
						var typeId = Ext.getCmp('typeId');
						var typeTree = Ext.getCmp('typeTree');
						var treeNode = typeTree.getNodeById(typeId.value);
						var newsTypeSelect = Ext.getCmp('newsTypeSelect');
						newsTypeSelect.setValue(treeNode.text);
						//载入成功后载入图片
						var display = Ext.getCmp('displayAtForm');
						var sub = Ext.getCmp('subjectIcon');
						if(sub.value!=''){
							display.body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/attachFiles/'+sub.value+'" border="0"/>');
						}
						// Ext.Msg.alert('编辑', '载入成功！');
					},
					failure : function(form, action) {
						// Ext.Msg.alert('编辑', '载入失败');
					}
				});
	}
	
	return formPanel;

};
/**
 * 上传新闻图标回调函数
 * @param {} data
 */
function uploadImage(data){
		var display = Ext.getCmp('displayAtForm');
		var sub = Ext.getCmp('subjectIcon');
		sub.setValue(data[0].filepath);
		display.body.update('<img style="border:0;" width="48" height="48" src="'+__ctxPath+'/attachFiles/'+data[0].filepath+'" border="0"/>');
	}
