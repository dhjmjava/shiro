/**  
 * Project Name:blog  
 * File Name:BloggerService.java  
 * Package Name:com.blog.service  
 * Date:2016-8-12上午9:42:30  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import com.shiro.dh.entity.Blogger;

/**  
 * ClassName:BloggerService <br/>  
 * Function: 博主信息接口. <br/>  
 * Date:     2016-8-12 上午9:42:30 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface BloggerService {
	/**
	 * 
	 * getBloggerInfoById:获取博主信息. <br/>   
	 *   
	 * @param id
	 * @return  
	 * @author daihui
	 * Date:2017年6月14日下午12:50:00
	 */
	 Blogger getBloggerInfoById(long id);
	
	/**
	 * 
	 * updateBloggerInfo:更新博主信息. <br/>   
	 *   
	 * @param blogger
	 * @return  
	 * @author daihui
	 * Date:2017年6月14日下午12:50:19
	 */
	 Blogger updateBloggerInfo(Blogger blogger);

}
  
