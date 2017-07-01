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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

	/**
	 * 
	 * getBlogPage:前台分页查询 <br/>   
	 *   
	 * @param offset
	 * @param limit
	 * @param blogType
	 * @param time
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午11:37:14
	 */
	@Override
	public Page<Blog> getBlogPage(int offset, int limit, long blogType, String time) {
		List<Blog> list = blogDao.queryPagination(offset,limit,blogType,time);
		Page<Blog> page = new Page<Blog>();
		page.page = list;
		page.currPage = offset;
		page.pageSize = limit;
		page.totalCount = list.size();
		page.setPageNumber(page.totalCount);
		
		return page;
		
	}

	@Override
	public Map<String, String> getBlogDate() {
		  
		return blogDao.queryByDate();
		
	}

	@Override
	public Map<String,String> getBlogType() {
		  
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
  
