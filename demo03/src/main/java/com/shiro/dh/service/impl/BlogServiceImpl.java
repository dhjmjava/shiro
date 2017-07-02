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
import java.util.Map;

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
		  
		return null;
		
	}

	@Override
	public Page<Blog> getBlogPage(int currPage, int pageSize, long typeId, String searchDate) {
		  
		return null;
		
	}

	@Override
	public List<Map<String,Integer>> getBlogDate() {
		
		return blogDao.queryByDate();
		
	}

	@Override
	public Map<String,Integer> getBlogType() {
		 
		return blogDao.queryByType();
		
	}

	@Override
	public void deleteBlog(long id) {
		
		blogDao.delete(id);
		
	}

	@Override
	public Blog saveOrUpdateBlog(Blog blog) {
		  
		return blogDao.save(blog);
		
	}

	@Override
	public Blog getBlogById(long id) {
		  
		return blogDao.findOne(id);
		
	}

	@Override
	public List<Blog> findAll() {
		  
		return blogDao.findAll();
		
	}



}
  
