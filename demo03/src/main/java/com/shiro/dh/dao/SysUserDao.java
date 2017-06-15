/**  
 * Project Name:demo03  
 * File Name:SysUserDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日下午6:56:18  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiro.dh.entity.SysUser;

/**  
 * ClassName:SysUserDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月14日 下午6:56:18 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public interface SysUserDao extends JpaRepository<SysUser, Long>{

}
  
