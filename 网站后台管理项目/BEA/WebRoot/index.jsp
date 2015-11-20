<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    	<title>BEA Ultimate</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
  		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
  		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  		<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/gray/easyui.css" type="text/css"></link>
  		<link rel="stylesheet" href="jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
  		 <script type="text/javascript" src="jslib/luUtil.js"></script>
  		 </head>
 	<script type="text/javascript" >	    
 		var userid;
 		var biy;
 		$(document).ready(function(){	
 			$('#user_login_loginDialog').dialog({
 				title:'登录',modal:true,closable:false,
 		         buttons:[{
 						text:'注册',
 						iconCls:'icon-edit',
 						handler:function(){
 						  $('#index_regform input').val('');
 						  $('#index_regDialog').dialog('open');
 						}
 					},{
 						text:'登录',
 						iconCls:'icon-save',				
 						handler:function(){							 							
 						  $.getJSON('denglu?name='+encodeURI($('#logname').val())+'&lgpwd='+encodeURI($('#logpwd').val()),
 		                   function (data){
 							 console.info(data);	 		                    
 		 					if (data.cf == "ok") {		 											
 		 						$('#user_login_loginDialog').dialog('close');
 		 						$.ajax({
 		 			 				type:"POST",
 		 			 				url:'getuserId',
 		 			 				dataType:'json',
 		 			 				success:function(result){
 		 			 					userid=result.id;
 		 			 					biy=result.biy;
 		 			 					//alert(biy);
 		 			 				}
 		 			 				
 		 			 			})
 		 					}
 		 					$.messager.show({
 			 						title : '提示',
 			 						msg : data.MSG
 			 					});
 		                                });
 						}
 					}]
 			});
 			$('#frmModules').window('close');
            $('#index_regDialog').dialog({
 				title : '注册',
 				modal : true,
 				buttons : [ {
 					text : '注册',
 					iconCls : 'icon-edit',
 					handler : function() {
 						$('#index_regform').submit();
 					}
 				} ]
 			}).dialog('close');
 		
 		 $('#user_login_loginForm').form({
 			 url : 'logUser?name='+$('#logname').val()+"&lgpwd="+$('#logpwd').val(),
 				success : function(data) {	
 					var obj=$.parseJSON(data);
 					if (obj.cf == "ok") {
 						console.info(data);						
 						$('#user_login_loginDialog').dialog('close');						
 					}
 					$.messager.show({
	 						title : '提示',
	 						msg : obj.MSG
	 					});
 				}
 			});
 		 $('#index_regform').form(
 				{
 				url : 'regUser?id='+1,
 				success : function(data) {
 					var obj=$.parseJSON(data);
 					if (obj.cf == "ok") {
 						console.info(data);						
 						$('#index_regDialog').dialog('close');						
 					}
 					$.messager.show({
	 						title : '提示',
	 						msg : obj.MSG
	 					});
 				}
 			});
 		$('#user_login_loginForm input').val('');	
 		});
 		function openfrmModules(url,frmTitle){
 		    $('#iframeModules').attr('src',url);
 			$('#frmModules').window({title:frmTitle});
 			$('#frmModules').window('open');
 		}	
 		
 		function zhuxiao(){			
 			  $.getJSON('tuichu?id='+userid,
	                   function (data){
						 console.info(data);	 		                    
	 					if (data.cf == "ok") {		 			
	 						$('#user_login_loginForm input').val('');
	 						$('#user_login_loginDialog').dialog('open');						
	 					}	 				
	                     });
 		}
 	</script>

  
  <body class="easyui-layout" style="margin:0px;padding:0px,text-align:center; ">
    <div region="north" style="height:30px;" data-options="border:false"
    	 id="frmModules" class="easyui-window" data-options="fit:true,inline:true,draggable:false,resizable:false,
      collapsible:false,minimizable:false,maximizable:false">
			<iframe id="iframeModules"  scrolling="no" style="width:99.5%;height:98.5%;"></iframe>
		</div>
    	<div class="easyui-layout" data-options="fit:true" >
    		<div region="north" data-options="border:false" style="height:60px;background-color:#1A76CB">
    			<div style="float:left;width:300px;text-align:left;margin-top:5px;">
    			<font color="#ffffff" size="6" style="font-style:italic;margin-left:10px"><strong>  B  E  A </strong></font>
    			<font color="#ffffff" size="3"><strong>U l t i m a t e</strong></font>
    			</div>
    			<div style="margin-top:30px;float: left;">
    			<input class="easyui-searchbox" style="width:200px;" ></input>
    			</div>
    			<div style="margin-top:30px;background-color: gray;float:right;">
    			   <button onclick="zhuxiao()">注销</button>
    			</div>
    		</div>
    		<div region="west" style="width:140px;height:250px;"   title="后台管理" data-options="border:false,collapsible:false" >
                <div id="aa" class="easyui-accordion" data-options="fit:true">   
                <div title="网站管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
                <a  class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true," 
    			onclick="$('#mainIFarme').attr('src','BEA/XinWenGuanLi.jsp');">新闻文章管理</a> 
                <a class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true," 
    			>董事长信箱</a> 
    			<a  class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true," 
    			>单页文章管理</a> 
    			<a class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true," 
    			>友情链接管理</a> 
            </div>   
         <div title="管理员及权限" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">   
         <a class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true"  
     			onclick="$('#mainIFarme').attr('src','BEA/GuanLiZu.jsp');">管理组管理</a> 
     			<a class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true"  
     			onclick="$('#mainIFarme').attr('src','BEA/GuanLiYuan.jsp');">管理员管理</a>   
          </div>   
         <div title="核心设置">   
         <a class="easyui-linkbutton" style="margin-left:18px;margin-top:7px;" data-options="plain:true" 
     			onclick="$('#mainIFarme').attr('src','BEA/LanMu.jsp');">栏目管理</a>  
         </div>  
         <div title="系统设置">   
         </div> 
        </div>  
    </div>
    		
    	
        <div region="center" >
           <iframe id="mainIFarme" scrolling="no" style="width:99.5%;height:98.5%;"></iframe>
    		</div>
    		 <div region="south" style="height:28px" >
         <button  style=" margin-left:10px;background-color:#014C9F" > <font size="2" color="#F2FCFB" ><strong>常用设置</strong></font></button>
    	
    		</div>
    	</div>
    	<div id="user_login_loginDialog" > 
	<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input  id="logname" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入用户名'" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input  id="logpwd"  type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入密码'" />
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="index_regDialog">
	<form id="index_regform" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input   name="yonghut.yongHuMing" class="easyui-validatebox" data-options="required:true,missingMessage:'必填'" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'minLength[6]'" />
				</td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="repwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPassword[\'#index_regform input[name=pwd]\']'" />
				</td>
			</tr>
			<tr>
				<th>真名</th>
				<td><input name="yonghut.realName"  class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input name="yonghut.teltPhone"  class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>电子邮件</th>
				<td><input name="yonghut.email"  class="easyui-validatebox" data-options="required:true,validType:'email'" />
				</td>
			</tr>
		</table>
	</form>
</div>
  </body>
</html>
