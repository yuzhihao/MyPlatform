package com.zhihao.platform.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhihao.platform.data.entity.Comment;
import com.zhihao.platform.data.entity.CommentInfo;
import com.zhihao.platform.service.CommentService;
import com.zhihao.platform.service.LoginService;


@Controller
@RequestMapping("blog")
public class commentController extends MyController{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired  
	private  HttpServletRequest request; 
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/addComment" ,method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody CommentInfo addComment(
			int blogId,
			String commentContent,
			HttpSession httpSession) throws Exception {
		logger.debug("-----------------addComment action called.-----------------");
		Comment comment = new Comment();
		CommentInfo commentInfo = new CommentInfo();
		//用户登录 则记录用户id和用户名 ，否则，记录用户的ip.
		if(httpSession.getAttribute(USERNAME)!=null){
			int userId; userId = loginService.getUserByName(httpSession.getAttribute(USERNAME).toString()).getId();
			comment.setUserId(userId);
			comment.setUsername(loginService.getUserById(userId).getUsername());
		}else{
			String ip = request.getRemoteAddr();
			comment.setUsername(ip);
		}
		comment.setContent(commentContent);
		comment.setBlogId(blogId);
		commentService.addComment(comment);
		//设置返回json值
		commentInfo.setCommentContent(comment.getContent());
		commentInfo.setUsername(comment.getUsername()+"(您)");
		commentInfo.setDate("刚刚");
		commentInfo.setPhotoPath(comment.getPhotopath());
		return commentInfo;
		
	}
}
