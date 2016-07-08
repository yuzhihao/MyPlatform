 /**
 * @author yu
 * @param 'pages/xxx.action', {param1:value1,param2:'value2',param3:'value3'}
 * 手动调用的post函数
 */
function post(url, params) {
	var temp = document.createElement("form");
	temp.action = url;
	temp.method = "post";
	temp.style.display = "none";
	for ( var x in params) {
		var opt = document.createElement("textarea");
		opt.name = x;
		opt.value = params[x];
		temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
	return temp;
}