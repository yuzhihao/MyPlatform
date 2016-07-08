<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://zhihaoyu/tags" prefix="myTag" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<c:url var="showWorkDetail_url" value="/work.html/id/" />
<c:url var="showMovie_url" value="/movie.html/id/" />
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>好吃鱼的作品</title>

<link href="/MyPlatform/webpage/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="/MyPlatform/webpage/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/js/my/addwork.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/js/my/work.js"></script>
<!-- Custom Theme files -->
<link href="/MyPlatform/webpage/css/dashboard.css" rel="stylesheet">
<link href="/MyPlatform/webpage/css/blog_style.css" rel='stylesheet' type='text/css' />
<link href="/MyPlatform/webpage/css/myworks.css" rel='stylesheet' type='text/css' />

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
<%@ include file="../../common_header.jsp"%>
<!---->
<link href="/MyPlatform/webpage/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<script src="/MyPlatform/webpage/js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
	 	<div class="work">
	 		<div>
	 			<h3 >${work.name}</h3>
	 		</div>
		 	<div class="work-left-right">
			 	<div class="work-left">
			  		<img src="/MyPlatform/${work.img_path }"/>
			  	</div>
			  	<div class="work-right">
			  		<div class="operation">
				  		<a href="/MyPlatform${work.res_path }" download>下载</a>
						<s:if test="work.type==1">
			  				<a href="${showMovie_url}${work.id}" target="_blank">播放</a>
			  			</s:if>
			  			<s:else>
			  				<a href="#"  onclick="clickSrc('${work.src_path}')">源码</a>
			  			</s:else>
				  	</div>
			  	</div>
		 	</div>		  	
		  	<div class="workDescription">
		  		<h4>说明</h4>
		  		${work.description }
		  	</div>
			<div class="workDetail">
				<s:if test="work.detail!=null">
					<h4>详细介绍</h4>
					${work.detail }
				</s:if>
			</div>	
	 	</div>
		<%@ include file="../../blog_footer.jsp"%>	 
	 </div>
	
</div>
<!---->
</body>
</html>