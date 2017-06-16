/**  
 * Project Name:blog  
 * File Name:RedisService.java  
 * Package Name:com.blog.service  
 * Date:2017年1月23日下午9:11:55  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service;  

import java.util.List;
import java.util.Set;

import com.shiro.dh.entity.Blog;

import redis.clients.jedis.Jedis;

/**
 * * ClassName: RedisService <br/>  
 * Function:redis操作接口. <br/>  
 * date: 2017年6月16日 下午3:10:31 <br/>  
 *  
 * @author daihui 
 * @version   
 * @since JDK 1.7
 */
public interface RedisService {
	
	//获取jedis实例
	 Jedis getResource();

	//将实例放回连接池
	 void returnResource(Jedis jedis); 
	
	//将k-v设置进redis
	 void set(String key,String value);
	
	 Jedis set(byte[] key,byte[] value);
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	 String get(String key);
	
	/**
	 * 将该键的数字值+1
	 * @param key
	 */
	 void incr(long id);
	
	 void mget(Set<String> keySet);
	
	 void mset(List<Byte[]> keys,List<Byte[]> values);
	
	 void hmset(Blog blog);
	
	 void hgetAll(String key);
	
	 Set<String> getKeys();
	

}
  
