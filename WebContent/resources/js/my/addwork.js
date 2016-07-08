function checkAddWork(){
	var name = document.getElementById("input_name").value;
	if(name==null || name==""){
		alert("作品名不能为空");
		//return false;
	}else{
		document.getElementById("addWorkForm").submit();
		//return true;
	}
}