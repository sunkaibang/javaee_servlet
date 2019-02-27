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
    
    <title>注册页面</title>
    
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
	<script type="text/javascript" src="<c:url value='/LoginWeb/js-lib/ajax.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/LoginWeb/js-lib/jquery.validate.messages_cn.js'/>"></script>
	
	<script type="text/javascript">
		var text;
		
		
		function idAjax(oIdNode, oTeacher) {
			var xmlHttp = createXMLHttpRequest();
			xmlHttp.open("POST", "<c:url value='/Servlet?method=ajaxLogin'/>",
					true);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			if (oTeacher.checked == true)
				xmlHttp.send("id=" + oIdNode.value + "&identity=teacher");
			else
				xmlHttp.send("id=" + oIdNode.value + "&identity=manager");
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var text = xmlHttp.responseText;
					if (text == "1") {
						document.getElementById("isExisted").innerHTML = "";
					} else if (text == "0") {
						document.getElementById("isExisted").innerHTML = "用户名已存在"
								.fontcolor("red");
					}
				}
			};
		}

		function radioClick(nd) {
		//管理员和教师切换后还是要再校验用户名
			var oPNode = nd.parentNode.childNodes[1];
			oPNode.checked = true;
			var oIdNode = document.getElementById("id");
			var oTeacher = document.getElementById("teacher");
			idAjax(oIdNode, oTeacher);
			mailCheck();
		}
		function change() {
		//改变验证码
			$("img").attr(
					"src",
					"<c:url value='Servlet?method=verifyCode&a='/>"
							+ new Date().getTime());
		}
		
		var t=90;	//90s后才能再次发送
		var a;
		function daoshu(){	//设置按钮时间和不能点击
				var sendButton=document.getElementById("sendEamil");
				sendButton.value=t+"秒后再发";
				sendButton.disabled=true;
				t--;
				if(t==0){
					clearInterval(a);
					sendButton.disabled=false;
					sendButton.value="发送邮件";
				}
					
			}
		function sendEmail() {	//发送邮件
				var email=document.getElementById("email");
				var xmlHttp=createXMLHttpRequest();
				xmlHttp.open("POST","<c:url value='/Servlet?method=sendEmail'/>",true);
				xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xmlHttp.send("email="+email.value);
				xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4&&xmlHttp.status==200){
						text=xmlHttp.responseText;
						document.getElementById("trueCode").innerHTML=text;		//返回服务器发的邮件验证码
					}
				};
				
				a=setInterval("daoshu()",1000);
			 
		}
		
		function mailCheck(){		//教师可以不传照片，管理员必须要照片
			var $manager=$("#manager");
			var $pic=$("#picture");
			if($manager.attr("checked")==true){
				$pic.attr("required",true);
				$pic.attr("accept",".jpg|.png|.jpeg");
			}else{
				$pic.attr("accept",".jpg|.png|.jpeg");
			}
		}

		$(function() {
			initDate("year", "month", "day");
			mailCheck();	//图片校验
			var oIdNode = document.getElementById("id");
			var oTeacher = document.getElementById("teacher");
			oIdNode.onblur = function() {
				idAjax(oIdNode, oTeacher);
			};

			var sVCNode = document.getElementById("verifyCode");
			sVCNode.onblur = function() { //邮件验证码验证是否正确
				if (text != sVCNode.value) {
					document.getElementById("emailInfo").innerHTML = "验证码错误";
					document.getElementById("submitButton").disabled = true;
				} else {
					document.getElementById("emailInfo").innerHTML = "";
					document.getElementById("submitButton").disabled = false;
				}
			}

			$("#myForm").validate({
				submitHandler : function(form) {
					form.submit();
				},
				rules : {
					id : {
						required : true,
						maxlength : 20
					},
					password : {
						required : true,
						rangelength : [ 5, 20 ],
					},
					surePaw : {
						equalTo : "#password"
					},
					realName : {
						required : true,
						maxlength : 20
					},
					telnumber : {
						required : true,
						rangelength : [ 11, 11 ]
					},
					email : {
						required : true,
						email : true,
						maxlength : 20
					},
					verifyCode : {
						required : true,
					},
				}
			});
		});
		
	</script>
	<style type="text/css">
		body{
			background: no-repeat fixed center 0;
			background-image:url(<c:url value='/LoginWeb/img/register.jpg'/>);
		}
		a:link,a:visited{
			text-decoration:none;
			font-size:20px;
			color:red;
		}
		.text{
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
  <!-- <img src="<c:url value='/web/img/register.jpg'/>"> -->
  	<center><h1>账号注册</h1></center>
  	<center>
  	<br/>
  	<br/>
    <form id="myForm" action="<c:url value='/Servlet?method=register'/>" method="post" enctype="multipart/form-data">
    	<p>
    		<font color="red" font-size="20">${requestScope.request_errorMsg }</font><br/>
    		用户名:
    		<input type="text" id="id"  name="id"/><span id="isExisted"></span>
    	</p>
    	<p>
    		密码:
    		<input type="password" id="password" name="password">
    	</p>
    	<p>
    		确认密码:
    		<input type="password" id="surePaw" name="surePaw">
    	</p>
    	<p>
    		真实姓名:
    		<input type="text" id="realName" name="realName"> 
    	</p>
    	<p>
    		出生日期:
    		<select id="year" name="birthday" class="data" onchange="addDay('year','month','day')" ></select>
			<select id="month" name="birthday" class="data" onchange="addDay('year','month','day')"></select>
			<select id="day" name="birthday" class="data"></select>
    	</p>
    	<p>
    		电话号码:
    		<input type="text" id="telnumber" name="telnumber" >
    	</p>
    	<p>
    		照片:
    		<input type="file" id="picture" name="picture">
    	</p>
    	<p>
    		<span id="manager1">
    		<input type="radio" id="manager" name="identity" value="manager" onchange="mailCheck()">
    		<span onclick="radioClick(this)">管理员</span>
    		</span>
    		<span id="teacher1">
    		<input type="radio" id="teacher" name="identity" value="teacher" checked="checked" onchange="mailCheck()">
    		<span  onclick="radioClick(this)">教师</span>
    		</span>
    	</p>
    	<p>
    		邮箱:
    		<input type="text" id="email" name="email"><input type="button" id="sendEamil" onclick="sendEmail()" value="发送邮件"> <br/>
    		<br/>
    		验证码:<input text="text" id="verifyCode" name="verifyCode"><span id="emailInfo"></span>
    	</p>
    	<input type="hidden" id="trueCode">
    	<input type="submit" value="注册" id="submitButton"> 
    </form>
    </center>
  </body>
</html>
