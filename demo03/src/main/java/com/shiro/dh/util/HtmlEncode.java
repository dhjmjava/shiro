/**  
 * Project Name:blog  
 * File Name:HtmlEncode.java  
 * Package Name:com.blog.util  
 * Date:2016年11月9日上午12:18:00  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  

import java.util.Set;
import java.util.regex.Pattern;

/**  
 * ClassName:HtmlEncode <br/>  
 * Function: 防注入，以及关键词过滤. <br/>  
 * Date:     2016年11月9日 上午12:18:00 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class HtmlEncode {
	
	public static String htmlEncode(String string) {  
        if(null == string || "".equals(string))  
            return null;  
        else{  
            String result = string;  
            result = result.replaceAll("&", "&");  
            result = result.replaceAll("<", "<");  
            result = result.replaceAll(">", ">");  
            result = result.replaceAll("\"", "'");  
            return (result.toString());  
        }  
    }  
    public static String htmlDecode(String string) {  
        if(null == string || "".equals(string))  
            return null;  
        else{  
            String result = string;  
            result = result.replaceAll("&", "&");  
            result = result.replaceAll("<", "<");  
            result = result.replaceAll(">", ">");  
            result = result.replaceAll("'", "\"");  
            return (result.toString());  
        }  
    }      
     
    //敏感词过滤
    public static boolean filterWord(String content){
    	boolean flag = false;
    	SensitivewordFilter filter = new SensitivewordFilter();
    	Set<String> set = filter.getSensitiveWord(content, 1);
    	if(set.size()>0){
    		flag = true;
    	}
    	
    	return flag;
    }
    
    private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|UNION|EXISTS)\\b)";  
  
    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
  
    //sql关键字过滤
    public static boolean isValid(String str) {  
    if (sqlPattern.matcher(str).find()) {  
           return true; 
       }  
    
         return false; 
         
     }  
    
    /**
     * 如果找到非法字符则返回true,如果没找到则返回false
    *
     * @param value
     * @return
    */
    public static boolean stripXSS(String value) {
	    boolean result = false;
	    if (value != null) {
		    value = value.replaceAll("","");
		    // Avoid anything between script tags
		    Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",Pattern.CASE_INSENSITIVE);
		    result = scriptPattern.matcher(value).find();
		    // //如果找到则为true
	    if (result)
	    return result;
	
	    // Avoid anything in a src='...' type of expression
	    scriptPattern = Pattern.compile("src[rn]*=[rn]*\'(.*?)\'",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    scriptPattern = Pattern.compile("src[rn]*=[rn]*\"(.*?)\"",
	    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Remove any lonesome </script> tag
	    scriptPattern = Pattern.compile("</script>",Pattern.CASE_INSENSITIVE);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Remove any lonesome <script ...> tag
	    scriptPattern = Pattern.compile("<script(.*?)>",
	    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Avoid eval(...) expressions
	    scriptPattern = Pattern.compile("eval\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Avoid expression(...) expressions
	    scriptPattern = Pattern.compile("expression\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    scriptPattern = Pattern.compile("vbscript:",Pattern.CASE_INSENSITIVE);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Avoid onload= expressions
	    scriptPattern = Pattern.compile("onload(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE| Pattern.DOTALL);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	
	    // Avoid alert:... expressions
	    scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
	    result = scriptPattern.matcher(value).find();
	    if (result)
	    return result;
	    }
	    
	    return result;
	 }
    
}
  
