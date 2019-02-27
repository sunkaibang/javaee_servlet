function toExecl(tableId, url,title) {
	//解析表格
	var arrData = new Array();
	var jsonData = "[";
	var objTable = document.getElementById(tableId);
	if (objTable) {

		for ( var i = 0; i < objTable.rows.length; i++)//行
		{
			//每一行开始
			var rowData = "{";
			for ( var j = 0; j < objTable.rows[i].cells.length ; j++)//列
			{
				//每一行的数据
				if (j > 0) {
					rowData = rowData + ","
							+ objTable.rows[i].cells[j].innerHTML;
				} else {
					rowData = rowData + objTable.rows[i].cells[j].innerHTML;
				}
			}
			//每一行结尾
			if (i < (objTable.rows.length - 1)) {
				rowData = rowData + "},";
			} else {
				rowData = rowData + "}";
			}
			//添加每一行到json字符串中
			jsonData = jsonData + rowData;
		}
	} else {
		alert("表格id错误");
	}
	jsonData = jsonData + "]";
	//alert(jsonData);

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
	input2.attr("value", title);
	
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