<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'XinWenGuanLi.jsp' starting page</title>
    
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
	<style type="text/css">
		.aImg a:hover{
			text-decoration: underline;
			color:red; }
	</style>
  	<script type="text/javascript">
  		var access=<%=session.getAttribute("biy")%>;
  	
  		function categoryOnClick(){
  		 var id=$("#categoryTree").tree("getSelected").id;
  		
  			var url1="getAllWenZhangByLanMuId?lanmuid="+id;
  				$('#dagWenZhang').datagrid({
  					url:url1
  			});
  		}
  		
  		$(document).ready(function(){
 			$('#frmModules').window('close');
 		});
 		
 		function openfrmModules(url,frmTitle){
 		    $('#iframeModules').attr('src',url);
 			$('#frmModules').window({title:frmTitle});
 			$('#frmModules').window('open');
 		}
  		
  		function onContextMenu(e, node){
		   e.preventDefault();
		 
		    $('#Menu123').menu('show', {
		        left:e.pageX,
		        top:e.pageY
		    });       
		}
  		
  		
  		function createSanCatogary(){
  			if(access & 2){
  				openfrmModules("BEA/addSanWenZhang.jsp","新增栏目文章!");
  			}else{
  				alert("权限不足，请联系管理员！");
  			}
  			
  		}
  	</script>
  </head>
  
  <body>
  <div id="w" class="easyui-window" title="新闻文章..." data-options="fit:true,collapsible:false,
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
  }]    ">
	
  	<div  class="easyui-layout" data-options="fit:true,disabled:false">
  	<div region="north"  style="width:180px;height:20px">
    	<font  size="2" style="margin-left:10px"><strong>下面文章栏目中"所有栏目"是只包含当前用户发表的文章,栏目名称红色变粗说明当前用户拥有该栏目审核权限,其他栏目则表示当前用户可以投稿！</strong></font>
    	
    </div>
    <div region="west" data-options="border:false,collapsible:false"style="width:200px;margin-top:3px">
    	<div id="divTree">
			    	<ul  
			    		id="categoryTree" 
			    		class="easyui-tree" 
			    		data-options="url:'getFatherLanMu',
			    					animate:true,
			    					lines:true,
			    					onContextMenu:onContextMenu,
			    					onClick:categoryOnClick
			    				" 
					    		>
					    	</ul>
	    		</div>
    </div>
    
    <script type="text/javascript">
    	var lanMuId;
    	function addZiLanMu(id){//在栏目action中定义
    		if(access & 2){
	    		lanMuId=id;
	    		$('#iframeModules').attr('src',"BEA/addSanWenZhang.jsp");
	 			$('#frmModules').window({title:"添加新文章"});
	 			$('#frmModules').window('open');
	    		
    		}else{
				alert("权限不足，请联系管理员！");
			}
    		//openfrmModules("BEA/addSanCatagory.jsp","新增子栏目");
    	}
    	
    	function getLanMuId(){
    		var id=$("#categoryTree").tree("getSelected").id;
    		lanMuId=id;
    		return lanMuId;
    	}
    	
    	
    	function closeWin(){
    		$('#frmModules').window('close');
    	}
    	
    	function refreshdgv(){
    		$('#dagWenZhang').datagrid('reload');
    	}
    </script>
    
    <div  style="height:450px;width:700px" data-options="border:false" id="frmModules" class="easyui-window" data-options="fit:true,inline:true,draggable:false,resizable:false,
      collapsible:false,minimizable:false,maximizable:false">
			<iframe id="iframeModules"  scrolling="no" style="width:98.5%;height:98.5%;"></iframe>
	</div>
    
    <div  id="Menu123" class="easyui-menu" style="width:140px;background:#FFFFFF">
		<div onClick="createSanCatogary()"><img src="img/gif-0997.gif" style="background:#D9D6D1" />创建该栏目文章</div>
	   
 	</div>
    <div region="center" style="height:400px" class="easyui-layout" >
    	<div>
    		<font  size="2"  color="#2188E5" style="margin-left:10px;"><strong>文章管理(所有栏目)按文章属性查看(未核,已核,置顶,未顶,推荐,未荐,有图,无图)</strong></font>
    	</div>
    	 	
    	
    	<table style="" id="dagWenZhang" >   
	    <thead>   
	        <tr>   
	        
	        	<th data-options="field:'ck',checkbox:true">选中</th>
	        	<th data-options="field:'id',width:150,hidden:true">ID</th>
	        	<th data-options="field:'lanmuid',width:150,hidden:true">ID</th>  
	            <th data-options="field:'title',width:150">标题</th>   
	            <th data-options="field:'author',width:80">作者（编辑）</th>   
	            <th data-options="field:'shuXing',width:100,align:'center'">属性</th>
	            <th data-options="field:'dianJiShu',width:80,align:'center'">点击数</th> 
	            <th data-options="field:'fabuShiJian',width:100,align:'center'">发布时间</th> 
	            <th data-options="field:'we',width:90,align:'center',formatter:returnBtnColumnStu">操作</th>   
	        </tr>   
    	</thead>
	</table>
	<div>
	 	<button onclick="checkAll()" style="background: #30C3EC;margin-left: 10px">选中本页所有的记录</button>
	 	<button onclick="deleteSelected()" style="background: #30C3EC;margin-left: 10px">删除选中的记录</button>
	 	<button onclick="shenHe()" style="background: #30C3EC;margin-left: 10px">审核选中的记录</button>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#dagWenZhang").datagrid({
				url:'getAllWenZhang',
				idField:'id',
				fitColumns:true,
				rownumbers:true,
				pagination:true,
				onCheck:checkSelect,
			});
		});
		
		function checkSelect(rowIndex,rowData){
			
			console.info(rowData);
			
			$.ajax({
				url:'getWenZhangByWid',
				type:'POST',
				data:'wId='+rowData.id,
				dataType:'json',
				success:function(redult){
					if(redult.shen){
						//alert("该文章已审核！");
						return;
					}
				}
			});
		}
		
		
		function checkAll(){
			//selectAll	 
			$("#dagWenZhang").datagrid("selectAll");
		}
		
		function deleteSelected(){
			//alert(access & 2);
			if(access & 2){
				var wenZhangs=$("#dagWenZhang").datagrid("getSelections");
				for(var i=0;i<wenZhangs.length;i++){
					var id=wenZhangs[i].id;
					$.ajax({
						type:"POST",
						url:"deleteWenZhang?wid="+id,
						success:function(result){
							if(result=="OK"){
							}}		
					});
				}
				alert("删除成功！");
				$("#dagWenZhang").datagrid("reload");
				}else{
					alert("权限不足，请联系管理员！");
				}
		}
		
		function shenHe(){
			if(access & 2){
				var wenZhangs=$("#dagWenZhang").datagrid("getSelections");
				for(var i=0;i<wenZhangs.length;i++){
					var idc=wenZhangs[i].id;
					//alert(idc);
					$.ajax({
						url:"modifyshenHeWenZhang?wid="+idc,
						type:"post",
						success:function(result){
							
						}
					});
				
				}
				$("#dagWenZhang").datagrid("unselectAll");
				$("#dagWenZhang").datagrid("reload");
				alert("审核成功！");
			}else{
				alert("权限不足，请联系管理员！");
			}
		}
		
		
   		function returnBtnColumnStu(field,rowData,rowIndex){
			//sid=rowIndex;
			
			return "<img  style='width:13px' src='img/mini_edit.png' onclick='editWenZhang("+field+","+rowIndex+")'></img>"
			+"<img  style='width:13px;margin-left:10px' src='img/edit_remove.png' onclick='deleteWenZhang("+field+","+rowIndex+")'></img>";
		}
		
		var wenZhangId;
		var dgvLanMuId;
		function editWenZhang(field,rowIndex){
			//alert(rowIndex);
			if(access & 2){
				wenZhangId=$("#dagWenZhang").datagrid('getRows')[rowIndex]['id'];
				dgvLanMuId=$("#dagWenZhang").datagrid('getRows')[rowIndex]['lanmuid'];
				//alert(wenZhangId);
				$('#iframeModules').attr('src',"BEA/editSanWenZhang.jsp");
	 			$('#frmModules').window({title:"编辑文章"});
	 			$('#frmModules').window('open');
			}else{
				alert("权限不足，请联系管理员！");
			}
		}
		
		function deleteWenZhang(field,rowIndex){
			if(access & 2){
				wenZhangId=$("#dagWenZhang").datagrid('getRows')[rowIndex]['id'];
				if(confirm("确定要删除此新闻吗？")){
					$.ajax({
					type:"POST",
					url:'deleteWenZhang',
					data:'wid='+wenZhangId,
					dataType:'json',
					success:function(result){
						if(result=="OK"){
							alert("删除成功！");
							refreshdgv();
						}
					}
				});
				}
			}else{
				alert("权限不足，请联系管理员！");
			}
		}
		
		//获取wenZhangId
		function getWenZhangId(){
			return wenZhangId;
		
		}
		//获取lanMuId
		function getDgvLanMuId(){
			return dgvLanMuId;
		
		}
    	</script>
	
    </div>
   
    </div>
     
  </div>
  </body>
</html>
