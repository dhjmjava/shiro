/**  
 * Project Name:demo03  
 * File Name:StringUtil.java  
 * Package Name:com.shiro.dh.util  
 * Date:2017年7月1日上午10:47:24  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  

import org.springframework.util.StringUtils;

/**  
 * ClassName:StringUtil <br/>  
 * Function: 字符串工具类. <br/>  
 * Date:     2017年7月1日 上午10:47:24 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class StringUtil {
	
	/**
	 * 
	 * isBlank:判断字符串是否为空. <br/>   
	 *   
	 * @param input
	 * @return  
	 * @author daihui
	 * Date:2017年7月1日上午10:51:14
	 */
	public static boolean isBlank(String input){
		return StringUtils.isEmpty(input);
	}

}
  
