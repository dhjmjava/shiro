/**  
 * Project Name:blog  
 * File Name:BlogService.java  
 * Package Name:com.blog.service.serviceImpl  
 * Date:2016-8-11下午3:31:57  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  


import java.util.List;

import com.shiro.dh.entity.Blog;
import com.shiro.dh.util.Page;

/**  
 * ClassName:BlogService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016-8-11 下午3:31:57 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface BlogService {
  
	/**
	 * 获取博客数据
	 * @param offset
	 * @param limit
	 * @return
	 */
	 List<Blog> getBlogList();
	
	/**
	 * 根据条件查询博客数据
	 * @param currPage
	 * @param pageSize
	 * @param typeId
	 * @param searchDate
	 * @return
	 */
	 Page<Blog> getBlogPage(int currPage,int pageSize,int typeId,String searchDate);
	 
	 List<Blog> findAll();
	
	/**
	 * 根据发布日期分类
	 * @return
	 */

	 List<Object[]> getBlogDate();

	
	/**
	 * 根据博客类型分类
	 * @return
	 */

	 List<Object[]> getBlogType();
	
	/**
	 * 根据id删除博客
	 * @param id
	 * @return
	 */
	 void deleteBlog(long id);
	
	/**
	 * 保存博客
	 * @param blog
	 * @return
	 */
	 Blog saveOrUpdateBlog(Blog blog);
	
	/**
	 * 根据id获取博客类容
	 * @param id
	 * @return
	 */
	 Blog getBlogById(long id);

	/**
	 * 
	 * incrReadCount:浏览量+1. <br/>   
	 *  
	 * @author daihui  
	 * @param bi  
	 * @since JDK 1.7 
	 * Date: 2017年7月6日 下午11:53:30 <br/>
	 */
	void incrReadCount(long bi);
	
}
  
