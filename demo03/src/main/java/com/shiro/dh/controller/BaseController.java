/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:BaseController.java  
 * Package Name:com.shiro.dh.controller  
 * Date:2017年3月20日下午5:42:52  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.service.FoodService;

/**
 * @author Administrator
 *
 */
public class BaseController {
	
	@Autowired
	protected FoodService foodServiceImpl;

}
