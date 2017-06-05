/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:Jpatest.java  
 * Package Name:com.shiro.dh.entity  
 * Date:2017年3月20日下午4:54:27  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Administrator
 *
 */
@Entity
public class Food implements Serializable{

    /**  
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
	 * @since JDK 1.7  
	 */
	 
	private static final long serialVersionUID = -8106438614445412814L;
	@Id  
	@GeneratedValue
    private long id; 
  
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
