/**  
 * Project Name:blog  
 * File Name:LinkService.java  
 * Package Name:com.blog.service  
 * Date:2016年10月3日下午11:07:16  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import java.util.List;

import com.shiro.dh.entity.Link;

/**  
 * ClassName:LinkService <br/>  
 * Function: link管理. <br/>  
 * Date:     2016年10月3日 下午11:07:16 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface LinkService {
  
	/**
	 * 查询所有连接
	 * @return
	 */
	 List<Link> getLinkList();
	
	/**
	 * 删除link
	 * @param id
	 * @return
	 */
	 void deleteLink(long id);
	
	/**
	 * 增加link
	 * @param link
	 * @return
	 */
	 Link addLink(Link link);
	
	/**
	 * 更新link
	 * @param link
	 * @return
	 */
	 Link updateLink(Link link);
	
	/**
	 * 根据id获取link
	 * @param id
	 * @return
	 */
	 Link getLinkById(long id);
}
  
