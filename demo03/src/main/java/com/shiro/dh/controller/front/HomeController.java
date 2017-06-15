/**  
 * Project Name:blog  
 * File Name:indexController.java  
 * Package Name:com.blog.controller  
 * Date:2016-8-11下午3:36:42  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.front;  

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shiro.dh.constants.Constants;
import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.entity.BlogComments;
import com.shiro.dh.entity.Blogger;
import com.shiro.dh.entity.Link;
import com.shiro.dh.entity.MessageBoard;
import com.shiro.dh.lucene.Searcher;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.ErrorInfo;
import com.shiro.dh.util.IpUtil;
import com.shiro.dh.util.Page;

/**  
 * ClassName:indexController <br/>  
 * Function: 核心控制器. <br/>  
 * Date:     2016-8-11 下午3:36:42 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Controller
public class HomeController extends BaseController{
	Logger logger = Logger.getLogger(HomeController.class);
	
	/**
	 * 
	 * initBinder:(注册该类进行参数控制). <br/>   
	 * @author daihui  
	 * @param binder  
	 * @since JDK 1.7
	 */
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
	   binder.registerCustomEditor(String.class, new StringEscapeEditor(false, false, false));
	}*/

	/**
	 * 
	 * home:(进入首页). <br/>   
	 *  
	 * @author daihui  
	 * @param model
	 * @param request
	 * @return  
	 * @since JDK 1.7
	 */
    @RequestMapping("/")
    public String home(Model model,HttpServletRequest request) {
    	
    	int currPage = Convert.strToInt(request.getParameter("currPage"), 1);
    	int pageSize = Constants.TEN;
    	long typeId = Convert.strToLong(request.getParameter("typeId"), -1);
    	String searchDate = request.getParameter("searchDate");
    	
    	Page<Blog> page = blogServiceImpl.getBlogPage(currPage,pageSize,typeId,searchDate);
    	
    	model.addAttribute("page", page);
        return "home";
    }
    
    /**
     * 
     * blogInfo:(文章详情). <br/>   
     *  
     * @author daihui  
     * @param bi 博客id
     * @param model
     * @return  
     * @since JDK 1.7
     */
    @RequestMapping(value="article-{bi}.html")
    public String blogInfo(@PathVariable("bi") long bi,Model model){
    	
    	Blog blog = blogServiceImpl.getBlogById(bi);
    	List<BlogComments> comments = blogCommentsServiceImpl.queryCommentsByBid(bi);
    	/*if(0 != bi){
    		blogServiceImpl.incrReadCount(bi);
    	}*/
    	model.addAttribute("blog",blog);
    	model.addAttribute("comments", comments);
    	return "front/blogInfo";
    }
    
    //关于博主
    @RequestMapping(value="aboutMe.html",method=RequestMethod.GET)
    public String aboutMe(Model model){
    	Blogger blogger=bloggerServiceImpl.getBloggerInfoById(1);
    	model.addAttribute("blogger", blogger);
        return "front/bloggerInfo";
    }
    
    //下载页面
    @RequestMapping(value="download.html",method=RequestMethod.GET)
    public String download(Model model,HttpServletRequest request){
        return "front/download";
    }
    
    //留言板
    @RequestMapping(value="messageboard.html",method=RequestMethod.GET)
    public String board(Model model){
    	List<MessageBoard> msgs = messageBoardServiceImpl.queryAll();
    	model.addAttribute("msgs",msgs);
    	return "front/messageboard";
    }
    
    /**
     * submitComment:(提交评论). <br/>   
     * @author Administrator  
     * @param request
     * @return  
     * @since JDK 1.7
     */
    @RequestMapping(value="submit-comment",method=RequestMethod.POST)
    @ResponseBody
    public ErrorInfo submitComment(HttpServletRequest request){
    	ErrorInfo error = new ErrorInfo();
    	String captchaCode = request.getParameter("captchaCode"); 
    	String content = request.getParameter("content");
    	String rands  = session.getAttribute("captcha").toString();
    	long blogId = Convert.strToLong(request.getParameter("blogId"), -1);
    	
    	if(null == captchaCode|| "".equals(captchaCode)){
    		error.code = -1;
    		error.msg = "请输入验证码！";
    		return error;
    	}else{
    		if(captchaCode.equalsIgnoreCase(rands)){
    		    error = blogCommentsServiceImpl.saveComment(content ,blogId ,request);
    		}else{
    			error.code = -1;
    			error.msg = "验证码错误，请重新输入！";
    		}
    	}
    	
    	return error;
    }
    
    /**
     * 
     * search:(搜索页面). <br/>   
     *  
     * @author daihui  
     * @param q
     * @param model
     * @param request
     * @return  
     * @since JDK 1.7
     */
    @RequestMapping(value="search",method=RequestMethod.GET)
    public String search(String q,Model model,HttpServletRequest request){
    	int currPage = Convert.strToInt(request.getParameter("currPage"), 1);
    	int pageSize = Constants.TEN;
    	
    	Page<Blog> page = new Page<Blog>();
	    try {
	    	page = Searcher.search(Constants.INDEXDIR, q);
	    	page.currPage=currPage;
	    	page.pageSize=pageSize;
	    	page.totalCount=page.page.size();
	    	page.page.subList(currPage-1,(currPage-1)*pageSize);
	    	page.setPageNumber(page.totalCount);
		} catch (Exception e) {
			e.printStackTrace();  
		}
	    
	    model.addAttribute("q", q);
	    model.addAttribute("page", page);
	    
	    return "front/search";
    }
    
    @RequestMapping(value="/saveMsg",method=RequestMethod.POST)
    @ResponseBody
    public String saveMsg(HttpServletRequest request){
    	String userName=request.getParameter("userName");
    	String email=request.getParameter("email");
    	String msgContent=request.getParameter("msgContent");
    	
    	MessageBoard mb = new MessageBoard();
    	mb.setMsgContent(msgContent);
    	mb.setEmail(email);
    	mb.setUserName(userName);
    	mb.setPublishTime(new Date());
    	mb.setStatus(false);
    	mb.setUse(false);
    	mb.setIp(IpUtil.getIRealIPAddr(request));
    	messageBoardServiceImpl.save(mb);
    	return "success";
    	
    }
    
    /**
	 * 
	 * common:(公共页面数据). <br/>   
	 *  
	 * @author Administrator  
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping(value= "/common",  method = RequestMethod.GET)
	@ResponseBody
	public String common(HttpServletRequest request){
		
		int currPage = Convert.strToInt(request.getParameter("currPage"), 1);
    	long typeId = Convert.strToLong(request.getParameter("typeId"), -1);
    	String searchDate = request.getParameter("searchDate");
		try{
			List<Link> links = linkServiceImpl.getLinkList();
			List<Blog> blogDate = blogServiceImpl.getBlogDate();
			List<Blog> typeBlogs = blogServiceImpl.getBlogType();
			Blogger blogger=bloggerServiceImpl.getBloggerInfoById(1);
			List<MessageBoard> msgs = messageBoardServiceImpl.queryAll();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("blogger", blogger);
			map.put("links", links);
			map.put("blogDate", blogDate);
			map.put("typeBlogs", typeBlogs);
			map.put("currPage", currPage);
			map.put("typeId", typeId);
			map.put("searchDate", searchDate);
			map.put("msgs",msgs.size()>5?msgs.subList(0, 5):msgs);
			String json = JSON.toJSONString(map); 
			return json;
			}catch(Exception e){
				e.printStackTrace();
			}
		return "";
		}
    
}
  