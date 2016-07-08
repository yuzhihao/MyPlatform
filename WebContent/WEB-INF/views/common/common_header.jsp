<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="login_url" value="/user/letslogin" />
<c:url var="regist_url" value="/user/regist"/>
<c:url var="listBlog_url" value="/blog/listBlog" /> 
<c:url var="intoWriteBlog_url" value="/blog/intoWriteBlog" /> 
<c:url var="intoBLogManage_url" value="/blog/intoBlogManage" /> 
<c:url var="intoUserInfo_url" value="/user/intoUserInfo" /> 
<c:url var="intoAddWork_url" value="/work/intoAddWork" />
<%-- <c:url var="intoWorkManage_url" value="/intoWorkManage.html" /> --%>
<c:url var="intoWorkManage_url" value="/intoWorkManage.html" />

<!-- <script src="../js/bootstrap.min.js" type="text/javascript"></script> -->
<!-- header -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1" ><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
      <a class="navbar-brand" href="#">好吃鱼的网站</a></div>

    <div class="collapse navbar-collapse header" id="defaultNavbar1" > 
      <ul class="nav navbar-nav navbar-right">
      
      	<c:choose>
      	
      		<c:when test="${sessionScope.username!=null}">
      			<li><a>您好：${sessionScope.nickname}</a></li>
				<li><a href="${listBlog_url}">我的博客</a></li>
				<li><a href="${intoWriteBlog_url}">写博客</a></li>
				<li><a href="${intoBLogManage_url}">博客管理</a></li>
				<li><a href="${intoUserInfo_url}">我的信息</a></li>
				<li><a href="javascript:logout();" id="logout">退出</a></li>
				<c:if test="#session.username=='yuzhihao@qq.com'">
					<li><a href="${intoAddWork_url}">添加作品</a></li>
					<li><a href="${intoWorkManage_url}">作品管理</a></li>
				</c:if>
      		</c:when>
      		<c:otherwise>
	      		<li><a href="${login_url }" target="_blank">登录</a></li>
	        	<li><a href="${regist_url }" target="_blank">注册</a></li>
      		</c:otherwise>
      	</c:choose>
        <script type="text/javascript">
	        function logout(){
	        	$.ajax({
	                type:'POST',
	                url:"../user/logout",
	                success: function(response){
	                    alert("您已经成功退出 ");
	                    location.reload();
	                }
	            });
			}
		</script>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>

<div class="col-sm-3 col-md-2 sidebar">
		 <div class="sidebar_top">
		   <h1>${master.nickname}</h1>
		   <img src="../${master.photopath}" alt=""/>
		 </div>
		<div class="details">
			 <h3>个人网站</h3>
			 <p><a href="${master.website}" target="_blank"> ${master.website} </a></p>
			 <h3>兴趣爱好 </h3>
			 
			 <p> ${master.interest} </p>  	
			 <h3>个性签名</h3>
			 <span>${master.signature} </span>
		</div>
		<div class="clearfix"></div>
</div>