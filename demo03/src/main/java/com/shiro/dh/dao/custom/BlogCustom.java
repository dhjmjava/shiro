/**  
 * Project Name:demo03  
 * File Name:BlogCustom.java  
 * Package Name:com.shiro.dh.dao.custom  
 * Date:2017年6月14日下午12:53:13  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.custom;

import java.util.List;
import java.util.Map;

import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.queryGraphDocBuilder;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.Blog;

/**  
 * ClassName:BlogCustom <br/>  
 * Function: 博客扩展方法接口. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月14日 下午12:53:13 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
@Repository
public interface BlogCustom {
	
	public List<Map<String,Integer>>  queryByDate();
	
	public Map<String,Integer> queryByType();

}
  
