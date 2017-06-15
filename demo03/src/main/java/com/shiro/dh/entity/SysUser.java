/**  
 * Project Name:demo03  
 * File Name:SysUser.java  
 * Package Name:com.shiro.dh.entity  
 * Date:2017年6月7日下午11:09:17  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**  
 * ClassName:SysUser <br/>  
 * Function: 系统用户 <br/>  
 * Date:     2017年6月7日 下午11:09:17 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Entity
@Table(name="sys_user")
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1041410069182170975L;

	@Id  
    @GeneratedValue  
    private long uid;// 用户id 
	
	@Column(unique = true)
    private String account;//登录账号
	
	@Column(name="password")
	private String password; // 密码;  
	
    @Column(name="username")
    private String username;// 名称（昵称或者真实姓名，不同系统不同定义）
    
    private String salt;// 加密密码的盐  
    
    private byte state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,  
                        // 1:正常状态,2：用户被锁定.  
  
   /* @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据  
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {  
            @JoinColumn(name = "roleId") }) */ 
   // private List<SysRole> roleList;// 一个用户具有多个角色 

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	/*public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}*/

	@Override
	public String toString() {
		  
		return "SysUser [uid=" + uid + ", username=" + username + ", account=" + account + ", password=" + password  
                + ", salt=" + salt + ", state=" + state + "]";
	}
	
	
	
}
  
