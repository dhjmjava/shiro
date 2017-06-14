/**  
 * Project Name:blog  
 * File Name:BloggerServiceImpl.java  
 * Package Name:com.blog.service.serviceImpl  
 * Date:2016-8-12上午9:42:50  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.shiro.dh.entity.Blogger;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BloggerService;

/**
 * * ClassName: BloggerServiceImpl <br/>  
 * Function: 博主信息service. <br/>  
 * date: 2017年5月19日 下午8:53:01 <br/>  
 *  
 * @author daihui  
 * @version   
 * @since JDK 1.7
 */
@Service
public class BloggerServiceImpl extends BaseService<Blogger> implements BloggerService {

	@Override
	public Blogger getBloggerInfoById(long id) {
		return bloggerDao.findOne(id);
	}

	@Override
	public Blogger updateBloggerInfo(Blogger blogger) {
		blogger.setLastLoginTime(Calendar.getInstance().getTime());
		return bloggerDao.save(blogger);
	}

}
  
