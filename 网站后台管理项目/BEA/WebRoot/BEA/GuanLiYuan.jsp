<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GuanLiYuan.jsp' starting page</title>
    
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
  			$('#frmAddGuanLiYuan').window('close');
  			});
  			 
  			function returnBtnColumn(guanliyuanid,row,rowIndex) {
            return  "<img src='image/edit.png'  onclick='editguanliyuan()'>&nbsp;&nbsp;"+
            "<img src='image/delete.png' ";
       }
        function editguanliyuan() {
           $('#iframeAddGuanLiYuan').attr('src','BEA/EditGuanLiYuan.jsp');
  			$('#frmAddGuanLiYuan').window('open');
			
		}
		function getguanliyuanID(){
 	     return  encodeURI($('#guanliyuanId').val());
 	  } 
      function DeleteEmployee(){ 
        var abc =$("#dg").datagrid('getSelections');
        for(var i=0;i<=abc.length;i++){
//         	alert(abc[i].ID);
        	var idee=abc[i].ID;
        	
        	$.ajax({
        		url:"deleteEmployee?employeeId="+idee,
        		type:'POST',
        		dataType:'json',
        		success:function(result){
        			
        		}
        	});
        	
         alert("删除成功！");
        }
     }
     function onClickRow(index,data){
             var id=data.ID;
             encodeURI($("#guanliyuanId").val(id));
     }
     function closeAddGuanLiYuan(){
  			$('#frmAddGuanLiYuan').window('close');
  			$("#dg").datagrid('reload');
  		}
     function AddOnClick(){
            $('#iframeAddGuanLiYuan').attr('src','BEA/AddGuanLiYuan.jsp');
  			$('#frmAddGuanLiYuan').window('open');
     }
     
     function refresh(){
     $("#dg").datagrid('reload');
     }
  	</script>
  </head>
  
  <body>
  <div id="w" class="easyui-window" title="管理员管理" data-options="fit:true,collapsible:false,
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
  	    <td>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>用户管理</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>详细帮助</strong></font></button>
    	  <button  style=" margin-left:10px;background-color:#2BC4F5" > <font size="2" color="#F2FCFB" ><strong>返回上一页</strong></font></button>
    	</td>
    	</div>
    	<div style="margin-top:10px;margin-left:10px">
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>提示信息</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	<font  size="2"  style="margin-left:10px;">1、管理员拥有后台权限,这里您可以对管理员进行管理。</font>
    	<br>
    	<font  size="2" style="margin-left:10px;">2、您可以添加新管理员,删除或更改管理员。</font>
    	<br><br>
    	<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>管理员管理</strong></font>
    	<hr color="#D9DDE0" style="width:99%">
    	</div>
    	
       <table id="dg"  class="easyui-datagrid" 
            data-options="collapsible:false,onClickRow:onClickRow,url:'getguanliyuan' ,fitColumns:false,
             ">
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true" ></th>
                <th data-options="field:'ID',width:50" >ID</th>
                <th data-options="field:'yonghuming',width:110">用户名</th>
                <th data-options="field:'name',width:110">姓名</th>
                <th data-options="field:'bumen',width:120">部门</th>
                <th data-options="field:'denglucishu',width:80">登录次数</th>
                <th data-options="field:'zuihoudengluIP',width:110">最后登录IP</th>
                <th data-options="field:'dengchushijian',width:130">最后登录/登出时间</th>
                <th data-options="field:'duorendenglu',width:100">多人登录</th>
                <th data-options="field:'zhuTai',width:120">状态</th>
                <th data-options="field:'guanliyuanid',align:'center',width:95,formatter:returnBtnColumn">操作</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
        <font   color="#235589"  size="2" style="margin-left:100px;">显示本页显示的所有数据</font>
         <button  onclick="DeleteEmployee()"><font size="3" color="#235589"  ><strong>删除选中的数据</strong></font></button>
         <button  onclick="AddOnClick()"style="margin-left:10px"><font size="3" color="#235589" ><strong>添加管理员</strong></font></button>
	</div>
   </div>
      <input type="hidden" id="guanliyuanId" value="0" />
    	<div id="frmAddGuanLiYuan" class="easyui-window" title="管理员信息" style="width:700px;height:250px;" 
    	data-options="resizable:false,collapsible:false,minimizable:false,maximizable:false,modal:true">
    	<iframe id="iframeAddGuanLiYuan" style="width:99%;height:98%;"></iframe>
    	</div>
   <input id="employee" type="hidden"/>
  </body>
</html>
