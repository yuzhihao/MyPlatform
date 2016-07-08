// login js定义


  function emailCheck() { 
    	var inputText = document.getElementById("form-username");  
      	var value = inputText.value;
        var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
        if (!pattern.test(value)) {  
           // alert("请输入正确的邮箱地址。");
        	//$("#form-username").data-content="邮箱地址不正确";
        	
        	$("#tipLabel").text("请输入正确的邮箱格式");
        	//$("#form-username").popover('show');
            $("#form-username").addClass('input-error');
            //inputText.addClass('input-error');
          
            return false;  
        }else{
        	$("#tipLabel").text("");
        	$("#form-username").removeClass('input-error');
        	return true;  
        }
       
    };
    function passwordCheck(){
    	var inputText = document.getElementById("form-password");
    	var value = inputText.value;
    	if(value==""){
    		$("#tipLabel").text("请输入密码");
    		$("#form-password").addClass('input-error');
            return false;  
        }else{
        	$("#tipLabel").text("");
        	$("#form-password").removeClass('input-error');
        	return true;  
        }
    };
    function checkInput(){
    	if(!emailCheck())
    		return false;
    	else if(!passwordCheck())
    		return false;
    	else
    		return true;
    };
    function login(){
    	if(!checkInput())
    		return false;
    	else{
    		//获取keyPair进行加密
    		$.post("keyPair",
    		
    		function(data){
    			var modules = data.modules, exponent = data.exponent;
    			var psw = $("#form-password").val();
    	        RSAUtils.setMaxDigits(200);
    	        var key = new RSAUtils.getKeyPair(exponent, "", modules);             
    	        var encrypedPwd = RSAUtils.encryptedString(key,psw);  
    	        $("#form-password").val(encrypedPwd);
    	        
    	        var psw =  $("#form-password").val();
        		//用加密后的密码登录
        		$.post("login", {
        			username : $("#form-username").val(),
        			password : psw,
        		},
        		function(data){
        			if(data.login){
        				post("../blog/listBlog",{});
        			}else{
            			if(data.isUserNameValid){
            				$("#tipLabel").text("该用户名不存在");
            				$("#form-username").addClass('input-error');
            				$("#form-password").addClass('input-error');
            			}else{
            				$("#tipLabel").text("密码不正确");
            				$("#form-password").addClass('input-error');
            			}
        			}
        		}, "json");
    		});
    		
    	};
    };

    function pswEncrypt(form) {
    	var psw = $("#form-username").val()
        RSAUtils.setMaxDigits(200);
        var key = new RSAUtils.getKeyPair("${pubexponent}", "", "${pubmodules}");             
        var encrypedPwd = RSAUtils.encryptedString(key,psw);  
        return encrypedPwd;
    }
   
jQuery(document).ready(function() {
	// $('[data-toggle="popover"]').popover();
    /*
        Fullscreen background
    */
    $.backstretch([
                    "../img/backgrounds/2.jpg"
	              , "../img/backgrounds/3.jpg"
	              , "../img/backgrounds/1.jpg"
	             ], {duration: 3000, fade: 750});
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
   /* $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	if(!emailCheck())
    		return false;
    	else if(!passwordCheck())
    		return false;
    	else
    		;
    	
    });*/
  
    
});