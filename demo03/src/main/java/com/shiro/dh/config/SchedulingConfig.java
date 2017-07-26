/**  
 * Project Name:blog  
 * File Name:SchedulingConfig.java  
 * Package Name:com.blog.config  
 * Date:2016年9月27日下午12:58:59  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.config;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.shiro.dh.service.BlogService;

/**  
 * ClassName:SchedulingConfig <br/>  
 * Function: 定时任务. <br/>  
 * Date:     2016年9月27日 下午12:58:59 <br/>  
 * @author   Administrator  
 * @since    JDK 1.6  
 */
@Configuration
@EnableScheduling //启用定时任务
public class SchedulingConfig {
	
	
	@Autowired
	private BlogService blogServiceImpl;
	
	/**
	 * 每天23:59:59运行此方法
	 * 将缓存的阅读量数据写入数据库
	 */
	@Scheduled(cron = "59 59 23 * * ?") //每天23:59:59执行一次
	//@Scheduled(cron = "10 * * * * ?") //每10s执行一次 
	//@Scheduled(fixedRate=10000) //每隔10秒执行此任务
	public void setDataToDB(){
		/*Set<String> keySet = redisServiceImpl.getKeys();
		Blog blog = new Blog(); 
		for(String str:keySet){
			String value = redisServiceImpl.get(str);
			String key = str.substring(4, str.length());
			blog.setId(Long.valueOf(key));
			blog.setReadCount(Integer.valueOf(value));
			blogServiceImpl.saveOrUpdateBlog(blog);
		}*/
	}

}
  
