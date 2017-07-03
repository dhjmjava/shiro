/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:Application.java  
 * Package Name:com.shiro.dh  
 * Date:2017年3月20日下午12:55:39  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.shiro.dh.util.SpringUtil;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(Application.class); 
        application.run(args);
	}

	/**
	 * 
	 * configure:外部容器启动，需实现此方法. <br/>   
	 *   
	 * @param application
	 * @return  
	 * @author daihui
	 * Date:2017年7月3日下午4:16:18
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	@Bean
    public SpringUtil springUtil(){return new SpringUtil();}

}