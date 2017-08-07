function autoTextProcess(inputText) {
	var resultDiv = $("#resultDiv");
	var beginSignValue = $("#beginSign").val();
	var endSignValue = $("#endSign").val();
	var inputTextValue = $("#inputText").val();
	var message = {
		"beginSign" : beginSignValue,
		"endSign" : endSignValue,
		"text" : inputTextValue
	}
	$
			.ajax({
				type : "POST",
				async : false,
				url : "getResultText.do",
				data : message,
				dataType : "json",
				error : function(result) {
					alert("通信失败 " + result);
				},
				success : function(resultPack) {
					if (resultPack.isSuccess) {
						var result = resultPack.returnObject;
						var listObj = eval(result);
						var html = "<div class=\"resultTitle\">筛选结果</div>";
						document.getElementById("resultDiv").innerHTML = html;
						for (var i = 0; i < listObj.length; i++) {
							var obji = listObj[i];
							/* 转义 */
							obji = obji.replace(/\n/g, "<br />");
							obji = obji.replace(/\r\n/g, "<br />");
							//obji = obji.replace(/"/g,"&quot;");
							obji = obji.replace(/&/g,"&amp;");
							obji = obji.replace(/</g,"&lt;");
							obji = obji.replace(/>/g,"&gt;");
							obji = obji.replace(/ /g,"&nbsp;");
							obji = obji.replace(/￠/g,"&cent;");
							obji = obji.replace(/£/g,"&pound;");
							obji = obji.replace(/¥/g,"&yen;");
							obji = obji.replace(/§/g,"&sect;");
							obji = obji.replace(/©/g,"&copy;");
							obji = obji.replace(/®/g,"&reg;");
							obji = obji.replace(/×/g,"&times;");
							obji = obji.replace(/÷/g,"&divide;");

							resultDiv.append("<div class=\"resultList\">" + obji + "</div>");
						}
					} else {
						alert("处理错误 : " + resultPack.errMessage);
					}
				}
			});
}