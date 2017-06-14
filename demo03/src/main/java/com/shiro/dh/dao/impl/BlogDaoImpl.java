/**  
 * Project Name:demo03  
 * File Name:BlogDaoImpl.java  
 * Package Name:com.shiro.dh.dao.impl  
 * Date:2017年6月14日下午12:54:21  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.dao.custom.BlogCustom;
import com.shiro.dh.entity.Blog;

/**  
 * ClassName:BlogDaoImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月14日 下午12:54:21 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class BlogDaoImpl implements BlogCustom{
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Blog> queryByDate() {
		  
		String sql= "";
		List list = em.createNativeQuery(sql).getResultList();
		return null;
		
	}

	@Override
	public List<Blog> queryByType() {
		  
		// TODO Auto-generated method stub  
		return null;
		
	}

}
  
