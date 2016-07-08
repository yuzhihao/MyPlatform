// regist js定义

  function emailCheck() { 
    	var inputText = document.getElementById("email");  
      	var value = inputText.value;
        var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
        if (!pattern.test(value)) {  
        	$("#tipLabel").text("请输入正确的邮箱格式");
            $("#email").addClass('input-error');
            return false;  
        }else{
        	$("#tipLabel").text("");
        	$("#email").removeClass('input-error');
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
    
    function regist(){
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
        		//用加密后的密码注册
    	        $.post("regist", {
        			email : $("#email").val(),
        			password : psw,
        		},
        		function(response){
        			if(response.regist){
        				post("../blog/listBlog",{isFromRegist:1});
        				//return;
        			}else{
        				var isRepeat = response.repeat;
            			if(isRepeat){
            				$("#tipLabel").text("该邮箱已被注册");
            				$("#email").addClass('input-error');
            			}else{
            				$("#tipLabel").text("注册异常，请重试");
            				$("#email").addClass('input-error');
            				$("#form-password").addClass('input-error');
            			}
        			}
        		}, "json");
    		}); 		
    	};
  };

jQuery(document).ready(function() {
	
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
    	alert($("#email").val());
    	$.ajax({
            type:'POST',
            async : true,
            url:"isEmailRepeat.action",
            data:{email:$('#email').val()},
            success: function(data){
            	alert(data);
            }
        });
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });*/
});