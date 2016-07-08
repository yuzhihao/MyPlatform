<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>好吃鱼简历</title>
<link href="/MyPlatform/webpage/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary JavaScript plugins) -->
<script src="/MyPlatform/webpage/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/MyPlatform/webpage/ckeditor/ckeditor.js"></script>
<!-- Custom Theme files -->
 <link href="/MyPlatform/webpage/css/dashboard.css" rel="stylesheet">
<link href="/MyPlatform/webpage/css/blog_style.css" rel='stylesheet' type='text/css' />
<link href="/MyPlatform/webpage/css/cv.css" rel='stylesheet' type='text/css' />

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
<%@ include file="../common/common_header.jsp"%>
<!---->
<link href="/MyPlatform/webpage/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<script src="/MyPlatform/webpage/js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
	 	<div class="details_header">
			 <ul>
				 <div id="small-dialog" class="mfp-hide">
					 <img src="/MyPlatform/photo/zhenxiang.jpg" style="" alt=""/>
				 </div>
				 <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});											
						});
				</script>
			 </ul>
		 </div>
		 <div class="blog">    
			<h3 class="clr1">我的基本信息</h3>
			
			<h6>姓名<span>余知昊</span></h6>
			<h6>学校<span>华南理工</span></h6>
			<h6>专业<span>软件工程</span></h6>
			<h6>邮箱<span>928435030@qq.com</span></h6>
			<h6>电话<span>13622367764</span></h6>
			<h6>照片<span><a class="play-icon popup-with-zoom-anim" href="#small-dialog"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span>点击查看</a></span></h6>
		 	
		 	<div>
		 	</div>
	     	<h3 class="clr2">工作项目经历</h3>
	     	<div class="company_details">
				 <h4>个人网站建设 <span>Oct 2015 - 今天</span></h4>
			 		<br/>
			 	 <ul>					 
					<li>个人项目，网站包含个人主页、个人博客平台、作品展示等 </li>
					<li>独立完成设计、开发、测试、维护等各个阶段的全部工作</li>
					<li>Web框架采用struts2+Spring+jdbc。服务器Linux，容器Tomcat，数据库Mysql</li>
				 </ul>
				 
				 <p class="cmpny1"></p>
			 </div>
			 <div class="company_details">
				 <h4>深圳万达宝公司 <span>Jul 2014 - Jul 2015</span></h4>
				 <h6>J2EE研发工程师</h6>
			 	 <ul>					 
					<li>在公司的j2ee底层框架上进行新系统模块功能开发 </li>
					<li>解决客户bug及实现新的需求，开发重构公司底层代码 </li>
					<li>使用技术/工具：ssh框架、EJB、JSF、js、sql server、JBoss、tomcat、RESTful、其他</li>
				 </ul>
				 <p class="cmpny1"></p>
			 </div>
			 <div class="company_details">
				 <h4>UC优视科技 <span>Aug 2013 - Nov 2013</span></h4>
				 <h6>C++实习研发工程师</h6>
			 	 <ul>					 
					<li>接受qt框架，cocos2d引擎，lua脚本方面的技能培训 </li>
					<li>自主完成了工作室内部美术资源编辑器的开发   </li>
					<li>使用技术/工具: C++ 、cocos2dx、qt框架、其他</li>
				 </ul>
				 <p class="cmpny1"></p>
			 </div>
	     	
	     	
	     	 <h3 class="clr3">专业技能评价</h3>
			 <div class="skill-panel">
				<div class="progress">
			      <span class="green" style="width: 90%;"><span>java语言</span></span>
			    </div>
			 	<div class="progress">
			      <span class="blue" style="width: 88%;"><span>J2EE三大框架使用</span></span>
			    </div>
			 	<div class="progress">
			      <span class="orange" style="width: 80%;"><span>C++语言</span></span>
			    </div>
			 	<div class="progress">
		     	  <span style="width: 70%;"><span>sql查询语言</span></span>
			    </div>
			    <div class="progress">
			      <span class="red" style="width: 69%;"><span>js脚本语言</span></span>
			    </div>
			 </div>
		 </div>
	 </div>
	 <%@ include file="../common/blog_footer.jsp"%>	 
</div>
<!---->
</body>
</html>