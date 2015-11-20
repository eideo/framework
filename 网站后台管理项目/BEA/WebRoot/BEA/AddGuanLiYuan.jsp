<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AddGuanLiYuan.jsp' starting page</title>
    
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
     function addguanliyuan(){
         var yonghuming=encodeURI($("#yonghuming").val());
         var name=encodeURI($("#name").val());
         var phone=encodeURI($("#phone").val());
         var bumenId=encodeURI($("#bumenId").combobox('getValue'));
         var ChuangJianRen=encodeURI($("#ChuangJianRen").combobox('getValue'));
         var duorendenglu= encodeURI($("input[name='duoren']:checked").val());
         var zhuangtai= encodeURI($("input[name='zhuangtai']:checked").val());
         var degnlutime=encodeURI($("#degnlutime").datebox('getValue'));
//         var  authorityNum=0;
// 	     var rows = $('#AuthorityAllocation').datagrid('getSelections');
// 	     for(var i=0; i<rows.length; i++){
// 	            authorityNum+=parseInt(rows[i].AuthorityNum, 10);
// 	       }
	  
            var url1="addyonghu?yonghuming="+encodeURI(yonghuming)+"&name="+encodeURI(name)+"&bumenId="+encodeURI(bumenId)+"&ChuangJianRen="+encodeURI(ChuangJianRen)+
                "&zhuangtai="+encodeURI(zhuangtai)+"&duorendenglu="+encodeURI(duorendenglu)+"&degnlutime="+degnlutime+"&phone="+phone;
            $.getJSON(url1,function(result){
               if(result=="OK"){
                 alert("新增成功！");
                 parent.refresh();
               }else{
                  alert("新增失败！");
               }
               
            });
          
         } 
    
</script>
  </head>
  <body>
  <div>
  <table>
       <td>
    	<fieldset>
    		<legend><strong>添加管理员</strong></legend>
    		<table style="margin-left:15px;margin-top:5px;margin-bottom:15px;">
    			<tr>
    				<td>&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;户&nbsp;&nbsp;名：<input type="text" id="yonghuming"/></td>
    				<td>&nbsp;&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;名：&nbsp;<input type="text" id="name"/></td>
    			</tr>
    			<tr>
    				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部&nbsp;&nbsp;门：&nbsp;&nbsp;<input class="easyui-combobox"id="bumenId"data-options="url:'getBuMen',
    				       valueField:'buMenId',textField:'buMenMingCheng',editable:false" />
											
					<td>&nbsp;&nbsp;&nbsp;管&nbsp;理&nbsp;组：<input   class="easyui-combobox" 
                     id="ChuangJianRen" name="ChuangJianRen" data-options="url:'getRolet',editable:false,
                      valueField:'RoletId',textField:'RoletMingCheng'"   /></td>
																	
    			</tr>
    			<tr>
    			<td>创建时间：<input class="easyui-datebox" type="text" id="degnlutime" 
    			  data-options="url:'getIP',
    				       valueField:'id',textField:'loginIp',editable:false,formatter:myformatter,parser:myparser"/></td>
    				<td>&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态&nbsp;：<input id="qiyong" onclick="trueOnClick()" size=2  type="radio" name="zhuangtai" value="true"/>启用&nbsp;
                    <input id="buqiyong" onclick="falseOnClick()" size=2 type="radio" name="zhuangtai" value="false"/>不启用</strong>
    			</tr>
    			<tr>
    				<td>&nbsp;&nbsp;电 话 号 码：<input type="text" id="phone"  /></td>
    				<td>&nbsp;&nbsp;&nbsp;多人登录：<input id="shi" onclick="trueOnClick()" size=2  type="radio" name="duoren" value="true"/>&nbsp;是&nbsp;&nbsp;
                    <input id="fou" onclick="falseOnClick()" size=2 type="radio" name="duoren" value="false"/>否</strong>
    			</tr>
    		</table>
    		</fieldset>
    		</td>
    	 </table>
    	<input type="reset" value="返回" onclick="parent.closeAddGuanLiYuan()" style="float:right;margin-top:5px;width:60px;">
    	<input type="submit" value="新增" onclick="addguanliyuan()"style="float:right;margin-top:5px;margin-right:5px;width:60px;">
  </div>
  </body>
</html>
