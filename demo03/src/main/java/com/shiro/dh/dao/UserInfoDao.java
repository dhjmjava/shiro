package com.shiro.dh.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shiro.dh.dao.custom.UserInfoCustom;
import com.shiro.dh.entity.UserInfo;

/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:UserInfoRepository.java  
 * Package Name:  
 * Date:2017年3月20日下午2:40:33  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */

/**
 * @author Administrator
 *
 */
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo, String> ,UserInfoCustom,BaseDao<UserInfo>{
	
	UserInfo findByUsername(String username);
	
}
