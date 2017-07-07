/**  
 * Project Name:blog  
 * File Name:MyStartupRunner.java  
 * Package Name:com.blog.config  
 * Date:2016年12月27日下午11:41:37  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.config;  

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shiro.dh.constants.Constants;
import com.shiro.dh.entity.Blog;
import com.shiro.dh.lucene.Index;
import com.shiro.dh.service.BaseService;
import com.shiro.dh.service.BlogService;

/**  
 * ClassName:MyStartupRunner <br/>  
 * Function: 系統啟動初始化索引. <br/>  
 * Date:     2016年12月27日 下午11:41:37 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Component
@Order(value=1)
public class MyStartupRunner1 extends BaseService implements CommandLineRunner{

	@Autowired
	private BlogService blogServiceImpl;
	/**
	 * 系统启动初始化索引
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		File file = new File(Constants.INDEXDIR);
		if(!file.exists()){
			file.mkdirs();
			if(file.listFiles().length<=0){
				List<Blog> blogs =blogServiceImpl.findAll();
				new Index().initIndex(blogs);
			}
		}
	}
	
	

}
  
