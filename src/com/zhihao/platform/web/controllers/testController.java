package com.zhihao.platform.web.controllers;

import org.apache.catalina.startup.Tomcat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("zhihaoyu")
public class testController {
 
    @RequestMapping(value = "/welcome", method = {RequestMethod.GET})
    public ModelAndView  hello(){   
    	
    	Tomcat tomcat = Tomcat.
    	ModelAndView modelAndView = new ModelAndView();  
    	modelAndView.setViewName("welcome");  
        return modelAndView;
    }
}