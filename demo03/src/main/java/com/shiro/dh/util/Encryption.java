/**  
 * Project Name:demo03  
 * File Name:Encryption.java  
 * Package Name:com.shiro.dh.util  
 * Date:2017年6月15日下午8:26:01  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;

import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;

/**  
 * ClassName:Encryption <br/>  
 * Function: 加密类. <br/>  
 * Date:     2017年6月15日 下午8:26:01 <br/>  
 * @author   daihui     
 * @since    JDK 1.7
 * @see        
 */
public class Encryption {
	
	/**
	 * 
	 * encrypt:MD5加密. <br/>   
	 *   
	 * @param source
	 * @param salt
	 * @return
	 * @throws CodecException
	 * @throws UnknownAlgorithmException  
	 * @author daihui
	 * Date:2017年6月15日下午8:28:17
	 */
	public  static String encryptMD5(String source,String salt)throws CodecException, UnknownAlgorithmException{
		
		String algorithmName="MD5";//加密算法名称
		int hashIterations=9;//加密次数
		
		Object result = new  SimpleHash(algorithmName, source, salt, hashIterations);
		
		return result.toString();
	}
	
	/**
	 * 
	 * encryptSHA1:SHA1加密. <br/>   
	 *   
	 * @param source 需要加密的内容
	 * @param salt 盐值
	 * @return
	 * @throws CodecException
	 * @throws UnknownAlgorithmException  
	 * @author daihui
	 * Date:2017年6月15日下午8:29:31
	 */
    public  static String encryptSHA1(String source,String salt)throws CodecException, UnknownAlgorithmException{
		
		String algorithmName="SHA1";//加密算法名称
		int hashIterations=9;//加密次数
		
		Object result = new  SimpleHash(algorithmName, source, salt, hashIterations);
		
		return result.toString();
	}
	
}
  
