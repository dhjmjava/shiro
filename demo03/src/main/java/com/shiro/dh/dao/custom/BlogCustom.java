/**  
 * Project Name:demo03  
 * File Name:BlogCustom.java  
 * Package Name:com.shiro.dh.dao.custom  
 * Date:2017年6月14日下午12:53:13  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.custom;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.Blog;

/**  
 * ClassName:BlogCustom <br/>  
 * Function: 博客扩展方法接口. <br/>  
 * Date:     2017年6月14日 下午12:53:13 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
@Repository
public interface BlogCustom {
	
	/**
	 * 
	 * queryByDate:根据日期分类查询. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年6月28日下午4:28:16
	 */
	public Map<String,String> queryByDate();
	
	/**
	 * 
	 * queryByType:根据类别查询. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年6月28日下午4:28:42
	 */
	public Map<String,String> queryByType();
	
	/**
	 * 
	 * queryPagination:根据条件分页查询. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年6月28日下午5:03:08
	 * @param time 
	 * @param blogType 
	 * @param limit 
	 * @param offset 
	 */
	public List<Blog> queryPagination(int offset, int limit, long blogType, String time);

}
  
