<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
	System.out.println(basePath);
%>
<!DOCTYPE html>

<c:url var="regist_url" value="/regist.html" />
<html lang="en">

    <head>
		<base herf="<%=basePath%>"/>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>登录好吃鱼的网站</title>

        <!-- CSS -->
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/form-elements.css">
        <link rel="stylesheet" href="../css/style.css">
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                          <h1>登录好吃鱼的网站</h1>
                          <div class="description">
                            <p>&nbsp;</p>
                          </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        		  <h3>输入您的用户名和密码 </h3>
                        		  <p>Enter your username and password to log in:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
                            	<label id="tipLabel" style="color:#F00"></label>
			                    <form role="form" action="login.action" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="username">Username</label>
			                        	<input type="text" name="username" placeholder="Username..." onblur="return checkInput()" class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">Password</label>
			                        	<input type="password" name="password" placeholder="Password..." onblur="return checkInput()"class="form-password form-control" id="form-password">
			                        </div>
			                         <div class="form-group">
                                		<a class="btn-link-1" href="#" onclick="login()" id="loginBtn" >登录 </a>
                                		<a class="btn-link-2" href="${regist_url }">注册</a>
	                                </div>
	                                <div>
	                                	
	                                </div>
	                                <script type="text/javascript">
	                                
	                                </script>
                                </form>
                               
		                    </div>
                        </div>
                    </div>
                   
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script type="text/javascript">
	        
		</script>
        <script src="<c:url value="/js/security.js"/>" type="text/javascript" ></script>
        <script src="../js/jquery-1.11.1.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../js/jquery.backstretch.min.js"></script>
        <script src="../js/my/login.js"></script>
        <script src="../js/my/util.js"></script>
        
	   
        <!--[if lt IE 10]>
            <script src="js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>