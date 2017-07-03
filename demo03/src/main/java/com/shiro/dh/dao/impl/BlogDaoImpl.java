/**  
 * Project Name:demo03  
 * File Name:BlogDaoImpl.java  
 * Package Name:com.shiro.dh.dao.impl  
 * Date:2017年6月14日下午12:54:21  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dh.dao.custom.BlogCustom;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.util.DateUtil;
import com.shiro.dh.util.StringUtil;

/**  
 * ClassName:BlogDaoImpl <br/>  
 * Function: blog扩展方法 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月14日 下午12:54:21 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class BlogDaoImpl implements BlogCustom{
	
	@Autowired
	private EntityManager em;

	/**
	 * 
	 * queryByDate:根据日期分类查询. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午11:48:44
	 */
	@SuppressWarnings("unchecked")
	@Override

	public Map<String,String> queryByDate() {	  
		String sql= "select count(DATE_FORMAT(publish_time,'%Y-%m')),DATE_FORMAT(publish_time,'%Y-%m') from t_blog group by publish_time desc";
        List<Object[]> result = em.createNativeQuery(sql).getResultList();
        Map<String,String> resultMap = new HashMap<String,String>();
        for (Object[] obj : result) {
        	resultMap.put(obj[1].toString(),obj[0].toString());
		}
		return resultMap;
		
	}

	/**
	 * 
	 * queryByType:根据类型查询. <br/>   
	 *   
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午11:49:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> queryByType() {
		  
		String sql= "select b.`name`,COUNT(a.blog_type) from t_blog a left join t_blog_types b on a.blog_type=b.id GROUP BY a.blog_type";
        List<Object[]> result = em.createNativeQuery(sql).getResultList();
        Map<String,String> resultMap = new HashMap<String,String>();
        for (Object[] obj : result) {
        	resultMap.put(obj[1].toString(),obj[0].toString());
		}
        
		return resultMap;  
		
	}

	/**
	 * 
	 * queryPagination:前台分页查询. <br/>   
	 *   
	 * @param offset
	 * @param limit
	 * @param blogType
	 * @param time
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午11:14:57
	 */
	@Override
	public List<Blog> queryPagination(int offset, int limit, long blogType, String time) {
		String sql="select {a.*},b.name as blogTypeName from t_blog a left join t_blog_types b on a.blog_type = b.id where 1=1";
		Map<String,Object> conditions = new HashMap<String,Object>();
		if(blogType>0){
			sql+=" and a.blog_type=:blogType";
			conditions.put("blogType",String.valueOf(blogType));
		}
		
		if(!StringUtil.isBlank(time)){
			sql+="and a.publish_time between :startTime and :endTime";
			String[] dates = DateUtil.getDate(time);
			conditions.put("startTime",dates[0]);
			conditions.put("endTime",dates[1]);
		}
		sql+=" order by a.publish_time desc"; 
		Query query = em.createNativeQuery(sql);
		List<?> resultList = query.unwrap(SQLQuery.class)
							      .addEntity("a",Blog.class)
							      .addScalar("blogTypeName", StandardBasicTypes.STRING)
							      .setFirstResult((offset-1)*limit)//分页
							      .setMaxResults(limit)
							      .setProperties(conditions)
							      .list();
		List<Blog> list = new ArrayList<Blog>();
		for (Object object : resultList) {
			  Object[] obj = (Object[])object;
			  Blog blog = (Blog) obj[0];
			  blog.setBlogTypeName(obj[1].toString());
			  list.add(blog);
		}
		return list;
		
	}

}
  
