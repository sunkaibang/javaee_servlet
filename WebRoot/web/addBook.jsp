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
    
    <title>My JSP 'addBook.jsp' starting page</title>
    
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
	
		function checkId(idNode,infoNode,infoText,flag,url){
			var xmlHttp=createXMLHttpRequest();
			xmlHttp.open("POST",url,true);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttp.send("id=" + idNode.value);
			xmlHttp.onreadystatechange=function(){
				if(xmlHttp.readyState==4&&xmlHttp.status==200){
					var text=xmlHttp.responseText;
					if(flag==true){
						if(text=="1"){
							infoNode.innerHTML="";
							submit.disabled=false;
						}else if(text=="0"){
							infoNode.innerHTML=infoText.fontcolor("red");
							submit.disabled=true;
						}
					}
					else{
						if(text=="1"){
							infoNode.innerHTML=infoText.fontcolor("red");
							submit.disabled=true;
						}else if(text=="0"){
							infoNode.innerHTML="";
							submit.disabled=false;
						}
					}
				}
			};
		}
		
		
	</script>
	<script type="text/javascript">
		$(function(){
			initDate("year","month","day");
			
			var idNode=$("#id")[0];
			idNode.onblur=function(){
				checkId($("#id")[0],$("#info")[0],"编号已存在",true,"<c:url value='/BServlet?method=ajaxBook'/>");
			};
			
			var classNode=$("#classId")[0];
			classNode.onblur=function(){
				checkId($("#classId")[0],$("#infoClass")[0],"编号不存在",false,"<c:url value='/BServlet?method=ajaxClass'/>");
			};
			
			var publishNode=$("#publishHouseId")[0];
			publishNode.onblur=function(){
				checkId($("#publishHouseId")[0],$("#infoPublish")[0],"编号不存在",false,"<c:url value='/BServlet?method=ajaxPublish'/>");
			};
			
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
			
		});
		
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
    <form id="myForm" action="<c:url value='/BServlet?method=addBook'/>" method="post">
    	<p>
    		编号:
    		<input type="text" id="id" name="id"><span id="info"></span>
    	</p>
    	<p>
    		期刊名:
    		<input type="text" id="name" name="name">
    	</p>
    	<p>
    		类别编号:
    		<input type="text" id="classId" name="classId"><span id="infoClass"></span>
    	</p>
    	<p>
    		出版社编号:
			<input type="text" id="publishHouseId" name="publishHouseId"><span id="infoPublish"></span>
    	</p>
    	<p>
    		日期:
    		<select id="year" name="year" class="data" onchange="addDay('year','month','day')" ></select>
			<select id="month" name="month" class="data" onchange="addDay('year','month','day')"></select>
			<select id="day" name="day" class="data"></select>
    	</p>
    	<p>
    		期号:
    		<input type="text" id="editNumber" name="editNumber">
    	</p>
    	<p>
    		简介:
    		<textarea id="introduce" name="introduce"></textarea>
    	</p>
    	<p>
    		状态:
    		<input type="text" id="state" name="state">
    	</p>
    	<input type="submit" id="submit" value="添加"/>
    </form>
    </center>	
  </body>
</html>
