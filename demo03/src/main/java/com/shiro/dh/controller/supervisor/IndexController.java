/**  
 * Project Name:blog  
 * File Name:AdminController.java  
 * Package Name:com.blog.controller  
 * Date:2016年9月14日下午1:15:17  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.entity.Blogger;
import com.shiro.dh.util.ErrorInfo;

/**  
 * ClassName:AdminController <br/>  
 * Function: 后台管理控制器. <br/>  
 * Date:     2016年9月14日 下午1:15:17 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Controller
@RequestMapping("/supervisor")
public class IndexController extends BaseController{
	Logger logger = Logger.getLogger(IndexController.class);
	
	//评论管理
	@RequestMapping("/commentMgt.html")
	public String commentMgt(Model model){
		return "supervisor/commentMgt/commentMgt";
	}
	

	@RequestMapping("/login.html")
	public String login(){
    	return "supervisor/login";
    }
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		session.removeAttribute("userName");
		return "supervisor/login";
	}
	
}
  
