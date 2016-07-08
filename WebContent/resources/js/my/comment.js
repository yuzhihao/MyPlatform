function comment(){
	var content = document.getElementById("commentContent").value;
	if( content==null || content==""){
		alert("评论内容不能为空");
		//return false;
	}else{
		$.post("/MyPlatform2/blog/addComment", {
			commentContent : content,
			blogId : $("#blogId").val(),
		},
		function(response){
			var comments = document.getElementById("comments");
			var tempHTML = comments.innerHTML;
			var appendHTML = "<div class=\"comment\">"+
        	"<div class=\"comment-header\">"+
            "<p>用户："+response.username+" 时间："+response.date+ " 评论：</p>"+
            "</div>"+
            "<div  class=\"comment-content\">"+
        	"<img src=\"/MyPlatform/photo/avt.png\"></img>"+
        	"<p>"+ response.commentContent+ "</p></div><br/><br/></div>";
        	comments.innerHTML= tempHTML +appendHTML;
			
		}, "json");
	}
}