/**  
 * Project Name:blog  
 * File Name:ObjectsTranscoder.java  
 * Package Name:com.blog.util  
 * Date:2017年1月24日下午1:57:03  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shiro.dh.entity.Blog;

/**  
 * ClassName:ObjectsTranscoder <br/>  
 * Function: 序列化与反序列化. <br/>  
 * Date:     2017年1月24日 下午1:57:03 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class ObjectsTranscoder {
	static Logger logger = Logger.getLogger(ObjectsTranscoder.class);
	
	//将list转化为byte数组
	public static byte[] serialize(List<Blog> value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(Blog Blog : value){  
                os.writeObject(Blog);  
            }  
            os.writeObject(null);  
            closeOutput(bos, os);  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
        	closeOutput(bos, os);
        }  
        return rv;  
    }  

	//将byte数组反序列化为list
    public static List<Blog> deserializeList(byte[] in) {  
        List<Blog> list = new ArrayList<Blog>();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
                    Blog Blog = (Blog) is.readObject();  
                    if(Blog == null){  
                        break;  
                    }else{  
                        list.add(Blog);  
                    }  
                }  
                closeInput(is, bis);  
            }  
        } catch (IOException e) {  
            logger.info("Caught IOException decoding %d bytes of data"+  
                    in == null ? 0 : in.length, e);  
        } catch (ClassNotFoundException e) {  
            logger.info("Caught CNFE decoding %d bytes of data"+  
                    in == null ? 0 : in.length, e);  
        } finally {  
        	closeInput(is, bis);  
        }  
        return list;  
    }  
  
    //序列化对象
    public static byte[] serialize(Object value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            os.writeObject(value);  
            closeOutput(bos, os);  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
        	closeOutput(bos, os);
        }  
        return rv;  
    }  

    public static Object deserialize(byte[] in) {  
        Object rv=null;  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                rv=is.readObject();  
                closeInput(is, bis);  
            }  
        } catch (IOException e) {  
            logger.info("Caught IOException decoding %d bytes of data"+  
                    in == null ? 0 : in.length, e);  
        } catch (ClassNotFoundException e) {  
            logger.info("Caught CNFE decoding %d bytes of data"+  
                    in == null ? 0 : in.length, e);  
        } finally {  
        	closeInput(is, bis);  
        }  
        return rv;  
    }  
  /**
   * 关闭流  
   * @param is
   * @param bis
   */
    private static void closeInput(ObjectInputStream is,ByteArrayInputStream bis){
    	if(null != is){
    		try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();  
			}
    	}
    	
    	if(null != bis){
    		try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();  
			}
    	}
    }
    
    private static void closeOutput( ByteArrayOutputStream bos,  ObjectOutputStream os){
    	if(null != os){
    		try {
    			os.close();
			} catch (IOException e) {
				e.printStackTrace();  
			}
    	}
    	
    	if(null != bos){
    		try {
    			bos.close();
			} catch (IOException e) {
				e.printStackTrace();  
			}
    	}
    }
  }  
  
