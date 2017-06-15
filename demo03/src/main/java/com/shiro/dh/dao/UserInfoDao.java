package com.shiro.dh.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.UserInfo;

/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:UserInfoRepository.java  
 * Package Name:  
 * Date:2017年3月20日下午2:40:33  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */

@Repository
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo,Long> {
	
	UserInfo findByUsername(String username);
	
}
