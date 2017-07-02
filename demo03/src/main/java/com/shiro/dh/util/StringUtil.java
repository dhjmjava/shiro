/**  
 * Project Name:demo03  
 * File Name:StringUtil.java  
 * Package Name:com.shiro.dh.util  
<<<<<<< HEAD
 * Date:2017年7月1日下午11:59:20  
=======
 * Date:2017年7月1日上午10:47:24  
>>>>>>> 77f930caba7ead22d10741524aa8776434deb010
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;

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
	 * isBlank:字符串是否为空. <br/>   
	 *  
	 * @author daihui  
	 * @param input
	 * @return  
	 * @since JDK 1.7 
	 * Date: 2017年7月2日 上午12:00:46 <br/>
	 */
	public static boolean isBlank(String input){
		return (input==null||input=="");
	}
	
	/**
	 * 
	 * strToLong:字符串转为Long. <br/>   
	 *  
	 * @author daihui  
	 * @param input
	 * @return
	 * @throws NumberFormatException  
	 * @since JDK 1.7 
	 * Date: 2017年7月2日 上午12:17:26 <br/>
	 */
	public static Long strToLong(String input) throws NumberFormatException{
		if(isBlank(input)){
			return null;
		}
		
		return Long.valueOf(input);
	}

}
  
