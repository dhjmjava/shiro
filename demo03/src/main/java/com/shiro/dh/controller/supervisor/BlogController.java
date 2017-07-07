/**  
   * Project Name:blog  
 * File Name:BlogController.java  
 * Package Name:com.blog.controller  
 * Date:2016-8-12下午2:39:48  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.entity.BlogTypes;
import com.shiro.dh.lucene.Index;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.ErrorInfo;

/**  
 * ClassName:BlogController <br/>  
 * Function: 博客控制器. <br/>  
 * Date:     2016-8-12 下午2:39:48 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@RestController
@RequestMapping("supervisor")
public class BlogController extends BaseController{
	Logger logger = Logger.getLogger(BlogController.class);
	private Index index = new Index();
	

	@RequestMapping("findAllBlogs")
	@RequiresPermissions("blog:query")
	public List<Blog> findAllBlogs(){
       return 	blogServiceImpl.findAll();	
	}
	
	@RequestMapping("findAllTypes")
	@RequiresPermissions("blogType:query")
	public List<BlogTypes> findAllTypes(){
       return 	blogTypesServiceImpl.getAllBlogTypes();	
	}
	
	@RequestMapping("findById")
	@RequiresPermissions("blog:findById")
	public Blog queryById(long id){
		return blogServiceImpl.getBlogById(id);
	}
	
	
	/**
	 * submitBlog:(新增博客). <br/>   
	 * @author Administrator  
	 * @param request
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping("submit_blog")
	@RequiresPermissions("blog:add")
	public ErrorInfo submitBlog(Blog blog) throws Exception{
		ErrorInfo error  = new ErrorInfo();
		blog.setPublishTime(new Date());
		Blog result = blogServiceImpl.saveOrUpdateBlog(blog);
		index.index(result);
		return error;
	}
	
	//修改blog
	@RequestMapping("update_blog")
	@RequiresPermissions("blog:update")
	public ErrorInfo submitUpdate(Blog blog){
		ErrorInfo error  = new ErrorInfo();
		Blog saveOrUpdateBlog = blogServiceImpl.saveOrUpdateBlog(blog);
		try {
			index.updateIndex(saveOrUpdateBlog);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return error;
	}
	
	/**
	 * deleteBlog:(删除博客). <br/>   
	 * @author Administrator  
	 * @param request
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping("deleteBlog")
	@RequiresPermissions("blog:del")
	public ErrorInfo deleteBlog(HttpServletRequest request,String id){
		ErrorInfo error = new ErrorInfo();
		long bid = Convert.strToLong(id, -1);
		blogServiceImpl.deleteBlog(bid);
		try {
			index.deleteIndex(bid);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return error;
	}
	
	//修改类型信息
	@RequestMapping("updateType")
	@RequiresPermissions("blogType:update")
	public ErrorInfo updateType(HttpServletRequest request,String id,String newBlogType){
		ErrorInfo error = new ErrorInfo();
		BlogTypes type = new BlogTypes();
		type.setId(Convert.strToLong(id, -1));
		type.setName(newBlogType);
		type.setIsUse(true);
		
		blogTypesServiceImpl.saveOrUpdateType(type);
		return error;
	}
	
	//增加blog类型
	@RequestMapping("addType")
	@RequiresPermissions("blogType:add")
	public ErrorInfo addType(HttpServletRequest request,String newBlogType){
		ErrorInfo error = new ErrorInfo();
		BlogTypes type = new BlogTypes();
		type.setName(newBlogType);
		type.setIsUse(true);
		
		blogTypesServiceImpl.saveOrUpdateType(type);
		return error;
	}
	
	
}
  
