/**  
 * Project Name:demo03  
 * File Name:BlogComments.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:48:11  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.BlogComments;

/**  
 * ClassName:BlogComments <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:48:11 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Repository
public interface BlogCommentsDao extends PagingAndSortingRepository<BlogComments, Long>{
	 /**
	  * 
	  * findByBlogId:通过博客id查找评论. <br/>   
	  *   
	  * @param blogId
	  * @return  
	  * @author daihui
	  * Date:2017年6月14日下午12:45:12
	  */
     List<BlogComments> findByBlogId(long blogId);
}
  
