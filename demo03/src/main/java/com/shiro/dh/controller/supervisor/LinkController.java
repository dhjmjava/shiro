/**  
 * Project Name:blog  
 * File Name:LinkController.java  
 * Package Name:com.blog.controller  
 * Date:2016年10月3日下午11:32:33  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.Link;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.ErrorInfo;

/**
 * * ClassName: LinkController <br/>  
 * Function: 友情链接控制器. <br/>  
 * date: 2017年5月29日 下午6:08:28 <br/>  
 *  
 * @author daihui  
 * @version   
 * @since JDK 1.7
 */
@RestController
@RequestMapping("supervisor")
public class LinkController extends BaseController{
	
	@RequestMapping("findAllLink")
	@RequiresPermissions("link:queryAll")
	public List<Link> findAllLinks(){
		return linkServiceImpl.getLinkList();
	}
	
	@RequestMapping("findLinkById")
	@RequiresPermissions("link:queryById")
	public Link findLinkById(String id){
		return linkServiceImpl.getLinkById(Convert.strToLong(id, -1));
	}
	
	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("link:saveOrUpdate")
	public ErrorInfo submitLink(HttpServletRequest request,Link link){
		ErrorInfo error = new ErrorInfo();
		linkServiceImpl.saveOrUpdate(link);
		 
		return error;
	}
	
	@RequestMapping("deleteLink")
	@RequiresPermissions("link:del")
	public ErrorInfo deleteLink(HttpServletRequest request,String id){
	   ErrorInfo error = new ErrorInfo();
	   linkServiceImpl.deleteLink(Convert.strToLong(id, -1));
	   
	   return error;	
	}
	
}
  
