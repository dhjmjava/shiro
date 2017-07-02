/**  
 * Project Name:blog  
 * File Name:MessageBoardServiceImpl.java  
 * Package Name:com.blog.service.impl  
 * Date:2017年5月18日上午12:07:20  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.shiro.dh.entity.MessageBoard;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.MessageBoardService;

/**  
 * ClassName:MessageBoardServiceImpl <br/>  
 * Function: 留言板service. <br/>  
 * Date:     2017年5月18日 上午12:07:20 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Service
public class MessageBoardServiceImpl extends BaseService implements MessageBoardService{

	@Override
	public List<MessageBoard> queryAll() {
		
		return messageBoardDao.queryMessage();
		
	}

	@Override
	public MessageBoard findMessageById(long msgId) {
		return messageBoardDao.findOne(msgId);
	}

	@Override
	public void deleteById(long messageId) {
		 messageBoardDao.delete(messageId);
	}

	@Override
	public MessageBoard save(MessageBoard msg) {
		return messageBoardDao.save(msg);
	}

	@Override
	public MessageBoard update(MessageBoard msg) {
		return messageBoardDao.save(msg);
	}

	@Override
	public List<MessageBoard> queryMsg() {
		  
		Order o1 = new Order(Direction.ASC, "status");
		//Order o2 = new Order(Direction.ASC, "is_use");
		Order o3 = new Order(Direction.DESC, "publishTime");
		List<Order> orders = new ArrayList<>();
		orders.add(o1);
		//orders.add(o2);
		orders.add(o3);
		Sort sort = new Sort(orders);
		Iterable<MessageBoard> iterables = messageBoardDao.findAll(sort);
		List<MessageBoard> msgs = new ArrayList<>();
		for (MessageBoard messageBoard : iterables) {
			  msgs.add(messageBoard);
		}
		return msgs;
		
	}

}
  
