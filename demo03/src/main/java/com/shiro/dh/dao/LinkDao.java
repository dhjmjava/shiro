/**  
 * Project Name:demo03  
 * File Name:LinkDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:47:27  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.Link;

/**  
 * ClassName:LinkDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:47:27 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Repository
public interface LinkDao extends PagingAndSortingRepository<Link, Long>{

}
  
