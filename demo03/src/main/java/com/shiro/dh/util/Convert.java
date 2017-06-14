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
	
	public static int strToInt(String str, int defaultValue) {
		int Result = defaultValue;
		try {
			Result = Integer.parseInt(str);
		} catch (Exception e) {
		}

		return Result;
	}

	public static long strToLong(String str, long defaultValue) {
		long Result = defaultValue;
		try {
			Result = Long.parseLong(str);
		} catch (Exception e) {
		}

		return Result;
	}

	public static float strToFloat(String str, float defaultValue) {
		float Result = defaultValue;
		try {
			Result = Float.parseFloat(str);
		} catch (Exception e) {
		}

		return Result;
	}

	public static double strToDouble(String str, double defaultValue) {
		double Result = defaultValue;
		try {
			Result = Double.parseDouble(str);
		} catch (Exception e) {
		}

		return Result;
	}

	public static boolean strToBoolean(String str, boolean defaultValue) {
		boolean Result = defaultValue;
		try {
			Result = Boolean.parseBoolean(str);
		} catch (Exception e) {
		}

		return Result;
	}

}
  
