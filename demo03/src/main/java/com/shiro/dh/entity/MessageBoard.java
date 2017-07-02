/**  
 * Project Name:blog  
 * File Name:MessageBoard.java  
 * Package Name:com.blog.entity  
 * Date:2017年5月16日下午11:20:53  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//github.com/dhjmjava/shiro.git
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shiro.dh.util.DateUtil;

/**  
 * ClassName:MessageBoard <br/>  
 * Function: 留言板. <br/>  
 * Date:     2017年5月16日 下午11:20:53 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Entity
@Table(name="t_message_board")
public class MessageBoard implements Serializable{
	
	private static final long serialVersionUID = -8957261577189231998L;
	
	private long messageId;
	private String msgContent;//留言内容
	private Date publishTime;//发表时间	
	private String userName;//用户名称
	private String email;//邮箱
	private String ip;
	private boolean status;//是否审核
	private boolean isUse;//是否显示
	
	public MessageBoard(){}

	@Id
	@GeneratedValue
	@Column(name="message_id")
	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	@Column(name="msg_content")
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name="publish_time")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	@Transient
	public String getPublishTimeText(){
		return DateUtil.dateToString(this.publishTime);
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Transient
	public String getStatusText(){
		return this.status?"<font color='green'>已审核</font>":"<font color='red'>未审核</font>";
	}

	@Column(name="is_use")
	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	
	@Transient
	public String getIsUseText(){
		return this.isUse?"<font color='green'>通过</font>":"<font color='red'>不通过</font>";
	}
	
}
  
