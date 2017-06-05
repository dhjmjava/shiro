/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:UserInfoController.java  
 * Package Name:com.shiro.dh.controller  
 * Date:2017年3月20日下午2:55:31  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller  
@RequestMapping("userInfo")
public class UserInfoController {
	
	/**  
     * 用户查询.  
     * @return  
     */  
    @RequestMapping("/userList")  
    @RequiresPermissions("userInfo:view")//权限管理;  
    public String userInfo(){  
       return "userInfo";  
    }  
     
    /**  
     * 用户添加;  
     * @return  
     */  
    @RequestMapping("/userAdd")  
    @RequiresPermissions("userInfo:add")//权限管理;  
    public String userInfoAdd(){  
       return "userInfoAdd";  
    }  
    /**  
     * 用户删除;  
     * @return  
     */  
    @RequestMapping("/userDel")  
    @RequiresPermissions("userInfo:del")//权限管理;  
    public String userDel(){  
       return "userInfoDel";  
    }  

}
