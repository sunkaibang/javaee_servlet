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
    
    <title>My JSP 'modifyBook.jsp' starting page</title>
    
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
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery.validate.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/ajax.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/web/js-lib/jquery.validate.messages_cn.js'/>"></script>
	<script type="text/javascript">
		jQuery.validator.addMethod("notChinese", function(value,element) {     
	    var reg = /^\w{1,}$/;     
	    return this.optional(element) || (reg.test(value)) ;     
	}, "不要输入汉字和非法字符");
	
		jQuery.validator.addMethod("state",function(value,element) {     
	    var reg = /^\d[\/]\d+$/;     
	    return this.optional(element) || (reg.test(value)) ;     
	}, "库存/总数"); 
	</script>
	<script type="text/javascript">
		function setSelectDate(){
			var date=document.getElementById("sYear").value;
			var dateCol=date.split("-");
			var yearNode=document.getElementById("year");
			var yearNodeChild=yearNode.childNodes;
			document.getElementById("month").selectedIndex=parseInt(dateCol[1])-1;
			document.getElementById("day").selectedIndex=parseInt(dateCol[2])-1;
			
			
			for(var i=0;i<yearNodeChild.length;i++){
				if(dateCol[0]==yearNodeChild[i].innerHTML){
					yearNode.selectedIndex=i;
					break;
				}
			}
		}
		window.onload=function(){
			initDate("year","month","day");
			setSelectDate();
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
					},
					classId:{
						required:true,
						notChinese:true
					},
					publishHouseId:{
						required:true,
						notChinese:true
					},
					editNumber:{
						required:true,
						notChinese:true
					},
					state:{
						required:true,
						state:true
					}
				},
			});
		};		
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
    <form id="myForm" action="<c:url value='/updateData?method=modifyBookSecond'/>" method="post">
    	<p>
    		编号:
    		<input type="text" id="id" name="id" value="${requestScope.request_book.id }">
    	</p>
    	<p>
    		期刊名:
    		<input type="text" id="name" name="name" value="${requestScope.request_book.name }">
    	</p>
    	<p>
    		类别编号:
    		<input type="text" id="classId" name="classId" value="${requestScope.request_book.classId }">
    	</p>
    	<p>
    		出版社编号:
			<input type="text" id="publishHouseId" name="publishHouseId" value="${requestScope.request_book.publishHouseId }">
    	</p>
    	<p>
    		日期:
    		<select id="year" name="year" class="data" onchange="addDay('year','month','day')" ></select>
			<select id="month" name="month" class="data" onchange="addDay('year','month','day')"></select>
			<select id="day" name="day" class="data"></select>
    	</p>
    	<p>
    		期号:
    		<input type="text" id="editNumber" name="editNumber" value="${requestScope.request_book.editNumber }">
    	</p>
    	<p>
    		简介:
    		<textarea id="introduce" name="introduce" >${requestScope.request_book.introduce }</textarea>
    	</p>
    	<p>
    		状态:
    		<input type="text" id="state" name="state" value="${requestScope.request_book.state }">
    	</p>
    	<input type="hidden" id="uuid" name="uuid" value="${requestScope.request_book.uuid }"/>
    	<input type="submit" value="修改"/>
    </form>
    <input type="hidden" id="sYear" value="${requestScope.request_book.date }"/>
    </center>
  </body>
</html>
