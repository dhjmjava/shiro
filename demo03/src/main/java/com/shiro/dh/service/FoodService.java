/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:JpatestService.java  
 * Package Name:com.shiro.dh.service  
 * Date:2017年3月20日下午4:58:49  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.service;

import com.shiro.dh.entity.Food;

/**
 * @author Administrator
 *
 */
public interface FoodService {
	public Food findJpatestById(long id);

}
