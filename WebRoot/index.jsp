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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<img src="/javawebLogin/web/imgs/login.jpg" width="100%" height="100%">
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script type="text/javascript" src="<c:url value='/web/js-lib/jquery-1.4.2.js'/>"></script>
  <script type="text/javascript">
  function toExecl(){
        //解析表格
        alert("a");
        var arrData=new Array();
        var jsonData = "[";
        var objTable=document.getElementById("tab");
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
                      rowData = rowData + "," + objTable.rows[i].cells[j].innerText;
                  }else{
                      rowData = rowData + objTable.rows[i].cells[j].innerText;
                  }
              }
              //每一行结尾
              if(i < (objTable.rows.length -1)){
                  rowData = rowData + "},";
              }else{
                  rowData = rowData + "}";
              }
                 //添加每一行到json字符串中
                 jsonData = jsonData + rowData;
           }
        }
        jsonData = jsonData + "]";
        alert(jsonData);
        
        /* //发送ajax请求
        $.ajax({
                    url: "toExecl",
                    type: "POST",
                    data: {"jsonData": jsonData},
                    dataType: "text",
                    cache: false,
                    success: function (result) {
                        alert("success");
                    }
                }); */
                
         var form=$("<form>");//定义一个form表单
         form.attr("style","display:none");
         form.attr("target","");
         form.attr("method","post");
         form.attr("action","/DatabaseBigWork/toExcelServlet");//跳转到servlet
         var input1=$("<input>");
         input1.attr("type","hidden");
         input1.attr("name","jsonData");
         input1.attr("value",jsonData);
         $("body").append(form);//将表单放置在web中
         form.append(input1);

         form.submit();//表单提交
    }
    
    //下载
    function downloadDoc(filePath,fileName){  
        var path = filePath+fileName;  
        var contextLength = "<%=request.getContextPath()%>";  
        var sp = path.substring(contextLength.length,path.length);  
        document.getElementById("path").value = sp;  
        document.getElementById("fileName").value = fileName;  
        //down_frame.location.href = path;decodeURI(path);encodeURIComponent  
        var sForm1 = document.form1;  
        sForm1.action = "<%=request.getContextPath()%>/com/icss/mdm/usermanual/servlet/StandardDocDownServlet";  
        sForm1.submit();  
    } 
</script>
  
  <body>
   <div id="tableExcel">

        <table id="tab">
            <tr height="18" style="height: 13.5pt">
                <td height="18" width="72" style="height: 13.5pt; width: 54pt">姓名</td>
                <td width="72" style="width: 54pt">性别</td>
                <td width="72" style="width: 54pt">年龄</td>
            </tr>
            <tr height="18" style="height: 13.5pt">
                <td height="18" style="height: 13.5pt">杨xx</td>
                <td>男</td>
                <td align="right">27</td>
            </tr>
            <tr height="18" style="height: 13.5pt">
                <td height="18" style="height: 13.5pt">测试1</td>
                <td>女</td>
                <td align="right">18</td>
            </tr>
            <tr height="18" style="height: 13.5pt">
                <td height="18" style="height: 13.5pt">测试2</td>
                <td>女</td>
                <td align="right">18</td>
            </tr>
            <tr height="18" style="height: 13.5pt">
                <td height="18" style="height: 13.5pt">测试3</td>
                <td>女</td>
                <td align="right">18</td>
            </tr>
            <tr height="18" style="height: 13.5pt">
                <td height="18" style="height: 13.5pt">刘德华</td>
                <td>男</td>
                <td align="right">48</td>
            </tr>
        </table>
    </div>
    <a href="javascript:toExecl()" >导出execl</a>
    
    <a href="/DatabaseBigWork/index.jsp">下载</a>
  </body>
  
</html>
