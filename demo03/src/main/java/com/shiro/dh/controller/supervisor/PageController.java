/**  
 * Project Name:blog  
 * File Name:PageController.java  
 * Package Name:com.blog.controller.supervisor  
 * Date:2017年5月25日下午11:38:43  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.util.Convert;

/**  
 * ClassName:PageController <br/>  
 * Function: 统一处理跳转页面. <br/>  
 * Date:     2017年5月25日 下午11:38:43 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Controller
@RequestMapping("supervisor")
public class PageController extends BaseController{
	
	@RequestMapping("admin.html")
	public String admin(){
		return "html/admin";
	}
	
	@RequestMapping("blogMgt.html")
	public String blogMgt(){
		return "html/supervisor/blogMgt";
	}
	
	@RequestMapping("add_blog.html")
	public String addBlog(){
		return "html/supervisor/add_blog";
	}
	
	@RequestMapping("update_blog.html")
	public String updateBlog(String id,Model model){
		model.addAttribute("types", blogTypesServiceImpl.getAllBlogTypes());
		model.addAttribute("blog",blogServiceImpl.getBlogById(Convert.strToLong(id, -1)));
		return "html/supervisor/update_blog";
	}
	
	@RequestMapping("typeMgt.html")
	public String typeMgt(Model model){
		model.addAttribute("types",blogTypesServiceImpl.getAllBlogTypes());
		return "html/supervisor/typeMgt";
	}
	
	@RequestMapping("link.html")
	public String linkMgt(Model model){
		return "html/supervisor/link";
	}
	
	@RequestMapping("updateInfo.html")
	public String updateInfo(Model model){
		model.addAttribute("blogger", bloggerServiceImpl.getBloggerInfoById(1));
		return "html/supervisor/updateInfo";
	}
	
	@RequestMapping("messageMgt.html")
	public String messageMgt(){
		return "html/supervisor/msgMgt";
	}
	

}
  
