<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>添加作品</title>
<link href="/MyPlatform/webpage/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="/MyPlatform/webpage/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/js/my/addwork.js"></script>
<!-- Custom Theme files -->
 <link href="/MyPlatform/webpage/css/dashboard.css" rel="stylesheet">
<link href="/MyPlatform/webpage/css/blog_style.css" rel='stylesheet' type='text/css' />

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
     	<div class="blog contact-grid">     
             <h3 class="clr1">添加作品</h3>
             <form name="addWorkForm" id="addWorkForm" action="addWork.action" method="post" enctype="multipart/form-data">
             	<div>
                	<label class="form-label">作品名</label>
                    <input type="text" class="form-control" name="name" id="input_name" value="${work.name }">		
                </div>
                <div>
                	<label class="form-label">简介</label>
                    <input type="text" class="form-control" name="description" value="${work.description }" >		
                </div>
                <div>
                	<label class="form-label">使用技术/工具</label>
                    <input type="text" class="form-control" name="tool" value="${work.tool }" >		
                </div>
                 <div>
                	<label class="form-label">源码地址</label>
                    <input type="text" class="form-control" name="srcPath" value="${work.src_path }">		
                </div>
                <div>
                	<label class="form-label">详细说明</label>
                    <textarea rows="30" cols="50" name="detail" >${work.detail }</textarea>
		 	   		<script type="text/javascript">CKEDITOR.replace('detail');</script>
                </div>
                <div >
                	<label >类型</label>
                    
                    <s:radio list='#{1:"视频",2:"应用"}' name="type"  label="" value="work.type"></s:radio> 
                </div>
                <div>
                    	<label class="form-label">图标</label>
                        <img src=""/>
                        <input type="file" value="cover" name="cover" onChange=""/>
                </div>
                </br>
                <div>
                    	<label class="form-label">作品</label>
                        <img src=""/>
                        <input type="file" value="res" name="res" onChange=""/>
                </div>
                <br/>
                <input type="hidden" name="workId" value=${work.id}>
                <div class="send">
                    <input type="button" onclick="checkAddWork()" value="添加" />
                </div>
             </form>
		</div>
		<%@ include file="../../blog_footer.jsp"%>	 
	 </div>
</div>
<!---->
</body>
</html>