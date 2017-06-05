/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:UserInfoService.java  
 * Package Name:com.shiro.dh.service  
 * Date:2017年3月20日下午2:41:58  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.service;

import com.shiro.dh.entity.UserInfo;

/**
 * @author Administrator
 *
 */
public interface UserInfoService {
	public UserInfo findByUsername(String username); 

}
