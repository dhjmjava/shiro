/**  
 * Project Name:blog  
 * File Name:BlogServiceImpl.java  
 * Package Name:com.blog.service.serviceImpl  
 * Date:2016-8-11下午3:33:07  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.List;

import org.springframework.stereotype.Service;

import com.shiro.dh.entity.Blog;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogService;
import com.shiro.dh.util.Page;

/**  
 * ClassName:BlogServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2016-8-11 下午3:33:07 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class BlogServiceImpl extends BaseService implements BlogService{

	@Override
	public Page<Blog> getBlogList(int offset, int limit) {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

	@Override
	public Page<Blog> getBlogPage(int currPage, int pageSize, long typeId, String searchDate) {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

	@Override
	public List<Blog> getBlogDate() {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

	@Override
	public List<Blog> getBlogType() {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

	@Override
	public void deleteBlog(long id) {
		  
		// TODO Auto-generated method stub  
		
	}

	@Override
	public Blog saveOrUpdateBlog(Blog blog) {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

	@Override
	public Blog getBlogById(long id) {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}



}
  
