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
    
    <title>My JSP 'classQuery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		@import url("<c:url value='/web/css/table.css'/>");
	</style>
	<style type="text/css">
		body{
			background: no-repeat fixed center 0;
			background-image:url(<c:url value='/web/img/mai.jpg'/>);
		}
		a:link,a:visited{
			text-decoration:none;
			font-size:18px;
			color:blue;
		}
	</style>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/Excel.js'/>"></script>
	<script type="text/javascript">

		function changeColor() {
			var oTabNode = document.getElementsByTagName("table")[0];
			var oTrNode = oTabNode.rows;
			for ( var i = 1; i < oTrNode.length; i++) {
				if (i % 2 == 0) {
					oTrNode[i].className = "shuangShu";
				} else {
					oTrNode[i].className = "danShu";
				}
			}
			var name;
			var flag = true;
			for ( var i = 1; i < oTrNode.length; i++) {
				oTrNode[i].onmousemove = function() {
					if (flag == true) {
						name = this.className;
					}
					this.className = "move";
					flag = false;
				};

				oTrNode[i].onmouseout = function() {
					if (flag == false)
						this.className = name;
					flag = true;
				};
			}
		}
		onload = function() {

			changeColor();
		};
	</script>
  </head>
  
  <body>
  <center>
    <table border="1" id="tbid">
    	<tr>
    		<th>类别编号</th>
    		<th>类别名称</th>
    		<%
    			if(request.getSession().getAttribute("session_manager")!=null){
    			 %>
    		<th>编辑</th>
    		<th>删除</th>
    		<%
    			} %>
    	</tr>
    	<c:forEach items="${requestScope.request_pb.beanList}" var="cstm">
			<tr>
			    <td>${cstm.id}</td>
				<td>${cstm.name}</td>
				<%
    			if(request.getSession().getAttribute("session_manager")!=null){
    			 %>
				<td>
				    <a href="<c:url value='updateData?method=modifyclassFirst&id=${cstm.id }'/>">编辑</a>
				</td>
				<td>
					<a href="<c:url value='updateData?method=deleteCData&id=${cstm.id }'/>">删除</a>
				</td>
				<%
    			} %>
			</tr>
		</c:forEach>
    </table>
    第${requestScope.request_pb.pc }页/共${requestScope.request_pb.tp }页
    <a href="<c:url value='BookServlet?method=classQuery&pc=1'/>">首页</a>
    
    <c:if test="${requestScope.request_pb.pc>1 }">
    	<a href="<c:url value='BookServlet?method=classQuery&pc=${requestScope.request_pb.pc-1 }'/>">上一页</a>
    </c:if>
    
    <c:choose>
    	<c:when test="${requestScope.request_pb.tp<=10 }">
    		<c:set var="begin" value="1"/>
    		<c:set var="end" value="${requestScope.request_pb.tp }"/>
    	</c:when>
    	<c:otherwise>
    		<c:set var="begin" value="${requestScope.request_pb.pc-5 }"/>
    		<c:set var="end" value="${requestScope.request_pb.pc+4 }"/>
    		
    		<c:if test="${begin<1 }">
    			<c:set var="begin" value="1"/>
    			<c:set var="end" value="10"/>
    		</c:if>
    		<c:if test="${end>requestScope.request_pb.tp }">
    			<c:set var="begin" value="${requestScope.request_pb.tp-9 }"/>
    			<c:set var="end" value="${requestScope.request_pb.tp }"/>
    		</c:if>
    	</c:otherwise>
    </c:choose>
    
    <c:forEach var="i" begin="${begin }" end="${end }">
    	<c:choose>
    		<c:when test="${i eq requestScope.request_pb.pc}">
    			${i }
    		</c:when>
    		<c:otherwise>
    			<a href="<c:url value='BookServlet?method=classQuery&pc=${i }'/>">${i }</a>
    		</c:otherwise>
    	</c:choose>
    </c:forEach>
   
    <c:if test="${requestScope.request_pb.pc<requestScope.request_pb.tp }">
    	 <a href="<c:url value='BookServlet?method=classQuery&pc=${requestScope.request_pb.pc+1 }'/>">下一页</a>
    </c:if>
    <a href="<c:url value='BookServlet?method=classQuery&pc=${requestScope.request_pb.tp }'/>">尾页</a>
    
    <br/>
    <a href="javascript:toExecl('tbid','<c:url value='/toExcelServlet'/>','类别信息表')" >导出表格</a>
    </center>
  </body>
</html>
