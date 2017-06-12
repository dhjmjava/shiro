/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:ShiroConfiguration.java  
 * Package Name:com.shiro.dh  
 * Date:2017年3月20日下午12:57:42  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.config;

import java.util.LinkedHashMap;  
import java.util.Map;  
  

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;  
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;  
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;  
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;

import com.shiro.dh.realms.ShiroRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfiguration {
	 @Bean  
     public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) { 
		 
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        // 设置SecuritManager  
        shiroFilterFactoryBean.setSecurityManager(securityManager);  
        // 拦截器  
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        filterChainDefinitionMap.put("/dologin", "anon");  //登录验证的url，可以匿名访问
        // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了  
        filterChainDefinitionMap.put("/logout", "logout");  
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 --> 
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都可以匿名访问-->  
        filterChainDefinitionMap.put("/**", "authc");  
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
        shiroFilterFactoryBean.setLoginUrl("/login");  
        // 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/index");  
        // 未授权界面;  
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        
        return shiroFilterFactoryBean;  
  
    }  
  
	 @Bean  
	 public SecurityManager securityManager(){  
		 DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();  
		 //设置realm  
		 securityManager.setRealm(myShiroRealm());  
		 return securityManager;  
	 }
   
    /**
     * myShiroRealm:用于身份认证. <br/>
     * ShiroRealm是shiro的核心。各任务都将交给其处理   
     * @author daihui 
     * @return  
     * @since JDK 1.7
     */
   @Bean  
   public ShiroRealm myShiroRealm() {  
        ShiroRealm myShiroRealm = new ShiroRealm();  
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        myShiroRealm.setCacheManager(ehCacheManager());//注入ehcache缓存
        return myShiroRealm;  
    } 
    
    
    /**  
     * 凭证匹配器  
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了  
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;  
     * ）  
     * @return  
     */  
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher(){  
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
       hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;  
       hashedCredentialsMatcher.setHashIterations(9);//散列的次数，比如散列两次，相当于 md5(md5(""));  
        
       return hashedCredentialsMatcher;  
    }  
    
    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
       System.out.println("ShiroConfiguration.getEhCacheManager()");
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
       return cacheManager;
    }
      
    
    /**  
     *  开启shiro aop注解支持.  
     *  使用代理方式，所以需要开启代码支持;  
     * @param securityManager  
     * @return  
     */  
    @Bean 
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){  
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
       return authorizationAttributeSourceAdvisor;  
    }  
    

    @Bean  
    public ShiroDialect shiroDialect(){  
    	return new ShiroDialect();  
    }
  

}
