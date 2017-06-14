/**  
 * Project Name:demo03  
 * File Name:BlogDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:40:54  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shiro.dh.entity.Blog;
import com.shiro.dh.entity.UserInfo;

/**  
 * ClassName:BlogDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:40:54 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface BlogDao extends PagingAndSortingRepository<Blog,Long>{

}
  
