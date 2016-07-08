<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<c:url var="listBLog1_url" value="/blog/listBlog?userId=1&isFromRegist=true" />
<c:url var="myworks_url" value="/myworks.html" />
<c:url var="cv_url" value="/cv.html" />
<!DOCTYPE html>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>好吃鱼的主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript">
 	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
	function hideURLbar(){ 
		window.scrollTo(0,1); 
	} 
 </script>
<link href="/MyPlatform2/resources/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="/MyPlatform2/resources/css/style_welcome.css" rel='stylesheet' type='text/css' />
<script src="/MyPlatform2/resources/js/jquery-1.11.1.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="/MyPlatform2/resources/js/move-top.js"></script>
<script type="text/javascript" src="/MyPlatform2/resources/js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
</script>
<!--start-smoth-scrolling-->
<!--animated-css-->
		<link href="/MyPlatform2/resources/css/animate.css" rel="stylesheet" type="text/css" media="all">
		<script src="/MyPlatform2/resources/js/wow.min.js"></script>
		<script>
		 new WOW().init();
		</script>
<!--animated-css-->  
</head>
<body>

	<!--start-banner-->
		<div class="banner" id="home">
			<div  id="top" class="callbacks_container">
			     <ul class="rslides" id="slider4">
			       <li>
	    				<div class="banner-1">
						</div>
					</li>
					 <li>
	    				<div class="banner-2">
						</div>
					</li>
			     </ul>
			</div>
			<div class="clearfix"> </div>
			<div class="header">
				<div class="logo">
					<a href="http://www.zhihaoyu.com"><img src="/MyPlatform2/resources/img/zhihaoyu.png" alt=""></a>
				</div>
				<div class="navigation">
				 <span class="menu"></span> 
					<ul class="navig">
						<li><a href="${listBLog1_url}" target="_blank">博客</a><span>|</span></li>
						<li><a href="${cv_url}" target="_blank">简介</a><span>|</span></li>
						<li><a href="${myworks_url}" target="_blank">作品</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="banner-bottom">
				<ul>
					<li><a href="http://wpa.qq.com/msgrd?v=1&uin=928435030&site=qq&menu=yes" target="_blank" ><span class="twt"> </span></a></li>
					<li><a href="http://blog.csdn.net/yu_zhihao" target="_blank"><span class="t"> </span></a></li>
					<li><a href="https://github.com/yuzhihao" target="_blank"><span class="g"> </span></a></li>
				</ul>
			</div>
		</div>	
	<!-- script-for-menu -->
		 <script>
				$("span.menu").click(function(){
					$(" ul.navig").slideToggle("slow" , function(){
					});
				});
		 </script>
		 <!-- script-for-menu -->
		<!--Slider-Starts-Here-->
				<script src="/MyPlatform2/resources/js/responsiveslides.min.js"></script>
			 <script>
			    // You can also use "$(window).load(function() {"
			    $(function () {
			      // Slideshow 4
			      $("#slider4").responsiveSlides({
			        auto: true,
			        pager: false,
			        nav: true,
			        speed: 500,
			        namespace: "callbacks",
			        before: function () {
			          $('.events').append("<li>before event fired.</li>");
			        },
			        after: function () {
			          $('.events').append("<li>after event fired.</li>");
			        }
			      });
			
			    });
			  </script>
			<!--End-slider-script-->
	<!--end-banner-->	
	<!--start-free-->	 
	<div class="free wow bounce" data-wow-delay="0.1s">
		<div class="container">
			<div class="free-main">
				<h1><sup><img src="/MyPlatform2/resources/images/quote-1.png" alt=""></sup>用心做事，用心做人 <sub><img src="/MyPlatform2/resources/images/quote-2.png" alt=""></sub> </h1>
			</div>
		</div>
	</div>
	<!--end-free-->	 
	
</div>
<link rel="stylesheet" href="/MyPlatform2/resources/css/swipebox.css">
	<script src="/MyPlatform2/resources/js/jquery.swipebox.min.js"></script> 
	    <script type="text/javascript">
			jQuery(function($) {
				$(".swipebox").swipebox();
			});
		</script>
	<!-- Portfolio Ends Here -->
	<script type="text/javascript" src="/MyPlatform2/resources/js/jquery.mixitup.min.js"></script>
