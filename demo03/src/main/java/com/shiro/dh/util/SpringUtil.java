/**  
 * Project Name:demo03  
 * File Name:SpringUtil.java  
 * Package Name:com.shiro.dh.util  
 * Date:2017年7月3日下午4:07:48  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**  
 * ClassName:SpringUtil <br/>  
 * Function: 用于在普通 类中获取spring管理的bean. <br/>  
 * Date:     2017年7月3日 下午4:07:48 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class SpringUtil implements ApplicationContextAware{
	
	 private static ApplicationContext applicationContext = null;
	 
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	       if(SpringUtil.applicationContext == null){
	           SpringUtil.applicationContext  = applicationContext;
	       }
	    }
	   
	    //获取applicationContext
	    public static ApplicationContext getApplicationContext() {
	       return applicationContext;
	    }
	   
	    //通过name获取 Bean.
	    public static Object getBean(String name){
	       return getApplicationContext().getBean(name);
	    }
	   
	    //通过class获取Bean.
	    public static <T> T getBean(Class<T> clazz){
	       return getApplicationContext().getBean(clazz);
	    }
	   
	    //通过name,以及Clazz返回指定的Bean
	    public static <T> T getBean(String name,Class<T> clazz){
	       return getApplicationContext().getBean(name, clazz);
	    }

}
  
