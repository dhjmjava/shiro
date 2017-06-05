package com.shiro.dh.controller;
/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:homeController.java  
 * Package Name:  
 * Date:2017年3月20日下午12:53:43  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
	  
@Controller  
public class HomeController extends BaseController{  
	
	@RequestMapping("/index")  
    public String index() { 
        return "index";  
    } 
	
	@RequestMapping("/userInfo")  
	@RequiresPermissions("userInfo:view")
	public String userInfo() { 
		return "userInfo";  
	}  
	
	@RequestMapping("/userAdd")  
	@RequiresPermissions("userInfo:add")
	public String userAdd(Model model) { 
		return "userInfoAdd";  
	}
	
	@RequestMapping("/userDel")
	@RequiresPermissions("userInfo:del")
	public String userDel(Model model) { 
		return "userDel";  
	}  
  
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
    public String login(Model model) { 
        return "/login";  
    }  
  
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)  
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,Model model) {  
        
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(true);
        //未验证则进行登录
        String msg = "";
        if(!subject.isAuthenticated()){
        	try {
        		
        		subject.login(token);
				
			} catch (UnknownAccountException e) {
				System.out.println("UnknownAccountException -->帐号不存在：");  
                msg = "UnknownAccountException -->帐号不存在：";  
			} catch (IncorrectCredentialsException e){
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");  
                msg = "IncorrectCredentialsException -- > 密码不正确：";
			} catch (Exception e){
				e.printStackTrace();
				msg = "登录失败！！";
			}
        	
        }
        
        model.addAttribute("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.  
        return "redirect:/index";  
    }  
}  



