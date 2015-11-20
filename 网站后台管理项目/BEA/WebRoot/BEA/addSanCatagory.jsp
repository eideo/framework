<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addSanCatagory.jsp' starting page</title>
    
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
  	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/gray/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
  </head>
  <script type="text/javascript">
  	$(function(){
  		$.ajax({
  			type: "POST",
  			url:"getLanMuById",
  			data:'mId='+parent.getLanMuId(),
  			dataType:'json',
  			success:function(result){
  				$("#FlanMuName").val(result.lName);
  				$("#FMoXingName").val(result.mName);
  			}
  		});
  	});
  	
  	
  	
  </script>
  <body>
  <span><font color="blue">父栏目信息:</font></span>
  <div style="background:#92CAFF;height:7px;width:452px;margin-top:6px"></div>
  	
    <table >
    	<tr>
    	<td>栏目名称：</td>
    	<td><input id="FlanMuName"></td>
    	
    	<td>模型：</td>
    	<td><input id="FMoXingName"></td>
    	</tr>
    </table>
    <div style="background:#92CAFF;height:7px;width:452px;margin-bottom:40px"></div>
    
    
    <span ><font  color="blue">选择要添加的子栏目信息:</font></span>
  <div style="background:#92CAFF;height:7px;width:452px;"></div>
  	
    <table>
    	<tr>
    	<td>栏目名称：</td>
    	<td><input id="SLanMuName"></td>
    	
    	<td>模型：</td>
    	<td><input id="SMoXing" class="easyui-combobox" data-options="url:'getMoXing',valueField:'mId',textField:'mName'"></td>
    	</tr>
    </table>
    <div style="background:#92CAFF;height:7px;width:452px"></div>
    
    <button onclick="addZiLanMu()"  style="margin-right:10px;background-color: #014C9F"><font color="white">添加</font></button>
    <button  style="background-color: #014C9F"><font color="white">取消</font></button>
    
  </body>
</html>
