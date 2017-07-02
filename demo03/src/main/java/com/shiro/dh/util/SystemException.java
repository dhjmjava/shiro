/**  
 * Project Name:demo03  
 * File Name:SystemException.java  
 * Package Name:com.shiro.dh.util  
 * Date:2017年7月2日上午12:06:23  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  
/**  
 * ClassName:SystemException <br/>  
 * Function: 自定义异常. <br/>  
 * Date:     2017年7月2日 上午12:06:23 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class SystemException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public SystemException(){}
	
	public SystemException(String message){
		super(message);
	}
}
  
