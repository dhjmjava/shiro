/**  
 * Project Name:blog  
 * File Name:MyWebAppConfigurer.java  
 * Package Name:com.blog.interceptor  
 * Date:2016年9月26日上午4:03:42  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.interceptor;  

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**  
 * ClassName:MyWebAppConfigurer <br/>  
 * Function: 配置拦截器. <br/>  
 * Date:     2016年9月26日 上午4:03:42 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Configuration
public class MyWebAppConfigurer  extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器 
		registry.addInterceptor(new URLInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	
	}

	
}
  
