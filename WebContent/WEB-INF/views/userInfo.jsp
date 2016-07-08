<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String contextPath=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort()+contextPath+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base herf="<%=basePath%>"/>
<title>好吃鱼Platform</title>
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
<link href="../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<!-- start menu -->
  
</head>
<body>
<!-- header -->
<%@ include file="./common/common_header.jsp"%>
<!---->
<script type="text/javascript">
function checkSubmit(){
    if($('#file').val() == ''){
        return true;
    }else if(!isvalidatefile($('#file').val())){
        return false;
    }
}
function isvalidatefile(obj) {  
    
    var extend = obj.substring(obj.lastIndexOf(".") + 1);  
    //alert(extend);  
    if (extend == "") {
    } else {  
        if ((extend == "jpg")||(extend == "bmp")||(extend == "png")) {  
            return true;  
        }
    }  
    alert("请上传png/jpg图片格式!");
    return false;  
}  
</script>
<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!---//pop-up-box---->			
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">    
     	<div class="blog contact-grid">     
             <h3 class="clr1">个人信息</h3>
             <form name="userInfoForm" action="../user/saveUserInfo" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit();">
             	<div>
                	<label class="form-label">昵称</label>
                    <input type="text" class="form-control" name="nickname" value="${master.nickname }" onblur="">		
                </div>
                <div>
                	<label class="form-label">个人主页</label>
                    <input type="text" class="form-control" name="website" value="${master.website}" onblur="">		
                </div>
                <div>
                	<label class="form-label">兴趣爱好</label>
                    <input type="text" class="form-control" name="interest" value="${master.interest}" onblur="">		
                </div>
                
                <div>
                	<label class="form-label">个性签名</label>
                    
                    <textarea class="form-control" name="signature" cols="77" rows="6" value=" " onblur="if (this.value == '') {this.value = 'Message';}">${master.signature}</textarea>
                </div>
                <div >
                	<label >性别</label>
                    <input type="radio" name="sex" value="1" <c:choose> <c:when test="${master.sex==1}"> checked </c:when> </c:choose> /> 男 
                    <input type="radio" name="sex" value="2" <c:choose> <c:when test="${master.sex==2}"> checked </c:when> </c:choose> /> 女
                    <%-- <s:radio list='#{1:"Boy",2:"Girl"}' name="sex"  label="Select your LoginType please" value="master.sex"></s:radio>  --%>
                </div>
                <div>
                	
                    	<label class="form-label">头像</label>
                        <img src="../${master.photopath}"/>
                        <!-- <input type="button" value="上传" onlick="uploadPhoto()" id="uploadPhoto"/> -->
                        <input type="file" value="file" name="file" id="file" onChange=""/>
                	
                </div>
                <br/>
                <div class="send">
                    <input type="submit" value="保存" >
                </div>
             </form>
		</div>
		<%@ include file="./common/blog_footer.jsp"%>	 
	 </div>
</div>
<!---->
</body>
</html>