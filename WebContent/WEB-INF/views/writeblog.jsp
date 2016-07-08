<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>编辑博客</title>
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
<!-- start menu -->
  
</head>
<body>
<!-- header -->
<%@ include file="./common/common_header.jsp"%>
<link href="../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
		 <div >     
         	<form role="form" action="../blog/writeBlog" method="post">
         	   <label>分类</label>
			   <select name="blogType" value="${blog.blogType }">
					<option value="1">技术分享</option>
					<option value="2">网络文摘</option>
					<option value="3">鸡汤</option>
					<option value="4">段子</option>
			   </select>
			   <label>是否原创</label>
			   <select name="isOriginal" value="${blog.isOriginal }">
					<option value="1">是</option>
					<option value="2">不是</option>
			   </select>
               <div>
	          	    <label class="form-label" style="float:left">标题</label>
	          	    <input type="text" class="form-control" placeholder="这里输入标题" name="blogTitle" value="${blog.title}"/>
			   </div>
			   
	       	   <textarea rows="30" cols="50" name="blogContent" >${blog.content }</textarea>
		 	   <script type="text/javascript">CKEDITOR.replace('blogContent');</script>
		 	   <input type="hidden" name="blogId" value=${blog.id}>
               <button type="submit" class=" btn">发表 </button>
            </form>
         </div>
         <%@ include file="./common/blog_footer.jsp"%>	 
	</div>
</div>
<!---->
</body>
</html>