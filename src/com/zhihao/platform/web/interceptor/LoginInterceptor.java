package com.zhihao.platform.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhihao.platform.web.controllers.MyController;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String[] LOGIN_REQUIRE_URL = {"/writeBlog", "/intoWriteBlog","intoBlogManage","blogManage","intoUserInfo","saveUserInfo"};
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;
        String url = request.getRequestURL().toString();
        System.out.println(">>>: " + url);
        for (String s : LOGIN_REQUIRE_URL) {
            if (url.contains(s)) {
                flag = false;
                break;
            }
        }
    	//判断session中状态 需要登录的这些，如果登录了，flag就设为true
    	if(!flag){
    		String userStatus = (String) request.getSession().getAttribute(MyController.STATUS);
        	if(userStatus!=null && userStatus.equalsIgnoreCase(MyController.STATUS_LOGIN)){
        		flag = true;
        	}	
    	}
        
        //登录拦截后跳转到登录页面
        if(!flag){
        	 response.sendRedirect("/MyPlatform2/user/login");
        }
        return flag;
		
	}

}
