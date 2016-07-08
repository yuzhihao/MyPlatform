<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<c:url var="showBlogDetail_url" value="/blog/blogDetail/" />
<c:url var="modifyBlog_url" value="/blog/intoModifyBlog" />
<c:url var="deleteBlog_url" value="/blog/deleteBlog" />
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>
	<s:if test="#session.username != null">
		<s:if test="#session.nickname!=null">
			<s:property value="#session.nickname"/>
		</s:if>
		<s:else>
			<s:property value="#session.username"/>
		</s:else>
	</s:if>
	<s:else>
		好吃鱼
	</s:else>
	的博客管理
</title>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="../js/jquery-1.11.1.min.js"></script>
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
<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link href="../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
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
		 <div class="blog">
		 	<c:choose>
		 		<c:when test="${blogs!=null}">
			 		<c:forEach items="${blogs }" var="item" varStatus="rowstatus">
			 			<h4>${rowstatus.count}<a href="${showBlogDetail_url}${item.id}">${item.title}</a></h4>
						<a href="${deleteBlog_url}?blogId=${item.id}">删除</a>   <a href="${modifyBlog_url}?blogId=${item.id}">编辑</a>
					
			 		</c:forEach>
		 		</c:when>
		 	</c:choose>
		 	
		 	<%-- <s:if test="blogs.size()>O">
		 		<s:iterator value = "blogs" status="rowstatus">
				   <h4>	<s:property value="#rowstatus.count" /> <a href="${showBlogDetail_url}${id}">${title}</a></h4>
					<a href="deleteBlog.action?blogId=${id}">删除</a>   <a href="intoModifyBlog.action?blogId=${id}">编辑</a>	
		 		</s:iterator>
			   <myTag:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="intoBlogManage.action" recordCount="${pm.recordCount}"/> 
		 	</s:if> --%>
		 </div>
		 <%@ include file="./common/blog_footer.jsp"%>	 
	 </div>
	 
</div>
<!---->
</body>
</html>