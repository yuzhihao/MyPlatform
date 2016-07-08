<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>

<c:url var="blogtype_url" value="/blog/${master.id}/type/" />
<c:url var="showBlogDetail_url" value="/blog/blogDetail/" />
<c:url var="listBlog_url" value="/listBlog.html"/>

<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>
	${master.nickname}的博客
</title>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<!-- Custom Theme files -->
<link href="../css/dashboard.css" rel="stylesheet">
<link href="../css/blog_style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Curriculum Vitae Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- start menu -->
  
</head>
<body>
<!-- header -->
<%@ include file="./common/common_header.jsp"%>
<!---->
<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
		 <div class="details_header">
			 <ul>
				 <li><a href="${blogtype_url}1"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>技术分享</a></li>
				 <li><a href="${blogtype_url}2"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>网络文摘</a></li>
				 <li><a href="${blogtype_url}3"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>鸡汤</a></li>
				 <li><a href="${blogtype_url}4"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>段子</a></li>
			 </ul>
		 </div>
		 <div class="blog">
		 	<c:choose>
		 		<c:when test="${isFromRegist==1}">
		 			<p>注册成功，欢迎使用好吃鱼博客平台</p>
		 		</c:when>
		 		<c:when test="${blogs!=null}">
		 			<c:forEach items = "${blogs}" var="item">
				 		<h3 class="clr1">${title}</h3>
						<div class="blog_title_short">
							 <h4><a href="${showBlogDetail_url}${item.id}" target="_blank">${item.title}</a>
							 	<c:choose>
							 		<c:when test="${item.isOriginal}==1">
							 			<img src="../img/blog_yuanzhu.jpg"></img>
							 		</c:when>
							 		<c:otherwise>
							 			<img src="../img/blog_zhuanzai.jpg"></img>
		 							</c:otherwise>
							 	</c:choose>
							 	<span>${item.date}</span></h4>
							 
							 <div class="blog_title_panel">
							 	 <img src="../img/blog_view.png"></img>阅读数(${item.viewcount})
							 	 <img src="../img/blog_zan.png"></img>赞(${item.markcount})
							 	 <img src="../img/blog_comments.png"></img>评论(${item.commentcount})
							 </div>
							 <p>${item.contentShort}</p>
							 <h6> </h6>
						</div>
		 			</c:forEach>
		 		</c:when>
		 		<c:otherwise>
		 		</c:otherwise>
		 	
		 	</c:choose>
		 	
		 	<c:if test="blogs.size()>O">
		 		
		 		<!-- listBlog.action -->
			    <%-- <myTag:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${listBlog_url }" recordCount="${pm.recordCount}"/> --%>
		 	</c:if>
		 </div>
	 
	 <%@ include file="./common/blog_footer.jsp"%>	 
	 </div>

</div>
<!---->

</body>
</html>