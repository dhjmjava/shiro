/**  
 * Project Name:blog  
 * File Name:CommentController.java  
 * Package Name:com.blog.controller.supervisor  
 * Date:2016年11月26日上午12:01:52  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.BlogComments;
import com.shiro.dh.entity.MessageBoard;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.ErrorInfo;

/**  
 * ClassName:CommentController <br/>  
 * Function: 评论管理. <br/>  
 * Date:     2016年11月26日 上午12:01:52 <br/>  
 * @author   Administrator  
 * @since    JDK 1.7  
 */
@Controller
@RequestMapping("supervisor")
public class CommentController extends BaseController {
 
	//查看指定blog所有评论
	@RequestMapping("checkComments")
	public String toCommsMgt(Long blogId, Model model , HttpServletRequest request){
		List<BlogComments> comments = blogCommentsServiceImpl.queryCommentsByBid(blogId);
		
		model.addAttribute("comments", comments);
		model.addAttribute("selected",2);
		return "supervisor/commentMgt/commentDetail";
		
	}
	
	/**
	 * 
	 * getAllMsg:查找所有留言. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年6月23日下午10:32:00
	 */
	@RequestMapping("findAllMsg")
	@RequiresPermissions("message:query")
	@ResponseBody
	public List<MessageBoard> getAllMsg(){
		return messageBoardServiceImpl.queryMsg();
	}
	
	/**
	 * 
	 * updateMsg:更新留言. <br/>   
	 *   
	 * @param id
	 * @return  
	 * @author daihui
	 * Date:2017年6月23日下午10:32:21
	 */
	@RequestMapping("updateMsg")
	@RequiresPermissions("message:update")
	@ResponseBody
	public MessageBoard  updateMsg(long id){
		MessageBoard msg = messageBoardServiceImpl.findMessageById(id);	
		return messageBoardServiceImpl.update(msg);
	}
	
	/**
	 * 
	 * deleteMsg:删除留言. <br/>   
	 *   
	 * @param id
	 * @return  
	 * @author daihui
	 * Date:2017年6月23日下午10:32:52
	 */
	@RequestMapping("deleteMsg")
	@RequiresPermissions("message:del")
	@ResponseBody
	public int deleteMsg(long id){
		
		messageBoardServiceImpl.deleteById(id);
		
		return 1;
	}
	
	//删除
	@RequestMapping("deleteComm")
	@ResponseBody
	public ErrorInfo deleteComm(HttpServletRequest request){
		ErrorInfo  error = new ErrorInfo();
		long id = Convert.strToLong(request.getParameter("id"), -1);
		
		if(!blogCommentsServiceImpl.deleteComm(id)){
			error.code = -1;
			error.msg = "删除失败！";
		}
		
		return error;
	}
	
	
	
	
}
  
