/**  
 * Project Name:demo03  
 * File Name:BlogTypeDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:46:28  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.BlogTypes;

/**  
 * ClassName:BlogTypeDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:46:28 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Repository
public interface BlogTypesDao  extends JpaRepository<BlogTypes,Long>{

}
  
