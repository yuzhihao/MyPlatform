<%@ page language="java" contentType="text/html; charset=UTF-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<c:url var="addComment_url" value="/article/addcomment" />
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>
	${blog.title}
</title>
<link href="../../css/bootstrap.css" rel='stylesheet' type='text/css' />
<script src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../../js/my/comment.js"></script>
<link href="../../css/dashboard.css" rel="stylesheet">
<link href="../../css/blog_style.css" rel='stylesheet' type='text/css' />

<!-- Custom Theme files -->
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Curriculum Vitae Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link href="../../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<!-- start menu -->
  
</head>
<body>
<!-- header -->
<%@ include file="./common/common_header.jsp"%>
<!---->
<script src="../../js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
		
		 <div class="blog">
		 	
		 	
			 		<h3 class="clr1">${blog.title}</h3>
					<div class="blog_title_short">
						 <h4>
						 	<c:choose>
						 		<c:when test="${blog.isOriginal==1}">
						 			<img src="../../img/blog_yuanzhu.jpg"></img>
						 		</c:when>
						 		<c:otherwise>
						 			<img src="../../img/blog_zhuanzai.jpg"></img>
						 		</c:otherwise>
						 	
						 	</c:choose>
						 	<span>${blog.date}</span>
						 </h4>
						 <div class="blog_title_panel">
							 	 <img src="../../img/blog_view.png"></img>阅读数(${blog.viewcount})
							 	 <img src="../../img/blog_zan.png"></img>
							 	 <label onclick="zan(${blog.id},<s:if test="#session.nickname!=null">1</s:if><s:else>0</s:else>)" id="zan">赞(${blog.markcount})</label>
							 	 <input id="isZan" type="hidden" value=0></input>
							 	 <img src="../../img/blog_comments.png"></img>评论(${blog.commentcount})
						 	</div>
						 <h6> </h6>
						
					</div>
					<div class="blog_content">
						 <%-- <p class="cmpny1">${blog.content}</p> --%>
						 ${blog.content}
					</div>
		 		
		 	
		 </div>
		 
		 <div>
		 	<br/>
		 	<form action="/MyPlatform/addComment.action" method="post" id="commentForm">
		 		<label >添加评论</label><br/>
	            <textarea name="commentContent" cols="77" rows="6"  id="commentContent">。。。</textarea>
	            <div class="sendComment">
	                <!--  <input type="submit" value="Send" > -->
	                <input type="button" onclick="comment()" value="评论" />
	            </div>
	            <input type=hidden name='blogId' id="blogId" value="${blog.id}" />
		 	</form>
        	<br/>
        </div>
        <div id="comments">
        	<c:choose>
        		<c:when test="${comments!=null}">
        			<c:forEach items="${comments}" var="item">
        				<div class="comment">
				        	<div class="comment-header">
				                <p>用户：${item.username} 时间： ${item.date} 评论：</p>
				            </div>
				            <div  class="comment-content">
				            	<img src="/MyPlatform/${item.photopath}"></img>
				            	<p>${item.content}</p>
				            </div>
				            <br/>
				            <br/>
				        </div>
        			</c:forEach>
        		</c:when>
        	</c:choose>
        </div>
        
		<%@ include file="./common/blog_footer.jsp"%>	
	 </div>
</div>
<script src="../../js/my/blogDetail.js" type="text/javascript"></script>
<script src="../../js/my/util.js" type="text/javascript"></script>
<!---->
</body>
</html>