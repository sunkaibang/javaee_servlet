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
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<script type="text/javascript" src="<c:url value='/LoginWeb/js-lib/SelectDate.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/LoginWeb/js-lib/jquery-1.4.2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/LoginWeb/js-lib/jquery.validate.js'/>"></script>
	<script type="text/javascript">
	$(function(){
			
			$("#myForm").validate({
				submitHandler:function(form){
					form.submit();
				},
				rules:{
					id:{
						required:true
					},
					password:{
						required:true
					},
					verifyCode:{
						required:true
					}
				},
				messages:{
					id:{
						required:"必填字段"
					},
					password:{
						required:"必填字段"
					},
					verifyCode:{
						required:"输入验证码"
					}
				}
			});
		});
		function radioClick(nd){
				var oPNode=nd.parentNode.childNodes[1];
				oPNode.checked=true;
		}
			
		function change(){
			$("#img").attr("src","<c:url value='Servlet?method=verifyCode&a='/>"+new Date().getTime());
		}
	</script>
	<style type="text/css">
		body{
			background-image:url(<c:url value='/LoginWeb/img/login.jpg'/>);
		}
		a:link,a:visited{
			text-decoration:none;
			font-size:20px;
			color:red;
		}
		.text{
			height:25px;
			width: 350px;
			border: none;
			border-bottom: solid red;
			background-color:gold;
		}
		#submitButton{
			width: 100px;
			height: 30px;
		}
	</style>
  </head>
  
  <body>
  	
  	<center><h1>学院期刊管理登录</h1></center>
  	
  	<center>
  	
  	
  	
    <form id="myForm" action="<c:url value='/Servlet?method=login'/>" method="post">
    	<p>
    		<font color="red" font-size="20">${requestScope.request_errorMsg }</font><br/>
    		用户名:
    		<input type="text" class="text" id="id" name="id" value="${requestScope.request_managerTeacher.id }"/>
    	</p>
    	<p>
    		密码:
    		<input type="password" class="text" id="password" name="password">
    	</p>
    	<p>
    			<span id="manager" class="aa">
	    		<input type="radio" id="manager" name="identity" value="manager">
	    		<span onclick="radioClick(this)">管理员</span>
	    		</span>
	    		<span id="teacher" class="aa">
	    		<input type="radio" id="teacher" name="identity" value="teacher" checked="checked">
	    		<span  onclick="radioClick(this)">教师</span>
	    		</span>
    	</p>
    	<p>
    			<img id="img" alt="加载验证码图片失败" src="<c:url value='/Servlet?method=verifyCode'/>">
    			<a href="javascript:change()">换一张</a><br/>
    		
    		
    		验证码:<input type="text" id="verifyCode" class="text" name=verifyCode> 
    	</p>
    		<input id="submitButton" type="submit" value="登录">
    		<a  href="<c:url value='/LoginWeb/Register.jsp'/>">注册账号</a>
    </form>
    </center>
   
  </body>
</html>
