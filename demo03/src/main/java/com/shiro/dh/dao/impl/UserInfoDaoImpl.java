/**  
 * Project Name:demo03  
 * File Name:UserInfoDaoImpl.java  
 * Package Name:com.shiro.dh.dao.impl  
 * Date:2017年6月6日下午1:06:40  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shiro.dh.dao.custom.UserInfoCustom;

/**  
 * ClassName:UserInfoDaoImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月6日 下午1:06:40 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
@Repository("LinkDaoCustom") 
public class UserInfoDaoImpl implements UserInfoCustom{

	@Override
	public int deleteByIds(List<Long> ids) {
		  
		return 0;
		
	}
	

}
  
