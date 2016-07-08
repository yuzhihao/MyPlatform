function zan(id,username){
	var iszan = $("#isZan").val();
	if(username==0){
		alert("您需要登录才能赞哦");
		return;
	}
	if(iszan==0){
		//执行赞动作
		$("#isZan").val(1);
		$.post("/MyPlatform/zan.action", {
			id : id,
			userId : username
		},
		function(response){
			if(response.zan<=0){
				alert("您已经赞过了")
			}else{
				$("#zan").html("赞(" + response.zan + ")");
			}
		}, "json");
		
	}else{
		alert("您已经赞过了")
	}
}