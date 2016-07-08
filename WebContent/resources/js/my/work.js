function clickSrc(url){
	if(url==null || url==""){
		alert("抱歉哦，主人未上传源码")
	}else{
		window.open("http://"+url);
	}
}