/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:BaseController.java  
 * Package Name:com.shiro.dh.controller  
 * Date:2017年3月20日下午5:42:52  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.service.BlogCommentsService;
import com.shiro.dh.service.BlogService;
import com.shiro.dh.service.BlogTypesService;
import com.shiro.dh.service.BloggerService;
import com.shiro.dh.service.LinkService;
import com.shiro.dh.service.MessageBoardService;

/**
 * @author Administrator
 *
 */
public class BaseController {
	
	@Autowired
	protected HttpSession session;
	
	@Autowired
	protected BloggerService bloggerServiceImpl;
	
	@Autowired
	protected BlogTypesService blogTypesServiceImpl;
	
	@Autowired
	protected BlogService blogServiceImpl;
	
	@Autowired
	protected LinkService linkServiceImpl;
	
	@Autowired
	protected BlogCommentsService blogCommentsServiceImpl;
	
	@Autowired
	protected MessageBoardService messageBoardServiceImpl;

}
