<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://zhihaoyu/tags" prefix="myTag" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<c:url var="showWorkDetail_url" value="/work.html/id/" />
<c:url var="intoWorkManage_url" value="intoWorkManage.html" />
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
	的作品管理
</title>
<link href="/MyPlatform/webpage/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="/MyPlatform/webpage/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/ckeditor/ckeditor.js"></script>
<!-- Custom Theme files -->
 <link href="/MyPlatform/webpage/css/dashboard.css" rel="stylesheet">
<link href="/MyPlatform/webpage/css/blog_style.css" rel='stylesheet' type='text/css' />
<link href="./webpage/css/myworks.css" rel='stylesheet' type='text/css' />

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
		<h3 class="clr1">作品管理</h3> 
	  	<br/>
	  	<table style="border:teal 4 double">
	  		<s:if test="works.size()>O">
	  			<s:iterator value="works">
	  				<tr>
	  					<td><s:property value="#rowstatus.count" /></td>
			  			<td>
			  				<div class="mywork-left">
						 		<a href="${showWorkDetail_url}${id}" target="_blank"><img src="/MyPlatform${img_path}" /></a>
						 	</div>
			  			</td>
			  			<td>
			  				<div class="mywork-right">
						 		<h4><a href="${showWorkDetail_url}${id}" target="_blank">${name}</a></h4>
						 		<small>${description}</small>		 		
						 	</div>
			  			</td>
			  			<td><a href="deleteWork.action?workId=${id}">删除</a></td>
			  			<td><a href="intoModifyWork.action?workId=${id}">编辑</a></td>
			  		</tr>
	  			</s:iterator>
	  			
	  		</s:if>
	  	</table>
	  	<myTag:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${intoWorkManage_url }" recordCount="${pm.recordCount}"/>
		  <%@ include file="../../blog_footer.jsp"%>	 
	 </div>
	 
</div>
<!---->
</body>
</html>