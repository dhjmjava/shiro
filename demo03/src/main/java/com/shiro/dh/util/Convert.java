/**  
 * Project Name:demo03  
 * File Name:Convert.java  
 * Package Name:com.shiro.dh.util  
 * Date:2017年6月14日上午9:18:54  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:Convert <br/>  
 * Function: 转换类 <br/>  
 * Date:     2017年6月14日 上午9:18:54 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class Convert<T> {
	
	public  List<T> iterableToList(Iterable<T> iterable){
		if(iterable==null){
			return null;
		}
		List<T> list = new ArrayList<>();
		for (T e : iterable) {
			list.add(e);
		}
		
		return list;
	}

}
  
