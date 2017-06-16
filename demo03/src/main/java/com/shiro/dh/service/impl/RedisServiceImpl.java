/**  
 * Project Name:blog  
 * File Name:RedisServiceImpl.java  
 * Package Name:com.blog.service.impl  
 * Date:2017年1月23日下午9:14:54  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.service.impl;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dh.constants.Constants;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.service.RedisService;
import com.shiro.dh.util.ObjectsTranscoder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**  
 * ClassName:RedisServiceImpl <br/>  
 * Function: REDIS接口实现. <br/>  
 * Date:     2017年1月23日 下午9:14:54 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class RedisServiceImpl implements RedisService {
    Logger logger = Logger.getLogger(RedisServiceImpl.class);
    
	@Autowired  
	private JedisPool jedisPool;  
	
	@Override
	public Jedis getResource(){
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/**
	 * 
	 * 将jedis实例放回池中（可选）.  
	 * @see com.blog.service.RedisService#returnResource(redis.clients.jedis.Jedis)
	 */
	@Override
	public void returnResource(Jedis jedis){
		jedisPool.returnResourceObject(jedis);  
	}
	
	/**
	 * 
	 * getJedis:(获取jedis实例). <br/>   
	 *  
	 * @author daihui  
	 * @return  
	 * @since JDK 1.7
	 */
	private Jedis getJedis(){
		Jedis jedis = null;
		try {
			jedis = getResource();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return jedis;
	}
    
	@Override
	public void set(String key, String value) {
        Jedis jedis = getResource();
        try {
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();  
			logger.info("加入缓存时出错！");
		}finally{
			returnResource(jedis);
		}
		

	}

	/**
	 * 
	 * 根据key获取value（可选）.  
	 * @see com.blog.service.RedisService#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		Jedis jedis = getJedis();
		String result = null;
        try {
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();  
			logger.info("获取缓存时出错！");
		}finally{
			returnResource(jedis);
		}  
        return result;
	}
	
	public static List<Long> idList = new ArrayList<Long>();
    /**
     * 
     * 博客的浏览量+1（可选）.  
     * @see com.blog.service.RedisService#incr(long)
     */
	@Override
	public void incr(long id) {
		Jedis jedis = getResource();
		byte[] result = jedis.get((Constants.BLOG+id).getBytes());
		Blog blog = (Blog)ObjectsTranscoder.deserialize(result);
		try {
			if(null != blog){
				blog.setReadCount(blog.getReadCount()+1);
				set((Constants.BLOG+id).getBytes(), ObjectsTranscoder.serialize(blog));
			    if(idList.contains(blog.getId()));
			}
		   } catch (Exception e) {
			   e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
	}

	/**
	 * 
	 * 将byte数组设置进缓存.  
	 * @see com.blog.service.RedisService#set(byte[], byte[])
	 */
	@Override
	public Jedis set(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		jedis.set(key, value);
		return jedis;
	}

	@Override
	public void mget(Set<String> keySet) {
		//Jedis jedis = getJedis();
		//jedis.mget(keys);
		//jedis.mset(keysvalues);
		//List<Byte[]>  values = jedis.mget(keys);
		
	}

	@Override
	public void mset(List<Byte[]> keys, List<Byte[]> values) {
		  
		// TODO Auto-generated method stub  
		
	}

	/**
	 * 
	 * 将blog对象设置进redis（可选）.  
	 * @see com.blog.service.RedisService#hmset(com.blog.entity.Blog, java.lang.String)
	 */
	@Override
	public void hmset(Blog blog) {
		Jedis jedis =null;
		try {
			    Map<String, String> blogMap = new HashMap<String, String>();
			    blogMap.put("id", String.valueOf(blog.getId()));
			    blogMap.put("read_count",String.valueOf(blog.getReadCount()));
				jedis = getJedis();
				jedis.hmset(Constants.BLOG+blog.getId(), blogMap);
			  } catch (Exception e) {
				e.printStackTrace();
			}finally{
				returnResource(jedis);
			}
		}

	/**
	 * 
	 * 从缓存中获取value，使read_count ++（可选）.  
	 * @see com.blog.service.RedisService#hgetAll(java.lang.String)
	 */
	@Override
	public void hgetAll(String key) {
		  
		Jedis jedis = getJedis();
		List<String> value = jedis.hmget(key,"read_count");
		if(null != value && value.size()>0){
			
		}
		
		
	}

	@Override
	public Set<String> getKeys() {
		Jedis jedis = getResource(); 
		Set<String> keySet = new HashSet<String>();
		keySet = jedis.keys(Constants.BLOG+"*");
		return keySet;
	}

}
  
