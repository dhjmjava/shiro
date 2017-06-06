/**  
 * Project Name:demo03  
 * File Name:BaseDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月6日下午1:03:46  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import java.util.List;

/**  
 * ClassName:BaseDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月6日 下午1:03:46 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public interface BaseDao<T> {
	/**
	 * 查找所有
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 更新或者保存数据
	 * @param bean
	 * @return
	 */
	public T save(T bean);
	
	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	public T findById(long id);
}
  
