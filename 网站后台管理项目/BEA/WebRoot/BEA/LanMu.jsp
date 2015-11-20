<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>My JSP 'LanMu.jsp' starting page</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/gray/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
  </head>
    <script type="text/javascript">
       $(function(){ 
    	    $('#loadMoX').combobox({
    	    	url:'getAllMoXing',
    	    	onSelect: function(rec){    
    	            var url = 'getLanMuFatherbyMX?id='+rec.id; 
    	            $('#dg').treegrid({url:url});
    	            }
    	    });
    	    $('#dg').treegrid({
    	        url:'getLanMuFather',
    	        idField: 'id',
    	        treeField: 'name',
    	        fitColumns:true, 	        
    	        singleSelect:false,
    	        columns:[[
                {field:'k',title:'Code',checkbox:true},          	       
    	        {field:'name',title:'名称(所属模型)',width:150,align:'left'},
    	        {field:'sort',title:'排序号',width:20,align:'center'},
    	        {field:'id',title:'操作',width:150,align:'center',formatter: function(value,row,index){
    	        	 return  "<img src='image/edit_add.png' onclick='addClick("+value+")'>"+"　　　"+
		    	             "<img src='image/pencil.png' onclick='editClick("+value+")'>"+"　　　"+
		    	             "<img src='image/cancel.png' onclick='delClick("+value+")'>";
    			   }
                }
    	        ]],
    	        toolbar:'#tb'
    	        });
    	    $('#dsave').dialog({
    	    	 closed: true,    
    	    	 cache: false,
    	    	 buttons:[{
    					text:'确定',
    					iconCls:'icon-save',
    					handler:function(){
    					if($('#name').val()!="" && $('#sort').val()!="" && $('#moXing').combobox('getText')!=""){
    						if($('#xiugai').val()>=0){							
    							var urledit="editLanM?pid="+$('#pid').val()+"&name="+encodeURI($('#name').val())+"&sort="+$('#sort').val()+"&moXing="+$('#moXing').combobox('getValue')+"&id="+$('#xiugai').val();
    							$.getJSON(urledit,function(data){		   		   
     							   if (data.msg=="ok") {
     									$.messager.show({
     										title : '提示',
     										msg : "恭喜，修改成功!",
     									});
     									$('#xiugai').val(-1);
     									$('#dg').treegrid('reload');
     								}	
     						      });
    						}else{
    						var url="addLanM?pid="+$('#pid').val()+"&name="+encodeURI($('#name').val())+"&sort="+$('#sort').val()+"&moXing="+$('#moXing').combobox('getValue');
    						 $.getJSON(url,function(data){		   		   
    							   if (data.msg=="ok") {
    									$.messager.show({
    										title : '提示',
    										msg : "恭喜，新增成功!",
    									});
    									$('#dg').treegrid('reload');
    								}	
    						      }); }
    					}else{
    					      alert("请输入完整的信息!");
    					   }
    					}
    				},{
    					text:'关闭',
    					iconCls:'icon-cancel',
    					handler:function(){
    					    $('#dsave').dialog('close');
    					}
    				}]
    	    });
    	    
    	    $('#dlanmu').dialog({
   	    	 closed: true,    
   	    	 cache: false,
   	    	 buttons:[{
   					text:'确定',
   					iconCls:'icon-save',
   					handler:function(){
   					if($('#xinname').val()!="" && $('#xinsort').val()!="" && $('#xinmoXing').combobox('getText')!=""){  						
   						var url="addXinLanM?name="+encodeURI($('#xinname').val())+"&sort="+$('#xinsort').val()+"&moXing="+$('#xinmoXing').combobox('getValue');
   						 $.getJSON(url,function(data){		   		   
   							   if (data.msg=="ok") {
   									$.messager.show({
   										title : '提示',
   										msg : "恭喜，新增成功!",
   									});
   									$('#dg').treegrid('reload');
   								}	
   						      }); 
   					}else{
   					      alert("请输入完整的信息!");
   					   }
   					}
   				},{
   					text:'关闭',
   					iconCls:'icon-cancel',
   					handler:function(){
   					    $('#dlanmu').dialog('close');
   					}
   				}]
   	    });
       });
       function addClick(value){
    	   if(<%=session.getAttribute("biy")%>& 8){
    	   $('#dsave').dialog({
  	    	 closed: false, 
  	    	 title:'新增栏目'
  	    });   	 
    	   $('#pid').val(value);
    	   $('#name').val('');
           $('#sort').val('');
           $('#moXing').combobox('setValue','');
       
       }else{
    	   alert("您的权限不足，请联系管理员！");
       }
       }
       function editClick(value){
    	   if(<%=session.getAttribute("biy")%>& 8){
    	   $.ajax({
		          type:"post",
		            url:"getEditLM?id="+value,
		            dataType:"json",
		            success:function(data){
		                $('#pid').val(data[0].pid);
		                $('#name').val(data[0].name);
		                $('#sort').val(data[0].sort);
		                $('#moXing').combobox('setValue',data[0].moXing);
		                $('#xiugai').val(data[0].id);
		                $('#dsave').dialog({
		       	    	 closed: false,   
		       	    	 title:'修改栏目'
		       	    });  
		            }
		        }); 
    	   }else{
        	   alert("您的权限不足，请联系管理员！");
           }
       }
       function delClick(value){
    	   if(<%=session.getAttribute("biy")%>& 8){
    	   $.messager.confirm('确认','您确认想要删除该栏目吗？',function(r){    
   		    if (r){    
   		        $.ajax({
   			          type:"post",
   			            url:"removeLanMu?id="+value,
   			            dataType:"json",
   			            success:function(data){
   			                if(data.msg=="ok"){
   			                  alert("删除成功！");
   			                   $('#dg').treegrid('reload');
   			                }else{
   			                 alert("删除失败！");
   			                }
   			            }
   			        });
   		    }    
   		});
    	   }else{
        	   alert("您的权限不足，请联系管理员！");
           }
       }
      
       function delpiLiang(){
    	   if(<%=session.getAttribute("biy")%> & 8){
    	   console.info( $('#dg').treegrid('getSelections'));
    	   var count=$('#dg').treegrid('getSelections');
    	   console.info(count);
    	   var arr="";
    	   for(var k=0;k<count.length;k++){
    		   arr=arr+count[k].id+",";
    	   }
    	   console.info(arr);
    	   $.messager.confirm('确认','您确认想要删除栏目吗？',function(r){    
      		    if (r){    
      		        $.ajax({
      			          type:"post",
      			            url:"removePiLiang?arr="+arr,
      			            dataType:"json",
      			            success:function(data){
      			                if(data.msg=="ok"){
      			                  alert("删除成功！");
      			                   $('#dg').treegrid('reload');
      			                }else{
      			                 alert("删除失败！");
      			                }
      			            }
      			        });
      		    }    
      		}); 
    	   }else{
        	   alert("您的权限不足，请联系管理员！");
           }
       }
       function addStu(){
    	   if(<%=session.getAttribute("biy")%> & 8){
    	   $('#xinmoXing').combobox({
   	    	url:'getAllMoXing',
   	    	onLoadSuccess:function(){
   	    		$('#dlanmu').dialog({
   	   	    	 closed: false, 
   		    });
   	    	}
   	    });
    	   }else{
        	   alert("您的权限不足，请联系管理员！");
           }
       }
    </script>
  <body>
  <div id="w" class="easyui-window"  data-options="fit:true,collapsible:false,
  	 tools: [{    
    iconCls:'icon-undo',  
    handler:function(){alert('undo')}    
  },{    
    iconCls:'icon-redo',    
    handler:function(){alert('redo')}    
  },
  {    
    iconCls:'icon-reload',    
    handler:function(){alert('reload')}    
  }]    " style="padding: 10px 0 0 0">
	
  	<div>
  	    
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>栏目管理</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>详细帮助</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>返回上一页</strong></font></button>
    	
    	</div>
    	<div style="margin-top:10px;margin-left:10px">
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>提示信息</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	<font  size="2"  style="margin-left:10px;">1、栏目管理,这里您可以对栏目进行管理。</font>
    	<br>
    	<font  size="2" style="margin-left:10px;">2、您可以添加新栏目,删除或更改栏目。</font>
    	<br><br>
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>分类管理(所有模型》添加分裂)</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	</div>  
        <table id="dg"></table>
    </div>
    
    <div id="dsave" class="easyui-dialog" title="增加栏目" style="width:600px;height:400px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true">  
        <input id="pid" type="hidden"/> 
        <input id="xiugai" value="-1" type="hidden"/>
          <form id="editform" method="post" style="margin:0 auto;background:#F4F4F4">
    	   <table style="margin:0 auto">   	      
               <tr>              
                  <th>名称：</th><td><input id="name" name="name" class="easyui-validatebox" data-options="required:true" ></td>
               </tr>
               <tr>
                  <th>排序号：</th><td><input id="sort" name="sort" class="easyui-numberbox" data-options="required:true" ></td>
               </tr>
               <tr>
                  <th>模型：</th><td><input id="moXing" name="moXing" class="easyui-combobox" name="moXing" data-options="valueField:'id',textField:'text',url:'getAllMoXing'" /></td>
               </tr>
            </table>
         </form>
    </div>  
    <div id="tb">
       <table>
         <tr>
        <td><input id="loadMoX" class="easyui-combobox" name="moXing" data-options="valueField:'id',textField:'text'" /></td>
        <td><a  class="easyui-linkbutton"   onclick="addStu()" data-options="iconCls:'icon-add',plain:true">添加新栏目</a></td>
        <td><a  class="easyui-linkbutton"   onclick="delpiLiang()" data-options="iconCls:'icon-remove',plain:true">批量删除</a></td>     
        </tr>
        </table>
   </div>
   
   <div id="dlanmu" class="easyui-dialog" title="增加x新栏目" style="width:600px;height:400px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true">  
        <input id="pid" type="hidden"/> 
        <input id="xiugai" value="-1" type="hidden"/>
          <form id="editform" method="post" style="margin:0 auto;background:#F4F4F4">
    	   <table style="margin:0 auto">   	      
               <tr>              
                  <th>名称：</th><td><input id="xinname"  class="easyui-validatebox" data-options="required:true" ></td>
               </tr>
               <tr>
                  <th>排序号：</th><td><input id="xinsort"  class="easyui-numberbox" data-options="required:true" ></td>
               </tr>
               <tr>
                  <th>模型：</th><td><input id="xinmoXing"  class="easyui-combobox" name="moXing" data-options="valueField:'id',textField:'text'" /></td>
               </tr>
            </table>
         </form>
    </div>
    
  </body>
</html>
