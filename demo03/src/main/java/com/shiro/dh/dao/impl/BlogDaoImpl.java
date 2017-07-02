/**  
 * Project Name:demo03  
 * File Name:BlogDaoImpl.java  
 * Package Name:com.shiro.dh.dao.impl  
 * Date:2017年6月14日下午12:54:21  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.dao.custom.BlogCustom;
import com.shiro.dh.entity.Blog;

/**  
 * ClassName:BlogDaoImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月14日 下午12:54:21 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class BlogDaoImpl implements BlogCustom{
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Map<String,Integer>> queryByDate() {
		String sql= "select DATE_FORMAT(publish_time,'%Y-%m') as `month`,count(DATE_FORMAT(publish_time,'%Y-%m')) as `count` from t_blog group by DATE_FORMAT(publish_time,'%Y-%m') order by publish_time desc";
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class)
		     .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Integer>> resultList = query.getResultList();
		for (Map<String, Integer> map : resultList) {
			Set<Entry<String, Integer>> entrySet = map.entrySet();
			for (Entry<String, Integer> entry : entrySet) {
				System.out.println(entry.getKey()+"---"+entry.getValue());
			}
		}
		
		return resultList;
		
	}

	@Override
	public Map<String,Integer> queryByType() {
		String sql= "select count(a.blog_type),b.name  from  t_blog a left join t_blog_types b on a.blog_type = b.id where b.name is not null group by a.blog_type";
		List resultList = em.createNativeQuery(sql).getResultList();
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		for (Object object : resultList) {
			Object[] obj = (Object[])object;
			resultMap.put(obj[1].toString(), (Integer)obj[0]);
		}
		
		return resultMap;
		
	}

}
  
