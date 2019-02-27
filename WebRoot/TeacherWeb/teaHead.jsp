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
    
    <title>My JSP 'teaHead.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
	<style type="text/css">
		ul {
			list-style: none;
			float: left;
			overflow: hidden;
			height: 23px;
		}
		
		.open {
			overflow: visible;
		}
		
		.close {
			overflow: hidden;
		}
		
		#men{
			position:absolute;
			left:620px;
			top:90px;
		}
		
		a:link,a:visited{
			text-decoration:none;
			font-size:18px;
			color:blue;
		}
		
		#exit{
			left:1300px;
		}
		span{
			top:10px;
			position:absolute;
			left:1100px;
		}
		
		body{
			background: no-repeat fixed center 0;
			background-image:url(<c:url value='/web/img/mai.jpg'/>);
		}
	</style>
	<script type="text/javascript">
		function list(nd, tag) {
			var oNd = nd.parentNode;
			var collsNds = document.getElementsByTagName(tag);
			for ( var i = 0; i < collsNds.length; i++) {
				if (collsNds[i] == oNd) {
					if (collsNds[i].className == "open")
						collsNds[i].className = "close";
					else
						collsNds[i].className = "open";
				} else
					collsNds[i].className = "close";
			}
		}
		$(function(){
			$("ul li").mouseover(function(){
				list(this,"ul");
			});
			$("ul li").mouseout(function(){
				list(this,"ul");
			});
		});
		function exit(){
			var iden="teacher";
			var form = $("<form>");//定义一个form表单
			form.attr("style", "display:none");
			form.attr("target", "");
			form.attr("method", "post");
			form.attr("action", "<c:url value='/exitServlet'/>");//跳转到servlet
			var input1 = $("<input>");
			input1.attr("type", "hidden");
			input1.attr("name", "iden");
			input1.attr("value", iden);
			$("body").append(form);//将表单放置在web中
			form.append(input1);
			form.submit();//表单提交
		}
	</script>

  </head>
  
  <body>
    <center><h1>教师期刊信息查询</h1></center><span>尊敬的教师${sessionScope.session_teacher.id },欢迎你!</span>
    <span id="exit"><a onclick="exit()">退出</a></span>
    <hr/>
    <span id="men">
    <ul class="close">
    	<li><font color="blue" size="4">查询</font></li>
    	<li class="menu"><a href="<c:url value='/BookServlet?method=bookQuery'/>" target="teaBody">期刊查询</a></li>
    	<li class="menu"><a href="<c:url value='/BookServlet?method=classQuery'/>" target="teaBody">类别查询</a></li>
    	<li class="menu"><a href="<c:url value='/BookServlet?method=publishQuery'/>" target="teaBody">出版社查询</a></li>
    </ul>
       <ul class="close">
    	<li><font color="blue" size="4">高级查询</font></li>
    	<li class="menu"><a href="<c:url value='/TeacherWeb/supBookQuery.jsp'/>" target="teaBody">期刊查询</a></li>
    </ul>
    </span>
  </body>
</html>
