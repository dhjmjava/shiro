/**  
 * Project Name:springboot-shiro  
 * File Name:DruidConfig.java  
 * Package Name:com.shiro.dh.config  
 * Date:2017年6月2日下午12:50:37  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**  
 * ClassName:DruidConfig <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月2日 下午12:50:37 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
@Configuration
public class DruidConfig {
	
	/**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
       //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
       ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
       //添加初始化参数：initParams
      
       //白名单：
       servletRegistrationBean.addInitParameter("allow","127.0.0.1");
       //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
       servletRegistrationBean.addInitParameter("deny","192.168.1.73");
       //登录查看信息的账号密码.
       servletRegistrationBean.addInitParameter("loginUsername","admin");
       servletRegistrationBean.addInitParameter("loginPassword","123456");
       //是否能够重置数据.
       servletRegistrationBean.addInitParameter("resetEnable","false");
       return servletRegistrationBean;
    }
   
    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
      
       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
       //添加过滤规则.
       filterRegistrationBean.addUrlPatterns("/*");
       //添加不需要忽略的格式信息.
       filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
       return filterRegistrationBean;
    }

}
  
