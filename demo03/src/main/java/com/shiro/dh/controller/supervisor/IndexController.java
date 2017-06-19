/**  
 * Project Name:blog  
 * File Name:AdminController.java  
 * Package Name:com.blog.controller  
 * Date:2016年9月14日下午1:15:17  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.dh.controller.BaseController;

/**
 * * ClassName: IndexController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * date: 2017年6月16日 上午8:54:45 <br/>  
 *  
 * @author daihui 
 * @version   
 * @since JDK 1.7
 */
@Controller
@RequestMapping("supervisor")
public class IndexController extends BaseController{
	Logger logger = Logger.getLogger(IndexController.class);
	
	/**
	 * 
	 * login:登录页面. <br/>   
	 *  
	 * @author daihui  
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping("/login.html")
	public String login(){
    	return "html/login";
    }
	
	/**
	 * 
	 * login:进行登录验证. <br/>   
	 *   
	 * @param username
	 * @param password
	 * @return  
	 * @author daihui
	 * Date:2017年6月15日下午8:17:01
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	@ResponseBody
    public String login(@RequestParam("username") String username,@RequestParam("password") String password) {  
        
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(true);
        String msg = "";
        //未验证则进行登录
        if(!subject.isAuthenticated()){
        	try {
        		
        		subject.login(token);
        		
			} catch (UnknownAccountException e) {
				System.out.println("UnknownAccountException -->帐号不存在：");  
                msg = "帐号不存在！";  
			} catch (IncorrectCredentialsException e){
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");  
                msg = "用户名或密码不正确！";
			} catch (Exception e){
				e.printStackTrace();
				msg = "登录失败！！";
			}
        	
        }
        System.out.println(subject.getSession().getId());
        // 此方法不处理登录成功,由shiro进行处理.  
        return msg;  
    }  
	
	/**
	 * 
	 * loginOut:退出系统，交给shiro管理. <br/>   
	 *   
	 * @param request
	 * @return  
	 * @author daihui
	 * Date:2017年6月15日下午8:15:51
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "html/login";
	}
	
}
  
