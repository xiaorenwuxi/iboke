<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.min.js"></script>
	<%-- <script>
		$(document).ready(function(){
		
			$("#select").click(function(){
				
				var id=$("#id").val()
			
				$.ajax({
					
					url: "<%=basePath%>demo/see.do",
					type: "post",
					dataType: "json",
					data:{"aid":id},
					success: function(data){
						alert(123)
					}
					/* error: function(data){
					 	alert("失败")
					} */
				
				
				});
			
			});
			
		});
		
		
		
	</script> --%>
	
	<script>
	$("document").ready(function(){
	
	
	$("#select").click(function(){
	
	
		var id = $("#id").val()
		$.ajaxSettings.async = false;

		$.getJSON("<%=basePath%>demo/see.do",{
			"aid":id
			},function(jsondata){
			if (jsondata != null) {
	      		alert(jsondata)					
	   			
			}
	});
	
	
	
	})
	
	})
	
	</script>
  </head>
  
  <body>
  	<div>
  	<label>请输入id:</label>
  	<input type="text" name="id" id="id"/>
   <input type="button" value="查询"   id="select"/>
   </div>
  </body>
</html>
