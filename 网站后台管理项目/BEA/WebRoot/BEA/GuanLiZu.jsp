<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GuanLiZu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/gray/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
  	<script type="text/javascript">
           $(document).ready(function(){
                     $('#dd').dialog({  
                     title:'权限分配',
                     width:550,
                     height:175, 
                     closed:false    
                 }).dialog('close');  
       });
		function append(){
		  var  authorityNum=0;
	      var rows = $('#AuthorityAllocation').datagrid('getSelections');
	      for(var i=0; i<rows.length; i++){
	            authorityNum+=parseInt(rows[i].quanxianzhi, 10);
	       }
		   var mingcheng=$("#add1").val();
		   var miaoshu=$("#add2").val();
		   if(authorityNum<=0){
                alert("请给此管理组分配权限！");
         }
         else{
		   var url="addguanliyuanzu?mingcheng="+encodeURI(mingcheng)+"&miaoshu="+encodeURI(miaoshu)+"&authorityNum="+authorityNum;
		      $.getJSON(url,function(result){
   						alert("新增成功！");
   						$("#dg").datagrid('reload');
   						$("#add1").val("");
		                $("#add2").val("");
		                $("#AuthorityAllocation").datagrid('reload');
   				});}
		}
		function reject(){
		$("#add1").val("");
		$("#add2").val("");
		$("#AuthorityAllocation").datagrid('reload');
		}
		function returnBtnColumn(guanlizuid,row,rowIndex) {
            return "<img src='image/delete.png'>"+
            "<img src='image/add.png' onclick='addClick("+rowIndex+")'>"+
            "<img src='image/edit.png'>"+
            "<img src='image/up.png'>"+
            "<img src='image/refresh.png'>";
        }
        function deleteOnClick(){
        var abc =$("#dg").datagrid('getSelections');
         var arr="";
    	   for(var k=0;k<abc.length;k++){
    		   arr=arr+abc[k].guanlizuid+",";
    	   }
    	   $.messager.confirm('确认','您确认删除吗？',function(r){    
      		    if (r){    
      		        $.ajax({
      			          type:"post",
      			            url:"deleteguanlizu?arr="+arr,
      			            dataType:"json",
      			            success:function(data){
      			                if(data=="ok"){
      			                	console.info(data);
      			                  alert("删除成功！");
      			                   $('#dg').datagrid('reload');
      			                }else{
      			                 alert("删除失败！");
      			                }
      			            }
      			        });
      		    }    
      		}); 
      		}
        function quanxian(){
             $('#dd').dialog('open');
        }
        function submitOnClick(){
        	alert('提交成功！');
        }
  	</script>
  </head>
  
  <body>
  <div id="w" class="easyui-window" title="管理组管理" data-options="fit:true,collapsible:false,
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
  }]    "style="padding: 10px 10px 0px 10xp">
	
  	<div>
  	    <div>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>管理组管理</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>详细帮助</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>返回上一页</strong></font></button>
    	</div>
    	
    	</div>
    	<div style="margin-top:10px;margin-left:10px">
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>提示信息</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	<font  size="2"  style="margin-left:10px;">通过设置不同的组可以达到分派权限的目的,具体功能有:添加、修改、删除管理组、添加管理组成员、设置管理组导航菜单,设置管理组栏目等强烈建议只让"超级管理员"管理此模块。</font>
    	<br><br>
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>管理组管理</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	</div>
    
    <table id="dg"  class="easyui-datagrid" 
            data-options="collapsible:false,url:'getGuanLiZu' ,fitColumns:false,
           ">
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true" ></th>
                <th data-options="field:'guanlizu',width:385,align:'center'" >名称</th>
                <th data-options="field:'describe',width:380,align:'center'">描述</th>
                <th data-options="field:'guanlizuid',align:'center',width:250,formatter:returnBtnColumn">操作</th>
            </tr>
        </thead>
    </table>
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()" ></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="reject()"></a>
	    <font style="margin-left:32px">添加管理组</font><input id="add1"  onblur="xinashi()"></input> <input id="add2"style="margin-left:195px"></input>
	    <button  onclick="quanxian()" style="margin-left:180px"><font size="3" ><strong>权限值分配</strong></font></button>
	</div>
	<div style="margin-left:350px">
	  
	     <button  onclick="submitOnClick()"><font size="3" ><strong>&nbsp;提&nbsp;交&nbsp;</strong></font></button>
         <button  onclick="deleteOnClick()"  style="margin-left:50px"><font size="3"><strong>&nbsp;删除选中记录&nbsp;</strong></font></button>     
	</div>
   </div>
 
    <div id="dd" >
	          <table  id="AuthorityAllocation" class="easyui-datagrid"   data-options="url:'getAuthority'" style="width:400px;">				               
				                <thead>
					             <tr>
                                	
                                	<th data-options="field:'quanxianzhi',hidden:true"></th>          
						            <th data-options="field:'id',hidden:true"></th>
						            <th data-options="field:'ck',checkbox:true"></th>
						            <th data-options="field:'quanxianName',width:'120'">全选/全不选</th>
					             </tr>
				               </thead>
			 </table>
	</div>
  </body>
</html>
