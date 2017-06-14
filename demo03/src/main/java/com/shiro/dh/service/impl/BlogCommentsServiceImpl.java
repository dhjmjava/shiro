/**  
 * Project Name:blog  
 * File Name:BlogCommentsServiceImpl.java  
 * Package Name:com.blog.service.impl  
 * Date:2016年11月9日上午12:51:26  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.shiro.dh.entity.BlogComments;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogCommentsService;
import com.shiro.dh.util.ErrorInfo;
import com.shiro.dh.util.HtmlEncode;
import com.shiro.dh.util.IpUtil;

/**  
 * ClassName:BlogCommentsServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2016年11月9日 上午12:51:26 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class BlogCommentsServiceImpl extends BaseService<BlogComments> implements BlogCommentsService{
    Logger logger = Logger.getLogger(BloggerServiceImpl.class);
	
	@Override
	public ErrorInfo saveComment(String content,long blogId,HttpServletRequest request) {
		ErrorInfo error  = new ErrorInfo();
		
		if(HtmlEncode.filterWord(content)){
			error.code = -1;
			error.msg = "请文明发言！";  
			return error;
		}
		
		if(HtmlEncode.isValid(content)){
			error.code = -1;
			error.msg = "本站仅供学习之用，请勿攻击！！！";
			return error;
		}
		
		BlogComments comment = new  BlogComments();
		comment.setCommentTime(Calendar.getInstance().getTime());
		comment.setContent(content);
		comment.setIsUse(true);
		comment.setBlogId(blogId);
		comment.setIp(IpUtil.getIRealIPAddr(request));
		
		try {
			
			blogCommentsDao.save(comment);
			
		} catch (Exception e) {
			error.code = -1;
			error.msg = "评论失败";
			e.printStackTrace();
		}
		
		return error;
	}

	@Override
	public List<BlogComments> queryCommentsByBid(long blog_id) {
		  
		return blogCommentsDao.findByBlogId(blog_id);
		
	}

	
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public boolean deleteComm(long id) {
		boolean flag = true;
		try {
			
			  blogCommentsDao.delete(id);
			  
			} catch (Exception e) {
			  flag = false;
			  e.printStackTrace();     
	          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		return flag;
	}

}

  
