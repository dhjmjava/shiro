/**  
 * Project Name:blog  
 * File Name:MyStartupRunner.java  
 * Package Name:com.blog.config  
 * Date:2016年12月27日下午11:41:37  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.config;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shiro.dh.constants.Constants;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogService;
import com.shiro.dh.service.RedisService;

/**  
 * ClassName:MyStartupRunner <br/>  
 * Function: 系統啟動初始化索引. <br/>  
 * Date:     2016年12月27日 下午11:41:37 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Component
@Order(value=2)
public class MyStartupRunner2 extends BaseService implements CommandLineRunner{

	@Autowired
	private BlogService blogServiceImpl;
	
	@Autowired 
	protected RedisService redisServiceImpl;
	/*
	 * 系统启动将博客加入缓存（可选）.  
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
			List<Blog> blogList =  blogServiceImpl.getBlogList();
			if(null != blogList && blogList.size()>0){
				for(Blog blog:blogList){
					redisServiceImpl.set(Constants.BLOG+blog.getId(),String.valueOf(blog.getReadCount()));
				}
			}
	   }
	}
