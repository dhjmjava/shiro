/**  
 * Project Name:blog  
 * File Name:LinkServiceImpl.java  
 * Package Name:com.blog.service.impl  
 * Date:2016年10月3日下午11:08:29  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.List;

import org.springframework.stereotype.Service;

import com.shiro.dh.entity.Link;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.LinkService;

/**  
 * ClassName:LinkServiceImpl <br/>  
 * Function: link实现. <br/>  
 * Date:     2016年10月3日 下午11:08:29 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class LinkServiceImpl extends BaseService implements LinkService {

	@Override
	public List<Link> getLinkList() {
		return linkDao.findAll();
	}

	@Override
	public void deleteLink(long id) {
		linkDao.delete(id);
	}

	@Override
	public Link saveOrUpdate(Link link) {
		return linkDao.save(link);
	}

	@Override
	public Link getLinkById(long id) {
		return linkDao.findOne(id);
	}

}
  
