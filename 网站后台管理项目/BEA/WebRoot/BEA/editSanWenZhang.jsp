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
    var access=<%=session.getAttribute("biy")%>;
  	$(function(){
  		//alert(parent.getWenZhangId());
  		//alert(parent.getDgvLanMuId());
  		
  		$.ajax({
  			type: "POST",
  			url:"getLanMuById",
  			data:'mId='+parent.getDgvLanMuId(),
  			dataType:'json',
  			success:function(result){
  				$("#FlanMuName").val(result.lName);
  				$("#FMoXingName").val(result.mName);
  				$.ajax({
		  			type: "POST",
		  			url:"getWenZhangByWid",
		  			data:'wId='+parent.getWenZhangId(),
		  			dataType:'json',
		  			success:function(result){
		  				$("#Title").val(result.title);
		  				$("#Author").val(result.author);
		  				$("#Date").datebox('setValue',result.fabuShiJian);
		  				$("#Content").val(result.content);
		  				if(result.shou){
		  					$("input[name='shouYe']").attr("checked", true);
		  				}
		  				if(result.ding){
		  					$("input[name='top']").attr("checked", true);
		  				}
		  				
		  				if(result.jian){
		  					$("input[name='tuiJian']").attr("checked", true);
		  				}
		  				
		  				if(result.shen){
		  					$("input[name='shenHe']").attr("checked", true);
		  				}
		  				
		  				if(result.zuo){
		  					$("input[name='zuo']").attr("checked", true);
		  				}
		  			}
		  		});	
  			}
  		});
  	});
  	
  	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
  	
  	
  	function addZiLanMu(){
  		if(access & 2){
	  		if($("#Title").val()==""){
	  			alert("请输入标题！");
	  		}else if($("#Author").val()==""){
	  			alert("请输入作者！");
	  		}else if($("#Date").datebox('getValue')==""){
	  			alert("请选择时间！");
	  		}else if($("#Content").val()==""){
	  			alert("请输入文章内容!");
	  		}else{
	  			$.ajax({
	  				type:"POST",
	  				url:'addWenZhang?pid='+parent.getLanMuId(),
	  				data:$("#article").serialize(),
	  				dataType:'json',
	  				success:function(result){
	  				  if(result!="0")
	  				  alert("添加成功！");
	  				  parent.refreshdgv();
	  				}
	  			});
	  		}
  		}else{
  			alert("权限不足，请联系管理员！");
  		}
  	}
  	
  	
  	function editWenZhang(){
  		if(access & 2){
  		
  		//alert(parent.getWenZhangId());
  		if($("#Title").val()==""){
  			alert("请输入标题！");
  		}else if($("#Author").val()==""){
  			alert("请输入作者！");
  		}else if($("#Date").datebox('getValue')==""){
  			alert("请选择时间！");
  		}else if($("#Content").val()==""){
  			alert("请输入文章内容!");
  		}else{
  			$.ajax({
  				type:"POST",
  				url:'modifyWenZhang?wId='+parent.getWenZhangId(),
  				data:$("#article").serialize(),
  				dataType:'json',
  				success:function(result){
  				  if(result=="true"){
  				  alert("修改成功！");
  				  
  				  parent.refreshdgv();
  				  parent.closeWin();
  				  }
  				}
  			});
  		}
  		}else{
  			alert("权限不足，请联系管理员！");
  		}
  	}
  </script>
  <body>
  <span><font color="blue">栏目信息:</font></span>
  <div style="background:#92CAFF;height:7px;width:652px;margin-top:6px"></div>
  	
    <table >
    	<tr>
    	<td>栏目名称：</td>
    	<td><input id="FlanMuName"></td>
    	
    	<td style="margin-left:50px">模型：</td>
    	<td><input id="FMoXingName"></td>
    	</tr>
    </table>
    <div style="background:#92CAFF;height:7px;width:652px;margin-bottom:10px"></div>
    
    
    <span ><font  color="blue">文章信息:</font></span>
  <div style="background:#92CAFF;height:7px;width:652px;"></div>
  <form id="article" action="">
    <table>
    	<tr>
    		<td>标题：</td>
    		<td colspan="3"><input id="Title" name="title" style="width:550px" id="SLanMuName"></td>
    	</tr>
    	<tr>
    		<td>作者：</td>
    		<td><input name="author" id="Author" style="width:200px" id="SLanMuName"></td>
    		
    		<td>发布时间：</td>
    		<td><input id="Date" name="date" class="easyui-datebox" id="SLanMuName"></td>
    	</tr>
    	
    	
    	<tr>
    		<td colspan="4">
    		<table>
    			<tr>
    				<td>首页否：<input name="shouYe" style="margin-right: 40px" type="checkbox" id="SLanMuName"></td>
    				<td  >顶否：<input name="top" type="checkbox" style="margin-right: 40px"  id="SLanMuName"></td>
    				<td >推荐否：<input name="tuiJian" type="checkbox" style="margin-right: 40px" id="SLanMuName"></td>
    				<td >审核否：<input name="shenHe" style="margin-right: 40px" type="checkbox" id="SLanMuName"></td>
    				<td >置左否：<input name="zuo"  type="checkbox" id="SnMuName"></td>
    			</tr>
    		</table>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>内容：</td>
    		
    		<td colspan="3"><textarea id="Content" name="content" style="width:600px;height:140px" ></textarea></td>
    	</tr>
    </table>
    </form>	
    <div style="background:#92CAFF;height:7px;width:652px"></div>
    
    <button onclick="editWenZhang()"  style="margin-right:10px;background-color: #014C9F"><font color="white">修改</font></button>
    <button  style="background-color: #014C9F"><font color="white">取消</font></button>
    
  </body>
</html>
