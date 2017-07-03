/**  
 * Project Name:demo03  
 * File Name:BlogDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:40:54  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shiro.dh.dao.custom.BlogCustom;
import com.shiro.dh.entity.Blog;

/**  
 * ClassName:BlogDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:40:54 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface BlogDao extends JpaRepository<Blog,Long>,BlogCustom{

	@Query(value="select count(1) from t_blog a where a.blog_type=?1",nativeQuery=true)
   	int queryBlogByBlogType(int blogType);
   	
   	@Query(value="select count(1) from t_blog a where a.publish_time between ?1 and ?2",nativeQuery=true)
	int queryBlogByPublishTime(String startTime,String endTime);
}
  
