/**  
 * Project Name:blog  
 * File Name:BlogTypesServiceImpl.java  
 * Package Name:com.blog.service.impl  
 * Date:2016年9月25日下午7:04:57  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.List;

import org.springframework.stereotype.Service;

import com.shiro.dh.entity.BlogTypes;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogTypesService;

/**  
 * ClassName:BlogTypesServiceImpl <br/>  
 * Function: 类别接口实现 <br/>  
 * Date:     2016年9月25日 下午7:04:57 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class BlogTypesServiceImpl extends BaseService implements BlogTypesService {

	@Override
	public List<BlogTypes> getAllBlogTypes() {
		
		return blogTypesDao.findAll();
		
	}

	@Override
	public BlogTypes getBlogTypesById(long id) {
		  
		return blogTypesDao.findOne(id);
		
	}

	@Override
	public BlogTypes saveOrUpdateType(BlogTypes type) {
		 
		return blogTypesDao.save(type);
		
	}

}
  
