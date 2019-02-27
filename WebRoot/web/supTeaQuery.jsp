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
    
    <title>My JSP 'supTeaQuery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/web/js-lib/SelectDate.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
	<script>
	window.onload=function(){
		initDate("year","month","day");
		isSelectDate();
	}
	function isSelectDate(){
			//是否修改照片
			var radioNode=document.getElementsByName("modPic");
			var fileNode=document.getElementById("isSelectDate");
			if(radioNode[0].checked==true){
				$(fileNode).css("display","block");
			}else{
				$(fileNode).css("display","none");
			}
		}
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
    <form id="myForm" action="<c:url value='/BookServlet?method=supTeaQuery'/>" method="get" >
    	<input type="hidden" name="method" value="supTeaQuery">
    	<p>
    		用户名:
    		<input type="text" id="id" name="id"/>
    	</p>
    	<p>
    		真实姓名:
    		<input type="text" id="realName" name="realName"/>
    	</p>
    	<p>
    		是否选择出生日期:<input type="radio" name="modPic" value="yes" onchange="isSelectDate()">是
    		<input type="radio" name="modPic" value="no" checked="checked" onchange="isSelectDate()">否<br/>
    		<span id="isSelectDate">
    			<select id="year" name="year" class="data" onchange="addDay('year','month','day')" ></select>
				<select id="month" name="month" class="data" onchange="addDay('year','month','day')"></select>
				<select id="day" name="day" class="data"></select>
			</span>
    	</p>
    	<p>
    		电话号码:
    		<input type="text" id="telnumber" name="telnumber" >
    	</p>
    	<p>
    		邮箱:
    		<input type="text" id="email" name="email">
    		<br/>
    	</p>
    	<input type="submit" value="查询" id="submitButton"> 
    </form>
    </center>
  </body>
</html>
