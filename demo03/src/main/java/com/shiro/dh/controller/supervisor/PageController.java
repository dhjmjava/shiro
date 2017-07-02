/**  
 * Project Name:blog  
 * File Name:PageController.java  
 * Package Name:com.blog.controller.supervisor  
 * Date:2017年5月25日下午11:38:43  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.dh.controller.BaseController;

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
	@RequiresPermissions("supervisor:admin")//shiro权限注解;
	public String admin(){
		return "html/admin";
	}
	
	@RequestMapping("blogMgt.html")
	@RequiresPermissions("blog:mgt")
	public String blogMgt(){
		return "html/supervisor/blogMgt";
	}
	
	@RequestMapping("add_blog.html")
	@RequiresPermissions("blog:page")
	public String addBlog(){
		return "html/supervisor/add_blog";
	}
	
	@RequestMapping("update_blog.html")
	@RequiresPermissions("blog:updatePage")//shiro权限注解;
	public String updateBlog(String id,Model model){
		model.addAttribute("id",id);
		return "html/supervisor/update_blog";
	}
	
	@RequestMapping("typeMgt.html")
	@RequiresPermissions("blogType:mgt")//shiro权限注解;
	public String typeMgt(Model model){
		model.addAttribute("types",blogTypesServiceImpl.getAllBlogTypes());
		return "html/supervisor/typeMgt";
	}
	
	@RequestMapping("link.html")
	@RequiresPermissions("link:mgt")//shiro权限注解;
	public String linkMgt(Model model){
		return "html/supervisor/link";
	}
	
	@RequestMapping("updateInfo.html")
	@RequiresPermissions("system:mgt")//shiro权限注解;
	public String updateInfo(Model model){
		model.addAttribute("blogger", bloggerServiceImpl.getBloggerInfoById(1));
		return "html/supervisor/updateInfo";
	}
	
	//评论管理
	@RequestMapping("commentMgt.html")
	@RequiresPermissions("comment:mgt")//shiro权限注解;
	public String commentMgt(Model model){
		return "supervisor/commentMgt/commentMgt";
	}
	
	@RequestMapping("messageMgt.html")
	@RequiresPermissions("message:mgt")//shiro权限注解;
	public String messageMgt(){
		return "html/supervisor/msgMgt";
	}
	

}
  
