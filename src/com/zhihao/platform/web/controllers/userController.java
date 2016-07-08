package com.zhihao.platform.web.controllers;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhihao.platform.data.entity.KeyPairInfo;
import com.zhihao.platform.data.entity.LoginInfo;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.service.LoginService;
import com.zhihao.platform.util.RSAUtil;







@Controller
@RequestMapping("user")
public class userController extends MyController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;
	
	private User master = new User(1);
	
	private String address;
	private String telephone;
	
	@Autowired  
	private  HttpServletRequest request; 
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("keyPair")
	public @ResponseBody KeyPairInfo keyPair() throws Exception{
		logger.debug("keyPair() called");
		KeyPair kp = RSAUtil.generateKeyPair();
        RSAPublicKey pubk = (RSAPublicKey) kp.getPublic();//生成公钥
        RSAPrivateKey prik= (RSAPrivateKey) kp.getPrivate();//生成私钥
        String publicKeyExponent = pubk.getPublicExponent().toString(16);//16进制  
        String publicKeyModulus = pubk.getModulus().toString(16);//16进制 
        /* //test 加解密
        byte[] encodedBytes = RSAUtil.encrypt(pubk, "yuzhihao".getBytes());
        String encodedStr = new String(encodedBytes);
        byte[] decodedBytes = RSAUtil.decrypt(prik,encodedBytes);
        String decodedStr = new String(decodedBytes);*/
        //将私匙放入session
        request.getSession().setAttribute("prik", prik); 
        //保存公钥指数  公钥系数 给前端,前端进行加密
        KeyPairInfo keyPairInfo = new KeyPairInfo(publicKeyExponent,publicKeyModulus);
		return keyPairInfo;
	}
	
	@RequestMapping(value = "/regist" ,method ={RequestMethod.GET})
	public ModelAndView intoRegist() throws Exception{
		logger.debug("intoRegist called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping(value = "/regist" ,method ={RequestMethod.POST})
	public @ResponseBody LoginInfo regist(
			String email,
			String password,
			HttpSession httpSession)throws Exception{
		logger.debug("regist.action called :Email:"+ email +"  password:"+password);
		if(email!=null && password!=null){
			//判断email是否已经被注册
			LoginInfo loginInfo = new LoginInfo();
			boolean isRepeat = loginService.isEmailRepeat(email);
			if(!isRepeat){
				//注册
				//! 用私匙解密 password  注：当前  直接把password放入数据库
				RSAPrivateKey prik = (RSAPrivateKey)httpSession.getAttribute("prik");
		        String reversePsw = RSAUtil.decryptString(password,prik);
		        String truPsw = new StringBuffer(reversePsw).reverse().toString();
				if(loginService.regist(email, truPsw, "")){
					//registSuccess = true;
					//注册成功默认自动登录，更新session 并跳转
					httpSession.setAttribute(USERNAME, email);
					httpSession.setAttribute(NICKNAME, email);
					httpSession.setAttribute(STATUS, STATUS_LOGIN);
					loginInfo.setRegist(true);
				}
			}else{
				getReturnJsonMap().put("isRepeat", true);
				loginInfo.setRepeat(true);
			}
			return loginInfo;
		}
		return null;
	}
	
	@RequestMapping("letslogin")
	public ModelAndView letslogin() throws Exception{
		logger.debug("letslogin called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/login" ,method = {RequestMethod.GET})
	public ModelAndView letslogin2() throws Exception{
		logger.debug("letslogin called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "/login" ,method = {RequestMethod.POST})
	public @ResponseBody LoginInfo login(
			String username,
			String password,
			HttpSession httpSession ) throws Exception{
		logger.debug("login called. username:"+ username +"  password:"+password);
		RSAPrivateKey prik = (RSAPrivateKey)httpSession.getAttribute("prik");
        //! 用私匙解密 password  注：当前  直接把password放入数据库
        String reversePsw = RSAUtil.decryptString(password,prik);
        String truPsw = new StringBuffer(reversePsw).reverse().toString();
		LoginInfo info = new LoginInfo();
		if (loginService != null && loginService.validate(username, truPsw)) {
			User user = loginService.getUserByName(username);
			//if login in succeed, put userinfo into session
			if(user.getNickname()!=null && !user.getNickname().equals("")){
				httpSession.setAttribute(NICKNAME, user.getNickname());	
			}
			else{
				httpSession.setAttribute(NICKNAME, username);	
			}
			httpSession.setAttribute(USERNAME, username);
			httpSession.setAttribute(STATUS, STATUS_LOGIN);
			
			//mav.setViewName("???");//不跳转，只返回json值
			//mav.addObject("isLogin", true);
			info.setLogin(true);
			return info;
		}
		info.setUserNameValid(loginService.getUserByName(username)!=null);
		return info;

	}
	
	
	@RequestMapping(value = "/logout" ,method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody LoginInfo logout(
			HttpSession httpSession ){
		logger.debug("Logout called. username:"+ httpSession.getAttribute(USERNAME));
		if (httpSession.getAttribute(USERNAME)!=null) {
			httpSession.removeAttribute(USERNAME);
			httpSession.removeAttribute(NICKNAME);
			httpSession.setAttribute(STATUS, STATUS_LOGOUT);
			return null;
		}
		return null;
	}	
	
	@RequestMapping(value = "/intoUserInfo" ,method = {RequestMethod.GET})
    public ModelAndView intoUserInfo(HttpSession httpSession) throws Exception{
    	logger.debug("intoUserInfo called");
    	String username = (String) httpSession.getAttribute(USERNAME);
    	ModelAndView mav = new ModelAndView();
    	if(username!=null){
    		master = loginService.getUserByName(username);
    		mav.addObject("master", master);
    	}
    	mav.setViewName("userInfo");
        return mav;
    }
	
/*	@Action(value="saveUserInfo", results={ 
			@Result(name = "success", type="redirect", location = "intoUserInfo"), 
			@Result(name = "input", location = "/webpage/login.jsp")},
    		interceptorRefs={ 
    				@InterceptorRef(value="fileUpload",params={"maximumSize","1024000","allowedTypes",
    						"image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg"}), 
    				@InterceptorRef("authorityStack")})*/
	@RequestMapping(value = "/saveUserInfo" ,method = {RequestMethod.POST})
	public String saveUserInfo(
			@RequestParam(required = false) MultipartFile file,  
	        @RequestParam(value = "file_info_id", required = false) Integer fileInfoId,  
	        String nickname,
	        HttpServletRequest request,
			HttpSession httpSession) throws Exception{
    	logger.debug("saveUserInfo called");
    	String root ="";
		Properties props=System.getProperties(); //获得系统属性集
		String osName = props.getProperty("os.name"); //操作系统名称
		if(osName.toLowerCase().contains("windows")){
			root = request.getSession().getServletContext()
	                .getRealPath("/photo");
		}
		else{
			root =  "/usr/local/tomcat/res/photo";
		}
        String username = (String) httpSession.getAttribute(USERNAME);
        User user = loginService.getUserByName(username);
        // 写到指定路径
        if(file!=null){
        	File tempFile = new File(root);  
            //判断指定的路径下是否有uplaod，如果没有，自动创建  
            if (!tempFile.exists()) {  
            	tempFile.mkdirs();  
            }  
            String fileName = "photo_" + username+".jpg";
            //检查路径下是否已经存在该用户头像图片，有的话删除
            //
            logger.debug("path:" +root + "; fileName:" +fileName);
            
            File javaFile = new File(root,fileName);
            file.transferTo(javaFile);
            //FileUtils.copyFile((File) file, new File(root, fileName));   
            user.setPhotopath("photo/"+fileName);
        }
        
        //将个人信息写入数据库
        String interest = request.getParameter("interest");
        String website = request.getParameter("website");
        String signature = request.getParameter("signature");
        String strSex = request.getParameter("sex");
       
        user.setNickname(nickname);
        user.setAddress(address);
        user.setTelephone(telephone);
        user.setSex(Integer.parseInt(strSex));
        user.setSignature(signature);
        user.setWebsite(website);
        user.setInterest(interest);
        loginService.updateUser(user);
        //更新session中的nickname
        if(nickname!=null && !nickname.equals("")){
        	 httpSession.setAttribute(NICKNAME, nickname);
        }
        else{
        	httpSession.setAttribute(NICKNAME, username);
        }
        return "redirect:intoUserInfo";
    }
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getMaster() {
		return master;
	}

	public void setMaster(User master) {
		this.master = master;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
}
