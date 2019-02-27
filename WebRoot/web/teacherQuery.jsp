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
    
    <title>My JSP 'teacherQuery.jsp' starting page</title>
    
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
	</style>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
	<script type="text/javascript">
	function toExecl(tableId,url){
        //解析表格
        var arrData=new Array();
        var jsonData = "[";
        var objTable=document.getElementById(tableId);
        if(objTable)
        {
        	
           for(var i=0;i<objTable.rows.length;i++)//行
           {
              //每一行开始
              var rowData = "{";
              for(var j=0;j<objTable.rows[i].cells.length;j++)//列
              {
                  //每一行的数据
	                  if(j > 0){
	                      rowData = rowData + "," + objTable.rows[i].cells[j].innerHTML;
	                  }else{
	                      rowData = rowData + objTable.rows[i].cells[j].innerHTML;
	                  }
              }
              //每一行结尾
              if(i < (objTable.rows.length -1)){
                  rowData = rowData + "},";
              }else{
                  rowData = rowData + "picture}";
              }
                 //添加每一行到json字符串中
                 jsonData = jsonData + rowData;
           }
        }else{
        	alert("表格id错误");
        }
        jsonData = jsonData + "]";
        	
        var form = $("<form>");//定义一个form表单
		form.attr("style", "display:none");
		form.attr("target", "");
		form.attr("method", "post");
		form.attr("action", url);//跳转到servlet
		var input1 = $("<input>");
		input1.attr("type", "hidden");
		input1.attr("name", "jsonData");
		input1.attr("value", jsonData);
		
		var input2 = $("<input>");	//标题
		input2.attr("type", "hidden");
		input2.attr("name", "title");
		input2.attr("value", "教师信息表");
		
		var input3 = $("<input>");
		input3.attr("type", "hidden");
		input3.attr("name", "colNumber");
		input3.attr("value", objTable.rows[0].cells.length);
		
		$("body").append(form);//将表单放置在web中
		form.append(input1);
		form.append(input2);
		form.append(input3);
	
		form.submit();//表单提交
                
    }
	</script>
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
    		<th>用户名</th>
    		<th>密码</th>
    		<th>真实姓名</th>
    		<th>出生日期</th>
    		<th>电话号码</th>
    		<th>电子邮件</th>
    		<th>照片</th>
    		<th>编辑</th>
    		<th>删除</th>
    	</tr>
    	<c:forEach items="${requestScope.request_pb.beanList}" var="cstm">
			<tr>
			    <td>${cstm.id}</td>
			    <td>${cstm.password }</td>
				<td>${cstm.realName}</td>
				<td>${cstm.birthday}</td>
				<td>${cstm.telnumber}</td>
				<td>${cstm.email}</td>
				<td><img src="${cstm.picture}" width="100" height="40" alt="无照片"></td>
				<td>
				    <a href="<c:url value='/updateData?method=modifyTeacherFirst&uuid=${cstm.uuid }'/>">编辑</a>
				</td>
				<td>
					<a href="<c:url value='/updateData?method=deleteTeacher&id=${cstm.id }'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
    </table>
    第${requestScope.request_pb.pc }页/共${requestScope.request_pb.tp }页
    <a href="${requestScope.request_pb.url }&pc=1">首页</a>
    
    <c:if test="${requestScope.request_pb.pc>1 }">
    	<a href="${requestScope.request_pb.url }&pc=${requestScope.request_pb.pc-1 }">上一页</a>
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
    			<a href="${requestScope.request_pb.url }&pc=${i }">${i }</a>
    		</c:otherwise>
    	</c:choose>
    </c:forEach>
   
    <c:if test="${requestScope.request_pb.pc<requestScope.request_pb.tp }">
    	 <a href="${requestScope.request_pb.url }&pc=${requestScope.request_pb.pc+1 }">下一页</a>
    </c:if>
    <a href="${requestScope.request_pb.url }&pc=${requestScope.request_pb.tp }">尾页</a>
	
	 <br/>
    <a href="javascript:toExecl('tbid','<c:url value='/toExcelServlet'/>')" >导出表格</a>
  </body>
  </center>
</html>
