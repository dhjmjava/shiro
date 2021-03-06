/**  
 * Project Name:blog  
 * File Name:BaseService.java  
 * Package Name:com.blog.service  
 * Date:2016-8-11下午3:58:11  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.dao.BlogCommentsDao;
import com.shiro.dh.dao.BlogDao;
import com.shiro.dh.dao.BlogTypesDao;
import com.shiro.dh.dao.BloggerDao;
import com.shiro.dh.dao.LinkDao;
import com.shiro.dh.dao.MessageBoardDao;

/**  
 * ClassName:BaseService <br/>  
 * Function: 基础业务接口. <br/>  
 * Date:     2016-8-11 下午3:58:11 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public abstract class BaseService{
	
	@Autowired
	protected BloggerDao bloggerDao;
	
	@Autowired
	protected BlogDao blogDao;
	
	@Autowired
	protected  BlogTypesDao blogTypesDao;
	
	@Autowired
	protected LinkDao linkDao;
	
	@Autowired
	protected BlogCommentsDao blogCommentsDao;
	
	@Autowired
	protected MessageBoardDao messageBoardDao;
	
}
  
