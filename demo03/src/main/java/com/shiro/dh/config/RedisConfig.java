/**  
 * Project Name:blog  
 * File Name:RedisConfig.java  
 * Package Name:com.blog.config  
 * Date:2017年1月23日下午9:07:35  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.config;  


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**  
 * ClassName:RedisConfig <br/>  
 * Function: Redis相关配置. <br/>  
 * Date:     2017年1月23日 下午9:07:35 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Configuration  
@EnableAutoConfiguration  
//@ConfigurationProperties(prefix = "spring.redis", locations = "classpath:application.properties")  
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String host;  
	@Value("${spring.redis.port}")  
    private int port;  
    @Value("${spring.redis.timeout}")
    private int timeout; 
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle; 
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle; 
    @Value("${spring.redis.pool.max-active}")
    private int maxActive; 
    @Value("${spring.redis.pool.max-wait}")
    private int maxWait; 
    
    @Bean  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
      
    @Bean  
    public JedisPool getJedisPool(){  
        JedisPoolConfig config = getRedisConfig();
        config.setMaxActive(maxActive);
        config.setMaxWait(maxWait);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(true);
        
        JedisPool pool = new JedisPool(config,host,port,timeout);  
        return pool;  
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	} 
  
}
