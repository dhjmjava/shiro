/**  
 * Project Name:blog  
 * File Name:MessageBoardService.java  
 * Package Name:com.blog.service  
 * Date:2017年5月18日上午12:03:39  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;

import java.util.List;

import com.shiro.dh.entity.MessageBoard;

/**  
 * ClassName:MessageBoardService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年5月18日 上午12:03:39 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface MessageBoardService{
	
	List<MessageBoard> queryAll();
	
	MessageBoard findMessageById(long msgId);
	
	void deleteById(long msgId);
	
	MessageBoard save(MessageBoard msg);
	
	MessageBoard update(MessageBoard msg);
	
	List<MessageBoard> queryMsg();

}
  
