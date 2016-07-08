package com.zhihao.platform.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhihao.platform.data.entity.Blog;
import com.zhihao.platform.data.entity.Comment;
import com.zhihao.platform.data.entity.User;
import com.zhihao.platform.service.BlogService;
import com.zhihao.platform.service.CommentService;
import com.zhihao.platform.service.LoginService;
import com.zhihao.platform.util.PageManager;



@Controller
@RequestMapping("blog")
public class blogController extends MyController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String blogTitle ;
	private String blogContent;
	private int blogType = 0;
	private int isOriginal;
	//是否是刚注册成功
	private int isFromRegist = 0; 
	//用于分页
	private PageManager pm;
	private int pageNo =1;
	
	/**
	 * 记录当前用户所浏览的博客主
	 * 默认就是我，new User 默认生成我的数据
	 */
	private User master = new User(1);
	
	private List<Blog> blogs = new ArrayList<Blog>();
	
	private Blog blog = new Blog();
	
	private List<Comment> comments;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CommentService commentService;
	
	//! ------read blog actions----------

	@RequestMapping("/listBlog")
	public ModelAndView listBlog(
			String userId,
			String isFromRegist,
			HttpSession httpSession
		) throws Exception {
		//logger.debug("listBlog action called.");
		//默认 userId： 我的id
		int userId_num = 1;
		ModelAndView mav = new ModelAndView();
		//判断 是否传入userId 
		if(userId!=null){
			userId_num = Integer.parseInt(userId);
		}
		//没有的话判断是否自己登录 ：session中的username 获取userId
		else if(httpSession.getAttribute(USERNAME)!=null){
			userId_num = loginService.getUserByName(httpSession.getAttribute(USERNAME).toString()).getId();
			if(isFromRegist!=null){
				mav.addObject("isFromRegist", isFromRegist);
			}
		}
		//分页逻辑
		pm = new PageManager();
		pm.setPageNo(pageNo);
		
		//获取blog
		blogs = blogService.listBlog(userId_num,blogType,pm);
		master = loginService.getUserById(userId_num);
		//有些用户可能并未设置nickname
		master.checkNickName();
		//分页逻辑
		
		
	    mav.addObject("account", "account -1");
	    mav.addObject("master",master);
	    mav.addObject("blogs", blogs);
	    
		if(blogs!=null)
			mav.setViewName("blog");
		else{
			//异常
		}
			
		return mav;
	}
	
	/**
	 * 点进进入博客文章页面
	 * param : id（博客id）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/blogDetail/{blogId}", method = {RequestMethod.GET})
	public ModelAndView showBlogDetail(
			@PathVariable String blogId,
			HttpSession httpSession) throws Exception {
		logger.debug("----------------------showBlogDetail Called-----------------");
		ModelAndView mav = new ModelAndView();
		int showBlogId = 0;
		showBlogId = Integer.parseInt(blogId);
		blogService.viewBlog(showBlogId);
		blog = blogService.getBlog(showBlogId);
		master = loginService.getUserById(blog.getUserId());
		
		//获取 comments
		comments = commentService.getCommentsByBlogId(blog.getId());
		commentService.processPhotoPath(comments);
		mav.addObject("blog", blog);
		mav.addObject("master", master);
		mav.addObject("comments", comments);
		mav.setViewName("blogDetail");
		return mav;
	}
	
	//!--------write blog actions -------------------
	/**
	 * 进入写博客页面 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/intoWriteBlog")
	public ModelAndView intoWriteBlog(HttpSession session) throws Exception {
		logger.debug("intoWriteBlog called" );
		ModelAndView mac = new ModelAndView();
		if(session.getAttribute(USERNAME)==null){
			mac.setViewName("redirect:../user/letslogin");
			return mac;
		}
		master = loginService.getUserByName(session.getAttribute(USERNAME).toString());
		mac.setViewName("writeblog");
		mac.addObject("master", master);
		return mac;
	}
	
	/**
	 * 提交新博客/提交修改博客
	 * request param:	blogTitle ,blotContent, isOriginal ,blogType
	 * @return 新博客 返回 listBlog.action  修改博客返回 intoBlogManage.action
	 * @throws Exception
	 */
	@RequestMapping(value = "/writeBlog" ,method = {RequestMethod.POST})
	public ModelAndView writeBlog(
			String blogTitle,
			String blogContent,
			String isOriginal,
			String blogType,
			String blogId,
			HttpSession httpSession ) throws Exception {
		logger.debug("writeBlog called. username:" );
		boolean isModifyBlog = false;
		ModelAndView mav  = new ModelAndView();
		
		//!通过 blodId参数来判断 用户调用的是 新增博客 or 修改博客 		
		if(blogId!=null && blogId!=""){
			isModifyBlog = true;	//是修改博客
		}
	
		if(blogTitle!=null && !blogTitle.equalsIgnoreCase("") && blogContent!=null ){ 
			if(httpSession.getAttribute(USERNAME)!=null&&
					httpSession.getAttribute(STATUS)!=null&&
							httpSession.getAttribute(STATUS).toString().equals(STATUS_LOGIN)){
				Blog blog = null;
				if(isModifyBlog){
					blog = blogService.getBlog(Integer.parseInt(blogId));
					blog.setTitle(blogTitle);
					blog.setContent(blogContent);
				}
				else{
					blog = new Blog(blogTitle,blogContent);
				}
				User user = loginService.getUserByName(httpSession.getAttribute(USERNAME).toString());
				blog.setContentShort(shortenBlogContent(blogContent));
				blog.setIsOriginal(Integer.parseInt(isOriginal));
				blog.setBlogType(Integer.parseInt(blogType));
				if(isModifyBlog){
					//update blog to db
					blogService.modifyBlog(blog);
					mav.setViewName("redirect:intoBlogManage");
				}
				else{
					//Write blog to db
					if(blogService.writeBlog(blog,user))
						mav.setViewName("redirect:listBlog");
				}
			}
		}
		return mav;
	}
	
	
	//!----------    博客管理    ----------- 
	
	/**
	 * 进入博客管理 
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/intoBlogManage" ,method = {RequestMethod.GET})
	public ModelAndView intoBlogManage(HttpSession httpSession) throws Exception {
		logger.debug("intoBlogManage action called.");
		ModelAndView mav = new ModelAndView();
		int userId=0;
		if(httpSession.getAttribute(USERNAME)!=null){
			userId = loginService.getUserByName(httpSession.getAttribute(USERNAME).toString()).getId();
		}
		
		//分页逻辑
		pm = new PageManager();
		pm.setPageNo(pageNo);
		//获取blog
		blogs = blogService.listBlog(userId,blogType,pm);
		master = loginService.getUserById(userId);
		
		mav.addObject("blogs", blogs);
		mav.addObject("master", master);
		mav.setViewName("blogManage");
		return mav;
		/*if(blogs!=null)
			return SUCCESS;
		else
			return INPUT;*/
	}
	
	/**
	 * 删除博客
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBlog" ,method = {RequestMethod.GET,RequestMethod.DELETE})
	public ModelAndView deleteBlog(
			String blogId,
			HttpSession httpSession) throws Exception {
		logger.debug("deleteBlog action called.");
		ModelAndView mav = new ModelAndView();
		if(httpSession.getAttribute(USERNAME)==null){
			return mav;
		}
		blogService.deleteBlog(Integer.parseInt(blogId));
		mav.setViewName("redirect:intoBlogManage");
		return mav;
	}
	
	/**
	 * 进入编辑博客
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/intoModifyBlog" ,method = {RequestMethod.GET})
	public ModelAndView intoModifyBlog(
			String blogId,
			HttpSession httpSession) throws Exception {
		logger.debug("--------intoModifyBlog called.------");
		ModelAndView mav = new ModelAndView();
		if(httpSession.getAttribute(USERNAME)!=null){
			master = loginService.getUserByName(httpSession.getAttribute(USERNAME).toString());
		}
		
		blog = blogService.getBlog(Integer.parseInt(blogId));
		mav.addObject("blog", blog);
		mav.addObject("master", master);
		mav.setViewName("writeblog");
		return mav;
	}
	
	
	//!------------util functions-------------
	
	//获取博客内容的前100个字符= =，作为博客简介内容
	public String shortenBlogContent(String str){
		//这代码简直醉了。。。 效率他么的就不管了0.0
		str=str.replaceAll("<a href[^>]*>", "");  
		str=str.replaceAll("</a>", "");  
		str=str.replaceAll("<img[^>]*/>", " ");  
		str = str.replaceAll("<p .*?>", "\r\n"); 
		// <br><br/>替换为换行 
		str = str.replaceAll("<br\\s*/?>", "\r\n"); 
		// 去掉其它的<>之间的东西 
		str = str.replaceAll("\\<.*?>", "");
		String result = new String(str);
		int stopIndex = result.indexOf("<img");
		if(stopIndex>-1){
			stopIndex = stopIndex>100? 100: stopIndex; 
		}else{
			stopIndex = 100;
		}
		result = result.substring(0, str.length()>stopIndex?stopIndex:str.length());
		result = result.replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<pre>", "").replaceAll("</pre", "");
		return result;
	}
	
	
	public int getIsFromRegist() {
		return isFromRegist;
	}

	public void setIsFromRegist(int isFromRegist) {
		this.isFromRegist = isFromRegist;
	}
}
