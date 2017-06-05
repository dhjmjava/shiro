/**  
 * Project Name:demo03  
 * File Name:BaseResponity.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月6日上午6:22:00  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.repository.CrudRepository;

/**  
 * ClassName:BaseResponity <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月6日 上午6:22:00 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface BaseRepository<T> extends CrudRepository<T, Long>{
    
}
  
