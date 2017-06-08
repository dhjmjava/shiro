/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:UserInfoServiceImpl.java  
 * Package Name:com.shiro.dh.service.impl  
 * Date:2017年3月20日下午2:42:48  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shiro.dh.dao.UserInfoDao;
import com.shiro.dh.entity.UserInfo;
import com.shiro.dh.service.UserInfoService;

/**
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Resource  
    private UserInfoDao userInfoRepository;  
  
    @Override  
    public UserInfo findByUsername(String username) {  
        return userInfoRepository.findByUsername(username);  
    }

	@Override
	public UserInfo findByUid(long uid) {
		return userInfoRepository.findOne(uid);
	}

	@Override
	public void deleteByUid(long uid) {
		 userInfoRepository.delete(uid);;
	}

	@Override
	public UserInfo saveUser(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}
	

}
