function toExecl(tableId, url,title) {
	//�������
	var arrData = new Array();
	var jsonData = "[";
	var objTable = document.getElementById(tableId);
	if (objTable) {

		for ( var i = 0; i < objTable.rows.length; i++)//��
		{
			//ÿһ�п�ʼ
			var rowData = "{";
			for ( var j = 0; j < objTable.rows[i].cells.length ; j++)//��
			{
				//ÿһ�е�����
				if (j > 0) {
					rowData = rowData + ","
							+ objTable.rows[i].cells[j].innerHTML;
				} else {
					rowData = rowData + objTable.rows[i].cells[j].innerHTML;
				}
			}
			//ÿһ�н�β
			if (i < (objTable.rows.length - 1)) {
				rowData = rowData + "},";
			} else {
				rowData = rowData + "}";
			}
			//���ÿһ�е�json�ַ�����
			jsonData = jsonData + rowData;
		}
	} else {
		alert("���id����");
	}
	jsonData = jsonData + "]";
	//alert(jsonData);

	/* //����ajax����
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

	var form = $("<form>");//����һ��form��
	form.attr("style", "display:none");
	form.attr("target", "");
	form.attr("method", "post");
	form.attr("action", url);//��ת��servlet
	
	var input1 = $("<input>");
	input1.attr("type", "hidden");
	input1.attr("name", "jsonData");
	input1.attr("value", jsonData);
	
	var input2 = $("<input>");	//����
	input2.attr("type", "hidden");
	input2.attr("name", "title");
	input2.attr("value", title);
	
	var input3 = $("<input>");
	input3.attr("type", "hidden");
	input3.attr("name", "colNumber");
	input3.attr("value", objTable.rows[0].cells.length);
	
	$("body").append(form);//����������web��
	form.append(input1);
	form.append(input2);
	form.append(input3);

	form.submit();//���ύ
}