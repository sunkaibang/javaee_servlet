<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addClass.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery.validate.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/ajax.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery.validate.messages_cn.js'/>"></script>
	<script type="text/javascript">
		jQuery.validator.addMethod("notChinese", function(value,element) {     
	    var reg = /^\w{1,}$/;     
	    return this.optional(element) || (reg.test(value)) ;     
	}, "不要输入汉字和非法字符");
	
		function checkId(idNode,infoNode){
			var xmlHttp=createXMLHttpRequest();
			xmlHttp.open("POST","<c:url value='/BServlet?method=ajaxClass'/>",true);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttp.send("id=" + idNode.value);
			xmlHttp.onreadystatechange=function(){
				if(xmlHttp.readyState==4&&xmlHttp.status==200){
					var text=xmlHttp.responseText;
					if(text=="1"){
						infoNode.innerHTML="";
						submit.disabled=false;
					}else if(text=="0"){
						infoNode.innerHTML="该编号已存在".fontcolor("red");
						submit.disabled=true;
					}
				}
			};
		}
	</script>
	<script type="text/javascript">
		$(function(){
			var idNode=$("#id")[0];
			idNode.onblur=function(){
				checkId($("#id")[0],$("#info")[0]);
			}
			
			$("#myForm").validate({
				submitHandler:function(form){
					form.submit();
				},
				rules:{
					id:{
						required:true,
						notChinese:true
					},
					name:{
						required:true
					}
				}
			})
		})
	</script>
	<style type="text/css">
		body{
			background: no-repeat fixed center 0;
			background-image:url(<c:url value='/web/img/mai.jpg'/>);
		}
	</style>

  </head>
  
  <body>
    <center>
    <form id="myForm" action="<c:url value='/BServlet?method=addClass'/>" method="post">
    	<p>
  			类别编号:<input type="text" name="id" id="id"><span id="info"></span>
  		</p>
  		<p>
  			类别名称:<input type="text" name="name">
  		</p>
  		<p>
  			<input type="submit" id="submit" value="添加">
  		</p>
    </form>
    </center>
  </body>
</html>
