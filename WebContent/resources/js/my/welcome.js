function sendAdvice(){
	if(!checkInput())
		return false;
	else{
		$.post("sendAdvice.action", {
			name : $("#name").val(),
			message : $("#message").val(),
			senderContact : $("#senderContact").val(),
		},
		function(response){
			 document.getElementById("name").value="您的称呼";
			 document.getElementById("senderContact").value="您的邮箱或别的联系方式";
			 document.getElementById("message").value="给我留言~"
			alert("发送成功,感谢您的留言")
			
		}, "json");
	}
}

function checkInput(){
	var value = document.getElementById("name").value;  
  	if(value=="您的称呼"){
  		alert("请输入您的称呼");
  		return false;
  	}else if(document.getElementById("senderContact").value=="您的邮箱或别的联系方式"){
  		alert("请输入您的联系方式");
  		return false;
  	}else if(document.getElementById("message").value=="给我留言~"){
  		alert("请输入您的留言内容");
  		return false;
  	}
  	return true;
}