/**  
 * Project Name:blog  
 * File Name:BlogTypesService.java  
 * Package Name:com.blog.service  
 * Date:2016年9月25日下午6:06:42  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import java.util.List;

import com.shiro.dh.entity.BlogTypes;

/**  
 * ClassName:BlogTypesService <br/>  
 * Function: 博客类别接口. <br/>  
 * Date:     2016年9月25日 下午6:06:42 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface BlogTypesService {

	/**
	 * 查询所有博客类别
	 * @return
	 */
	 List<BlogTypes> getAllBlogTypes();
	
	/**
	 * 根据id获取类别信息
	 * @param id
	 * @return
	 */
	 BlogTypes getBlogTypesById(long id);
	
	/**
	 * 保存或更新博客类别
	 * @param type
	 * @return
	 */
	 BlogTypes saveOrUpdateType(BlogTypes type);
	

}
  
