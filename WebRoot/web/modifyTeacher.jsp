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
    
    <title>My JSP 'modifyTeacher.jsp' starting page</title>
    
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
		function idAjax(oIdNode){
				var xmlHttp=createXMLHttpRequest();
				xmlHttp.open("POST","<c:url value='/Servlet?method=ajaxLogin'/>",true);
				xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xmlHttp.send("id="+oIdNode.value+"&identity=teacher");
				xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4&&xmlHttp.status==200){
						var text=xmlHttp.responseText;
						if(text=="1"){
							document.getElementById("isExisted").innerHTML="";
						}else if(text=="0"){
							document.getElementById("isExisted").innerHTML="用户名已经存在".fontcolor("red");
						}
					}
				}
		}
		
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
		
		function isModPic(){
			//是否修改照片
			var radioNode=document.getElementsByName("modPic");
			var fileNode=document.getElementById("picture");
			if(radioNode[0].checked==true){
				$(fileNode).css("display","block");
			}else{
				$(fileNode).css("display","none");
			}
		}
		
		$(function(){
			initDate("year","month","day");
			setSelectDate();
			isModPic();
			var oIdNode=document.getElementById("id");
			oIdNode.onblur=function(){
				idAjax(oIdNode);
			}
			
			$("#myForm").validate({
				submitHandler:function(form){
					form.submit();
				},
				rules:{
					id:{
						required:true,
						maxlength:20
					},
					password:{
						required:true,
						rangelength:[5,20],
					},
					surePaw:{
						equalTo:"#password"
					},
					realName:{
						required:true,
						maxlength:20
					},
					telnumber:{
						required:true,
						rangelength:[11,11]
					},
					email:{
						required:true,
						email:true,
						maxlength:20
					},
					verifyCode:{
						required:true,
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
    <form id="myForm" action="<c:url value='/updateData?method=modifyTeacherSecond'/>" method="post" enctype="multipart/form-data">
    	<p>
    		<input type="hidden" name="uuid" value="${requestScope.request_teacher.uuid }"/>
    		用户名:
    		<input type="text" id="id" name="id" value="${requestScope.request_teacher.id }"/><span id="isExisted"></span>
    	</p>
    	<p>
    		密码:
    		<input type="password" id="password" name="password" value="${requestScope.request_teacher.password }">
    	</p>
    	<p>
    		确认密码:
    		<input type="password" id="surePaw" name="surePaw" value="${requestScope.request_teacher.password }">
    	</p>
    	<p>
    		真实姓名:
    		<input type="text" id="realName" name="realName" value="${requestScope.request_teacher.realName }"> 
    	</p>
    	<p>
    		出生日期:
    		<select id="year" name="birthday" class="data" onchange="addDay('year','month','day')" ></select>
			<select id="month" name="birthday" class="data" onchange="addDay('year','month','day')"></select>
			<select id="day" name="birthday" class="data"></select>
    	</p>
    	<p>
    		电话号码:
    		<input type="text" id="telnumber" name="telnumber" value="${requestScope.request_teacher.telnumber }" >
    	</p>
    	<p>
    		是否修改照片:<input type="radio" name="modPic" value="yes" onchange="isModPic()">是
    		<input type="radio" name="modPic" value="no" checked="checked" onchange="isModPic()">否
    		<input type="file" id="picture" name="picture">
    	</p>
    	<p>
    		邮箱:
    		<input type="text" id="email" name="email" value="${requestScope.request_teacher.email }">
    	</p>
    	
    	<input type="submit" value="修改" id="submitButton"> 
    </form>
    </center>
    <input type="hidden" id="sYear" value="${requestScope.request_teacher.birthday }"/>
  </body>
</html>
