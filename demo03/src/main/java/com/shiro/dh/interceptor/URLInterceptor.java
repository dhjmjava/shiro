/**  
 * Project Name:blog  
 * File Name:SecurityInterceptor.java  
 * Package Name:com.blog.interceptor  
 * Date:2016-8-12上午11:33:13  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.interceptor;  


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shiro.dh.util.HtmlEncode;

/**  
 * ClassName:SecurityInterceptor <br/>  
 * Function: 后台登陆拦截. <br/>  
 * Date:     2016-8-12 上午11:33:13 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class URLInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取所有请求参数
		String uri = request.getRequestURI();
		if(uri.equals("/supervisor/updateBloggerInfo")||uri.equals("/supervisor/submit_blog")||uri.equals("/supervisor/update_blog")){
			return true;
		}
		Map<String,String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
        	String[] value = entry.getValue();
        	if (value != null) {
	        	int strLength = value.length;
	        	for (int i = 0; i < strLength; i++) {
	        		boolean result = HtmlEncode.stripXSS(value[i]);
		        	if(result){
		        		response.sendRedirect(request.getContextPath() + "/");
		        		return false;
		        	}
	        	}
	        }
        }
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		  
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		  
		
	}
	
}
  
