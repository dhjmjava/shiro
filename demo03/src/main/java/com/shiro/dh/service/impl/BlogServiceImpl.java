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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dh.constants.Constants;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogService;
import com.shiro.dh.service.RedisService;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.DateUtil;
import com.shiro.dh.util.Page;
import com.shiro.dh.util.StringUtil;

import redis.clients.jedis.Jedis;

/**  
 * ClassName:BlogServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2016-8-11 下午3:33:07 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Service
public class BlogServiceImpl extends BaseService implements BlogService{
	@Autowired
	private RedisService redisServiceImpl;

	/**
	 * 
	 * getBlogList:搜索缓存所需数据. <br/>   
	 *  
	 * @author daihui  
	 * @return  
	 * @since JDK 1.7 
	 * Date: 2017年7月7日 下午11:28:03 <br/>
	 */
	@Override
	public List<Blog> getBlogList() {
		return blogDao.findBlogByIsUse(true);
	}

	/**
	 * 
	 * getBlogPage:前台分页查询 <br/>   
	 *   
	 * @param offset 当前页
	 * @param limit 每页条数
	 * @param blogType  类型
	 * @param time  时间
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午11:37:14
	 */
	@Override
	public Page<Blog> getBlogPage(int offset, int limit, int blogType, String time) {
		List<Blog> list = blogDao.queryPagination(offset,limit,blogType,time);
		Page<Blog> page = new Page<Blog>();
		page.page = list;
		page.currPage = offset;
		page.pageSize = limit;
		if(!StringUtil.isBlank(time)){
			String[] dates = DateUtil.getDate(time);
			page.totalCount=blogDao.queryBlogByPublishTime(String.valueOf(dates[0]), String.valueOf(dates[1]));
		}else if(blogType>0){
			page.totalCount=blogDao.queryBlogByBlogType(blogType);
		}else{
			page.totalCount=(int) blogDao.count();
		}
		page.setPageNumber(page.totalCount);
		
		return page;
		
	}

	@Override
	public List<Object[]> getBlogDate() {
		return blogDao.queryByDate();
	}

	@Override
	public List<Object[]> getBlogType() {

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

	/**
	 * 
	 * incrReadCount:缓存浏览量+1. <br/>   
	 *  
	 * @author daihui  
	 * @param bi  
	 * @since JDK 1.7 
	 * Date: 2017年7月7日 下午11:29:39 <br/>
	 */
	@Override
	public void incrReadCount(long bi) {
		 Jedis jedis = redisServiceImpl.getResource();
		  String value = jedis.get(Constants.BLOG+bi);
		  if(null != value){
			  jedis.set(Constants.BLOG+bi, String.valueOf(Convert.strToLong(value, 0)+1));
		  }else{
			  redisServiceImpl.returnResource(jedis);
		  }
	}



}
  
