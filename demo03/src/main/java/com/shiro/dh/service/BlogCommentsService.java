/**  
 * Project Name:blog  
 * File Name:BlogCommentsService.java  
 * Package Name:com.blog.service  
 * Date:2016年11月9日上午12:50:25  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shiro.dh.entity.BlogComments;
import com.shiro.dh.util.ErrorInfo;

/**  
 * ClassName:BlogCommentsService <br/>  
 * Function: 评论管理接口. <br/>  
 * Date:     2016年11月9日 上午12:50:25 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface BlogCommentsService {
    
	/**
     * 保存评论
     * @param content
     * @param blogId
     * @param request
     * @return
     */
	 ErrorInfo saveComment(String content,long blogId,HttpServletRequest request);
	
	/**
	 * 通过博客id查询评论
	 * @param blog_id
	 * @return
	 */
	 List<BlogComments> queryCommentsByBid(long blog_id); 
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	 boolean deleteComm(long id);
}
  
