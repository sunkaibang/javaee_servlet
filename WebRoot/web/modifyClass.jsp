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
    
    <title>My JSP 'modifyClass.jsp' starting page</title>
    
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
	</script>
	<script type="text/javascript">
		$(function(){
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
    <form id="myForm" action="<c:url value='/updateData?method=modifyclassSecond'/>" method="post">
    	<p>
  			类别编号:<input type="text" name="id" value="${requestScope.request_book.id }" disabled="disabled">
  		</p>
  		<p>
  			类别名称:<input type="text" name="name" value="${requestScope.request_book.name }">
  		</p>
  		<p>
  			<input type="submit" value="修改">
  		</p>
  		<input type="hidden" name="uuid" value="${requestScope.request_book.uuid }">
    </form>
    </center>
  </body>
</html>
