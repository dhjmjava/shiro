/**  
 * Project Name:springboot-shiro Maven Webapp  
 * File Name:MyRelam.java  
 * Package Name:com.shiro.dh  
 * Date:2017年3月20日下午2:33:56  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
 */
package com.shiro.dh.realms;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.shiro.dh.entity.SysPermission;
import com.shiro.dh.entity.SysRole;
import com.shiro.dh.entity.UserInfo;
import com.shiro.dh.service.UserInfoService;

/**
 * @author Administrator
 *
 */
public class ShiroRealm extends AuthorizingRealm{

	@Resource  
    private UserInfoService userInfoService;  
  
    /**  
     * 认证信息(身份验证) Authentication 是用来验证用户身份  
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
        // 获取用户的输入帐号  
    	UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token; 
        String username = usernamePasswordToken.getUsername();  
        // 通过username从数据库中查找 User对象  
        UserInfo userInfo = userInfoService.findByUsername(username);  
        if (userInfo == null) {  
            throw new UnknownAccountException("账号不存在！！"); 
        }  
  
        //密码比对
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		userInfo.getUsername(), // 用户名  
        		userInfo.getPassword(), // 密码  
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt  
                getName() // realm name  
        );  
        return authenticationInfo;  
    }  
  
    /**
     * 
     * 此方法用于授权.  
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");  
        String username = (String)principals.getPrimaryPrincipal();
        UserInfo userInfo = userInfoService.findByUsername(username);  
          
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for(SysRole role:userInfo.getRoleList()){  
               authorizationInfo.addRole(role.getRole());  
               System.out.println(role.getPermissions());  
               for(SysPermission p:role.getPermissions()){  
                   System.out.println(p);  
                  authorizationInfo.addStringPermission(p.getPermission());  
               }  
           }  
        return authorizationInfo; 
    }  
    
    public static void main(String[] args) {
		String algorithmName="MD5";//加密算法名称
		Object source="123456";//需要加密的内容
		Object salt="admin"; //加密盐值
		int hashIterations=9;//加密次数
		Object result = new  SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(result.toString());
	}


}