<script type="text/javascript">
$(function () {
	
	var filterList = {
	
		init: function () {
		
			// MixItUp plugin
		// http://mixitup.io
		$('#portfoliolist').mixitup({
			targetSelector: '.portfolio',
			filterSelector: '.filter',
			effects: ['fade'],
			easing: 'snap',
			// call the hover effect
			onMixEnd: filterList.hoverEffect()
		});				
	
	},
	
	hoverEffect: function () {
	
		// Simple parallax effect
		$('#portfoliolist .portfolio').hover(
			function () {
				$(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
				$(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
			},
			function () {
				$(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
				$(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
			}		
		);				
	
	}

};

// Run the show!
	filterList.init();
	
});	
</script>
<!--Blog-Starts-Here-->
	<div class="blog">
		<div class="container">
			<div class="blog-main">
				<div class="col-md-4 blog-left">
					<div class="blog-one wow bounceInLeft" data-wow-delay="0.4s"  onclick="window.open('http://www.zhihaoyu.com/cv.html')">
						<img src="/MyPlatform2/resources/images/blog-1.png" alt="" />
						<h3>简介</h3>
						<p>鄙人布衣，来自井盖省，善良无鞋，是个好人</p>
					</div>
				</div>
				<div class="col-md-4 blog-left">
					<div class="blog-one wow bounce" data-wow-delay="0.1s"  onclick="window.open('http://www.zhihaoyu.com/blog/1')">
						<img src="/MyPlatform2/resources/img/xixi_big.jpg" alt="" />
						<h3>博客</h3>
						<p>山上有个庙，庙里有个老和尚在跟小和尚讲故事 </p>
					</div>
				</div>
				<div class="col-md-4 blog-left ">
					<div class="blog-one wow bounceInRight" data-wow-delay="0.4s" onclick="window.open('http://www.zhihaoyu.com/myworks.html')">
						<img src="/MyPlatform2/resources/images/blog-3.png" alt=""  />
						<h3>作品</h3>
						<p>要看就点我吧，哼！</p>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
<!--Blog-Ends-Here-->
<!--Contact-Starts-Here-->
	<div class="contact" id="contact">
		<div class="container">
			<div class="contact-main">
				<div class="col-md-6 contact-left wow bounceInLeft" data-wow-delay="0.4s">
					<h3>余知昊</h3>
					<p> </p>
					<p>您的每一个意见</p>
					<ul>
						<li><p>QQ: 928435030</p></li>
						<li><p><a href="mailto:928435030@qq.com"> Email: 928435030@qq.com </a></p></li>
					</ul>
				</div>
				<div class="col-md-6 contact-left wow bounceInRight" data-wow-delay="0.4s">
					<input type="text" value="您的称呼" id="name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '您的称呼';}"/>
					<input type="text" value="您的邮箱或别的联系方式" id="senderContact" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '您的邮箱或别的联系方式';}"/>
					<div class="contact-textarea">
						<textarea value="Your question" id="message" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '给我留言~';}">给我留言~</textarea>
					</div>
					<div class="contact-but">
						<input type="submit" onclick="sendAdvice()" value="发发发" />
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
<!--Contact-Ends-Here-->
<!--Footer-Starts-Here-->

	<div class="footer">
		<div class="conatiner">
			<div class="footer-text wow bounce" data-wow-delay="0.1s">
				<p>&copy; yuzhihao 2015  <a href="#"></a></p>
				<ul>
					<li><a href="http://wpa.qq.com/msgrd?v=1&uin=928435030&site=qq&menu=yes" target="_blank"><span class="twt"> </span></a></li>
					<li><a href="http://blog.csdn.net/yu_zhihao" target="_blank"><span class="t"> </span></a></li>
					<li><a href="https://github.com/yuzhihao" target="_blank"><span class="g"> </span></a></li>
				</ul>
			</div>
		</div>
		 <script type="text/javascript">
									$(document).ready(function() {
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
	</div>
    <footer>&copy; 2015 YuZhihao</footer>
<!--Footer-Ends-Here-->
 <script src="/MyPlatform2/resources/js/my/util.js"></script>
 <script src="/MyPlatform2/resources/js/my/welcome.js"></script>
</body>
</html>